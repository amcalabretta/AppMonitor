package com.amc.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
	private static SimpleDateFormat parserSDF=new SimpleDateFormat("dd-MM-yyyy");
	private static SimpleDateFormat parserTS=new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
	private static SimpleDateFormat parserSQLITE=new SimpleDateFormat("yyyy-MM-dd");
	private static int APP_ID=2;
	public static void main(String[] args) throws ParseException {
		//System.out.println("Start with file:"+args[0]);
		String timeString = null;
		long timestamp = 0;
		Row prevFirst = null;
		Row prevSecond = null;
		Row first = null;
		Row second = null;
		Calendar current = null;
		int mode = 0;
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(args[0]));
			while ((sCurrentLine = br.readLine()) != null) {
				switch (mode) {
				case 0:
					current = Calendar.getInstance();
					current.setTime(parserSDF.parse(sCurrentLine.substring(1, sCurrentLine.length()-1)));
					Calendar ts = Calendar.getInstance();
					ts.setTime(parserTS.parse(sCurrentLine.substring(1, sCurrentLine.length()-1)+" 22:30:00"));
					timestamp = ts.getTimeInMillis()/1000;
					//System.out.println("Date:"+parserSDF.format(current.getTime())+" ts:"+timestamp);
					timeString = parserSQLITE.format(current.getTime())+" 22:30:00";
					mode = 1;
					break;
				default:
					char firstChar = sCurrentLine.charAt(0);
					if (firstChar=='5') {
						int idx1 = sCurrentLine.indexOf(',');
						int idx2 = sCurrentLine.lastIndexOf(' ');
						String num = sCurrentLine.substring(idx1+1, idx2).trim();
						int n = Integer.parseInt(num);
						//System.out.println("5 stars mode "+mode+":"+sCurrentLine+"-->*"+num+"*");
						if (mode==1) {
							first = new Row();
							first.five = n;
						} else if (mode==2) {
							second = new Row();
							second.five = n;
						}
					} else if (firstChar=='4') {
						int idx1 = sCurrentLine.indexOf(',');
						int idx2 = sCurrentLine.lastIndexOf(' ');
						String num = sCurrentLine.substring(idx1+1, idx2).trim();
						int n = Integer.parseInt(num);
						//System.out.println("4 stars mode "+mode+":"+sCurrentLine+"-->*"+num+"*");
						if (mode==1) {
							first.four = n;
						} else if (mode==2) {
							second.four = n;
						}
					} else if (firstChar=='3') {
						int idx1 = sCurrentLine.indexOf(',');
						int idx2 = sCurrentLine.lastIndexOf(' ');
						String num = sCurrentLine.substring(idx1+1, idx2).trim();
						int n = Integer.parseInt(num);
						if (mode==1) {
							first.three = n;
						} else if (mode==2) {
							second.three = n;
						}
						//System.out.println("3 stars mode "+mode+":"+sCurrentLine+"-->*"+num+"*");
					} else if (firstChar=='2') {
						int idx1 = sCurrentLine.indexOf(',');
						int idx2 = sCurrentLine.lastIndexOf(' ');
						String num = sCurrentLine.substring(idx1+1, idx2).trim();
						int n = Integer.parseInt(num);
						if (mode==1) {
							first.two = n;
						} else if (mode==2) {
							second.two = n;
						}
						//System.out.println("2 stars mode "+mode+":"+sCurrentLine+"-->*"+num+"*");
					} else if (firstChar=='1') {
						int idx1 = sCurrentLine.indexOf(',');
						int idx2 = sCurrentLine.lastIndexOf(' ');
						String num = sCurrentLine.substring(idx1+1, idx2).trim();
						int n = Integer.parseInt(num);
						if (mode==1) {
							first.one = n;
						} else if (mode==2) {
							second.one = n;
						}
						//System.out.println("1 stars mode "+mode+":"+sCurrentLine+"-->*"+num+"*");
						if (mode==1){
							mode =2;
						}  else if (mode==2) {
							//roll all the data
							String sqlString1 = null;
							String sqlString2 = null;
//							System.out.println("Rolling the data:");
//							System.out.println(" - Time:"+timeString);
//							System.out.println(" - Stamp:"+timestamp);
//							System.out.println(" - First 1:"+first.one);
//							System.out.println(" - First 2:"+first.two);
//							System.out.println(" - First 3:"+first.three);
//							System.out.println(" - First 4:"+first.four);
//							System.out.println(" - First 5:"+first.five);
//							System.out.println(" - Second 1:"+second.one);
//							System.out.println(" - Second 2:"+second.two);
//							System.out.println(" - Second 3:"+second.three);
//							System.out.println(" - Second 4:"+second.four);
//							System.out.println(" - Second 5:"+second.five);
							//release data
							int totalRelease = first.five + first.four + first.three + first.two + first.one;
							String avgRel = getAverage(first.five, first.four, first.three, first.two, first.one);
							String percFiveRelease = getPerc(0,first.five, first.four, first.three, first.two, first.one);
							String percFourRelease = getPerc(1,first.five, first.four, first.three, first.two, first.one);
							String percThreeRelease = getPerc(2,first.five, first.four, first.three, first.two, first.one);
							String percTwoRelease = getPerc(3,first.five, first.four, first.three, first.two, first.one);
							String percOneRelease = getPerc(4,first.five, first.four, first.three, first.two, first.one);
							//total data
							int totalTotal = second.five + second.four + second.three + second.two + second.one;
							String avgTotal = getAverage(second.five, second.four, second.three, second.two, second.one);
							String percFiveTotal = getPerc(0,second.five, second.four, second.three, second.two, second.one);
							String percFourTotal = getPerc(1,second.five, second.four, second.three, second.two, second.one);
							String percThreeTotal = getPerc(2,second.five, second.four, second.three, second.two, second.one);
							String percTwoTotal = getPerc(3,second.five, second.four, second.three, second.two, second.one);
							String percOneTotal = getPerc(4,second.five, second.four, second.three, second.two, second.one);
							//var release
							int varCommentsRelease = 0;
							int varAverageRelease = 0;
							String percFiveReleaseVar = "0";
							String percFourReleaseVar = "0";
							String percThreeReleaseVar ="0";
							String percTwoReleaseVar = "0";
							String percOneReleaseVar = "0";
							//var total
							int varCommentsTotal = 0;
							int varAverageTotal = 0;
							String percFiveTotalVar = "0";
							String percFourTotalVar = "0";
							String percThreeTotalVar = "0";
							String percTwoTotalVar = "0";
							String percOneTotalVar = "0";
							if (prevFirst!=null) {
								varCommentsRelease = totalRelease -prevFirst.five -prevFirst.four - prevFirst.three -prevFirst.two - prevFirst.one;
								varAverageRelease = Integer.parseInt(avgRel) - Integer.parseInt(getAverage(prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one));
								percFiveReleaseVar = ""+(Integer.parseInt(percFiveRelease) - Integer.parseInt(getPerc(0,prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one)));
								percFourReleaseVar = ""+(Integer.parseInt(percFourRelease) - Integer.parseInt(getPerc(1,prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one)));
								percThreeReleaseVar = ""+(Integer.parseInt(percFourRelease) - Integer.parseInt(getPerc(2,prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one)));
								percTwoReleaseVar = ""+(Integer.parseInt(percFourRelease) - Integer.parseInt(getPerc(3,prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one)));
								percOneReleaseVar = ""+(Integer.parseInt(percFourRelease) - Integer.parseInt(getPerc(4,prevFirst.five, prevFirst.four, prevFirst.three, prevFirst.two, prevFirst.one)));
								varCommentsTotal = totalTotal - -prevSecond.five -prevSecond.four - prevSecond.three -prevSecond.two - prevSecond.one;
								varAverageTotal = Integer.parseInt(avgTotal) - Integer.parseInt(getAverage(prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one));
								percFiveTotalVar = ""+(Integer.parseInt(percFiveTotal) - Integer.parseInt(getPerc(0,prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one)));
								percFourTotalVar = ""+(Integer.parseInt(percFourTotal) - Integer.parseInt(getPerc(1,prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one)));
								percThreeTotalVar = ""+(Integer.parseInt(percFourTotal) - Integer.parseInt(getPerc(2,prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one)));
								percTwoTotalVar = ""+(Integer.parseInt(percFourTotal) - Integer.parseInt(getPerc(3,prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one)));
								percOneTotalVar = ""+(Integer.parseInt(percFourTotal) - Integer.parseInt(getPerc(4,prevSecond.five, prevSecond.four, prevSecond.three, prevSecond.two, prevSecond.one)));
							}
							String version = "3.0.1";
							if (timestamp > 1361395800) {
								version = "3.0.2";
							}
							sqlString1 = "INSERT INTO IOS_RELEASE_RATINGS(timestamp,rating_time,app_id,release,five_stars,four_stars,three_stars,two_stars,one_star"+
								    ",total,var_total,average,var_average,perc_five,perc_four,perc_three,perc_two,perc_one,perc_five_var,perc_four_var,perc_three_var,perc_two_var,perc_one_var) VALUES (";
							sqlString1 = sqlString1+timestamp+","+"'"+timeString+"',"+APP_ID+",'"+version+"',"+first.five+","+first.four+","+first.three+","+first.two+","+first.one+","+totalRelease;
							sqlString1 = sqlString1+","+varCommentsRelease+","+avgRel+","+varAverageRelease+","+percFiveRelease+","+percFourRelease+","+percThreeRelease+","+percTwoRelease+","+percOneRelease;
							sqlString1 = sqlString1+","+percFiveReleaseVar+","+percFourReleaseVar +","+percThreeReleaseVar +","+percTwoReleaseVar +","+percOneReleaseVar+");";
							sqlString2 = "INSERT INTO IOS_TOTAL_RATINGS(timestamp,rating_time,app_id,five_stars,four_stars,three_stars,two_stars,one_star"+
										",total,var_total,average,var_average,perc_five,perc_four,perc_three,perc_two,perc_one,perc_five_var,perc_four_var,perc_three_var,"+
									    "perc_two_var,perc_one_var) VALUES ("+timestamp+","+"'"+timeString+"',"+APP_ID+","+second.five+","+second.four+","+second.three;
							sqlString2 = sqlString2 + ","+second.two+","+second.one+","+totalTotal+","+varCommentsTotal+","+avgTotal+","+varAverageTotal+","+percFiveTotal+",";
							sqlString2 = sqlString2 + percFourTotal+","+percThreeTotal+","+percTwoTotal+","+percOneTotal+","+percFiveTotalVar+","+percFourTotalVar;
							sqlString2 = sqlString2 + "," + percThreeTotalVar+","+percTwoTotalVar+","+percOneTotalVar+");";
							prevFirst = first;
							prevSecond = second;
							mode = 0;
							System.out.println("/*SQL RELEASE */\n"+sqlString1);
							//System.out.println("/*SQL TOTAL */\n"+sqlString2);
							//System.out.println(" ++++++++++++++++++++++++++++++++ ");
						} 
					} 
				}
			}
 		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
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
		BigDecimal h = new BigDecimal(100);
		BigDecimal h1 = new BigDecimal(10000);
		BigDecimal s = new BigDecimal(one + two + three + four + five);
		BigDecimal r = g.divide(s, 4, RoundingMode.UP);
		BigDecimal k = r.multiply(h).multiply(h1);
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
