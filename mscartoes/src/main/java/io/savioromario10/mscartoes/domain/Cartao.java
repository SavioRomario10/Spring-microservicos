package io.savioromario10.mscartoes.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "cartao")
@Data
@NoArgsConstructor
public class Cartao {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String nome;
  @Enumerated(EnumType.STRING)
  private BandeiraCartao bandeira;
  @Column
  private BigDecimal renda;
  @Column
  private BigDecimal limiteBasico;

  public Cartao(String nome, BandeiraCartao bandeira, BigDecimal renda, BigDecimal limiteBasico) {
    this.nome = nome;
    this.bandeira = bandeira;
    this.renda = renda;
    this.limiteBasico = limiteBasico;
  }
}