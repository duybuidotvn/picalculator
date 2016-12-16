package com.duyb.pi.approximation;

import java.util.concurrent.ForkJoinPool;

import com.duyb.pi.formula.Formula;
import com.duyb.pi.task.FJTask;

class PiApproximationForkJoin implements PiApproximation {
	@Override
	public double approximate(long n, Formula f) {
		long maxLength = n / Runtime.getRuntime().availableProcessors();
		if(maxLength == 0) {
			maxLength = 1;
		}
		ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
		return forkJoinPool.invoke(new FJTask(0, n, f, maxLength));
	}
}
