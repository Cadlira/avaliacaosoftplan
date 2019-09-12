package br.com.softplan.avaliacao;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.service.ComposicaoService;

@SpringBootApplication
public class AvaliacaoSoftplanApplication implements CommandLineRunner {

	@Autowired
	private ComposicaoService composicaoService;
	
	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoSoftplanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		carregarCabecalho();
		
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

	private void carregarCabecalho() {
		pularLinha(18);
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
