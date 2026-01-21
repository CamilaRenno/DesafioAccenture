package com.desafio.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity @Table(name="empresas", uniqueConstraints = @UniqueConstraint(columnNames = "cnpj"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Empresa {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable=false, length=18, unique=true)
  private String cnpj;

  @Column(nullable=false)
  private String nomeFantasia;

  @Column(nullable=false, length=9)
  private String cep;

  @ManyToMany
  @JoinTable(name="empresa_fornecedor",
    joinColumns=@JoinColumn(name="empresa_id"),
    inverseJoinColumns=@JoinColumn(name="fornecedor_id"))
  private Set<Fornecedor> fornecedores = new HashSet<>();
}
