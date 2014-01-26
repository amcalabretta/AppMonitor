package com.amc.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main {
	private static String dateS = "2013-09-28 21:30:01";
	private static SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static SimpleDateFormat formatter2 = new SimpleDateFormat("dd/MM/yyyy@HH:mm:ss");
    private static long expectedTs = 1380403801;
    
    
    //used for differences
    private static long h24 = 86400;
    
    private static long previousTs = 1384381801;
    private static String previousDate = "2013-11-13 22:30:01";
    
    
    
    
	public static void main(String[] args) throws ParseException {
		Date dateStr = formatter1.parse(dateS);
		System.out.println("Original String:"+dateS);
		System.out.println("Date:"+formatter2.format(dateStr));
		long ts = dateStr.getTime()/1000;
		System.out.println("TS:"+ts);
		System.out.println("Expected:"+expectedTs);
		System.out.println("Difference:"+(ts - expectedTs));
		//next date:
		System.out.println("Previous date:"+formatter2.format(formatter1.parse(previousDate)));
		System.out.println("Previous ts  :"+previousTs);
		Calendar cal = Calendar.getInstance();
		cal.setTime(formatter1.parse(previousDate));
		cal.add(Calendar.DAY_OF_YEAR, 1);
		String currentDate = formatter1.format(cal.getTime());
		long currentTs = previousTs + h24;
		System.out.println("Current date:"+currentDate);
		System.out.println("Current ts  :"+currentTs);
		String sql = "INSERT INTO IOS_RELEASE_RATINGS VALUES (\""+currentTs+"\",\""+currentDate+"\",\"1\",\"2.1.0\",\"51\",\"12\",\"7\",\"5\",\"2\",\"77\",\"0\",\"43636\",\"0\",\"662337\",\"155844\",\"90909\",\"64935\",\"25974\",\"0\",\"0\",\"0\",\"0\",\"0\");";
		System.out.println("SQL:"+sql);
		
		
		
		
	}

}
