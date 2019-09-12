package br.com.softplan.avaliacao.exercicio2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.model.Insumo;
import br.com.softplan.avaliacao.exercicio2.model.ItemComposicao;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;

@RunWith(SpringRunner.class)
public class ComposicaoRepositoryTest {

	private ComposicaoRepository composicaoRepository;

	@Before
	public void setUp() {
		this.composicaoRepository = new ComposicaoRepositoryImpl();
	}

	@Test
	public void validar_insercao_de_composicoes_diferentes() {

		Insumo insumo1 = new Insumo(1, "descricao insumo 1", Unidade.H, 1.0d);
		Insumo insumo2 = new Insumo(2, "descricao insumo 2", Unidade.M2, 3.5d);

		Composicao composicao1 = new Composicao(1, "descricao 1", Unidade.CHI,
				new HashSet<>(Arrays.asList(new ItemComposicao(insumo1, 2))));
		Composicao composicao2 = new Composicao(2, "descricao 2", Unidade.CHI,
				new HashSet<>(Arrays.asList(new ItemComposicao(insumo1, 2), new ItemComposicao(insumo2, 1))));

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(0);

		this.composicaoRepository.save(composicao1);

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(1);

		this.composicaoRepository.save(composicao2);

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(2);

	}

	@Test
	public void validar_insercao_de_composicoes_iguais_atualizando() {
		Insumo insumo1 = new Insumo(1, "descricao 1", Unidade.H, 1.0d);
		Insumo insumo2 = new Insumo(1, "descricao 2", Unidade.M2, 3.5d);

		Composicao composicao1 = new Composicao(1, "descricao 1", Unidade.CHI,
				new HashSet<>(Arrays.asList(new ItemComposicao(insumo1, 2))));
		Composicao composicao2 = new Composicao(1, "descricao 2", Unidade.CHP,
				new HashSet<>(Arrays.asList(new ItemComposicao(insumo1, 2), new ItemComposicao(insumo2, 1))));

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(0);

		this.composicaoRepository.save(composicao1);

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(1);
		assertThat(this.composicaoRepository.findByCodigo(1).get().getValorUnitario()).isEqualTo(2.0d);

		this.composicaoRepository.save(composicao2);

		assertThat(this.composicaoRepository.findAll().size()).isEqualTo(1);

		assertThat(this.composicaoRepository.findByCodigo(1).get().getDescricao()).isEqualTo("descricao 2");
		assertThat(this.composicaoRepository.findByCodigo(1).get().getUnidade()).isEqualTo(Unidade.CHP);
		assertThat(this.composicaoRepository.findByCodigo(1).get().getValorUnitario()).isEqualTo(5.5d);

	}
}
