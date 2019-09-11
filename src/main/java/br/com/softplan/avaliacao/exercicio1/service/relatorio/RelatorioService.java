package br.com.softplan.avaliacao.exercicio1.service.relatorio;

import java.util.List;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.VersaoGerador;

public interface RelatorioService {

	String geraObservacao(List<Integer> lista, VersaoGerador versaoGeradorObservacao);

}
