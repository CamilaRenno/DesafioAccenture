package com.desafio.controller;

import com.desafio.domain.Empresa;
import com.desafio.dto.EmpresaDTO;
import com.desafio.service.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/empresas") @RequiredArgsConstructor
@CrossOrigin
public class EmpresaController {
  private final EmpresaService service;

  @PostMapping
  public ResponseEntity<Empresa> create(@RequestBody EmpresaDTO dto) {
    return ResponseEntity.ok(service.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Empresa> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

  @PutMapping("/{id}")
  public ResponseEntity<Empresa> update(@PathVariable Long id, @RequestBody EmpresaDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

  @PostMapping("/{empresaId}/vincular/{fornecedorId}")
  public ResponseEntity<Empresa> vincular(@PathVariable Long empresaId, @PathVariable Long fornecedorId) {
    return ResponseEntity.ok(service.vincular(empresaId, fornecedorId));
  }

  @PostMapping("/{empresaId}/desvincular/{fornecedorId}")
  public ResponseEntity<Empresa> desvincular(@PathVariable Long empresaId, @PathVariable Long fornecedorId) {
    return ResponseEntity.ok(service.desvincular(empresaId, fornecedorId));
  }

  @GetMapping
  public ResponseEntity<List<Empresa>> list() { return ResponseEntity.ok(service.listAll()); }
}
