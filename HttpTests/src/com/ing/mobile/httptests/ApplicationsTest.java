package com.ing.mobile.httptests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public class ApplicationsTest implements StatsTest{

	@Override
	public boolean success() throws ClientProtocolException, IOException {
		return false;
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
