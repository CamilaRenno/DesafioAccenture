
package com.desafio.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.desafio.repository.EmpresaRepository;
import com.desafio.model.Empresa;

@Service
public class EmpresaService {

    private final EmpresaRepository repo;

    public EmpresaService(EmpresaRepository repo) {
        this.repo = repo;
    }

    public List<Empresa> listar() {
        return repo.findAll();
    }

    public Empresa salvar(Empresa e) {
        return repo.save(e);
    }
}
