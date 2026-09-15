package io.savioromario10.infra.mqueue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.core.Queue;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.savioromario10.msavaliadorcredito.domain.model.DadosSolicitacaoEmissaoCartao;

@Component
@RequiredArgsConstructor
public class SolicitacaoEmissaoCartaoPublisher {

  private final RabbitTemplate rabbitTemplate;
  private final Queue queueEmissaoCartoes;

  public void solicitarCartao(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {

    var json = convertIntoJson(dados);

    rabbitTemplate.convertAndSend(queueEmissaoCartoes.getName(), json);
  }

  private String convertIntoJson(DadosSolicitacaoEmissaoCartao dados) throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    var json = mapper.writeValueAsString(dados);

    return json;
  }
}