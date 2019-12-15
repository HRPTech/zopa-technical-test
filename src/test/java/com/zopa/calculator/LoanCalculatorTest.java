package com.zopa.calculator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zopa.LoanCalculator;
import com.zopa.lender.LenderChooser;
import com.zopa.model.MarketData;
import com.zopa.model.Offer;
import com.zopa.validation.LoanValidator;

public class LoanCalculatorTest {

	private InterestCalculator calculator = new CompoundInterestCalculator();
	private LoanValidator loanValidator = new LoanValidator();
	private LenderChooser lenderChooser = new LenderChooser();

	private LoanCalculator loanCalculator = new LoanCalculator(calculator, loanValidator, lenderChooser);

	@Test
	public void testCalculateLoan() {
		List<MarketData> marketData = new ArrayList<>();
		MarketData data = new MarketData("test", BigDecimal.valueOf(0.1), BigDecimal.valueOf(1000));
		marketData.add(data);

		Offer offer = loanCalculator.calculate(marketData, BigDecimal.valueOf(1000), 36);
		
		assertEquals(BigDecimal.valueOf(1000),offer.getAmount());
		assertEquals(BigDecimal.valueOf(33.67),offer.getMonthlyAmount());
		assertEquals(BigDecimal.valueOf(10),offer.getRate());
		assertEquals(BigDecimal.valueOf(1212),offer.getTotalRepayment());
	}
}
