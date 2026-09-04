package io.savioromario10.mscartoes.infra.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.savioromario10.mscartoes.domain.Cartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long> {

  List<Cartao> findByRendaLessThanEqual(BigDecimal renda);
}