package br.com.softplan.avaliacao.exercicio2.service.dto;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.model.Unidade;

/**
 * Classe DTO para armazenar os dados do JSON de entrada
 * 
 * @author leonardo.lira
 *
 */
public class ItemComposicaoDto {

	private static final Logger logger = LoggerFactory.getLogger(ItemComposicaoDto.class);
	
	private static final NumberFormat numberFormat = NumberFormat.getInstance(new Locale("pt", "BR"));
	
	private int codigoComposicao;
	private String descricaoComposicao;
	private Unidade unidadeComposicao;
	private TipoItem tipoItem;
	private int codigoItem;
	private String descricaoItemComposicao;
	private Unidade unidadeItem;
	private String quantidadeComposicao;
	private String valorUnitario;

	public int getCodigoComposicao() {
		return codigoComposicao;
	}

	public void setCodigoComposicao(int codigoComposicao) {
		this.codigoComposicao = codigoComposicao;
	}

	public String getDescricaoComposicao() {
		return descricaoComposicao;
	}

	public void setDescricaoComposicao(String descricaoComposicao) {
		this.descricaoComposicao = descricaoComposicao;
	}

	public Unidade getUnidadeComposicao() {
		return unidadeComposicao;
	}

	public void setUnidadeComposicao(Unidade unidadeComposicao) {
		this.unidadeComposicao = unidadeComposicao;
	}

	public TipoItem getTipoItem() {
		return tipoItem;
	}

	public void setTipoItem(TipoItem tipoItem) {
		this.tipoItem = tipoItem;
	}

	public int getCodigoItem() {
		return codigoItem;
	}

	public void setCodigoItem(int codigoItem) {
		this.codigoItem = codigoItem;
	}

	public String getDescricaoItemComposicao() {
		return descricaoItemComposicao;
	}

	public void setDescricaoItemComposicao(String descricaoItemComposicao) {
		this.descricaoItemComposicao = descricaoItemComposicao;
	}

	public Unidade getUnidadeItem() {
		return unidadeItem;
	}

	public void setUnidadeItem(Unidade unidadeItem) {
		this.unidadeItem = unidadeItem;
	}

	public String getQuantidadeComposicao() {
		return quantidadeComposicao;
	}

	public void setQuantidadeComposicao(String quantidadeComposicao) {
		this.quantidadeComposicao = quantidadeComposicao;
	}

	public String getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(String valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	/**
	 * Converte a string contendo o valor unitário no formato brasileiro para o tipo double
	 * 
	 * @return double com o valor unitário
	 */
	public double getValorUnitarioDouble() {
		if (StringUtils.isEmpty(this.valorUnitario)) {
			return 0d;
		}
		try {
			return numberFormat.parse(this.valorUnitario).doubleValue();
		} catch (ParseException e) {
			logger.error(String.format("Não foi possível converter a string %s em um double válido", this.valorUnitario));
			return 0d;
		}
	}
	
	/**
	 * Converte a string contendo a quantidade do item composição no formato brasileiro para o tipo double
	 * 
	 * @return double com a quantidade
	 */
	public double getQuantidadeComposicaoDouble() {
		try {
			return numberFormat.parse(this.quantidadeComposicao).doubleValue();
		} catch (ParseException e) {
			logger.error(String.format("Não foi possível converter a string %s em um double válido", this.quantidadeComposicao));
			return 0d;
		}
	}
}
