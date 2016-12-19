package com.duyb.pi.web.service.impl;

import org.springframework.stereotype.Service;

import com.duyb.pi.approximation.PiApproximation;
import com.duyb.pi.approximation.PiApproximationEnum;
import com.duyb.pi.formula.Formula;
import com.duyb.pi.web.domain.PiModel;
import com.duyb.web.pi.service.PiCalculatorService;
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
	public String getPi(String framework, String formula, long terms) throws Exception {
		PiApproximation piApproximation = null;
		Formula f = null;
		
		if(PiApproximationEnum.SINGLE_THREAD.name().equals(framework)) {
			piApproximation = PiApproximationEnum.SINGLE_THREAD;
		} else if(PiApproximationEnum.THREAD_POOL.name().equals(framework)) {
			piApproximation = PiApproximationEnum.THREAD_POOL;
		} else if(PiApproximationEnum.FORK_JOIN.name().equals(framework)) {
			piApproximation = PiApproximationEnum.FORK_JOIN;
		}
		
		if(Formula.LEIBNIZ.name().equals(formula)) {
			f = Formula.LEIBNIZ;
		} else if(Formula.NILAKANTHA.name().equals(formula)) {
			f = Formula.NILAKANTHA;
		} 
		
		if(piApproximation == null || f == null) {
			throw new IllegalArgumentException("Invalid framework or formula");
		}
		
		return String.valueOf(piApproximation.approximate(terms, f));
	}
	/**
	 * Finds the difference between calculated PI and defined PI
	 * @param: piResult result from method getPi(String formula, long terms)
	 * @return: number of correct digits between piResult and Java Pi
	 */
	@Override
	public int getError(String piResult) {
		String Pi = "3.14159265358979323846";
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
		String piResult = getPi(piModel.getFramework(), piModel.getFormula(), piModel.getTerms());
		long end =  System.currentTimeMillis();
		String time = String.valueOf((end - start)+" ms");
		String javaPi = "3.14159265358979323846";
		int digit = getError(piResult);

		piModel.setPi(piResult);
		piModel.setTime(time);
		piModel.setCorrectDigit(digit);
		piModel.setJavaPi(javaPi);
	}

}
