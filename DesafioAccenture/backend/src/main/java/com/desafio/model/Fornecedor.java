package com.desafio.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Fornecedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    @Column(unique = true)
    private String cpfCnpj;
    private String nome;
    private String email;
    private String cep;
    private String rg;
    private LocalDate dataNascimento;
}
