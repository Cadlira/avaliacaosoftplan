package br.com.softplan.avaliacao.exercicio2.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ItemComposicaoTest {

	@Test
	public void validar_retorno_getValor_com_item() {
		Item item = mock(Item.class);
		when(item.getValorUnitario()).thenReturn(30d);
		ItemComposicao itemComposicao = new ItemComposicao(item, 0.1d);
		double valor = itemComposicao.getValor();
		
		assertThat(valor).isEqualTo(0.1d * 30);
	}
	
	@Test
	public void validar_retorno_getValor_com_item_null() {
		ItemComposicao itemComposicao = new ItemComposicao(null, 0.1d);
		double valor = itemComposicao.getValor();
		
		assertThat(valor).isEqualTo(0d);
	}
	
	@Test
	public void compara_itemComposicao_item_mesmo_codigo_classe_diferente() {
		Item insumo = new Insumo(11, "descricao insumo", Unidade.UN, 100);
		Item composicao = new Composicao(11, "Descrição composição", Unidade.UN);
		
		ItemComposicao item1 = new ItemComposicao(insumo, 2);
		ItemComposicao item2 = new ItemComposicao(composicao, 2);
		
		assertThat(item1.equals(item2)).isEqualTo(false);
	}
}
