package com.desafio.controller;

import com.desafio.domain.Fornecedor;
import com.desafio.dto.FornecedorDTO;
import com.desafio.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/api/fornecedores") @RequiredArgsConstructor
@CrossOrigin
public class FornecedorController {
  private final FornecedorService service;

  @PostMapping
  public ResponseEntity<Fornecedor> create(@RequestBody FornecedorDTO dto) {
    return ResponseEntity.ok(service.create(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Fornecedor> get(@PathVariable Long id) { return ResponseEntity.ok(service.get(id)); }

  @PutMapping("/{id}")
  public ResponseEntity<Fornecedor> update(@PathVariable Long id, @RequestBody FornecedorDTO dto) {
    return ResponseEntity.ok(service.update(id, dto));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) { service.delete(id); return ResponseEntity.noContent().build(); }

  @GetMapping
  public ResponseEntity<List<Fornecedor>> search(
      @RequestParam(required = false) String nome,
      @RequestParam(required = false) String documento) {
    return ResponseEntity.ok(service.search(nome, documento));
  }
}
