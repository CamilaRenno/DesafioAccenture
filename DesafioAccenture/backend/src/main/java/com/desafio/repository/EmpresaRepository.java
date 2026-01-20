
package com.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.desafio.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {}
