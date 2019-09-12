package br.com.softplan.avaliacao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.softplan.avaliacao.exercicio1.Exercicio1TestSuite;
import br.com.softplan.avaliacao.exercicio2.Exercicio2TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ Exercicio1TestSuite.class, Exercicio2TestSuite.class })
public class AvaliacaoSoftplanApplicationTests {} 
