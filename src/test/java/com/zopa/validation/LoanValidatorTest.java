package com.zopa.validation;

import java.math.BigDecimal;

import org.junit.Test;

import com.zopa.exception.ZopaLoanException;
import com.zopa.validation.LoanValidator;

public class LoanValidatorTest {
	private LoanValidator validator = new LoanValidator();

	@Test(expected = ZopaLoanException.class)
	public void validateAmountZeroTest() {
		validator.validateAmount(BigDecimal.ZERO);
	}

	@Test(expected = ZopaLoanException.class)
	public void validateAmountTest() {
		validator.validateAmount(BigDecimal.valueOf(999999999));
	}
	
	@Test(expected = ZopaLoanException.class)
	public void validateAmountInNon100IncrementsTest() {
		validator.validateAmount(BigDecimal.valueOf(1050));
	}
}
