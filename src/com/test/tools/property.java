package com.test.tools;

import java.io.FileInputStream;
import java.util.Properties;

public class property {
	
	public String readRcErpURL(String name){  
	      try{  
	             String url = this.getClass().getResource("").getPath().replaceAll("%20", " "); 
	             String path = url.substring(1, url.length()) + "/Test.properties"; 
	             System.out.println("log::info:configpath="+path);
	             Properties config = new Properties();
	             try{
	            	 config.load(new FileInputStream(path));  
	            	 return config.getProperty(name); 
	             }catch(Exception e1){
	            	 path = "/" + path;
	            	 config.load(new FileInputStream(path));  
	            	 return config.getProperty(name);
	             }
	         }  
	         catch(Exception e){  
	             e.printStackTrace();  
	         }  
	      return null;  
	     } 


}
