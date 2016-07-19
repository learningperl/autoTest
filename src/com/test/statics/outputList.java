package com.test.statics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class outputList {
	public static ArrayList<Map<String, String>> l = null;
	public static ArrayList<Map<String, String>> l_key = null;
	public static ArrayList<ArrayList<String>> keySet = null;
	public static Map<String,String> map = null;
	public static String logs=null;
	public static String list=null;
	
	public outputList(){	//数据存储结构静态类
		l = new ArrayList<Map<String, String>>();	//存储需要的所有UI和接口用例
		l_key = new ArrayList<Map<String, String>>();	//存储需要的所有关键字
		keySet = new ArrayList<ArrayList<String>>(); 	//存储各个类型关键字list
		map = new HashMap<String, String>();	//存储单个用例或关键字
		logs = "";								//日志
	}
	
}
