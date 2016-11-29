package com.tma.picalculator.coretest;

import java.util.ArrayList;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.tma.picalculator.common.Constant;
import com.tma.picalculator.formula.LeibnizPiCalculator;

import junit.framework.TestCase;

public class LeibnizPiTest extends TestCase {

	private List<Long> listTestCase;
	private LeibnizPiCalculator piCal = new LeibnizPiCalculator();

	public LeibnizPiTest() {
		if (listTestCase == null) {
			listTestCase = new ArrayList<Long>();
		}
		for (int i = 0; i < Constant.NO_TESTCASES; i++) {
			listTestCase.add(1000L);
			listTestCase.add(10000L);
			listTestCase.add(100000L);
			listTestCase.add(1000000L);
			listTestCase.add(10000000L);
			listTestCase.add(100000000L);
			//listTestCase.add(1000000000L);
			//listTestCase.add(10000000000L);
			// listTestCase.add(1000000000000L);
		}
	}

	public void testLebinizFormular() throws InterruptedException,
			ExecutionException {
		System.out.println("Java PI: " + Math.PI);
		System.out.println("------------Result------------");
		for (int i = 0; i < Constant.NO_TESTCASES; i++) {
			Long numberRequest = listTestCase.get(i);
			boolean validate = (numberRequest >= 0);

			System.out.println("Request Number is " + numberRequest);
			if (validate == true) {
				long start = System.currentTimeMillis();
				String result = piCal.calculate(numberRequest);
				System.out.println("Result is: " + result);
				long end = System.currentTimeMillis();
				System.out.println("Run time seconds: " + (float) (end - start)
						/ 1000);
				System.out.println("----------------------");
			} else {
				System.out.println("false");
				System.out.println("Reason: Should be positive number");
				System.out.println("----------------------");
				assertEquals(true, validate);
			}
		}
	}
}
