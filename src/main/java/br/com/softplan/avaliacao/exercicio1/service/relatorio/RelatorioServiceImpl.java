package br.com.softplan.avaliacao.exercicio1.service.relatorio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.avaliacao.exercicio1.service.factory.GeradorObservacaoFactory;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.VersaoGerador;

/**
 * Classe concreta para o serviço de relatório
 * 
 * @author leonardo.lira
 *
 */
@Service
public class RelatorioServiceImpl implements RelatorioService {

	@Autowired
	private GeradorObservacaoFactory geradorObservacaoFactory;

	@Override
	public String geraObservacao(List<Integer> lista, VersaoGerador versaoGeradorObservacao) {
		/*
		 *  utilizar o mecanismo de localização de bean do spring para selecionar a implementação correta
		 *  do gerador de observação
		 */
		
		return this.geradorObservacaoFactory.getGeradorObservacao(versaoGeradorObservacao.getBeanName())
				.geraObservacao(lista);
	}

}
