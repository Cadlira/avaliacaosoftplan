package br.com.softplan.avaliacao.exercicio2.model;

public class Insumo extends Item {

	private double valorUnitario;
	
	public Insumo(int codigo, String descricao, Unidade unidade, double valor) {
		super(codigo, descricao, unidade);
		this.valorUnitario = valor;
	}

	@Override
	protected double getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	protected TipoItem getTipo() {
		return TipoItem.INSUMO;
	}

}
