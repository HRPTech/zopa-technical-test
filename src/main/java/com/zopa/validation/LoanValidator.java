package com.zopa.validation;

import java.math.BigDecimal;

import com.zopa.exception.ZopaLoanException;

public class LoanValidator {

	public void validateAmount(final BigDecimal amount) {
		if (amount.compareTo(BigDecimal.valueOf(1000)) < 0 || amount.compareTo(BigDecimal.valueOf(15000)) > 0) {
			throw new ZopaLoanException("Loan request amount £" + amount + " is below £1,000 or above £15,000");
		}
		if (amount.doubleValue() % 100 != 0) {
			throw new ZopaLoanException("Loan request amount is not in increments of £100");
		}
	}
	
}
