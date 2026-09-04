package io.savioromario10.mscartoes.application;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import io.savioromario10.mscartoes.domain.ClienteCartao;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesPorClienteResponse {

  private String nome;
  private String bandeira;
  private BigDecimal limite;

  public static CartoesPorClienteResponse fromModel(ClienteCartao model) {
    return new CartoesPorClienteResponse(
        model.getCartao().getNome(),
        model.getCartao().getBandeira().toString(),
        model.getLimite());
  }
}