package com.ing.mobile.httptests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class Main {
	private static StatsTest[] tests = {new AndroidRawDataStatsTest()};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i=0;i<tests.length;i++) {
			System.out.print("Executing:"+tests[0].description()+":");
			boolean outcome = false;
			try {
				outcome = tests[0].success();
				System.out.println(outcome?"SUCCESS":"FAILURE");
			} catch (ClientProtocolException e) {
				System.out.println("FAILURE "+e.getMessage());
			} catch (IOException e) {
				System.out.println("FAILURE "+e.getMessage());
			}
			
		}
		

	}

}
