package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	//1.GET Method without headers:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url);
		CloseableHttpResponse closeableHttpResponse=httpclient.execute(httpget);
		
		return closeableHttpResponse;
}
	//1.GET Method with headers:
		public CloseableHttpResponse get(String url,HashMap<String,String> headermap) throws ClientProtocolException, IOException {
			CloseableHttpClient httpclient=HttpClients.createDefault();
			HttpGet httpget = new HttpGet(url);
			for(Map.Entry<String, String> entry:headermap.entrySet()) {
				httpget.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse closeableHttpResponse=httpclient.execute(httpget);
			
			return closeableHttpResponse;
	}
		//3.POST METHOD:
		public CloseableHttpResponse post(String url,String entityString,HashMap<String,String>headermap) throws ClientProtocolException, IOException {
			CloseableHttpClient closeableHttpClient=HttpClients.createDefault();
			HttpPost httppost=new HttpPost(url);
			httppost.setEntity(new StringEntity(entityString)); //For payload
			
			//For Headers:
			for(Map.Entry<String, String> entry:headermap.entrySet()) {
				httppost.addHeader(entry.getKey(),entry.getValue());
			}
			CloseableHttpResponse closeableHttpResponse=	closeableHttpClient.execute(httppost);
	return closeableHttpResponse;
		}
}
