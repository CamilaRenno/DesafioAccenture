package com.desafio.dto;

import lombok.Data;

import java.util.Set;

@Data
public class EmpresaDTO {
  private Long id;
  private String cnpj;
  private String nomeFantasia;
  private String cep;
  private Set<Long> fornecedoresIds;
}
