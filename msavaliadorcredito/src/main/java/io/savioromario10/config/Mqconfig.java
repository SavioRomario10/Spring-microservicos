package io.savioromario10.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Mqconfig {

  @Value("${mq.queue.emissao.cartoes}")
  private String queueEmissaoCartoes;

  @Bean
  public Queue queueEmissaoCartoes() {
    return new Queue(queueEmissaoCartoes, true);
  }
}
