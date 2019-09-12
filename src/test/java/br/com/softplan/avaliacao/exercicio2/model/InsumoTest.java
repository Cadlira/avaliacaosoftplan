package br.com.softplan.avaliacao.exercicio2.model;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class InsumoTest {

	private Item item;
	
	@Before
	public void setUp() {
		this.item = new Insumo(7325,"ADITIVO IMPERMEABILIZANTE DE PEGA NORMAL PARA ARGAMASSAS E  CONCRETOS SEM ARMACAO", Unidade.KG, 4.44d);
	}
	
	
	@Test
	public void validar_tipoItem_do_objeto_insumo() {
		
		assertThat(item.getTipo()).isEqualTo(TipoItem.INSUMO);
	}
	
	@Test
	public void validar_descricao_insumo() {
		assertThat(item.getDescricao()).isEqualTo("ADITIVO IMPERMEABILIZANTE DE PEGA NORMAL PARA ARGAMASSAS E  CONCRETOS SEM ARMACAO");
	}
	
	@Test
	public void validar_codigo_insumo() {
		assertThat(item.getCodigo()).isEqualTo(7325);
	}
	
	@Test
	public void validar_valorUnitario_insumo() {
		assertThat(item.getValorUnitario()).isEqualTo(4.44d);
	}
	
	@Test
	public void validar_unidade_insumo() {
		assertThat(item.getUnidade()).isEqualTo(Unidade.KG);
	}
}
