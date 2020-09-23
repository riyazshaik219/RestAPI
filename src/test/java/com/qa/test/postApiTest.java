package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class postApiTest extends TestBase {
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
	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {
		restclient=new RestClient();
		HashMap<String,String> headermap= new HashMap<String,String>();
		headermap.put("Content-Type", "application/json");
		
		//jackson API:
		
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus","leader");
		
		//object to json file:
		mapper.writeValue(new File("D:\\Automation\\RestAPI\\src\\main\\java\\com\\qa\\data\\users.json"), users);
		
		//object to json in string:
		String usersJsonString=mapper.writeValueAsString(users);
		System.out.println(usersJsonString);
		
		closeableHttpResponse=restclient.post(url, usersJsonString, headermap);
	
	//1.status code:
		int statuscode=closeableHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode,testbase.RESPONSE_STATUS_CODE_201 );
		
		//2.Json String:
		String responseString=EntityUtils.toString(closeableHttpResponse.getEntity(),"UTF-8");
		JSONObject jsonObject=new JSONObject(responseString);
		System.out.println("Response from API:"+jsonObject);
		//json to java object:
		Users usersobj=mapper.readValue(responseString, Users.class);
		System.out.println(usersobj);
		
		System.out.println(users.getName().equals(usersobj.getName()));
		System.out.println(users.getJob().equals(usersobj.getJob()));
		System.out.println(usersobj.getId());
		System.out.println(usersobj.getCreatedAt());
		
		
	}

}
