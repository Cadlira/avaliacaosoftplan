package br.com.softplan.avaliacao.exercicio2.factory;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.model.Insumo;
import br.com.softplan.avaliacao.exercicio2.model.Item;
import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;
import br.com.softplan.avaliacao.exercicio2.repository.ComposicaoRepository;
import br.com.softplan.avaliacao.exercicio2.repository.InsumoRepository;

/**
 * 
 * @author leonardo.lira
 *
 */
@Component
public class ItemFactoryImpl implements ItemFactory {

	private InsumoRepository insumoRepository;

	private ComposicaoRepository composicaoRepository;

	public ItemFactoryImpl(InsumoRepository insumorepository, ComposicaoRepository composicaoRepository) {
		this.insumoRepository = insumorepository;
		this.composicaoRepository = composicaoRepository;
	}
	
	


	@Override
	public Item getItem(int codigo, String descricao, Unidade unidade, double valor, TipoItem tipoItem) {

		// caso o código seja inválido retorna null
		if (codigo <= 0) {
			return null;
		}
		
		// caso não seja informado o tipo será retornado um item do tipo insumo
		if (Objects.isNull(tipoItem)) {
			return getItemInsumo(codigo, descricao, unidade, valor);
		}

		switch (tipoItem) {
		case COMPOSICAO:
			return getItemComposicao(codigo, descricao, unidade);
		default:
			return getItemInsumo(codigo, descricao, unidade, valor);
		}

	}

	/*
	 * Esse método pesquisa no repositório uma composição com o código informado,
	 * 
	 * Caso seja encontrado, o mesmo será atualizado com as informações passadas
	 * como paramêtros e atualizado no repositório
	 * 
	 * Caso não seja encontrado será criado uma nova composição com os dados
	 * informados nos parâmetros e salva no repositório
	 */
	private Item getItemComposicao(int codigo, String descricao, Unidade unidade) {
		Optional<Composicao> composicaoOpt = this.composicaoRepository.findByCodigo(codigo);
		Composicao composicao = null;
		if (composicaoOpt.isPresent()) {
			composicao = composicaoOpt.get();
		} else {
			composicao = new Composicao();
		}
		composicao.setCodigo(codigo);
		composicao.setDescricao(descricao);
		composicao.setUnidade(unidade);
		return this.composicaoRepository.save(composicao);
	}

	/*
	 * Esse método pesquisa no repositório um insumo com o código informado,
	 * 
	 * Caso seja encontrado, o mesmo será atualizado com as informações passadas
	 * como paramêtros e atualizado no repositório
	 * 
	 * Caso não seja encontrado será criado um novo insumo com os dados
	 * informados nos parâmetros e salva no repositório
	 */
	private Item getItemInsumo(int codigo, String descricao, Unidade unidade, double valor) {
		Optional<Insumo> insumoOpt = this.insumoRepository.findByCodigo(codigo);
		Insumo insumo = null;
		if (insumoOpt.isPresent()) {
			insumo = insumoOpt.get();
		} else {
			insumo = new Insumo();
		}
		insumo.setCodigo(codigo);
		insumo.setDescricao(descricao);
		insumo.setUnidade(unidade);
		insumo.setValorUnitario(valor);
		return this.insumoRepository.save(insumo);
	}

}
