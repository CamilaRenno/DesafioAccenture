package com.desafio.service;

import com.desafio.cep.CepClient;
import com.desafio.cep.CepResponse;
import com.desafio.dto.FornecedorDTO;
import com.desafio.repository.FornecedorRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FornecedorServiceTest {

  @Test
  void shouldCreateFornecedorWithValidCpf() {
    var repo = Mockito.mock(FornecedorRepository.class);
    var cepClient = Mockito.mock(CepClient.class);
    var cep = new CepResponse(); cep.setUf("SP");
    Mockito.when(cepClient.getCep(Mockito.anyString())).thenReturn(cep);

    var service = new FornecedorService(repo, cepClient);
    var dto = new FornecedorDTO();
    dto.setDocumento("11144477735"); // CPF válido
    dto.setTipo("CPF");
    dto.setNome("Teste");
    dto.setEmail("t@t.com");
    dto.setCep("01001000");

    assertDoesNotThrow(() -> service.create(dto));
  }
}
