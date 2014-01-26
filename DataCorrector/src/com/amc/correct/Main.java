package com.amc.correct;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

//"2890","1000","361","185","365"
//"2904","1008","361","186","367"
public class Main {
	private static boolean firstRow = false;
	private static int star5 = 2904;
	private static int star4 = 1008;
	private static int star3 = 361;
	private static int star2 = 186;
	private static int star1 = 367;
	private static int prevstar5 = 2890;
	private static int prevstar4 = 1000;
	private static int prevstar3 = 361;
	private static int prevstar2 = 185;
	private static int prevstar1 = 365;
	private static String time = "1367184601";
	private static String timeDate = "2013-04-28 21:30:02";
	private static String APP_ID="1";
	private static String RELEASE="2.2.0";
	
	
	public static void main(String[] args) {
		//System.out.println("Correcting");
		String average = getAverage(star5, star4, star3, star2, star1);
		String total = Integer.toString(star5 + star4 + star3 + star2 + star1);
		String varTotal = "0";
		String varAverage = "0";
		String perc5 = getPerc(0, star5, star4, star3, star2, star1);
		String perc4 = getPerc(1, star5, star4, star3, star2, star1);
		String perc3 = getPerc(2, star5, star4, star3, star2, star1);
		String perc2 = getPerc(3, star5, star4, star3, star2, star1);
		String perc1 = getPerc(4, star5, star4, star3, star2, star1);
		String varperc5 = "0";
		String varperc4 = "0";
		String varperc3 = "0";
		String varperc2 = "0";
		String varperc1 = "0";
		if (!firstRow) {
			varTotal = Integer.toString(star5 + star4 + star3 + star2 + star1 -prevstar1 -prevstar2 - prevstar3 - prevstar4 -prevstar5);
			int varAverageS = Integer.parseInt(average) - Integer.parseInt(getAverage(prevstar5, prevstar4, prevstar3, prevstar2, prevstar1));
			varAverage = varAverageS+"";
			varperc5 = (Integer.parseInt(perc5) - Integer.parseInt(getPerc(0, prevstar5, prevstar4, prevstar3, prevstar2, prevstar1)))+"";
			varperc4 = (Integer.parseInt(perc4) - Integer.parseInt(getPerc(1, prevstar5, prevstar4, prevstar3, prevstar2, prevstar1)))+"";
			varperc3 = (Integer.parseInt(perc3) - Integer.parseInt(getPerc(2, prevstar5, prevstar4, prevstar3, prevstar2, prevstar1)))+"";
			varperc2 = (Integer.parseInt(perc2) - Integer.parseInt(getPerc(3, prevstar5, prevstar4, prevstar3, prevstar2, prevstar1)))+"";
			varperc1 = (Integer.parseInt(perc1) - Integer.parseInt(getPerc(4, prevstar5, prevstar4, prevstar3, prevstar2, prevstar1)))+"";
		} 
		System.out.println("INSERT INTO IOS_RELEASE_RATINGS VALUES (\""+time+"\",\""+timeDate+"\",\""+APP_ID+"\",\""+RELEASE+"\",\""+star5+"\",\""+star4+"\",\""+star3+"\",\""+star2+"\",\""+star1+"\",\""+total+"\",\""+varTotal+"\",\""+average+"\",\""+varAverage+"\",\""+perc5+"\",\""+perc4+"\",\""+perc3+"\",\""+perc2+"\",\""+perc1+"\",\""+varperc5+"\",\""+varperc4+"\",\""+varperc3+"\",\""+varperc2+"\",\""+varperc1+"\");");
	}
	
	private static String getPerc(int i, int five, int four, int three,int two, int one) {
		int a = 0;
		switch (i) {
			case 0:
				a = five;
				break;
			case 1:
				a=four;
				break;
			case 2:
				a=three;
				break;
			case 3:
				a=two;
				break;
			case 4:
				a=one;
				break;
			default:
				break;
		}
		BigDecimal g = new BigDecimal(a);
		//System.out.println("g="+g);
		BigDecimal h = new BigDecimal(100);
		BigDecimal h1 = new BigDecimal(10000);
		BigDecimal s = new BigDecimal(one + two + three + four + five);
		BigDecimal r = g.divide(s, 4, RoundingMode.UP);
		//System.out.println("r="+r);
		BigDecimal k = r.multiply(h).multiply(h1);
		//System.out.println("k="+k);
		String ret = k.toString();
		return ret.substring(0,ret.indexOf('.'));
	}


	private static String getAverage(int five,int four,int three,int two,int one) {
		BigDecimal averageRelease = new BigDecimal(five*5 + four*4 + three*3 + two*2 + one);
		averageRelease = averageRelease.divide(new BigDecimal(five+four+three+two+one), 4, RoundingMode.HALF_UP);
		averageRelease = averageRelease.multiply(new BigDecimal(10000),new MathContext(0));
		String avgRel = averageRelease.toString();
		avgRel = avgRel.substring(0,avgRel.indexOf('.'));
		return avgRel;
	}

}
