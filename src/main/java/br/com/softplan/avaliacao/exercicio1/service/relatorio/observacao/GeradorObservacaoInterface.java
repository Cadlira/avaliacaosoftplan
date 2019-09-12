package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

import java.util.List;

import org.springframework.util.CollectionUtils;

/**
 * Classe abstrata para centralizar as logicas comuns das 
 * classes geradoras de observacao
 * 
 * @author leonardo.lira
 *
 */
public abstract class GeradorObservacaoInterface {

	// Gera observações, com texto pre-definido, incluindo os números, das notas
	// fiscais, recebidos no parâmetro
	@SuppressWarnings("rawtypes")
	public String geraObservacao(List lista) {
		String observacao = "";
		if (!CollectionUtils.isEmpty(lista)) {
			observacao = "Fatura da nota fiscal de simples remessa: %s.";
			if (lista.size() > 1) {
				observacao = "Fatura das notas fiscais de simples remessa: %s.";
			}
			observacao = String.format(observacao, retornaCodigos(lista));
		}
		return observacao;
	}

	// Criar observação
	@SuppressWarnings("rawtypes")
	protected abstract String retornaCodigos(List lista);
	
	
}
