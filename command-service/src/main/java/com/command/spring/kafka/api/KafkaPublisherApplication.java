package com.command.spring.kafka.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication

public class KafkaPublisherApplication {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","application");
        SpringApplication.run(KafkaPublisherApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
