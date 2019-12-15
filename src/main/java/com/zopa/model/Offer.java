package com.zopa.model;

import java.math.BigDecimal;

public class Offer {
	private final BigDecimal amount;
	private final BigDecimal rate;
	private final BigDecimal monthlyAmount;
	private final BigDecimal totalRepayment;

	public Offer(final BigDecimal amount, final BigDecimal rate, final BigDecimal monthlyAmount,
			final BigDecimal totalRepayment) {
		super();
		this.amount = amount;
		this.rate = rate;
		this.monthlyAmount = monthlyAmount;
		this.totalRepayment = totalRepayment;
	}

	public final BigDecimal getAmount() {
		return amount;
	}

	public final BigDecimal getRate() {
		return rate;
	}

	public final BigDecimal getMonthlyAmount() {
		return monthlyAmount;
	}

	public final BigDecimal getTotalRepayment() {
		return totalRepayment;
	}

	@Override
	public String toString() {
		return "Offer [amount=" + amount + ", rate=" + rate + ", monthlyAmount=" + monthlyAmount + ", totalRepayment="
				+ totalRepayment + "]";
	}

}
