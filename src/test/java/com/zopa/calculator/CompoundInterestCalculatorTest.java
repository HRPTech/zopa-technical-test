package com.zopa.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;

import com.zopa.calculator.CompoundInterestCalculator;

public class CompoundInterestCalculatorTest {

	CompoundInterestCalculator calculator = new CompoundInterestCalculator();
	
	@Test
	public void testCompoundInterestCalculation(){
		assertEquals(BigDecimal.valueOf(1108),calculator.calculate(BigDecimal.valueOf(1000), BigDecimal.valueOf(0.07), 36));		
		assertEquals(BigDecimal.valueOf(1049),calculator.calculate(BigDecimal.valueOf(1100), BigDecimal.valueOf(0.02), 36));
	}
}
