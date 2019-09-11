package br.com.softplan.avaliacao.exercicio1.model;

public class NotaFiscal {
	private int numero;
	
	private float valor;
	
	public NotaFiscal(int numero, float valor) {
		super();
		this.numero = numero;
		this.valor = valor;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}
}
