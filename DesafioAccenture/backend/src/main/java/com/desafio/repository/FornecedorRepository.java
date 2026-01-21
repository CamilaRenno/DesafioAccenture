package com.desafio.repository;

import com.desafio.domain.Fornecedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
  Optional<Fornecedor> findByDocumento(String documento);

  @Query("select f from Fornecedor f where (:nome is null or lower(f.nome) like lower(concat('%', :nome, '%'))) " +
         "and (:doc is null or f.documento like concat('%', :doc, '%'))")
  List<Fornecedor> search(String nome, String doc);
}
