package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.Base.TestBase;
import com.qa.client.RestClient;
import com.qa.utl.TestUtil;

public class GetAPITest extends TestBase {
	TestBase testbase;
	RestClient restclient;
	String serviceurl;
	String ApIURL;
	String url ;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testbase=new TestBase();
		serviceurl=prop.getProperty("url");
		 ApIURL=prop.getProperty("serviceurl");
		 url = serviceurl+ApIURL;
	
	}
	@Test(priority=0)
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		closeableHttpResponse=restclient.get(url);
		
		//a.status Code:
				int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code--->"+statuscode);
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200);
				
				//b.JsonString
				String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responsejson= new JSONObject(responseString);
				System.out.println("Response JSON From API-->"+responsejson);
				
				String perpagevalue=TestUtil.getValueByJPath(responsejson, "/per_page");
				System.out.println(perpagevalue);
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
				
				//get the value from JSON ARRAY:
				String lastname=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
				String firstname=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
				String email=TestUtil.getValueByJPath(responsejson, "/data[0]/email");
				System.out.println(lastname);
				System.out.println(firstname);
				System.out.println(email);
				
				
				//c.All Headers:
				Header[] headersarray=closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allHeaders= new HashMap<String,String>();
				
				for(Header header:headersarray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array-->"+allHeaders);
				
			}
	
	@Test(priority=1)
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {
		restclient = new RestClient();
		HashMap<String,String> headermap= new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		closeableHttpResponse=restclient.get(url,headermap);
		
		//a.status Code:
				int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
				System.out.println("Status Code--->"+statuscode);
				Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200);
				
				//b.JsonString
				String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
				JSONObject responsejson= new JSONObject(responseString);
				System.out.println("Response JSON From API-->"+responsejson);
				
				String perpagevalue=TestUtil.getValueByJPath(responsejson, "/per_page");
				System.out.println(perpagevalue);
				Assert.assertEquals(Integer.parseInt(perpagevalue), 6);
				
				//get the value from JSON ARRAY:
				String lastname=TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
				String firstname=TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");
				String email=TestUtil.getValueByJPath(responsejson, "/data[0]/email");
				System.out.println(lastname);
				System.out.println(firstname);
				System.out.println(email);
				
				
				//c.All Headers:
				Header[] headersarray=closeableHttpResponse.getAllHeaders();
				HashMap<String,String> allHeaders= new HashMap<String,String>();
				
				for(Header header:headersarray) {
					allHeaders.put(header.getName(), header.getValue());
				}
				System.out.println("Headers Array-->"+allHeaders);
				
			}
	}

