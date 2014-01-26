package com.ing.mobile.httptests;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONValue;

public class AndroidRawDataStatsTest implements StatsTest {

	@Override
	public boolean success() throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/MobileStats/data/rawData?domain=android&type=rawData");
		CloseableHttpResponse response = httpclient.execute(httpGet);
		try {
			HttpEntity entity = response.getEntity();
		    if (entity != null) {
		    		String jsonResponse = EntityUtils.toString(entity);
		    		Object obj=JSONValue.parse(jsonResponse);
		    		JSONArray array=(JSONArray)obj;
		    		int num = array.size();
		    		if (num==875)
		    			return true;
		    }
		} finally {
		    response.close();
		}
		return false;
	}

	@Override
	public String description() {
		return "ANDROID RAW DATA TEST";
	}
}