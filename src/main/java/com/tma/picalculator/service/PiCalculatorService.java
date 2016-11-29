package com.tma.picalculator.service;

import com.tma.picalculator.domain.PiModel;

/**
 * @author duybui
 *
 */
public interface PiCalculatorService {
	/**
	 * @return Finds PI base on the formula and number of terms.
     * Returns {@code String} of PI number.
	 */
	String getPi(String formula, long terms) throws Exception;
	
	/**
	 * @return Finds the difference between calculated PI and defined PI
     * Returns {@code String}.
	 */
	int getError(String piResult);
	/**
	 * handle piModel
	 * @param piModel
	 * @throws Exception
	 */
	void getPi(PiModel piModel) throws Exception;
}
