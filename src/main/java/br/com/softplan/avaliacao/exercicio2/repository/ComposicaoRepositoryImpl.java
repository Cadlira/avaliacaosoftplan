package br.com.softplan.avaliacao.exercicio2.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;

/**
 * Classe concreta para o repositório de composição
 * 
 * @author leonardo.lira
 *
 */
@Repository
public class ComposicaoRepositoryImpl extends Exercicio2AbstractRepository<Composicao> implements ComposicaoRepository {

	private Map<Integer, Composicao> itens = Collections.synchronizedMap(new HashMap<Integer, Composicao>());

	@Override
	protected Map<Integer, Composicao> getItens() {
		return this.itens;
	}

}
