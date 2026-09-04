package io.savioromario10.mscartoes.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.savioromario10.mscartoes.domain.Cartao;
import io.savioromario10.mscartoes.domain.ClienteCartao;
import io.savioromario10.mscartoes.application.representation.CartaoSaveRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CartoesResource {

  private final CartaoService service;
  private final ClienteCartaoService clienteCartaoService;

  @GetMapping
  public String status() {
    return "ok";
  }

  @PostMapping
  public ResponseEntity<?> cadastra(@RequestBody CartaoSaveRequest request) {
    var cartao = request.toModel();
    service.save(cartao);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping(params = "renda")
  public ResponseEntity<List<Cartao>> getCartoesRendaAte(@RequestParam("renda") Long renda) {
    List<Cartao> cartoes = service.getCartoesByRenda(renda);
    return ResponseEntity.ok(cartoes);
  }

  @GetMapping(params = "cpf")
  public ResponseEntity<List<CartoesPorClienteResponse>> getCartoesByCliente(@RequestParam("cpf") String cpf) {
    List<ClienteCartao> cartoes = clienteCartaoService.getCartoesByCpf(cpf);

    List<CartoesPorClienteResponse> responses = cartoes
        .stream()
        .map(CartoesPorClienteResponse::fromModel)
        .collect(Collectors.toList());

    return ResponseEntity.ok(responses);
  }
}