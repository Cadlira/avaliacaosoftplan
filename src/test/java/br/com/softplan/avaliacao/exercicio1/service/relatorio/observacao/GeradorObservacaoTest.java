package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class GeradorObservacaoTest {

	private GeradorObservacao gerador;
	
	@Before
	public void setUp() throws Exception {
		gerador = new GeradorObservacao();
	}
	
	@Test
	public void gerar_observacao_com_apenas_uma_nota() throws Exception {
		String resultado = this.gerador.geraObservacao(Arrays.asList(1));
		
		assertThat(resultado).isEqualTo("Fatura da nota fiscal de simples remessa: 1.");
	}
	
	@Test
	public void gerar_observacao_com_mais_de_uma_nota() throws Exception {
		String resultado = this.gerador.geraObservacao(Arrays.asList(1,2,3,4,5));
		
		assertThat(resultado).isEqualTo("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.");
	}
	
	@Test
	public void gerar_observacao_com_lista_vazia() throws Exception {
		String resultado = this.gerador.geraObservacao(new ArrayList<Integer>());
		
		assertThat(resultado).isEqualTo("");
	}
	
	@Test
	public void gerar_observacao_com_lista_nula() throws Exception {
		String resultado = this.gerador.geraObservacao(null);
		
		assertThat(resultado).isEqualTo("");
	}
}
