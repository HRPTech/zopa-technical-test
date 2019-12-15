package com.zopa.lender;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.zopa.exception.ZopaLoanException;
import com.zopa.model.MarketData;

public class LenderChooserTest {
	private LenderChooser chooser = new LenderChooser();

	@Test
	public void getOfferTest() {
		List<MarketData> dataList = new ArrayList<>();
		MarketData data = new MarketData("test", BigDecimal.valueOf(0.1), BigDecimal.valueOf(1000));
		dataList.add(data);

		List<MarketData> offers = chooser.getOffers(dataList, BigDecimal.valueOf(100));

		assertEquals(1, offers.size());

		MarketData expected = new MarketData("test", BigDecimal.valueOf(0.1), BigDecimal.valueOf(100));
		assertEquals(expected, offers.get(0));
	}
	
	@Test
	public void getMultipleOfferTest() {
		List<MarketData> dataList = new ArrayList<>();
		MarketData quote1 = new MarketData("test", BigDecimal.valueOf(0.1), BigDecimal.valueOf(10));
		MarketData quote2 = new MarketData("abc", BigDecimal.valueOf(0.2), BigDecimal.valueOf(100));
		dataList.add(quote1);
		dataList.add(quote2);

		List<MarketData> offers = chooser.getOffers(dataList, BigDecimal.valueOf(100));

		assertEquals(2, offers.size());

		MarketData expectedQuote1 = new MarketData("test", BigDecimal.valueOf(0.1), BigDecimal.valueOf(10));
		MarketData expectedQuote2 = new MarketData("abc", BigDecimal.valueOf(0.2), BigDecimal.valueOf(90));
		assertEquals(expectedQuote1, offers.get(0));
		assertEquals(expectedQuote2, offers.get(1));
	}

	@Test(expected = ZopaLoanException.class)
	public void getNoOfferAvailableTest() {
		List<MarketData> dataList = new ArrayList<>();
		chooser.getOffers(dataList, BigDecimal.valueOf(100));
	}
	
	@Test
	public void noMarketDataTest() {
		List<MarketData> offers = 	chooser.getOffers(null, BigDecimal.valueOf(100));
		assertTrue(offers.isEmpty());
	}

}
