package com.desafio.cep;

import lombok.Data;

@Data
public class CepResponse {
  private String cep;
  private String uf;
  private String cidade;
  private String bairro;
  private String logradouro;
}
