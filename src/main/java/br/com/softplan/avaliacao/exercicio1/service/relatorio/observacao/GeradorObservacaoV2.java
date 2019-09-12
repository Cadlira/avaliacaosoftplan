package br.com.softplan.avaliacao.exercicio1.service.relatorio.observacao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import br.com.softplan.avaliacao.exercicio1.model.NotaFiscal;
import br.com.softplan.avaliacao.exercicio1.repository.NotaFiscalRepository;

/**
 * Classe concreta contendo a implementação da nova versão do gerador de observações
 * 
 * @author leonardo.lira
 *
 */
public class GeradorObservacaoV2 extends GeradorObservacaoInterface {

	private NotaFiscalRepository notaFiscalRepository;

	private static final NumberFormat moedaFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

	public GeradorObservacaoV2(NotaFiscalRepository notaFiscalRepository) {
		//Utiliza o NUmberFormat para retornar o número no padrão pt-BR
		
		this.notaFiscalRepository = notaFiscalRepository;
		DecimalFormatSymbols decimalFormatSymbols = ((DecimalFormat) moedaFormat).getDecimalFormatSymbols();
		decimalFormatSymbols.setCurrencySymbol("");
		((DecimalFormat) moedaFormat).setDecimalFormatSymbols(decimalFormatSymbols);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected String retornaCodigos(List lista) {
		int ultimaPosicao = lista.size() - 1;
		String ultimoDelimitador = " e ";
		if (ultimaPosicao < 1) {
			ultimoDelimitador = "";
		}
		
		// retorna uma nota fiscal do repositório de acordo com o número informado
		Optional<NotaFiscal> ultimaNotaOpt = this.notaFiscalRepository.findByNumero((Integer)lista.get(ultimaPosicao));
		
		String textoFinal = ". Total =%s";
		
		/*
		 * objeto utilizado para acumular o valor total das notas,
		 * visto que, dentro dos lambdas não seria possível modificar
		 * uma variável externa
		 */
		AtomicInteger valorTotal = new AtomicInteger(Float.floatToIntBits(0.0f));
		
		
		// pega a última nota para montar o texto final
		if (ultimaNotaOpt.isPresent()) {
			textoFinal = String.format("%d cujo valor é R$%s", ultimaNotaOpt.get().getNumero(), moedaFormat.format(ultimaNotaOpt.get().getValor())) + textoFinal;
			valorTotal.set((Float.floatToIntBits(Float.intBitsToFloat(valorTotal.get()) + ultimaNotaOpt.get().getValor())));
		}
				
		/*
		 * Lê nota por nota do repositório adicionando o texto de cada nota
		 * e acumulando o valor para exibir no final
		 */
		return String.join(ultimoDelimitador,
				String.join(", ", (String) lista.subList(0, ultimaPosicao).stream().map(numeroNota -> {
					Optional<NotaFiscal> notaOpt = this.notaFiscalRepository.findByNumero((Integer)numeroNota);
					if (notaOpt.isPresent()) {
						NotaFiscal nota = notaOpt.get();
						valorTotal.set(Float.floatToIntBits(Float.intBitsToFloat(valorTotal.get()) + nota.getValor()));
						return String.format("%d cujo valor é R$%s", numeroNota, moedaFormat.format(nota.getValor()));
					}
					return "";
				}).collect(Collectors.joining(", "))), String.format(textoFinal, moedaFormat.format(Float.intBitsToFloat(valorTotal.get()))));
	}

}
