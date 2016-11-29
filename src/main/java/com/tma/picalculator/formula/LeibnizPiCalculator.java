package com.tma.picalculator.formula;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import com.tma.picalculator.common.Constant;

/**
 * Applying strategy pattern design, class LeibnizPicalculator implements interface 
 * PiCalculatorStrategy in which re-write method calculate() and using method getList()
 * to support for calculation smoothly.
 * 
 * @author duybui
 *
 */
public class LeibnizPiCalculator implements PiCalculatorStrategy {

	/**
	 * The algorithms to implement this method following step: get a list of milestone by dividing
	 * the big number to smaller range; put each of range in the list to executor services; get result
	 * and put to a set of future; and finally being get the result from the future list. That is the
	 * the Pi number.
	 * 
	 * @param: terms being the request number from user interface.
	 * @throws: InterruptedException, ExecutionException
	 * @return: the value of pi in String type. 
	 */

	public String calculate(long terms) throws InterruptedException, ExecutionException {
		//store the number of threads from package Constant. 
		int numberOfThread = Constant.NO_MAXTHREADS;
		/* 
		 * declares listData as a List to store a list of milestone after calling 
		 * the getPi() methods. 
		 */
		List<Long> listData = getList(terms, numberOfThread);
		// declares a pool executor with the number of core threads. 
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThread);
		// declare a set future to store return value in each of thread.
		Set<Future<Double>> set = new HashSet<Future<Double>>();

		for (int i = 0; i < listData.size() - 1; i++) {
			// declare callable  
			LeibnizCalculationThread callable = new LeibnizCalculationThread(listData.get(i), listData.get(i + 1));
			//submit callable to thread
			Future<Double> temp = executor.submit(callable);
			// add return result to future set
			set.add(temp);
		}
		
		double result = 0D;
		/* 
		 * get the result from summing the value of iterators in future set.  
		 */
		for (Future<Double> future : set) {
			result += future.get();
		}
		// terminate all threads in executor service. 
		executor.shutdown();
		return String.valueOf(result);
	}

	/**
	 * @param terms request number from user interface.
	 * @param numberOfThread used to declare the size of list
	 * @return the List of milestone after processing. 
	 */
	private List<Long> getList(long terms, int numberOfThread) {
		long temp = terms / numberOfThread;
		List<Long> listData = new ArrayList<Long>();
		listData.add((long) 0);
		// to ensure the terms greater than the default number of thread
		if (temp > 0) {
			// to ensure the terms greater than the default limit number running per thread.
			if (terms>Constant.BOUND_TERM) {
				temp = Constant.RANGE;
			}
			for (long i = temp; i < terms; i += temp) {
				if ((i % temp) == 0) {
					listData.add(i);
				}
			}
		}
		listData.add((long) terms+1);
		return listData;
	}
}
