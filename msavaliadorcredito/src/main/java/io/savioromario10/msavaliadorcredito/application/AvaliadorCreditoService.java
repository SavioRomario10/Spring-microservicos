package io.savioromario10.msavaliadorcredito.application;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import io.savioromario10.msavaliadorcredito.infra.clients.ClienteResourceClient;
import io.savioromario10.msavaliadorcredito.domain.model.SituacaoCliente;
import io.savioromario10.msavaliadorcredito.domain.model.DadosCliente;
import io.savioromario10.msavaliadorcredito.domain.model.RetornoAvaliacaoCliente;
import io.savioromario10.msavaliadorcredito.infra.clients.CartoesResourceClient;
import io.savioromario10.msavaliadorcredito.domain.model.CartaoCliente;
import io.savioromario10.msavaliadorcredito.domain.model.CartaoAprovado;
import io.savioromario10.msavaliadorcredito.application.ex.DadosClienteNotFoundException;
import io.savioromario10.msavaliadorcredito.application.ex.ErroComunicacaoMicroserviceException;
import io.savioromario10.msavaliadorcredito.domain.model.Cartao;

import feign.FeignException;

@Service
@RequiredArgsConstructor
public class AvaliadorCreditoService {

  private final ClienteResourceClient clienteClient;
  private final CartoesResourceClient cartoesClient;

  public SituacaoCliente obterSituacaoCliente(String cpf)
      throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {

    try {

      ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);

      ResponseEntity<List<CartaoCliente>> cartoesResponse = cartoesClient.getCartoesByCliente(cpf);

      return SituacaoCliente
          .builder()
          .cliente(dadosClienteResponse.getBody())
          .cartoes(cartoesResponse.getBody())
          .build();
    } catch (FeignException.FeignClientException e) {

      int status = e.status();
      if (HttpStatus.NOT_FOUND.value() == status) {
        throw new DadosClienteNotFoundException();
      }
      throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
    }
  }

  public RetornoAvaliacaoCliente realizarAvaliacao(String cpf, long renda)
      throws DadosClienteNotFoundException, ErroComunicacaoMicroserviceException {
    try {

      ResponseEntity<DadosCliente> dadosClienteResponse = clienteClient.dadosCliente(cpf);

      ResponseEntity<List<Cartao>> cartoesResponse = cartoesClient.getCartoesRendaAte(renda);

      List<Cartao> cartoes = cartoesResponse.getBody();
      var listaCartoesAprovados = cartoes.stream().map(
          cartao -> {

            DadosCliente dadosCliente = dadosClienteResponse.getBody();

            BigDecimal limiteBasico = cartao.getLimiteBasico();
            BigDecimal idadeBD = BigDecimal.valueOf(dadosCliente.getIdade());

            BigDecimal fator = idadeBD.divide(BigDecimal.valueOf(10));
            BigDecimal limiteAprovado = fator.multiply(limiteBasico);

            CartaoAprovado aprovado = new CartaoAprovado();
            aprovado.setCartao(cartao.getNome());
            aprovado.setBandeira(cartao.getBandeira());
            aprovado.setLimiteAprovado(limiteAprovado);

            return aprovado;
          }).collect(Collectors.toList());

      return new RetornoAvaliacaoCliente(listaCartoesAprovados);

    } catch (FeignException.FeignClientException e) {

      int status = e.status();
      if (HttpStatus.NOT_FOUND.value() == status) {
        throw new DadosClienteNotFoundException();
      }
      throw new ErroComunicacaoMicroserviceException(e.getMessage(), status);
    }
  }
}