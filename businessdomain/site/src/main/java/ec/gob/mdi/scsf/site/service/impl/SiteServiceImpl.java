/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.gob.mdi.scsf.site.service.impl;

import ec.gob.mdi.scsf.site.dto.CoordinationDTO;
import ec.gob.mdi.scsf.site.dto.ResponseDTO;
import ec.gob.mdi.scsf.site.entities.SiteInspection;
import ec.gob.mdi.scsf.site.repository.SiteInspectionRepository;
import ec.gob.mdi.scsf.site.service.ISiteService;
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

/**
 *
 * @author Jose Alvear
 */
@Service
public class SiteServiceImpl implements ISiteService {

    @Autowired
    private SiteInspectionRepository siteRepository;

    /*@Autowired
    private RestTemplate restTemplate;*/
    private final WebClient.Builder webClientBuilder;

    public SiteServiceImpl(WebClient.Builder webClientBuilder) {
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

    public ResponseDTO getSiteById(long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            SiteInspection site = siteRepository.findById(id).get();
            responseDTO.setSite(site);
            responseDTO.setCoordination(getCoordination(site.getCoordinationID()));
        } catch (UnknownHostException ex) {
            Logger.getLogger(SiteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return responseDTO;
    }

    /***
     * 
     * @param id
     * @return
     * @throws UnknownHostException 
     */
    private CoordinationDTO getCoordination(long id) throws UnknownHostException {
        CoordinationDTO coordination = new CoordinationDTO();
        try {
            WebClient client = webClientBuilder.clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                    .baseUrl("http://localhost:8096/v1/coordination")
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8096/v1/coordination"))
                    .filter(basicAuthentication("admin", "qwerty"))
                    .build();
            Mono<CoordinationDTO> objeto = client.get()
                    .uri("/" + id).accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .bodyToMono(CoordinationDTO.class);
            coordination = objeto.block();
        } catch (WebClientResponseException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                return null;
            } else {
                throw new UnknownHostException(e.getMessage());
            }
        }
        return coordination;
    }
}
