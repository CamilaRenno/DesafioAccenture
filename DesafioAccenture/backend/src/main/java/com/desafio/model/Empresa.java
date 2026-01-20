package com.desafio.model;

import jakarta.persistence.*;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String cnpj;
    private String nomeFantasia;
    private String cep;
    private String uf;
}
