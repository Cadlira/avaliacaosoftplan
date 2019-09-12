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

		if (codigo <= 0) {
			return null;
		}
		
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
