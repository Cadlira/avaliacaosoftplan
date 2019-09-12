package br.com.softplan.avaliacao.exercicio2.repository;

import java.util.Collection;
import java.util.Optional;

import br.com.softplan.avaliacao.exercicio2.model.Item;

public interface Exercicio2Repository<T extends Item> {

	Optional<T> findByCodigo(int codigo);
	
	T save(T item);
	
	Collection<T> findAll();
		
}
