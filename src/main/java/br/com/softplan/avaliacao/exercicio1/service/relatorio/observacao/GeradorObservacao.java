package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe contendo o método antigo de geração de observação
 * 
 * @author leonardo.lira
 *
 */
public class GeradorObservacao extends GeradorObservacaoInterface {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected String retornaCodigos(List lista) {
		/*
		 * Foi simplificado o código de geração de observações
		 * utilizando lambda e stream
		 */
		int ultimaPosicao = lista.size() - 1;
		String ultimoDelimitador = " e ";
		if (ultimaPosicao < 1) {
			ultimoDelimitador = "";
		}
		return String.join(ultimoDelimitador,
				String.join(", ", (String) lista.subList(0, ultimaPosicao).stream()
						.map(numeroNota -> numeroNota.toString()).collect(Collectors.joining(", "))),
				String.valueOf(lista.get(ultimaPosicao)));
	}
}
