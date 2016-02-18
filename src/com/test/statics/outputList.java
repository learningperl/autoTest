package com.test.statics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class outputList {
	public static ArrayList<Map<String, String>> l = null;
	public static ArrayList<Map<String, String>> l_key = null;
	public static Map<String,String> map = null;
	public static String logs=null;
	public static String list=null;
	public static String path="";
	
	public outputList(){	//数据存储结构静态类
		l = new ArrayList<Map<String, String>>();	//存储需要的所有UI和接口用例
		l_key = new ArrayList<Map<String, String>>();	//存储需要的所有关键字
		map = new HashMap<String, String>();	//存储单个用例或关键字
		logs = "";								//日志
		File directory = new File("");			//获取相对路径
		try {
			path = directory.getCanonicalPath();
		} catch (IOException e) {
			path = "";
		}
	}
	
}
