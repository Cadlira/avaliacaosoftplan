package br.com.softplan.avaliacao.exercicio1.service.factory;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoInterface;

public interface GeradorObservacaoFactory {
	GeradorObservacaoInterface getGeradorObservacao(String tipoBean);
}
