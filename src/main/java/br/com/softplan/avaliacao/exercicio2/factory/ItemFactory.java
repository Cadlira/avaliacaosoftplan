package br.com.softplan.avaliacao.exercicio2.factory;

import br.com.softplan.avaliacao.exercicio2.model.Item;
import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;

public interface ItemFactory {

	Item getItem(int codigo, String descricao, Unidade unidade, double valor, TipoItem tipoItem);

}
