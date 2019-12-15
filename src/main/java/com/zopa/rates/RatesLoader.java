package com.zopa.rates;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.zopa.exception.ZopaLoanException;
import com.zopa.model.MarketData;

public class RatesLoader {
	public List<MarketData> loadFile(final String fileName) {
		List<MarketData> dataList = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
			stream.skip(1).forEach(input -> {
				String[] inputArr = input.split(",");
				dataList.add(new MarketData(inputArr[0], new BigDecimal(inputArr[1]), new BigDecimal(inputArr[2])));
			});
		} catch (final Exception exception) {
			throw new ZopaLoanException("No quote can be provided at this time. Please try again later.");
		}
		return dataList;
	}
}
