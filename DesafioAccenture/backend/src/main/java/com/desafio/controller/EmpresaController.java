
package com.desafio.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.desafio.model.Empresa;
import com.desafio.service.EmpresaService;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    private final EmpresaService service;

    public EmpresaController(EmpresaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Empresa> listar() {
        return service.listar();
    }

    @PostMapping
    public Empresa salvar(@RequestBody Empresa e) {
        return service.salvar(e);
    }
}
