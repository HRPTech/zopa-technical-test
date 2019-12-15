package com.zopa;

import java.math.BigDecimal;
import java.util.List;

import com.zopa.calculator.CompoundInterestCalculator;
import com.zopa.calculator.InterestCalculator;
import com.zopa.exception.ZopaLoanException;
import com.zopa.lender.LenderChooser;
import com.zopa.model.MarketData;
import com.zopa.model.Offer;
import com.zopa.rates.RatesLoader;
import com.zopa.validation.LoanValidator;

public class Main {
	public static void main(String[] args) {
		if (args.length == 2) {
			String marketDataFile = args[0];
			BigDecimal loanAmount = BigDecimal.ZERO;
			try {
				loanAmount = new BigDecimal(args[1]);
			} catch (Exception exception) {
				throw new ZopaLoanException("Invalid input");
			}
			RatesLoader loader = new RatesLoader();
			List<MarketData> marketData = loader.loadFile(marketDataFile);

			LenderChooser offerGenerator = new LenderChooser();
			LoanValidator loanValidator = new LoanValidator();
			InterestCalculator calculator = new CompoundInterestCalculator();
			LoanCalculator loanCalculator = new LoanCalculator(calculator, loanValidator, offerGenerator);
			Offer amount = loanCalculator.calculate(marketData, loanAmount, 36);

			System.out.println("Requested amount: £" + amount.getAmount());
			System.out.println("Rate: " + amount.getRate() + "%");
			System.out.println("Monthly repayment: £" + amount.getMonthlyAmount());
			System.out.println("Total repayment: £" + amount.getTotalRepayment());
		} else {
			System.out.println("Invalid arguments. [market_file] [loan_amount] expected");
		}
	}
}
