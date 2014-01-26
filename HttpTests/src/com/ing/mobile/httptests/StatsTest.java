package com.ing.mobile.httptests;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface StatsTest {
		boolean success() throws ClientProtocolException, IOException;
		public String description();
}
