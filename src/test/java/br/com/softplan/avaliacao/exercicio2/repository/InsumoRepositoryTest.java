package br.com.softplan.avaliacao.exercicio2.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio2.model.Insumo;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;

@RunWith(SpringRunner.class)
public class InsumoRepositoryTest {
	
	private InsumoRepository insumoRepository;
	
	@Before
	public void setUp() {
		this.insumoRepository = new InsumoRepositoryImpl();
	}
	
	@Test
	public void validar_insercao_de_insumos_diferentes() {
		Insumo insumo1 = new Insumo(1, "descricao 1", Unidade.H, 1.0d);
		Insumo insumo2 = new Insumo(2, "descricao 2", Unidade.M2, 3.5d);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(0);
		
		this.insumoRepository.save(insumo1);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(1);
		
		this.insumoRepository.save(insumo2);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(2);
		
	}
	
	@Test
	public void validar_insercao_de_insumos_iguais_atualizando() {
		Insumo insumo1 = new Insumo(1, "descricao 1", Unidade.H, 1.0d);
		Insumo insumo2 = new Insumo(1, "descricao 2", Unidade.M2, 3.5d);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(0);
		
		this.insumoRepository.save(insumo1);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(1);
		
		this.insumoRepository.save(insumo2);
		
		assertThat(this.insumoRepository.findAll().size()).isEqualTo(1);
		
		assertThat(this.insumoRepository.findByCodigo(1).get().getDescricao()).isEqualTo("descricao 2");
		assertThat(this.insumoRepository.findByCodigo(1).get().getUnidade()).isEqualTo(Unidade.M2);
		assertThat(this.insumoRepository.findByCodigo(1).get().getValorUnitario()).isEqualTo(3.5d);
		
	}
}
