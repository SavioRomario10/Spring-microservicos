package io.savioromario10.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("avaliacoes-credito")
public class AvaliadorCreditoController {

  @GetMapping
  public String status(){
    return "ok";
  }
}