package io.savioromario10.mscartoes.application;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import io.savioromario10.mscartoes.domain.Cartao;
import io.savioromario10.mscartoes.infra.repository.CartaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartaoService {

  private final CartaoRepository repository;

  @Transactional
  public Cartao save(Cartao cartao) {
    return repository.save(cartao);
  }

  public List<Cartao> getCartoesByRenda(Long renda) {
    var rendaBigDecimal = BigDecimal.valueOf(renda);
    return repository.findByRendaLessThanEqual(rendaBigDecimal);
  }
}