package br.com.softplan.avaliacao.exercicio2.factory;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.model.Insumo;
import br.com.softplan.avaliacao.exercicio2.model.Item;
import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;
import br.com.softplan.avaliacao.exercicio2.repository.ComposicaoRepository;
import br.com.softplan.avaliacao.exercicio2.repository.InsumoRepository;

@RunWith(SpringRunner.class)
public class ItemFactoryTest {

	@MockBean
	private InsumoRepository insumorepository;
	
	@MockBean
	private ComposicaoRepository composicaoRepository;
	
	private ItemFactory itemFactory;
	
	@Before
	public void setUp() {
		this.itemFactory = new ItemFactoryImpl(this.insumorepository, this.composicaoRepository);
	}
	
	@Test
	public void recuperar_item_do_tipo_insumo() {
		
		Item insumo = new Insumo(6014, "Descricao", Unidade.UN, 1.0d);
		
		when(this.insumorepository.findByCodigo(insumo.getCodigo())).thenReturn(Optional.empty());
		when(this.insumorepository.save((Insumo)insumo)).thenReturn((Insumo)insumo);
		
		Item insumoCriado = this.itemFactory.getItem(insumo.getCodigo(), insumo.getDescricao(), insumo.getUnidade(), insumo.getValorUnitario(), TipoItem.INSUMO);
		
		verify(this.insumorepository).findByCodigo(insumo.getCodigo());
		verify(this.insumorepository).save((Insumo)insumo);
		
		assertThat(insumoCriado.getTipo()).isEqualTo(TipoItem.INSUMO);
		assertThat(insumoCriado).isInstanceOf(Insumo.class);
	}

	@Test
	public void recuperar_item_do_tipo_composicao() {
		Item composicao = new Composicao(6014, "Descricao", Unidade.UN);
		
		when(this.composicaoRepository.findByCodigo(composicao.getCodigo())).thenReturn(Optional.empty());
		when(this.composicaoRepository.save((Composicao)composicao)).thenReturn((Composicao)composicao);
		
		Item insumoCriado = this.itemFactory.getItem(composicao.getCodigo(), composicao.getDescricao(), composicao.getUnidade(), 0, TipoItem.COMPOSICAO);
	
		verify(this.composicaoRepository).findByCodigo(composicao.getCodigo());
		verify(this.composicaoRepository).save((Composicao)composicao);
		
		assertThat(insumoCriado.getTipo()).isEqualTo(TipoItem.COMPOSICAO);
		assertThat(insumoCriado).isInstanceOf(Composicao.class);
	}
	
	@Test
	public void recuperar_item_do_tipo_insumo_quando_tipo_informa_for_null() {
		Item insumo = new Insumo(6014, "Descricao", Unidade.UN, 1.0d);
		
		when(this.insumorepository.findByCodigo(insumo.getCodigo())).thenReturn(Optional.empty());
		when(this.insumorepository.save((Insumo)insumo)).thenReturn((Insumo)insumo);
		
		Item insumoCriado = this.itemFactory.getItem(insumo.getCodigo(), insumo.getDescricao(), insumo.getUnidade(), 1.0d, null);

		verify(this.insumorepository).findByCodigo(insumo.getCodigo());
		verify(this.insumorepository).save((Insumo)insumo);
		
		assertThat(insumoCriado.getTipo()).isEqualTo(TipoItem.INSUMO);
		assertThat(insumoCriado).isInstanceOf(Insumo.class);
	}
	
	@Test
	public void recuperar_item_null_quando_codigo_menor_ou_igual_a_zero() {
		Item insumo = new Insumo(0, "Descricao", Unidade.UN, 1.0d);
		
		Item insumoCriado = this.itemFactory.getItem(insumo.getCodigo(), insumo.getDescricao(), insumo.getUnidade(), 1.0d, TipoItem.INSUMO);
		
		assertThat(insumoCriado).isNull();
	}
	
}
