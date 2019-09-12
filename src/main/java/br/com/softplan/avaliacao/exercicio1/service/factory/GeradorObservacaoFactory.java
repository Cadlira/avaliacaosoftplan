package br.com.softplan.avaliacao.exercicio1.service.factory;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoInterface;

/**
 * Interface utilizada pelo beanLocator para selecionar a implementação correta do gerador de observação
 * 
 * @author leonardo.lira
 *
 */
public interface GeradorObservacaoFactory {
	GeradorObservacaoInterface getGeradorObservacao(String tipoBean);
}
