package br.com.softplan.avaliacao.exercicio2.model;

public class Insumo extends Item {

	private double valorUnitario;
	
	public Insumo() {
		super();
	}

	public Insumo(int codigo, String descricao, Unidade unidade, double valor) {
		super(codigo, descricao, unidade);
		this.valorUnitario = valor;
	}

	@Override
	public double getValorUnitario() {
		return this.valorUnitario;
	}

	public void setValorUnitario(double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	@Override
	public TipoItem getTipo() {
		return TipoItem.INSUMO;
	}

}
