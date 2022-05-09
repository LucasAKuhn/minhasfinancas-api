package com.lkuhn.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lkuhn.minhasfinancas.model.entity.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
