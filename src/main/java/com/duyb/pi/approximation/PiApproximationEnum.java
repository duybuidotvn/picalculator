package com.duyb.pi.approximation;

import com.duyb.pi.formula.Formula;

public enum PiApproximationEnum implements PiApproximation {
	
	SINGLE_THREAD {
		@Override
		public double approximate(long n, Formula f) {
			return new PiApproximationSingleThread().approximate(n, f);
		}
	},
	
	THREAD_POOL {
		@Override
		public double approximate(long n, Formula f) {
			return new PiApproximationThreadPool().approximate(n, f);
		}
	},
	
	FORK_JOIN {
		@Override
		public double approximate(long n, Formula f) {
			return new PiApproximationForkJoin().approximate(n, f);
		}
	};
}
