package com.duyb.pi.task;

import com.duyb.pi.formula.Formula;

public class Task {
	private final long begin;
	private final long end;
	
	public Task(long begin, long end) {
		this.begin = begin;
		this.end = end;
	}
	
	public double compute(Formula f) {
		double result = 0;
		for(long i = begin; i < end; i++) {
			result += f.at(i);
		}
		
		return result;
	}
}
