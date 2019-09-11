package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

public enum VersaoGerador {
	V1("geradorObservacaoV1"), V2("geradorObservacaoV2");
	
	private String beanName;
	
	private VersaoGerador(String beanName) {
		this.beanName = beanName;
	}
	
	public String getBeanName() {
		return beanName;
	}
}
