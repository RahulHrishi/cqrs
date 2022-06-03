package com.command.spring.kafka.api.config;

import com.command.spring.kafka.api.Excption.AuctionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class Actuator {
    @Autowired
    private static RestTemplate restTemplate;

    public static void checkHealth() throws AuctionException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange("http://localhost:9090/actuator", HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            throw new AuctionException();
        }
    }
}
