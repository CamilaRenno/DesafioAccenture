package com.desafio.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="fornecedores", uniqueConstraints = @UniqueConstraint(columnNames = "documento"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Fornecedor {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, length=18, unique=true)
  private String documento; // CPF ou CNPJ

  @Enumerated(EnumType.STRING)
  @Column(nullable=false)
  private DocumentoTipo tipo;

  @Column(nullable=false)
  private String nome;

  @Column(nullable=false)
  private String email;

  @Column(nullable=false, length=9)
  private String cep;

  // Pessoa física
  private String rg;
  private LocalDate dataNascimento;

  @ManyToMany(mappedBy = "fornecedores")
  private Set<Empresa> empresas = new HashSet<>();
}
