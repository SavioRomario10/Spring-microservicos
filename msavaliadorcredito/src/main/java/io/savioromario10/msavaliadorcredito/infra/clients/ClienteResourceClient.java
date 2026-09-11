package io.savioromario10.msavaliadorcredito.infra.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.savioromario10.msavaliadorcredito.domain.model.DadosCliente;

@FeignClient(name = "msclientes", path = "/clientes")
public interface ClienteResourceClient {

  @GetMapping(value = "cpf")
  ResponseEntity<DadosCliente> dadosCliente(@RequestParam("cpf") String cpf);

}