package ec.gob.mdi.scsf.sites.services.impl;


import ec.gob.mdi.scsf.sites.dto.LocationDTO;
import ec.gob.mdi.scsf.sites.dto.QualificationsRenewalsDTO;
import ec.gob.mdi.scsf.sites.dto.ResponseDTO;
import ec.gob.mdi.scsf.sites.entities.Sites;
import ec.gob.mdi.scsf.sites.repository.SitesRepository;
import ec.gob.mdi.scsf.sites.services.ISitesService;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import org.springframework.http.HttpStatus;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;
import reactor.core.publisher.Mono;

@Service
public class SitesServiceImpl implements ISitesService {

    @Autowired
    private SitesRepository sitesRepository;
    private final WebClient.Builder webClientBuilder;

    public SitesServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }
    //define timeout
    TcpClient tcpClient = TcpClient
            .create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .doOnConnected(connection -> {
                connection.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS));
                connection.addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS));
            });

    public ResponseDTO getSitesById(long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            Sites sites = sitesRepository.findById(id).get();
            responseDTO.setSites(sites);
            responseDTO.setLocation(getLocation(sites.getParroquiaID()));           
            System.out.println("Este es un mensaje para la consola"+getLocation(sites.getParroquiaID()));
            responseDTO.setQualificationsRenewalsDTO(getQualificationsRenewals(sites.getQualificationRenewalsID()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(SitesServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseDTO;
    }

    private LocationDTO getLocation(long id) throws UnknownHostException {
        LocationDTO location = new LocationDTO();
        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://localhost:8082/parroquia")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8082/parroquia"))
                    .filter(basicAuthentication("admin", "qwerty"))
                    .build();
            Mono<LocationDTO> objeto = client.get()
                    .uri("/location/" + id).accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(LocationDTO.class);
            location = objeto.block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return location;
    }

    private QualificationsRenewalsDTO getQualificationsRenewals(long id) throws UnknownHostException {
        QualificationsRenewalsDTO qualificationsRenewals = new QualificationsRenewalsDTO();
        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://localhost:8088/qualificationsrenewals")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8088/qualificationsrenewals"))
                    .filter(basicAuthentication("admin", "qwerty"))
                    .build();
            Mono<QualificationsRenewalsDTO> objeto = client.get()
                    .uri("/" + id).accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(QualificationsRenewalsDTO.class);
            qualificationsRenewals = objeto.block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return qualificationsRenewals;
    }


}
