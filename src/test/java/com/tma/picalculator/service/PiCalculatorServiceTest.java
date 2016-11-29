package com.tma.picalculator.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.tma.picalculator.BaseAwareDomain;

public class PiCalculatorServiceTest extends BaseAwareDomain{
	
	@Autowired
	PiCalculatorService piService;
	
	@Test
	public void testServiceConfiguration(){
		// Spring IOC container instantiated and prepared PiCalculatorService
		assertNotNull(piService);
	}

}
