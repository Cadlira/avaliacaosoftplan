package br.com.softplan.avaliacao.exercicio2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.softplan.avaliacao.exercicio2.factory.ItemFactoryTest;
import br.com.softplan.avaliacao.exercicio2.model.ModelTestSuite;
import br.com.softplan.avaliacao.exercicio2.repository.RepositoryTestSuite;
import br.com.softplan.avaliacao.exercicio2.service.ComposicaoServiceTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ModelTestSuite.class, ItemFactoryTest.class, RepositoryTestSuite.class, ComposicaoServiceTest.class})
public class Exercicio2TestSuite {

}
