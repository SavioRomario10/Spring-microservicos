package io.savioromario10.msavaliadorcredito.application;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;

import lombok.RequiredArgsConstructor;

import io.savioromario10.msavaliadorcredito.domain.model.SituacaoCliente;
import io.savioromario10.msavaliadorcredito.domain.model.DadosAvaliacao;
import io.savioromario10.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.savioromario10.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.savioromario10.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.savioromario10.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;
import io.savioromario10.msavaliadorcredito.domain.model.ProtocoloSolicitacaoCartao;

@RestController
@RequestMapping("avaliacoes-credito")
@RequiredArgsConstructor
public class AvaliadorCreditoController {

  private final AvaliadorCreditoService avaliadorCreditoService;

  @GetMapping
  public String status() {
    return "ok";
  }

  @GetMapping(value = "situacao-cliente", params = "cpf")
  public ResponseEntity<?> consultaSituacaoCliente(@RequestParam("cpf") String cpf) {

    try {

      SituacaoCliente situacaoCliente = avaliadorCreditoService.obterSituacaoCliente(cpf);
      return ResponseEntity.ok(situacaoCliente);
    } catch (DadosClienteNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (ErroComunicacaoMicroserviceException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
  }

  @PostMapping
  public ResponseEntity realizarAvaliacao(@RequestBody DadosAvaliacao dados) {

    try {

      RetornoAvaliacaoCliente retornoAvaliacaoCliente = avaliadorCreditoService.realizarAvaliacao(dados.getCpf(),
          dados.getRenda());
      return ResponseEntity.ok(retornoAvaliacaoCliente);

    } catch (DadosClienteNotFoundException e) {
      return ResponseEntity.notFound().build();
    } catch (ErroComunicacaoMicroserviceException e) {
      return ResponseEntity.status(e.getStatus()).body(e.getMessage());
    }
  }

  @PostMapping("/solicitoes-cartao")
  public ResponseEntity solicitarEmissaoCartao(@RequestBody DadosSolicitacaoEmissaoCartao dados) {
    try {
      ProtocoloSolicitacaoCartao protocoloSolicitacaoCartao = avaliadorCreditoService.solicitarEmissaoCartao(dados);

      return ResponseEntity.ok(protocoloSolicitacaoCartao);
    } catch (ErroSolicitacaoCartaoException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
  }
}