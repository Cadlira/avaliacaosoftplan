package br.com.softplan.avaliacao.exercicio1.service.relatorio;

import java.util.List;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.VersaoGerador;

/**
 * Interface para o serviço de geração de relatório
 * 
 * @author leonardo.lira
 *
 */
public interface RelatorioService {

	/**
	 * Gera um observação de acordo com a versão escolhida
	 * 
	 * @param lista lista contendo os números das notas fiscais
	 * @param versaoGeradorObservacao versão do gerador escolhida
	 * @return
	 */
	String geraObservacao(List<Integer> lista, VersaoGerador versaoGeradorObservacao);

}
