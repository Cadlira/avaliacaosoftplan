package br.com.softplan.avaliacao.exercicio1.repository;

import java.util.Optional;

import br.com.softplan.avaliacao.exercicio1.model.NotaFiscal;

/**
 * Interface para o repositório de notas fiscais
 * 
 * @author leonardo.lira
 *
 */
public interface NotaFiscalRepository {

	/**
	 * Encontra uma nota fiscal pelo número
	 * 
	 * @param i
	 * @return
	 */
	Optional<NotaFiscal> findByNumero(int i);

}
