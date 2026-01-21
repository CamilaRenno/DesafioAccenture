package com.desafio.cep;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "cepClient", url = "${cep.base-url}", configuration = com.desafio.config.FeignConfig.class)
public interface CepClient {
  @GetMapping("/api/{cep}")
  CepResponse getCep(@PathVariable("cep") String cep);
}
