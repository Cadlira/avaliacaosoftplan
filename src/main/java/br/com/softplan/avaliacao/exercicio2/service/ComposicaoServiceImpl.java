package br.com.softplan.avaliacao.exercicio2.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.softplan.avaliacao.exercicio2.factory.ItemFactory;
import br.com.softplan.avaliacao.exercicio2.model.Composicao;
import br.com.softplan.avaliacao.exercicio2.model.ItemComposicao;
import br.com.softplan.avaliacao.exercicio2.model.TipoItem;
import br.com.softplan.avaliacao.exercicio2.repository.ComposicaoRepository;
import br.com.softplan.avaliacao.exercicio2.service.dto.ItemComposicaoDto;

/**
 * Implementação da regra de negócio das composições
 * 
 * @author leonardo.lira
 *
 */
@Service
public class ComposicaoServiceImpl implements ComposicaoService {

	private static final Logger logger = LoggerFactory.getLogger(ComposicaoServiceImpl.class);

	@Autowired
	private ComposicaoRepository composicaoRepository;

	@Autowired
	private ItemFactory ItemFactory;

	@Override
	public List<Composicao> carregarComposicoes(String jsonComposicoes) {

		List<ItemComposicaoDto> itensComposicaoDto = carregarItensComposicaoDto(jsonComposicoes);

		return carregarComposicoes(itensComposicaoDto);
	}

	
	/*
	 * Transforma os itensComposicaoDto em composições
	 * 
	 * carrega a composição do repository utlizando o factory e depois adiciona o item existente na mesma,
	 * salvando-a em seguida no repositorio novamente.
	 * 
	 * estes dados são carreegados em um SET com o objetivo de não se repetir, convertendo posteriormente
	 * em uma lista
	 * 
	 */
	private List<Composicao> carregarComposicoes(List<ItemComposicaoDto> itensComposicaoDto) {

		Set<Composicao> composicoesSet = itensComposicaoDto.stream().map(dto -> {
			Composicao composicaoRecuperada = (Composicao) this.ItemFactory.getItem(dto.getCodigoComposicao(),
					dto.getDescricaoComposicao(), dto.getUnidadeComposicao(), 0d, TipoItem.COMPOSICAO);
			ItemComposicao itemComposicaoRecuperado = new ItemComposicao(
					this.ItemFactory.getItem(dto.getCodigoItem(), dto.getDescricaoItemComposicao(),
							dto.getUnidadeItem(), dto.getValorUnitarioDouble(), dto.getTipoItem()),
					dto.getQuantidadeComposicaoDouble());
			composicaoRecuperada.addItem(itemComposicaoRecuperado);

			this.composicaoRepository.save(composicaoRecuperada);

			return composicaoRecuperada;
		}).collect(Collectors.toSet());

		return new ArrayList<Composicao>(composicoesSet);
	}

	/*
	 * Carrega uma lista de itemComposicaoDto utilizando o jackson para fazer a comversão
	 */
	private List<ItemComposicaoDto> carregarItensComposicaoDto(String jsonComposicoes) {
		ObjectMapper mapper = new ObjectMapper();

		try {
			return mapper.readValue(jsonComposicoes, new TypeReference<List<ItemComposicaoDto>>() {
			});
		} catch (IOException e) {
			logger.error("Ocorreu um erro ao deserializar o json: ", e);

		}

		return null;
	}

}
