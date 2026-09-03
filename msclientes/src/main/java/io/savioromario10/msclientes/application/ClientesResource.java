package io.savioromario10.msclientes.application;

import java.net.URI;
import lombok.RequiredArgsConstructor;

import io.savioromario10.msclientes.domain.Cliente;
import io.savioromario10.msclientes.application.representation.ClienteSaveRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
public class ClientesResource {

  private final ClienteService service;

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping
  public ResponseEntity<?> save(@RequestBody ClienteSaveRequest request) {

    Cliente cliente = request.toModel();
    service.save(cliente);

    URI headerLocation = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .query("cpf={cpf}")
        .buildAndExpand(cliente.getCpf())
        .toUri();

    return ResponseEntity.created(headerLocation).build();
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<?> dadosCliente(@RequestParam("cpf") String cpf) {

    var cliente = service.getByCpf(cpf);

    if (cliente.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(cliente.get());
  }
}