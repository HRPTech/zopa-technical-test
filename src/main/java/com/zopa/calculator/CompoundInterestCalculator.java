package com.zopa.calculator;

import java.math.BigDecimal;

import com.zopa.Constants;

public class CompoundInterestCalculator implements InterestCalculator {

	public BigDecimal calculate(final BigDecimal principle, final BigDecimal rate, final int term) {
		return principle.multiply(BigDecimal.valueOf(0.9970221).add(rate.divide(BigDecimal.valueOf(12), Constants.mc)).pow(term))
				.round(Constants.mc);
	}
}
