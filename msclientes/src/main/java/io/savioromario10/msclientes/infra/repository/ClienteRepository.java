package io.savioromario10.msclientes.infra.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import io.savioromario10.msclientes.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

  Optional<Cliente> findByCpf(String cpf);
}