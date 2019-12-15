package com.zopa;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.zopa.calculator.InterestCalculator;
import com.zopa.lender.LenderChooser;
import com.zopa.model.MarketData;
import com.zopa.model.Offer;
import com.zopa.validation.LoanValidator;

public class LoanCalculator {
	private static final Comparator<MarketData> comparator = Comparator.comparing(MarketData::getRate,
			Comparator.nullsFirst(BigDecimal::compareTo));

	private final InterestCalculator calculator;
	private final LoanValidator loanValidator;
	private final LenderChooser lenderChooser;

	public LoanCalculator(final InterestCalculator calculator, final LoanValidator loanValidator,
			final LenderChooser lenderChooser) {
		super();
		this.calculator = calculator;
		this.loanValidator = loanValidator;
		this.lenderChooser = lenderChooser;
	}

	public Offer calculate(final List<MarketData> marketData, final BigDecimal amount, final int term) {
		// determine if loan request amount is valid
		loanValidator.validateAmount(amount);

		List<MarketData> offers = lenderChooser
				.getOffers(marketData.stream().sorted(comparator).collect(Collectors.toList()), amount);

		BigDecimal totalRepayment = totalRepaymentCalculator(offers, term);
		BigDecimal rate = blendedRateCalculator(offers).round(Constants.rateContext);

		return new Offer(amount, rate, totalRepayment.divide(BigDecimal.valueOf(term), Constants.mc), totalRepayment);
	}

	/**
	 * Calculate the total repayment
	 * 
	 * @param offers
	 * @param term
	 * @return
	 */
	private BigDecimal totalRepaymentCalculator(final List<MarketData> offers, final int term) {
		BigDecimal total = offers.stream()
				.map(offer -> calculator.calculate(offer.getAmountAvailable(), offer.getRate(), term))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		return total;
	}

	/**
	 * Calculate the blended rate across different lenders
	 * 
	 * @param offers
	 * @return
	 */
	private BigDecimal blendedRateCalculator(final List<MarketData> offers) {
		BigDecimal totalByRate = offers.stream().map(offer -> offer.getAmountAvailable().multiply(offer.getRate()))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		BigDecimal totalSum = offers.stream().map(offer -> offer.getAmountAvailable()).reduce(BigDecimal.ZERO,
				BigDecimal::add);
		return totalByRate.divide(totalSum, Constants.rateContext).multiply(BigDecimal.valueOf(100));
	}

}
