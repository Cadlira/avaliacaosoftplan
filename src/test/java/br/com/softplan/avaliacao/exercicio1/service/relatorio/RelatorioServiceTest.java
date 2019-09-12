package br.com.softplan.avaliacao.exercicio1.service.relatorio;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao.VersaoGerador;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RelatorioServiceTest {

	@Autowired(required = true)
	private RelatorioService relatorioService;
	
	@Test
	public void gerar_observacao_com_apenas_uma_nota_e_gerador_V1() throws Exception {
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1), VersaoGerador.V1);
		
		assertThat(resultado).isEqualTo("Fatura da nota fiscal de simples remessa: 1.");
	}
	
	@Test
	public void gerar_observacao_com_mais_de_uma_nota_e_gerador_V1() throws Exception {
		
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1,2,3,4,5), VersaoGerador.V1);
		
		assertThat(resultado).isEqualTo("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.");
	}
	
	@Test
	public void gerar_observacao_com_apenas_uma_nota_e_gerador_null() throws Exception {
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1), null);
		
		assertThat(resultado).isEqualTo("Fatura da nota fiscal de simples remessa: 1.");
	}
	
	@Test
	public void gerar_observacao_com_mais_de_uma_nota_e_gerador_null() throws Exception {
		
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1,2,3,4,5), null);
		
		assertThat(resultado).isEqualTo("Fatura das notas fiscais de simples remessa: 1, 2, 3, 4 e 5.");
	}
	
	@Test
	public void gerar_observacao_com_lista_vazia_gerador_V1() throws Exception {
		String resultado = this.relatorioService.geraObservacao(new ArrayList<Integer>(), VersaoGerador.V1);
		
		assertThat(resultado).isEqualTo("");
	}
	
	@Test
	public void gerar_observacao_com_lista_nula_gerador_V1() throws Exception {
		String resultado = this.relatorioService.geraObservacao(null, VersaoGerador.V1);
		
		assertThat(resultado).isEqualTo("");
	}
	
	@Test
	public void gerar_observacao_com_apenas_uma_nota_e_gerador_V2() throws Exception {
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1), VersaoGerador.V2);
		
		assertThat(resultado).isEqualTo("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total = 10,00.");

	}
	
	@Test
	public void gerar_observacao_com_mais_de_uma_nota_e_gerador_V2() throws Exception {
		String resultado = this.relatorioService.geraObservacao(Arrays.asList(1,2,3,4,5), VersaoGerador.V2);
		
		assertThat(resultado).isEqualTo("Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = 1.550,30.");		
	}
	
	@Test
	public void gerar_observacao_com_lista_vazia_gerador_V2() throws Exception {
		String resultado = this.relatorioService.geraObservacao(new ArrayList<Integer>(), VersaoGerador.V2);
		
		assertThat(resultado).isEqualTo("");
	}
	
	@Test
	public void gerar_observacao_com_lista_nula_gerador_V2() throws Exception {
		String resultado = this.relatorioService.geraObservacao(null, VersaoGerador.V2);
		
		assertThat(resultado).isEqualTo("");
	}
}
