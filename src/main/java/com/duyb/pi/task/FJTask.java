package com.duyb.pi.task;

import java.util.concurrent.RecursiveTask;

import com.duyb.pi.formula.Formula;

public class FJTask extends RecursiveTask<Double> {

	private static final long serialVersionUID = 1L;
	
	private final Formula f;
	private final long begin;
	private final long end;
	private final long maxLength;
	
	public FJTask(long begin, long end, Formula f, long maxLength) {
		this.f = f;
		this.begin = begin;
		this.end = end;
		this.maxLength = maxLength;
	}

	@Override
	protected Double compute() {
		long length = end - begin;
		if(length <= maxLength) {
			return computeDirectly();
		} else {
			long middle = begin + length / 2;
			FJTask left = new FJTask(begin, middle, f, maxLength);
			left.fork();
			FJTask right = new FJTask(middle, end, f, maxLength);
			return right.compute() + left.join();
		}
	}
	
	private double computeDirectly() {
		return new Task(begin, end).compute(f);
	}

}
