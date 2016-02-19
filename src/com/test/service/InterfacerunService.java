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
import com.test.tools.ImageTool;
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
							System.out.println("**88888");
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
								sqlu = "update interfacescene set runStates='PASS';";
							else
								sqlu = "update interfacescene set runStates='FAIL';";
						} catch (Exception e) {
							sqlu = "update interfacescene set runStates='PASS';";
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

	public static String runCase(sendUrl obj, jsonPase json) {
		runable = false;
		String s = "PASS";
		String url = "";
		actualRes = "NULL";

		responseList.json = "";
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
				System.out.println(responseList.res.size());
				result = responseList.res.get(i).get(map.get("checkName"))
						.toString();
				result = result.substring(result.indexOf('[')+1,result.length()-1);
				System.out.println("result:" + result);
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
}
