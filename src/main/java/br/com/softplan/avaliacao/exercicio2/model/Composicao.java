package br.com.softplan.avaliacao.exercicio2.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import br.com.softplan.avaliacao.exercicio2.Util.DoubleUtil;

public class Composicao extends Item {

	private Set<ItemComposicao> itens;

	public Composicao(int codigo, String descricao, Unidade unidade) {
		super(codigo, descricao, unidade);
		this.itens = new HashSet<ItemComposicao>();
	}

	public Composicao(int codigo, String descricao, Unidade unidade, Set<ItemComposicao> itens) {
		this(codigo, descricao, unidade);
		this.itens = itens;
	}

	@Override
	protected double getValorUnitario() {

		if (CollectionUtils.isEmpty(this.itens)) {
			return 0d;
		}

		return DoubleUtil.round(this.itens.stream().filter(item -> item.getItem().getCodigo() != this.getCodigo())
				.mapToDouble(item -> item.getValor()).sum(), 5);
	}

	@Override
	protected TipoItem getTipo() {
		return TipoItem.COMPOSICAO;
	}

	public Set<ItemComposicao> getItens() {
		return this.itens;
	}

	public void addItem(ItemComposicao item) {
		if (Objects.nonNull(item)) {
			if (Objects.isNull(this.itens)) {
				this.itens = new HashSet<ItemComposicao>();
			}
			this.itens.add(item);
		}

	}

}
