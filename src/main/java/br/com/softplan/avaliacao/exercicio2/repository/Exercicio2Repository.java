package br.com.softplan.avaliacao.exercicio2.repository;

import java.util.Collection;
import java.util.Optional;

import br.com.softplan.avaliacao.exercicio2.model.Item;

/**
 * Interface comum para os repositórios dos itens
 * 
 * @author leonardo.lira
 *
 * @param <T> tipo do Item
 */
public interface Exercicio2Repository<T extends Item> {

	/**
	 * Pesquisa o item pelo código
	 * 
	 * @param codigo código único do item salvo no repositório
	 * @return Optional do item, podendo ser um Optional Empty
	 */
	Optional<T> findByCodigo(int codigo);
	
	
	/**
	 * Salva um item no repositório
	 * 
	 * @param item item a ser salvo
	 * @return item salvo
	 */
	T save(T item);
	
	/**
	 * Lista todos os itens no repositorio
	 * 
	 * @return 
	 */
	Collection<T> findAll();
		
}
