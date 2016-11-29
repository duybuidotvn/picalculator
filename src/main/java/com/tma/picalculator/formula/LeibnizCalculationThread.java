package com.tma.picalculator.formula;

import java.util.concurrent.Callable;
/**
 * LeibnizCalculationThread class implements Callable interface. It used to 
 * calculate the Pi number following: startPoint(beginning number of a series) 
 * and endPoint (ending number of series). The results of pi number will be the 
 * return value of method call()
 * @author duybui
 *
 */
public class LeibnizCalculationThread implements Callable<Double> {
	/*
	 * store beginning number of the series 
	 */
	private long startPoint;
	/*
	 * store ending number of the series 
	 */
	private long endPoint;
	/*
	 * store result of series from startPoint to endPoint
	 */
	private double result = 0d;


	public LeibnizCalculationThread(long startPoint, long endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}
	
	/**
	 * calculate pi number following Leibniz formula
	 * @return result of pi calculation from startPoint to endPoint  
	 */
	public Double call() {
		double d;
		for (long i = this.startPoint; i < this.endPoint; i++) {
			d = 1d/(2*i + 1);
			if ((i & 1) == 0) {
				result += d;
			} else {
				result -= d;
			}
		}
		return result*4d;
	}

}
