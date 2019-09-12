package br.com.softplan.avaliacao;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.RelatorioService;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.VersaoGerador;
import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.service.ComposicaoService;

@SpringBootApplication
public class AvaliacaoSoftplanApplication implements CommandLineRunner {

	@Autowired
	private ComposicaoService composicaoService;
	
	@Autowired
	private RelatorioService relatorioService;
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoSoftplanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		
		carregarCabecalhoExercicio1();
		
		System.out.println("---------------------------------------------------");
		System.out.println("GERADOR DE OBSERVACAO ANTIGO");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println(this.relatorioService.geraObservacao(Arrays.asList(1,2,3,4,5), VersaoGerador.V1));
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------");		
		System.out.println("---------------------------------------------------");		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("GERADOR DE OBSERVACAO NOVO");
		System.out.println("---------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println(this.relatorioService.geraObservacao(Arrays.asList(1,2,3,4,5), VersaoGerador.V2));
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------");		
		System.out.println("---------------------------------------------------");
		carregarRodape();
				
		
		carregarCabecalhoExercicio2();
		
		//Carrega o arquivo json de dentro do projeto
		String jsonComposicoes = IOUtils.toString(
			      this.getClass().getResourceAsStream("/dados-entrada-servicos-composicoes.json"),
			      "UTF-8"
			    );
		// REcupera as composições contidas no arquivo JSON
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		// Imprime as informações das composições no console
		composicoes.forEach(System.out::println);
		
		carregarRodape();
	}
	
	private void carregarRodape() {
		pularLinha(5);
		System.out.println("***************************************************************************************************************************");
		System.out.println("***************************************************************************************************************************");
		
	}
	
	private void carregarCabecalhoExercicio1() {
		pularLinha(10);
		System.out.println("***************************************************************************************************************************");
		System.out.println("*******************************************  AVALIACAO SOFTPLAN - EXERCICIO 01  *******************************************");
		System.out.println("***************************************************************************************************************************");
		pularLinha(2);
	}

	private void carregarCabecalhoExercicio2() {
		pularLinha(4);
		System.out.println("***************************************************************************************************************************");
		System.out.println("*******************************************  AVALIACAO SOFTPLAN - EXERCICIO 02  *******************************************");
		System.out.println("***************************************************************************************************************************");
		pularLinha(2);
	}
	
	private void pularLinha(int vezes) {
		for (int i = 0; i < vezes; i++) {
			System.out.println();
		}
	}

}
