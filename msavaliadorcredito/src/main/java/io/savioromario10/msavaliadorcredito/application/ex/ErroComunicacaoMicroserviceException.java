package io.savioromario10.msavaliadorcredito.application.ex;

import lombok.Getter;

@Getter
public class ErroComunicacaoMicroserviceException extends Exception {

  private final Integer status;

  public ErroComunicacaoMicroserviceException(String msg, Integer status) {
    super(msg);
    this.status = status;
  }
}