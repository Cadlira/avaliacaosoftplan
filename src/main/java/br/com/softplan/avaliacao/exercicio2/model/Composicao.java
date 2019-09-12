package br.com.softplan.avaliacao.exercicio2.model;

import java.text.NumberFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import br.com.softplan.avaliacao.exercicio2.Util.DoubleUtil;

/**
 * Classe responsável por manter um composição e suas regras de negócio
 * 
 * @author leonardo.lira
 *
 */
public class Composicao extends Item {

	private static NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));

	private Set<ItemComposicao> itens;

	public Composicao(int codigo, String descricao, Unidade unidade) {
		super(codigo, descricao, unidade);
		this.itens = new HashSet<ItemComposicao>();
	}

	public Composicao(int codigo, String descricao, Unidade unidade, Set<ItemComposicao> itens) {
		this(codigo, descricao, unidade);
		this.itens = itens;
	}

	public Composicao() {
		super();
	}

	@Override
	public double getValorUnitario() {
		// Caso a composição não tenha itensComposicao o valor unitário será 0
		if (CollectionUtils.isEmpty(this.itens)) {
			return 0d;
		}

		/*
		 * Aqui é aplicada a lógica para calcular o valor da composição
		 * 
		 * o valor da composição é calculada somando os valores de cada itemComposicao
		 * 
		 * Fora isso, foi adicionada uma lógica para evitar loop infinito caso um dos
		 * itens da composição seja ela mesma, fazendo que não seja chamado o método
		 * getValor() do itemComposicao, caso o item seja ele mesmo
		 */
		return DoubleUtil.round(this.itens.stream()
				.filter(item -> (TipoItem.INSUMO.equals(item.getItem().getTipo())
						|| (item.getItem().getCodigo() != this.getCodigo()
								&& TipoItem.COMPOSICAO.equals(item.getItem().getTipo()))))
				.mapToDouble(item -> item.getValor()).sum(), 2);
	}

	@Override
	public TipoItem getTipo() {
		return TipoItem.COMPOSICAO;
	}

	public Set<ItemComposicao> getItens() {
		return this.itens;
	}

	/**
	 * Adiciona um novo item na composição
	 * 
	 * @param item composição para ser armazenada
	 */
	public void addItem(ItemComposicao item) {
		if (Objects.nonNull(item)) {
			if (Objects.isNull(this.itens)) {
				this.itens = new HashSet<ItemComposicao>();
			}
			this.itens.add(item);
		}

	}

	@Override
	public String toString() {
		return String.format("%d   %s   %s   %s", this.getCodigo(), this.getDescricao(), this.getUnidade().name(),
				numberFormat.format(this.getValorUnitario()));
	}

}
