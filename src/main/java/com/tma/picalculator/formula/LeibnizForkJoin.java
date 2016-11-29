package com.tma.picalculator.formula;

public class LeibnizForkJoin implements PiCalculatorStrategy{
	public String calculate(long terms) throws Exception {
		double result = 0d;

		for (long i = 0; i < terms; i++) {
			double temp = 1d/(2*i + 1);
			if ((i & 1) == 0) {
				result += temp;
			} else {
				result -= temp;
			}
		}
		result*=4;
		return String.valueOf(result);
	}
}
