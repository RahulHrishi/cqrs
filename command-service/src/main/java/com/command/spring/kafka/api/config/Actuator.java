package com.command.spring.kafka.api.config;

import com.command.spring.kafka.api.Excption.CommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@PropertySource("classpath:application.properties")
public class Actuator {
    @Value("${queryService.health.url}")
    private String actuatorUrl;

    @Value("${queryService.down}")
    private String excMsg;
    @Autowired
    private RestTemplate restTemplate;

    public void checkHealth() throws CommandException {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
            HttpEntity<String> entity = new HttpEntity<>(headers);
            restTemplate.exchange(actuatorUrl, HttpMethod.GET, entity, String.class).getBody();
        } catch (Exception e) {
            throw new CommandException(excMsg);
        }
    }
}
