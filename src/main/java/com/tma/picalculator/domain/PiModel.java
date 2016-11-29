package com.tma.picalculator.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
/**
 * The PiModel class is a domain model which is representation of the data storage 
 * types required. It describe various domain objects (entities). However, in this 
 * project, we just use Pi domain which included attributes a formal formula such 
 * as: request number (terms); name of formula (formula); error number; time to calculate. 
 * 
 * @author duybu
 *
 */
public class PiModel {
	/*
	 * annotation @NotNull to validate type will not null.
	 *            @Min(0) to validate type will be positive number  
	 */
	@NotNull
	private String formula;
	@NotNull  
	@Min(0)
	@Max(1000000000001L)
	private long terms;
	private String pi;
	private int correctDigit;
	private String javaPi;
	private String time;

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public long getTerms() {
		return terms;
	}

	public void setTerms(long terms) {
		this.terms = terms;
	}

	public String getPi() {
		return pi;
	}

	public void setPi(String pi) {
		this.pi = pi;
	}

	public String getJavaPi() {
		return javaPi;
	}

	public void setJavaPi(String javaPi) {
		this.javaPi = javaPi;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getCorrectDigit() {
		return correctDigit;
	}

	public void setCorrectDigit(int correctDigit) {
		this.correctDigit = correctDigit;
	}
}
