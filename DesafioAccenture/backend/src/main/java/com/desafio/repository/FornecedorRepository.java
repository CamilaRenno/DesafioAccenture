
package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {
    boolean existsByCpfCnpj(String cpfCnpj);
}
