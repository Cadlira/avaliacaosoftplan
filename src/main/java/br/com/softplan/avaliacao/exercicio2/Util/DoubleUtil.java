package br.com.softplan.avaliacao.exercicio2.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Classe utilitária para manipulação de doubles
 * 
 * @author leonardo.lira
 *
 */
public class DoubleUtil {
	
	/**
	 * Arredonda o double com as casas decimais
	 * 
	 * @param value double
	 * @param places casas decimais
	 * @return
	 */
	public static double round(double value, int places) {
		if (places < 0) {
			throw new IllegalArgumentException();
		}
		BigDecimal bd = BigDecimal.valueOf(value);
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
