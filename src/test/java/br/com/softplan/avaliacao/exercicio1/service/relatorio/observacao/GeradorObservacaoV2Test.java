package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio1.model.NotaFiscal;
import br.com.softplan.avaliacao.exercicio1.repository.NotaFiscalRepository;

@RunWith(SpringRunner.class)
public class GeradorObservacaoV2Test {

	private GeradorObservacaoV2 gerador;
	
	@MockBean
	private NotaFiscalRepository notaFiscalRepository;
	
	private Map<Integer, NotaFiscal> notas;
	
	@Before
	public void setUp() throws Exception {
		gerador = new GeradorObservacaoV2(this.notaFiscalRepository);
		this.notas = new HashMap<Integer, NotaFiscal>();
		this.notas.put(1, new NotaFiscal(1, 10.00f));
		this.notas.put(2, new NotaFiscal(2, 35.00f));
		this.notas.put(3, new NotaFiscal(3, 5.00f));
		this.notas.put(4, new NotaFiscal(4, 1500.00f));
		this.notas.put(5, new NotaFiscal(5, 0.30f));
	}
	
	@Test
	public void gerar_observacao_com_apenas_uma_nota() throws Exception {
		
		when(this.notaFiscalRepository.findByNumero(1)).thenReturn(Optional.of(this.notas.get(1)));
		
		String resultado = this.gerador.geraObservacao(Arrays.asList(1));
		
		assertThat(resultado).isEqualTo("Fatura da nota fiscal de simples remessa: 1 cujo valor é R$ 10,00. Total = 10,00.");
	}
	
	@Test
	public void gerar_observacao_com_mais_de_uma_nota() throws Exception {
		
		when(this.notaFiscalRepository.findByNumero(1)).thenReturn(Optional.of(this.notas.get(1)));
		when(this.notaFiscalRepository.findByNumero(2)).thenReturn(Optional.of(this.notas.get(2)));
		when(this.notaFiscalRepository.findByNumero(3)).thenReturn(Optional.of(this.notas.get(3)));
		when(this.notaFiscalRepository.findByNumero(4)).thenReturn(Optional.of(this.notas.get(4)));
		when(this.notaFiscalRepository.findByNumero(5)).thenReturn(Optional.of(this.notas.get(5)));
		
		String resultado = this.gerador.geraObservacao(Arrays.asList(1,2,3,4,5));
		
		assertThat(resultado).isEqualTo(
				"Fatura das notas fiscais de simples remessa: 1 cujo valor é R$ 10,00, 2 cujo valor é R$ 35,00, 3 cujo valor é R$ 5,00, 4 cujo valor é R$ 1.500,00 e 5 cujo valor é R$ 0,30. Total = 1.550,30.");
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
