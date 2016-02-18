package com.test.service;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;

import com.test.statics.Mysql;
import com.test.tools.sendUrl;

public class InterfacerunService {		//接口测试用例运行，未完成
	public static String state = "初始化";
	public static String actualRes = "NULL";
	private static String url_m = "";;
	public static WebDriver driver = null;
	public static HashMap<String, String> map = null;
	public static boolean runable = true;

	public InterfacerunService() {
		InterfacerunService.state = "等待运行";
	}

	public static void run(String[] check) {
		runable = false;
		state = "正在运行...";
		String sql = "";
		String sqlu = "";
		sendUrl obj = new sendUrl();
		try {
			Statement sm = Mysql.ct.createStatement();
			Mysql.ct.createStatement();
			for (int i = 0; i < check.length; i++) {
				try {
					map = new HashMap<String, String>();
					sql = "select url from interfacescene where sceneId="
							+ check[i] + ";";
					ResultSet rs = sm.executeQuery(sql);
					rs.next();
					url_m = rs.getString(1);
					System.out.println(url_m);
					sql = "select * from interfacecase where sceneId="
							+ check[i] + " order by order_id;";
					rs = null;
					rs = sm.executeQuery(sql);
					while (rs.next()) {
						ResultSetMetaData rsmd = rs.getMetaData();
						for (int j = 1; j <= rsmd.getColumnCount(); j++) {
							if (j == 1)
								rs.getString(j);
							map.put(rsmd.getColumnName(j), rs.getString(j));
						}
						try {
							System.out.println("**88888");
							runCase(obj);
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

	public static String runCase(sendUrl obj) {
		runable = false;
		String s = "PASS";
		String url = "";
		actualRes = "NULL";

		switch (map.get("method")) {
		case "post":
			if (map.get("url").toString().equals("NULL")
					|| map.get("url").toString().equals("")
					|| map.get("url").toString() == null)
				url = url_m;
			else
				url = map.get("url").toString();
			obj.sendPost(url, map.get("parameter").toString());
			break;
		default:
			System.out.println("方法不支持");
		}

		runable = true;
		return s;
	}
}
