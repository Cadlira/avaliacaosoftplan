package br.com.softplan.avaliacao.exercicio1.repository;

import java.util.Optional;

import br.com.softplan.avaliacao.exercicio1.model.NotaFiscal;

public interface NotaFiscalRepository {

	Optional<NotaFiscal> findByNumero(int i);

}
