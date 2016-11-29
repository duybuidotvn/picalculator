package com.tma.picalculator.common;

/**
 * class contains default number
 * @author duybui
 *
 */
public class Constant {
	/*
	 * Stored the name using for initialize singleton pattern.
	 */
	public static final String LEIBNIZ_PI_FORMULA = "LeibnizPiFormula";
	/*
	 * Stored the name using for initialize singleton pattern.
	 */
	public static final String LEIBNIZ_FORK_JOIN = "LeibnizForkJoin";	
	/*
	 * Stored the number of test case.
	 */
	public static final Integer NO_TESTCASES = 8;
	/*
	 * Optimize number of threads = number of CPUs + 1.
	 */
	public static final Integer NO_MAXTHREADS = Runtime.getRuntime().availableProcessors()+1;
	/*
	 * The mile stone to identify the request number greater.
	 */
	public static final Long BOUND_TERM = 8000000000L;
	/*
	 * The mile stone to identify the default range running per thread. 
	 */
	public static final Long RANGE = 1000000000L;
}
