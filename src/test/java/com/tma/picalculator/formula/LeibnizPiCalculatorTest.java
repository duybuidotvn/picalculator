package com.tma.picalculator.formula;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import junit.framework.TestCase;

import org.junit.Test;

import com.tma.picalculator.common.Constant;

public class LeibnizPiCalculatorTest extends TestCase {

	private LeibnizPiCalculator piCal = new LeibnizPiCalculator();

	@Test
	public void testBasicTerms() throws InterruptedException, ExecutionException {
		List<Long> listData = new ArrayList<Long>();
		for (int i=0; i<Constant.NO_TESTCASES; i++){;
			listData.add(1000L);
			listData.add(10000L);
			listData.add(100000L);
			listData.add(1000000L);
			listData.add(10000000L);
			listData.add(100000000L);
			//listData.add(1000000000L);
			//listData.add(10000000000L);
			//listData.add(100000000000L);

		}
		for (int i=0; i<Constant.NO_TESTCASES; i++){
			System.out.println("Request No: " + listData.get(i) + ". Result: " + piCal.calculate(listData.get(i)));
		}
	}

}
