package io.savioromario10.mscartoes.application;

import java.util.List;

import org.springframework.stereotype.Service;

import io.savioromario10.mscartoes.domain.ClienteCartao;
import io.savioromario10.mscartoes.infra.repository.ClienteCartaoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteCartaoService {

  private final ClienteCartaoRepository repository;

  public List<ClienteCartao> getCartoesByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }

}