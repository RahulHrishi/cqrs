package com.query.spring.kafka.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QueryActuator {
    @GetMapping("/actuator")
    public String health() {
        return "UP";
    }
}
