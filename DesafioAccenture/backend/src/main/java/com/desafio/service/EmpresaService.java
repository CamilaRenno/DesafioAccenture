package com.desafio.service;

import com.desafio.cep.CepClient;
import com.desafio.common.BusinessException;
import com.desafio.common.NotFoundException;
import com.desafio.domain.Empresa;
import com.desafio.domain.Fornecedor;
import com.desafio.dto.EmpresaDTO;
import com.desafio.repository.EmpresaRepository;
import com.desafio.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

@Service @RequiredArgsConstructor
public class EmpresaService {
  private final EmpresaRepository empresaRepo;
  private final FornecedorRepository fornecedorRepo;
  private final CepClient cepClient;

  @Transactional
  public Empresa create(EmpresaDTO dto) {
    empresaRepo.findByCnpj(dto.getCnpj()).ifPresent(e -> { throw new BusinessException("CNPJ já cadastrado"); });
    validateCep(dto.getCep());
    Empresa e = Empresa.builder()
      .cnpj(dto.getCnpj())
      .nomeFantasia(dto.getNomeFantasia())
      .cep(dto.getCep())
      .fornecedores(new HashSet<>())
      .build();
    return empresaRepo.save(e);
  }

  public Empresa get(Long id) {
    return empresaRepo.findById(id).orElseThrow(() -> new NotFoundException("Empresa não encontrada"));
  }

  @Transactional
  public Empresa update(Long id, EmpresaDTO dto) {
    Empresa e = get(id);
    if (!e.getCnpj().equals(dto.getCnpj())) {
      empresaRepo.findByCnpj(dto.getCnpj()).ifPresent(x -> { throw new BusinessException("CNPJ já cadastrado"); });
    }
    validateCep(dto.getCep());
    e.setCnpj(dto.getCnpj());
    e.setNomeFantasia(dto.getNomeFantasia());
    e.setCep(dto.getCep());
    return empresaRepo.save(e);
  }

  @Transactional
  public void delete(Long id) { empresaRepo.delete(get(id)); }

  @Transactional
  public Empresa vincular(Long empresaId, Long fornecedorId) {
    Empresa e = get(empresaId);
    Fornecedor f = fornecedorRepo.findById(fornecedorId).orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
    // Regra PR: se empresa é do PR, fornecedor PF deve ser maior de idade
    var cep = cepClient.getCep(e.getCep());
    if ("PR".equalsIgnoreCase(cep.getUf()) && f.getTipo().name().equals("CPF")) {
      if (f.getDataNascimento() == null || java.time.Period.between(f.getDataNascimento(), java.time.LocalDate.now()).getYears() < 18) {
        throw new BusinessException("Fornecedor pessoa física menor de idade não permitido para empresa do Paraná");
      }
    }
    e.getFornecedores().add(f);
    return empresaRepo.save(e);
  }

  @Transactional
  public Empresa desvincular(Long empresaId, Long fornecedorId) {
    Empresa e = get(empresaId);
    e.getFornecedores().removeIf(f -> f.getId().equals(fornecedorId));
    return empresaRepo.save(e);
  }

  private void validateCep(String cep) {
    var resp = cepClient.getCep(cep);
    if (resp == null || resp.getUf() == null) throw new BusinessException("CEP inválido");
  }

  public java.util.List<Empresa> listAll() { return empresaRepo.findAll(); 
  }

}