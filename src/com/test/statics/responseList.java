package com.test.statics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class responseList {
	public static ArrayList<Map<String, Object>> res = null;
	public static Map<String, Object> jsonObject = null;
	public static Map<String,String> parameter = null;
	
	public static String logs=null;
	public static String json;
	public static int	delay ;
	
	public responseList(){	//数据存储结构静态类
		res = new ArrayList<Map<String, Object>>();	//存储接口返回结果
		jsonObject = new HashMap<String, Object>();	//存储需要保留的json
		parameter = new HashMap<String, String>();	//存储需要保留的json
		logs = "";								//日志
		json = "";								//解析前的json
		delay = 0;
	}
}
