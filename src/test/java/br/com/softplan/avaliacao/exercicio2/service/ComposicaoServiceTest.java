package br.com.softplan.avaliacao.exercicio2.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.repository.ComposicaoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ComposicaoServiceTest {

	@Autowired
	private ComposicaoService composicaoService;
	
	@Autowired
	private ComposicaoRepository composicaoRepository;
	
	private String jsonComposicoes;
	
	@Before
	public void setUp() throws IOException {
		this.jsonComposicoes = IOUtils.toString(
			      this.getClass().getResourceAsStream("/dados-entrada-servicos-composicoes.json"),
			      "UTF-8"
			    );
	}
	
	@Test
	public void verificar_quantidade_composicoes_existentes_no_json() {
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		assertThat(composicoes).isNotNull();
		assertThat(composicoes.size()).isEqualTo(5);
	}
	
	@Test
	public void validar_valoresUnitarios_composicao_codigo_94793() {
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		assertThat(composicoes).isNotNull();
		assertThat(composicoes.size()).isEqualTo(5);
		
		Composicao composicaoSalva = this.composicaoRepository.findByCodigo(94793).orElse(null);
		
		assertThat(composicaoSalva).isNotNull();
		
		assertThat(composicaoSalva.getValorUnitario()).isEqualTo(128.61);
	}
	
	@Test
	public void validar_valoresUnitarios_composicao_codigo_98561() {
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		assertThat(composicoes).isNotNull();
		assertThat(composicoes.size()).isEqualTo(5);
		
		Composicao composicaoSalva = this.composicaoRepository.findByCodigo(98561).orElse(null);
		
		assertThat(composicaoSalva).isNotNull();
		
		assertThat(composicaoSalva.getValorUnitario()).isEqualTo(28.75);
	}
	
	@Test
	public void validar_valoresUnitarios_composicao_codigo_87286() {
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		assertThat(composicoes).isNotNull();
		assertThat(composicoes.size()).isEqualTo(5);
		
		Composicao composicaoSalva = this.composicaoRepository.findByCodigo(87286).orElse(null);
		
		assertThat(composicaoSalva).isNotNull();
		
		assertThat(composicaoSalva.getValorUnitario()).isEqualTo(289.98);
	}
	
	@Test
	public void validar_valoresUnitarios_composicao_codigo_88831() {
		List<Composicao> composicoes = this.composicaoService.carregarComposicoes(jsonComposicoes);
		
		assertThat(composicoes).isNotNull();
		assertThat(composicoes.size()).isEqualTo(5);
		
		Composicao composicaoSalva = this.composicaoRepository.findByCodigo(88831).orElse(null);
		
		assertThat(composicaoSalva).isNotNull();
		
		assertThat(composicaoSalva.getValorUnitario()).isEqualTo(0.22);
	}
}
