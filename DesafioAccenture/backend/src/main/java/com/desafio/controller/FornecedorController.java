
package com.desafio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.desafio.model.Fornecedor;
import com.desafio.service.FornecedorService;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {

    private final FornecedorService service;

    public FornecedorController(FornecedorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Fornecedor> listar() {
        return service.listar();
    }

    @PostMapping
    public Fornecedor salvar(@RequestBody Fornecedor f) {
        return service.salvar(f, new com.desafio.model.Empresa());
    }
}
