package io.savioromario10.msclientes.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "clientes")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column
  private String cpf;
  @Column
  private String nome;
  @Column
  private Integer idade;

  public Cliente(String cpf, String nome, Integer idade) {
    this.cpf = cpf;
    this.nome = nome;
    this.idade = idade;
  }
}