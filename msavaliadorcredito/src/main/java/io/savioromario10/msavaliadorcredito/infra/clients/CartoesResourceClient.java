package io.savioromario10.msavaliadorcredito.infra.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.savioromario10.msavaliadorcredito.domain.model.Cartao;
import io.savioromario10.msavaliadorcredito.domain.model.CartaoCliente;

@FeignClient(name = "mscartoes", path = "/cartoes")
public interface CartoesResourceClient {

  @GetMapping(value = "cpf")
  ResponseEntity<List<CartaoCliente>> getCartoesByCliente(@RequestParam("cpf") String cpf);

  @GetMapping(value = "renda")
  ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda);
}