package com.tma.picalculator.service.impl;

import org.springframework.stereotype.Service;

import com.tma.picalculator.common.Constant;
import com.tma.picalculator.domain.PiModel;
import com.tma.picalculator.formula.LeibnizPiCalculator;
import com.tma.picalculator.formula.LeibnizForkJoin;
import com.tma.picalculator.formula.PiCalculatorStrategy;
import com.tma.picalculator.service.PiCalculatorService;
/**
 * PiCalculatorServiceImpl class implements the PiCalculatorService is a part of the Services Layer.
 * The service layer in spring execute all the business logic. Therefore, after controller get request from
 * user interface and store it to PiModel, PiCalculatorServiceImpl will handle request by some methods such
 * as: getPi() and getError()
 * 
 * @author duybui
 *
 */
@Service
public class PiCalculatorServiceImpl implements PiCalculatorService {

	/**
	 * Using singleton pattern design and call method calculate()
	 * Finds PI base on the formula and number of terms.
	 * @param: formula being the name of Formula type
	 * @param: terms request number from user interface
	 * @throw: Exception
	 * @return: get the value result from method calculate()
	 */
	public String getPi(String formula, long terms) throws Exception {
		PiCalculatorStrategy piStrategy = null;
		
		if (Constant.LEIBNIZ_PI_FORMULA.equals(formula)) {
			piStrategy = new LeibnizPiCalculator();
		}
		
		if (Constant.LEIBNIZ_FORK_JOIN.equals(formula)) {
			piStrategy = new LeibnizForkJoin();
		}

		return piStrategy.calculate(terms);
	}
	/**
	 * Finds the difference between calculated PI and defined PI
	 * @param: piResult result from method getPi(String formula, long terms)
	 * @return: number of correct digits between piResult and Java Pi
	 */
	@Override
	public int getError(String piResult) {
		String Pi = String.valueOf(Math.PI);
		int cnt = 0;
		for(int i = 0 ; i<piResult.length() && i<Pi.length(); i++){
			if(piResult.charAt(i) != Pi.charAt(i))
				break;
			if(piResult.charAt(i) != '.')
				cnt++;
		}
		return cnt;
	}

	@Override
	public void getPi(PiModel piModel) throws Exception {
		long start = System.currentTimeMillis();
		String piResult = getPi(piModel.getFormula(), piModel.getTerms());
		long end =  System.currentTimeMillis();
		String time = String.valueOf((end - start)+" ms");
		String javaPi = String.valueOf(Math.PI);
		int digit = getError(piResult);

		piModel.setPi(piResult);
		piModel.setTime(time);
		piModel.setCorrectDigit(digit);
		piModel.setJavaPi(javaPi);
	}

}
