package io.savioromario10.mscartoes.application.representation;

import java.math.BigDecimal;
import lombok.Data;

import io.savioromario10.mscartoes.domain.BandeiraCartao;
import io.savioromario10.mscartoes.domain.Cartao;

@Data
public class CartaoSaveRequest {

  private String nome;
  private BandeiraCartao bandeira;
  private BigDecimal renda;
  private BigDecimal limiteBasico;

  public Cartao toModel() {
    return new Cartao(nome, bandeira, renda, limiteBasico);
  }
}
