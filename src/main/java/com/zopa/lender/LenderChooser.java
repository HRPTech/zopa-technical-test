package com.zopa.lender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.zopa.exception.ZopaLoanException;
import com.zopa.model.MarketData;

public class LenderChooser {

	/**
	 * Get the best offers available for the given amount
	 * 
	 * @param offers
	 * @param amount
	 * @return
	 */
	public List<MarketData> getOffers(final List<MarketData> offers, final BigDecimal amount) {
		List<MarketData> offersApplied = new ArrayList<>();

		if (offers != null) {
			//check if the requested loan amount is above the available total lend amount available
			if (amount.compareTo(offers.stream().map(MarketData::getAmountAvailable).reduce(BigDecimal.ZERO,
					BigDecimal::add)) == 1) {
				throw new ZopaLoanException("No quote can be provided at this time due to insufficient amount");
			}
			BigDecimal tempAmount = amount;

			for (MarketData offer : offers) {
				if (tempAmount.compareTo(offer.getAmountAvailable()) < 1) {
					offersApplied.add(new MarketData(offer.getLender(), offer.getRate(), tempAmount));
					break;
				} else {
					tempAmount = tempAmount.subtract(offer.getAmountAvailable());
					offersApplied.add(offer);
				}
			}
		}
		return offersApplied;
	}
	
}
