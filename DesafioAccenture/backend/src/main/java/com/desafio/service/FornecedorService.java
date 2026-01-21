package com.desafio.service;

import com.desafio.cep.CepClient;
import com.desafio.common.BusinessException;
import com.desafio.common.NotFoundException;
import com.desafio.domain.DocumentoTipo;
import com.desafio.domain.Fornecedor;
import com.desafio.dto.FornecedorDTO;
import com.desafio.repository.FornecedorRepository;
import com.desafio.validation.CpfCnpjValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @RequiredArgsConstructor
public class FornecedorService {
  private final FornecedorRepository repo;
  private final CepClient cepClient;

  @Transactional
  public Fornecedor create(FornecedorDTO dto) {
    validateDocumento(dto.getDocumento());
    repo.findByDocumento(dto.getDocumento()).ifPresent(f -> { throw new BusinessException("CPF/CNPJ já cadastrado"); });
    validateCep(dto.getCep());
    Fornecedor f = Fornecedor.builder()
      .documento(dto.getDocumento())
      .tipo(DocumentoTipo.valueOf(dto.getTipo()))
      .nome(dto.getNome())
      .email(dto.getEmail())
      .cep(dto.getCep())
      .rg(dto.getRg())
      .dataNascimento(dto.getDataNascimento())
      .build();
    return repo.save(f);
  }

  public Fornecedor get(Long id) {
    return repo.findById(id).orElseThrow(() -> new NotFoundException("Fornecedor não encontrado"));
  }

  @Transactional
  public Fornecedor update(Long id, FornecedorDTO dto) {
    Fornecedor f = get(id);
    if (!f.getDocumento().equals(dto.getDocumento())) {
      validateDocumento(dto.getDocumento());
      repo.findByDocumento(dto.getDocumento()).ifPresent(x -> { throw new BusinessException("CPF/CNPJ já cadastrado"); });
    }
    validateCep(dto.getCep());
    f.setDocumento(dto.getDocumento());
    f.setTipo(DocumentoTipo.valueOf(dto.getTipo()));
    f.setNome(dto.getNome());
    f.setEmail(dto.getEmail());
    f.setCep(dto.getCep());
    f.setRg(dto.getRg());
    f.setDataNascimento(dto.getDataNascimento());
    return repo.save(f);
  }

  @Transactional
  public void delete(Long id) { repo.delete(get(id)); }

  public List<Fornecedor> search(String nome, String doc) {
    return repo.search(nome, doc);
  }

  private void validateDocumento(String doc) {
    if (!(CpfCnpjValidator.isCpf(doc) || CpfCnpjValidator.isCnpj(doc))) {
      throw new BusinessException("Documento inválido (CPF/CNPJ)");
    }
  }

  private void validateCep(String cep) {
    var resp = cepClient.getCep(cep);
    if (resp == null || resp.getUf() == null) throw new BusinessException("CEP inválido");
  }
}
