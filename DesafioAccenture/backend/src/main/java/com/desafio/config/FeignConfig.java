package com.desafio.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
  @Bean
  public RequestInterceptor cepHeaders() {
    return template -> template.header("Accept", "application/json");
  }
}
