package ec.gob.mdi.scsf.apigateway.setups;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFiltering extends AbstractGatewayFilterFactory<AuthenticationFiltering.Config> {

    private final WebClient.Builder webClientBuilder;

    public AuthenticationFiltering(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    public static class Config {
        // Put your configuration properties here if needed
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "Missing Authorization header", HttpStatus.UNAUTHORIZED);
            }

            String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String[] parts = authHeader.split(" ");
            if (parts.length != 2 || !"Bearer".equals(parts[0])) {
                return onError(exchange, "Bad Authorization structure", HttpStatus.UNAUTHORIZED);
            }

            String token = parts[1];

            return webClientBuilder.build()
                    .get()
                    .uri("http://infraestructuredomain-keycloack/roles")
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .bodyToMono(JsonNode.class)
                    .flatMap(response -> {
                        if (response == null || !response.has("scsf_admin") || response.get("scsf_admin").asText().trim().isEmpty()) {
                            return onError(exchange, "Role scsf_admin missing", HttpStatus.UNAUTHORIZED);
                        }
                        return chain.filter(exchange);
                    })
                    .onErrorResume(error -> onError(exchange, "Communication Error", HttpStatus.SERVICE_UNAVAILABLE));
        };
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus httpStatus) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        DataBuffer buffer = response.bufferFactory().wrap(err.getBytes());
        return response.writeWith(Mono.just(buffer));
    }
}

