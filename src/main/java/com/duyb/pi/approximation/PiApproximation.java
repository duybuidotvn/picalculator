package com.duyb.pi.approximation;

import com.duyb.pi.formula.Formula;

public interface PiApproximation {
	double approximate(long n, Formula f);
}
