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

@Configuration
public class GeradorObservacaoConfig {

	
	@Bean("geradorObservacaoV1")
	public GeradorObservacaoInterface geradorObservacao() {
		return new GeradorObservacao();
	}
	
	@Bean("geradorObservacaoV2")
	@DependsOn("notaFiscalRepository")
	public GeradorObservacaoInterface geradorObservacaoV2(NotaFiscalRepository notaFiscalRepository) {
		return new GeradorObservacaoV2(notaFiscalRepository);
	}
	
	  @Bean
	  public ServiceLocatorFactoryBean serviceLocatorFactoryBean() {
	    ServiceLocatorFactoryBean serviceLocatorFactoryBean = new ServiceLocatorFactoryBean();
	    serviceLocatorFactoryBean.setServiceLocatorInterface(GeradorObservacaoFactory.class);
	    return serviceLocatorFactoryBean;
	  }
}
