package io.savioromario10.mscartoes.infra.mqueue;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.savioromario10.mscartoes.infra.repository.CartaoRepository;
import io.savioromario10.mscartoes.infra.repository.ClienteCartaoRepository;
import io.savioromario10.mscartoes.domain.Cartao;
import io.savioromario10.mscartoes.domain.ClienteCartao;
import io.savioromario10.mscartoes.domain.DadosSolicitacaoEmissaoCartao;

@Component
@RequiredArgsConstructor
public class EmissaoCartaoSubscriber {

  private final CartaoRepository repository;
  private final ClienteCartaoRepository clienteCartaoRepository;

  @RabbitListener(queues = "${mq.queues.emissao-cartoes}")
  public void receberSolicitacaoEmissaoCartao(@Payload String payload) {
    try {

      var mapper = new ObjectMapper();
      var dados = mapper.readValue(payload, DadosSolicitacaoEmissaoCartao.class);

      Cartao cartao = repository.findById(dados.getIdCartao()).orElseThrow();

      ClienteCartao clienteCartao = new ClienteCartao();

      clienteCartao.setCartao(cartao);
      clienteCartao.setCpf(dados.getCpf());
      clienteCartao.setLimite(dados.getLimiteAprovado());

      clienteCartaoRepository.save(clienteCartao);

    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}