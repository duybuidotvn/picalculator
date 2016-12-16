package com.duyb.pi.approximation;

import com.duyb.pi.formula.Formula;
import com.duyb.pi.task.Task;

class PiApproximationSingleThread implements PiApproximation {
	public double approximate(long n, Formula f) {
		return new Task(0, n).compute(f);
	}
}
