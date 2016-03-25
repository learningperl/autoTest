package com.test.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;

import com.test.statics.Mysql;
import com.test.statics.responseList;
import com.test.tools.encodeType;
import com.test.tools.jsonPase;
import com.test.tools.sendUrl;

public class InterfacerunService { // 接口测试用例运行，未完成
	public static String state = "初始化";
	public static String actualRes = "NULL";
	private static String url_m = "";;
	public static WebDriver driver = null;
	public static HashMap<String, String> map = null;
	public static boolean runable = true;
	public static String jsonResult = "NULL";

	public InterfacerunService() {
		InterfacerunService.state = "等待运行";
	}

	public static void run(String[] check) {
		runable = false;
		state = "正在运行...";
		String sql = "";
		String sqlu = "";
		sendUrl obj = new sendUrl();
		jsonPase json = new jsonPase();
		try {
			Statement sm = Mysql.ct.createStatement();
			Statement sm1 = Mysql.ct.createStatement();
			Mysql.ct.createStatement();
			for (int i = 0; i < check.length; i++) {
				try {
					map = new HashMap<String, String>();
					sql = "select delay from interfacescene where sceneId="
							+ check[i] + ";";
					ResultSet rs = sm.executeQuery(sql);
					rs.next();
					responseList.delay = Integer.parseInt(rs.getString(1));
					rs = null;
					sql = "select url from interfacescene where sceneId="
							+ check[i] + ";";
					rs = sm.executeQuery(sql);
					rs.next();
					url_m = rs.getString(1);
					System.out.println(url_m);
					sql = "select * from interfacecase where sceneId="
							+ check[i] + " order by order_id;";
					rs = null;
					rs = sm.executeQuery(sql);
					while (rs.next()) {
						ResultSetMetaData rsmd = rs.getMetaData();
						String id = null;
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							if (j == 1)
								id  = rs.getString(j);
							map.put(rsmd.getColumnName(j), rs.getString(j));
						}
						try {
							//System.out.println("**88888");
							String str = runCase(obj, json);
							sqlu = "update interfacecase set runState='"
									+ str + "',actualRes='" + actualRes + "',jsonResult='" + jsonResult + "' where id=" + id;
							//System.out.println(sqlu);
							sm1.executeUpdate(sqlu);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					try {
						rs = null;
						sql = "select id from interfacecase where sceneId="
								+ check[i] + " and runState='FAIL';";
						rs = sm.executeQuery(sql);
						rs.next();
						try {
							if (rs != null && rs.getString(1) != null)
								sqlu = "update interfacescene set runStates='FAIL' where sceneId=" + check[i];
							else
								sqlu = "update interfacescene set runStates='PASS' where sceneId=" + check[i];
						} catch (Exception e) {
							sqlu = "update interfacescene set runStates='PASS' where sceneId=" + check[i];
						}
						sm.executeUpdate(sqlu);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			state = "运行完毕";
		} catch (Exception e) {
			e.printStackTrace();
		}
		runable = true;
	}
	
	public static void debug(String url,String param,String method){
		
		state = "正在调试";
		
		sendUrl obj = new sendUrl();
		switch (method) { // 发送请求
		case "post":
			if(param.equals("请输入接口参数"))
				param = "";
			responseList.json = obj.sendPost(url, param).toString();
			break;
		case "get":
			if(param.equals("请输入接口参数"))
				param = "";
			System.out.println(url+param);
			responseList.json = obj.sendGet(url, param).toString();
			break;
		default :
			responseList.json = "方法:"+method+"不支持！";
		}
		System.out.println(encodeType.getEncoding(responseList.json));
		state = "调试完成";		
	}
	
	public static String runCase(sendUrl obj, jsonPase json) {
		runable = false;
		String s = "PASS";
		String url = "";
		actualRes = "NULL";
		String method="";
		responseList.json = "";
		
		getParameter();
		
		if (map.get("method").contains("(")) {// 处理关键字中带括号的情况
			method = map.get("method").substring(
					map.get("method").indexOf("(") + 1,
					map.get("method").indexOf(")"));
			map.put("method",
					map.get("method").substring(0,
							map.get("method").indexOf("(")));
			//System.out.println("method:" + method + " method:"+ map.get("method"));
		}
		
		switch (map.get("method")) { // 发送请求
		case "post":
			if (map.get("url").toString().equals("NULL")
					|| map.get("url").toString().equals("")
					|| map.get("url").toString() == null)
				url = url_m;
			else
				url = map.get("url").toString();
			responseList.json = obj.sendPost(url, map.get("parameter")
					.toString());
			break;
		case "get":
			if (map.get("url").toString().equals("NULL")
					|| map.get("url").toString().equals("")
					|| map.get("url").toString() == null)
				url = url_m;
			else
				url = map.get("url").toString();
			responseList.json = obj.sendGet(url, map.get("parameter")
					.toString());
			break;
			
		case "setParameter":
			if (map.get("parameter").equals("") || map.get("parameter").equals("null")
					|| map.get("parameter").equals("NULL"))
				System.out.println("参数名不能为空！！！");
			else
				responseList.parameter.put(map.get("url"), map.get("parameter"));
			break;

		case "getJson":
			if (method.equals("post")) {
				responseList.json = obj.sendPost(map.get("url"),
						map.get("parameter"));
				responseList.res = new ArrayList<Map<String, Object>>();
				json.Pase(responseList.json, 0, true);
				try {
					responseList.jsonObject = responseList.res
							.get(responseList.res.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}
			else if (method.equals("get")) {
				responseList.json = obj.sendGet(map.get("url"),
						map.get("parameter"));
				responseList.res = new ArrayList<Map<String, Object>>();
				json.Pase(responseList.json, 0, true);
				try {
					responseList.jsonObject = responseList.res
							.get(responseList.res.size() - 1);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			}else
				System.out.println("关键字getJson不支持" + method + "方法!");
			break;
			
		default:
			System.out.println("方法不支持");
		}

		jsonResult = responseList.json;
		try{
			jsonResult = jsonResult.substring(0,511);
		}catch (Exception e){
			
		}
		System.out.println(jsonResult);
		// 处理json 结果，获取返回值
		String result = "";
		responseList.res = new ArrayList<Map<String, Object>>();
		json.Pase(responseList.json, 0, true);
		for (int i = 0; i < responseList.res.size(); i++) {
			try {
				//System.out.println(responseList.res.size());
				result = responseList.res.get(i).get(map.get("checkName"))
						.toString();
				result = result.substring(result.indexOf('[')+1,result.length()-1);
				//System.out.println("result:" + result);
				break;
			} catch (Exception e) {
				//System.out.println("获取返回值失败！");
				// e.printStackTrace();
			}
		}
		actualRes = result;
		try{
			actualRes = actualRes.substring(0,31);
		}catch (Exception e){
			
		}
		switch (map.get("checkMethod")) { // 校验结果
		case "NULL":
			s = "PASS";
			break;
		
		case "unEquel":
			if (result.equals(map.get("expRes")))
				s = "FAIL";
			else
				s = "PASS";
			break;
		case "equel":
			if (result.equals(map.get("expRes")))
				s = "PASS";
			else
				s = "FAIL";
			break;
		case "contain":
			if (result.contains(map.get("expRes")))
				s = "PASS";
			else
				s = "FAIL";
			break;
		default:
			System.out.println("方法不支持");
		}

		try {
			int t = responseList.delay;
			while (t > 0) {
				Thread.sleep(1000);
				t = t - 1000;
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		runable = true;
		return s;
	}
	
	private static void getParameter() {// 获取系统参数和json参数
		int l = 0;
		int r = 0;
		String datas = "";
		String str = map.get("parameter");
		int flag = 0;
		try {
			while (str != null && str.length() > 0) {
				flag = 0;
				if (str.contains("{") || str.contains("[")) {
					if (str.indexOf("[") >= 0 && str.indexOf("{") >= 0) {
						if (str.indexOf("{") > str.indexOf("[")) {
							l = str.indexOf("[");
							r = str.indexOf("]") + 1;
							flag = 1;
						} else {
							l = str.indexOf("{");
							r = str.indexOf("}") + 1;
							flag = 2;
						}
					} else {
						if (str.indexOf("[") >= 0) {
							l = str.indexOf("[");
							r = str.indexOf("]") + 1;
							flag = 1;
						} else {
							l = str.indexOf("{");
							r = str.indexOf("}") + 1;
							flag = 2;
						}
					}

					if (r > 0) {
						String str1 = str.substring(l + 1, r - 1);
						//System.out.println("???"+str);
						if (flag == 1)
							datas += str.substring(0, l)
									+ responseList.jsonObject.get(str1);
						else if (flag == 2)
							datas += str.substring(0, l)
									+ responseList.parameter.get(str1);
						str = str.substring(r, str.length());
					} else {
						System.out.println("log：参数引用格式错误！！");
						break;
					}

				} else
					break;
			}

			if (datas.length() > 0) {
				datas += str;
				map.put("parameter", datas);
			}
			//System.out.println("参数："+map.get("parameter"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
