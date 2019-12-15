package com.zopa.model;

import java.math.BigDecimal;

public class MarketData {
	private final String lender;
	private final BigDecimal rate;
	private final BigDecimal amountAvailable;

	public MarketData(final String lender, final BigDecimal rate, final BigDecimal amountAvailable) {
		super();
		this.lender = lender;
		this.rate = rate;
		this.amountAvailable = amountAvailable;
	}

	public final String getLender() {
		return lender;
	}

	public final BigDecimal getRate() {
		return rate;
	}

	public final BigDecimal getAmountAvailable() {
		return amountAvailable;
	}

	@Override
	public String toString() {
		return "MarketData [lender=" + lender + ", rate=" + rate + ", amountAvailable=" + amountAvailable + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amountAvailable == null) ? 0 : amountAvailable.hashCode());
		result = prime * result + ((lender == null) ? 0 : lender.hashCode());
		result = prime * result + ((rate == null) ? 0 : rate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MarketData other = (MarketData) obj;
		if (amountAvailable == null) {
			if (other.amountAvailable != null)
				return false;
		} else if (!amountAvailable.equals(other.amountAvailable))
			return false;
		if (lender == null) {
			if (other.lender != null)
				return false;
		} else if (!lender.equals(other.lender))
			return false;
		if (rate == null) {
			if (other.rate != null)
				return false;
		} else if (!rate.equals(other.rate))
			return false;
		return true;
	}
	
}
