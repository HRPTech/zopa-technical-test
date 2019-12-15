package com.zopa;

import java.math.MathContext;
import java.math.RoundingMode;

public class Constants {
	public static final MathContext mc = new MathContext(4, RoundingMode.HALF_UP);
	public static final MathContext rateContext = new MathContext(2, RoundingMode.HALF_UP);
}
