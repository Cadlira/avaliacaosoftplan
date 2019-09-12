package br.com.softplan.avaliacao.exercicio2.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import br.com.softplan.avaliacao.exercicio2.model.Item;

/**
 * Classe abstrata para os repositórios dos itens, tanto composição e insumo
 * 
 * Essa classe centraliza os códigos comuns para os repositórios dos dois tipos
 * 
 * @author leonardo.lira
 *
 * @param <T> Tipo do Item
 */
public abstract class Exercicio2AbstractRepository<T extends Item> implements Exercicio2Repository<T> {
	
	@Override
	public Optional<T> findByCodigo(int codigo) {
		return Optional.ofNullable(this.getItens().get(codigo));
	}

	@Override
	public T save(T item) {
		if (Objects.nonNull(item)) {
			this.getItens().put(item.getCodigo(), item);
		}
		return item;
	}

	@Override
	public Collection<T> findAll() {		
		return this.getItens().values();
	}
	
	/**
	 * Retorno o map dos itens que serve como repositório
	 * 
	 * @return
	 */
	protected abstract Map<Integer, T> getItens();
}
