package com.test.tools;

import java.io.FileInputStream;
import java.util.Properties;

public class property {
	
	public String readRcErpURL(String name){  
	      try{  
	             String url = this.getClass().getResource("").getPath().replaceAll("%20", " "); 
	             //System.out.println(url);
	             String path = url.substring(1, url.length()) + "/Test.properties";  
	             Properties config = new Properties();  
	             config.load(new FileInputStream(path));  
	             return config.getProperty(name);  
	         }  
	         catch(Exception e){  
	             e.printStackTrace();  
	         }  
	      return null;  
	     } 


}
