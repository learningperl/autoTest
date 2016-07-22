package com.test.tools;

import java.io.File;

import com.test.statics.property;

public class Verify {

	public static String path="";
	public static File file = null;
	public static boolean flag = false;
	
	public Verify(){
		path = property.url;
	}
	
	public static void savePic(){  
		file = new File(path+"/verify.jpg");
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete(); 
	        flag = true;
	    }
	    
	}
	
}
