package br.com.softplan.avaliacao.exercicio2.service;

import java.util.List;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;

/**
 * Interface para a regra de negócio da composição
 * 
 * @author leonardo.lira
 *
 */
public interface ComposicaoService {

	/**
	 * Carrega as composições passadas no JSON de entrada, convertendo o mesmo em um DTO
	 * de itensComposicao e, em seguida, recuperando as composições
	 * 
	 * @param jsonComposicoes String com JSON de entrada
	 * @return lista com as composições existentes no JSON de entrada
	 */
	List<Composicao> carregarComposicoes(String jsonComposicoes);

}
