package br.com.softplan.avaliacao.exercicio2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.com.softplan.avaliacao.exercicio2.factory.ItemFactoryTest;
import br.com.softplan.avaliacao.exercicio2.model.ModelTestSuite;
import br.com.softplan.avaliacao.exercicio2.repository.RepositoryTestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ModelTestSuite.class, ItemFactoryTest.class, RepositoryTestSuite.class})
public class Exercicio2TestSuite {

}
