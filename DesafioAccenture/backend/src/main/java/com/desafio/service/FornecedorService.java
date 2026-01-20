
package com.desafio.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import com.desafio.model.*;
import com.desafio.repository.FornecedorRepository;

@Service
public class FornecedorService {

    private final FornecedorRepository repo;

    public FornecedorService(FornecedorRepository repo) {
        this.repo = repo;
    }

    public List<Fornecedor> listar() {
        return repo.findAll();
    }

    public Fornecedor salvar(Fornecedor f, Empresa e) {
        if ("PF".equals(f.getTipo())
            && "PR".equalsIgnoreCase(e.getUf())
            && f.getDataNascimento() != null
            && f.getDataNascimento().isAfter(LocalDate.now().minusYears(18))) {
            throw new RuntimeException("Fornecedor menor de idade não permitido no PR");
        }
        return repo.save(f);
    }
}
