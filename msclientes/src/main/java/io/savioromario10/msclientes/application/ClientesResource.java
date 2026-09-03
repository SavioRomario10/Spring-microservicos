package io.savioromario10.msclientes.application;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("clientes")
public class ClientesResource {

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping
  public void save() {

  }
}