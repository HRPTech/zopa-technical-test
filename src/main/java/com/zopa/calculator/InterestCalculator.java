package com.zopa.calculator;

import java.math.BigDecimal;

public interface InterestCalculator {

	BigDecimal calculate(BigDecimal principle, BigDecimal rate, int term);
}
