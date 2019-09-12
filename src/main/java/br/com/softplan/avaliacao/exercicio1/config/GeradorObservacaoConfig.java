package br.com.softplan.avaliacao.exercicio1.config;

import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import br.com.softplan.avaliacao.exercicio1.repository.NotaFiscalRepository;
import br.com.softplan.avaliacao.exercicio1.service.factory.GeradorObservacaoFactory;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacao;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoInterface;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoV2;

/**
 * Classe de configuração utilizada para o strategy dos geradores de observação
 * 
 * @author leonardo.lira
 *
 */
@Configuration
public class GeradorObservacaoConfig {

	/**
	 * Gerar o bean da versão antiga do gerador de observação
	 * 
	 * @return
	 */
	@Bean("geradorObservacaoV1")
	public GeradorObservacaoInterface geradorObservacao() {
		return new GeradorObservacao();
	}

	/**
	 * Gerar o bean da versão nova do gerador de observação
	 * 
	 * @return
	 */
	@Bean("geradorObservacaoV2")
	@DependsOn("notaFiscalRepository")
	public GeradorObservacaoInterface geradorObservacaoV2(NotaFiscalRepository notaFiscalRepository) {
		return new GeradorObservacaoV2(notaFiscalRepository);
	}

	/**
	 * Adicionar o factory do gerador de observação para ser utilizado para encontrar o bean correto
	 * 
	 * @return
	 */
	@Bean
	public ServiceLocatorFactoryBean serviceLocatorFactoryBean() {
		ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
		serviceLocatorFactoryBean.setServiceLocatorInterface(GeradorObservacaoFactory.class);
		return serviceLocatorFactoryBean;
	}
}
