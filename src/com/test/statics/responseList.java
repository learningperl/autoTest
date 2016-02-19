package com.test.statics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class responseList {
	public static ArrayList<Map<String, String>> res = null;
	public static ArrayList<Map<String, String>> paramter = null;
	public static Map<String,String> map = null;
	public static String logs=null;
	public String json;
	
	public responseList(){	//数据存储结构静态类
		res = new ArrayList<Map<String, String>>();	//存储接口返回结果
		paramter = new ArrayList<Map<String, String>>();	//存储需要保留的参数
		map = new HashMap<String, String>();	//存储单个字段对应值
		logs = "";								//日志
		json = "";								//解析前的json
	}
}
