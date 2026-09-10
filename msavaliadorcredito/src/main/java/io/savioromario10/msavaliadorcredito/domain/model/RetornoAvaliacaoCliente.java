package io.savioromario10.msavaliadorcredito.domain.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
public class RetornoAvaliacaoCliente {

  private List<CartaoAprovado> cartoes;
}
