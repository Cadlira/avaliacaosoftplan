package br.com.softplan.avaliacao.exercicio1;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.RelatorioServiceTest;
import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.GeradorObservacaoTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ GeradorObservacaoTestSuite.class, RelatorioServiceTest.class })
public class Exercicio1TestSuite {

}
