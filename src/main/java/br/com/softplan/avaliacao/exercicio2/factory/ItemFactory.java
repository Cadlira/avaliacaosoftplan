package br.com.softplan.avaliacao.exercicio2.factory;

import br.com.softplan.avaliacao.exercicio2.model.Item;
import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;

/**
 * Classe responsável pela instanciação dos itens, levando em consideração o tipo do mesmo
 * 
 * @author leonardo.lira
 *
 */
public interface ItemFactory {

	/**
	 * Instância um Item de acordo com o Tipo do item, caso o item já exista na base o mesmo e carregado e atualizado.
	 * 
	 * @param codigo Código do item gerado
	 * @param descricao descrição do item gerado
	 * @param unidade A unidade de medida do Item
	 * @param valor o valor do item
	 * @param tipoItem o tipo do item [INSUMO ou COMPOSICAO]
	 * @return
	 */
	Item getItem(int codigo, String descricao, Unidade unidade, double valor, TipoItem tipoItem);

}
