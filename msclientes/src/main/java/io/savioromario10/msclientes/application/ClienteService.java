package io.savioromario10.msclientes.application;

import java.util.Optional;

import io.savioromario10.msclientes.domain.Cliente;
import io.savioromario10.msclientes.infra.repository.ClienteRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService {

  private final ClienteRepository repository;

  @Transactional
  public void save(Cliente cliente) {
    repository.save(cliente);
  }

  public Optional<Cliente> getByCpf(String cpf) {
    return repository.findByCpf(cpf);
  }
}
