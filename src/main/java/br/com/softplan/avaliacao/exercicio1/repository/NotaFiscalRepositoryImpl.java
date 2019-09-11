package br.com.softplan.avaliacao.exercicio1.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import br.com.softplan.avaliacao.exercicio1.model.NotaFiscal;

@Repository("notaFiscalRepository")
public class NotaFiscalRepositoryImpl implements NotaFiscalRepository {

	private Map<Integer, NotaFiscal> DB = new HashMap<Integer, NotaFiscal>();
	
	public NotaFiscalRepositoryImpl() {
		this.DB.put(1, new NotaFiscal(1, 10.00f));
		this.DB.put(2, new NotaFiscal(2, 35.00f));
		this.DB.put(3, new NotaFiscal(3, 5.00f));
		this.DB.put(4, new NotaFiscal(4, 1500.00f));
		this.DB.put(5, new NotaFiscal(5, 0.30f));
	}
	
	@Override
	public Optional<NotaFiscal> findByNumero(int numeroNota) {		
		return Optional.ofNullable(this.DB.get(numeroNota));
	}

}
