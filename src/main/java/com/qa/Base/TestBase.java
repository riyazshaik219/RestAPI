package com.qa.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestBase {
	
	public  int RESPONSE_STATUS_CODE_200=200;
	public  int RESPONSE_STATUS_CODE_201=201;
	
	 
		public static Properties prop;
	public TestBase() {
		try {
			prop= new Properties();
			FileInputStream file = new FileInputStream("D:\\Automation\\RestAPI\\src\\main\\java\\com\\qa\\config\\config.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
}
