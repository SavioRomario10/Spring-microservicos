package io.savioromario10.msavaliadorcredito.application.ex;

public class ErroComunicacaoMicroserviceException extends Exception {
  public ErroComunicacaoMicroserviceException(String msg, Integer status) {
    super(msg + " | Status code: " + status);
  }
}