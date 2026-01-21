package com.desafio.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class FornecedorDTO {
  private Long id;
  private String documento;
  private String tipo;
  private String nome;
  private String email;
  private String cep;
  private String rg;
  private LocalDate dataNascimento;
  private Set<Long> empresasIds;
}
