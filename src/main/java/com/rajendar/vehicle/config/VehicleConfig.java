package com.rajendar.vehicle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VehicleConfig {

  @Bean
  RestTemplate restTemplate(){
    return new RestTemplate();
  }
}
