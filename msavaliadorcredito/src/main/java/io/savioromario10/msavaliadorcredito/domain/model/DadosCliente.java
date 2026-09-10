package io.savioromario10.msavaliadorcredito.domain.model;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DadosCliente {

  private Long id;
  private String nome;
  private Integeer idade;

}