package br.com.softplan.avaliacao.exercicio2.model;

import java.util.Objects;

public class ItemComposicao {

	private Item item;

	private double quantidade;

	public ItemComposicao(Item item, double quantidade) {
		super();
		this.item = item;
		this.quantidade = quantidade;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		if (Objects.isNull(item)) {
			return 0f;
		}
		return item.getValorUnitario() * this.getQuantidade();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((item == null) ? 0 : item.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemComposicao other = (ItemComposicao) obj;
		if (item == null) {
			if (other.item != null)
				return false;
		} else if (!item.equals(other.item))
			return false;
		if (Double.doubleToLongBits(quantidade) != Double.doubleToLongBits(other.quantidade))
			return false;
		return true;
	}

}
