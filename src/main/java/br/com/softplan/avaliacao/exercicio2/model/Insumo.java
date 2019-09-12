package br.com.softplan.avaliacao.exercicio2.model;

/**
 * Classe responsável por manter um insumo e suas regras de negócio
 * 
 * @author leonardo.lira
 *
 */
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
