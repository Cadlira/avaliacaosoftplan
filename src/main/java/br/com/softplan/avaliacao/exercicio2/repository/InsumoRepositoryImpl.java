package br.com.softplan.avaliacao.exercicio2.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import br.com.softplan.avaliacao.exercicio2.model.Insumo;

public class InsumoRepositoryImpl extends Exercicio2AbstractRepository<Insumo> implements InsumoRepository {

	private Map<Integer, Insumo> itens = Collections.synchronizedMap(new HashMap<Integer, Insumo>());

	@Override
	protected Map<Integer, Insumo> getItens() {
		return this.itens;
	}

}
