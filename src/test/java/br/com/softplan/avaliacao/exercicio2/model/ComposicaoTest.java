package br.com.softplan.avaliacao.exercicio2.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.avaliacao.exercicio2.Util.DoubleUtil;

@RunWith(SpringRunner.class)
public class ComposicaoTest {

	private Item composicaoComItensNull;
	private Item composicaoComItensVazio;

	private Item insumo1;
	private Item insumo2;
	private Item insumo3;

	private ItemComposicao item1;
	private ItemComposicao item2;
	private ItemComposicao item3;

	private Item composicaoComItens;

	@Before
	public void setUp() {
		this.composicaoComItensNull = new Composicao(98561,
				"IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018",
				Unidade.M2, null);
		this.composicaoComItensVazio = new Composicao(98561,
				"IMPERMEABILIZAÇÃO DE PAREDES COM ARGAMASSA DE CIMENTO E AREIA, COM ADITIVO IMPERMEABILIZANTE, E = 2CM. AF_06/2018",
				Unidade.M2);

		this.insumo1 = new Insumo(88248, "AUXILIAR DE ENCANADOR OU BOMBEIRO HIDRÁULICO COM ENCARGOS COMPLEMENTARES",
				Unidade.H, 15.19);
		this.insumo2 = new Insumo(88267, "ENCANADOR OU BOMBEIRO HIDRÁULICO COM ENCARGOS COMPLEMENTARES", Unidade.H,
				20.33);
		this.insumo3 = new Insumo(6014,
				"REGISTRO GAVETA COM ACABAMENTO E CANOPLA CROMADOS, SIMPLES, BITOLA 1 1/4  (REF 1509)", Unidade.UN,
				100.41);

		this.item1 = new ItemComposicao(this.insumo1, 0.7890000);
		this.item2 = new ItemComposicao(this.insumo2, 0.7890000);
		this.item3 = new ItemComposicao(this.insumo3, 1);

		this.composicaoComItens = new Composicao(94793,
				"REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016",
				Unidade.UN, Stream.of(this.item1, this.item2, this.item3).collect(Collectors.toSet()));
	}

	@Test
	public void validar_tipoItem_do_objeto_composicao() {
		assertThat(composicaoComItensNull.getTipo()).isEqualTo(TipoItem.COMPOSICAO);
	}

	@Test
	public void validar_get_valorUnitario_itens_null() {
		assertThat(composicaoComItensNull.getValorUnitario()).isEqualTo(0d);
	}

	@Test
	public void validar_get_valorUnitario_itens_vazio() {
		assertThat(composicaoComItensVazio.getValorUnitario()).isEqualTo(0d);
	}

	@Test
	public void adicionar_novo_itemComposicao() {
		assertThat(((Composicao) composicaoComItensVazio).getItens().size()).isEqualTo(0);

		((Composicao) composicaoComItensVazio).addItem(this.item1);

		assertThat(((Composicao) composicaoComItensVazio).getItens().size()).isEqualTo(1);
	}

	@Test
	public void adicionar_novo_itemComposicao_null() {
		assertThat(((Composicao) composicaoComItensVazio).getItens().size()).isEqualTo(0);

		((Composicao) composicaoComItensVazio).addItem(null);

		assertThat(((Composicao) composicaoComItensVazio).getItens().size()).isEqualTo(0);
	}

	@Test
	public void adicionar_novo_itemComposicao_em_composicao_com_itens_null() {
		((Composicao) composicaoComItensNull).addItem(this.item1);

		assertThat(((Composicao) composicaoComItensNull).getItens().size()).isEqualTo(1);
	}

	@Test
	public void calcular_valorUnitario_composicao() {
		assertThat(this.composicaoComItens.getValorUnitario())
				.isEqualTo(DoubleUtil.round(this.item1.getValor() + item2.getValor() + item3.getValor(), 2));
		assertThat(this.composicaoComItens.getValorUnitario())
				.isEqualTo(DoubleUtil.round((15.19 * 0.7890000) + (20.33 * 0.7890000) + (100.41 * 1), 2));
	}

	@Test
	public void calcular_valorUnitario_composicao_ignorando_referencia_ciclica() {
		((Composicao) this.composicaoComItens).addItem(new ItemComposicao(composicaoComItens, 2));
		assertThat(this.composicaoComItens.getValorUnitario())
				.isEqualTo(DoubleUtil.round(this.item1.getValor() + item2.getValor() + item3.getValor(), 2));
		assertThat(this.composicaoComItens.getValorUnitario())
				.isEqualTo(DoubleUtil.round((15.19 * 0.7890000) + (20.33 * 0.7890000) + (100.41 * 1), 2));
	}

	@Test
	public void calcular_valorUnitario_composicao_com_composicao_nos_itens() {
		((Composicao) this.composicaoComItensVazio).addItem(new ItemComposicao(this.insumo1, 2));
		((Composicao) this.composicaoComItensVazio).addItem(new ItemComposicao(this.insumo2, 3));
		((Composicao) this.composicaoComItens).addItem(new ItemComposicao(composicaoComItensVazio, 2));

		assertThat(this.composicaoComItens.getValorUnitario()).isEqualTo(DoubleUtil.round(this.item1.getValor() + item2.getValor()
				+ item3.getValor() + this.composicaoComItensVazio.getValorUnitario() * 2, 2));
		assertThat(this.composicaoComItens.getValorUnitario())
				.isEqualTo(DoubleUtil.round((15.19 * 0.7890000) + (20.33 * 0.7890000) + (100.41 * 1) + (((15.19*2)+(20.33*3))*2), 2));
	}
	
	@Test
	public void exibir_toString_da_composicao_com_itens() {
		StringBuilder valorVerificacao = new StringBuilder();
		
		NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
		
		valorVerificacao.append(94793).append("   ")
		.append("REGISTRO DE GAVETA BRUTO, LATÃO, ROSCÁVEL, 1 1/4, COM ACABAMENTO E CANOPLA CROMADOS, INSTALADO EM RESERVAÇÃO DE ÁGUA DE EDIFICAÇÃO QUE POSSUA RESERVATÓRIO DE FIBRA/FIBROCIMENTO FORNECIMENTO E INSTALAÇÃO. AF_06/2016").append("   ")
		.append(Unidade.UN.name()).append("   ")
		.append(numberFormat.format(DoubleUtil.round((15.19 * 0.7890000) + (20.33 * 0.7890000) + (100.41 * 1), 2)));
		
		assertThat(this.composicaoComItens.toString()).isEqualTo(valorVerificacao.toString());
		
	}
}
