package br.com.softplan.avaliacao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AvaliacaoSoftplanApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoSoftplanApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		for (String string : args) {
			System.out.println(string);
		}
		
	}

}
