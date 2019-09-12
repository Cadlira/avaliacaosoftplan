package br.com.softplan.avaliacao.exercicio2.model;

public abstract class Item {
	
	private int codigo;
	private String descricao;
	private Unidade unidade;

	public Item(int codigo, String descricao, Unidade unidade) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.unidade = unidade;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	protected abstract double getValorUnitario();
	
	protected abstract TipoItem getTipo();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codigo;
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
		Item other = (Item) obj;
		if (codigo != other.codigo)
			return false;
		return true;
	}


}
