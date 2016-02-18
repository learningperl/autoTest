package com.test.service;

import com.test.statics.Mysql;
import com.test.statics.outputList;

import java.util.HashMap;
import java.sql.*;

public class serviceSearch { // UI用例查询类
	public serviceSearch() {
		outputList.l.clear();
		outputList.map = new HashMap<String, String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			//查询用例场景
			ResultSet rs = sm.executeQuery("select * from casescene");
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					outputList.map.put(rsmd.getColumnName(i), rs.getString(i));
				}
				outputList.l.add(outputList.map);
				outputList.map = new HashMap<String, String>();
				outputList.map = new HashMap<String, String>();
				Statement sm1 = Mysql.ct.createStatement();
				//查询场景对应用例，并排序
				sql = "select * from caseoption where casesId='"
						+ rs.getString(1) + "' order by order_id";
				ResultSet rs1 = sm1.executeQuery(sql);
				while (rs1.next()) {
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
						outputList.map.put(rsmd1.getColumnName(i),
								rs1.getString(i));
					}
					outputList.l.add(outputList.map);
					outputList.map = new HashMap<String, String>();
				}
				outputList.map = new HashMap<String, String>();
			}
			/*
			 * System.out.println("size:"+outputList.l.size()); for (int
			 * i=0;i<outputList.l.size();i++)
			 * System.out.println(outputList.l.get(i).get("id"));
			 */
			sm = null;
			rs = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public serviceSearch(String p) {//条件查询
		outputList.l.clear();
		outputList.map = new HashMap<String, String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			sql = "SELECT * FROM casescene WHERE casesN LIKE '%" + p + "%'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					outputList.map.put(rsmd.getColumnName(i), rs.getString(i));
				}
				outputList.l.add(outputList.map);
				outputList.map = new HashMap<String, String>();
				outputList.map = new HashMap<String, String>();
				Statement sm1 = Mysql.ct.createStatement();
				sql = "select * from caseoption where casesId='"
						+ rs.getString(1) + "' order by order_id";
				ResultSet rs1 = sm1.executeQuery(sql);
				while (rs1.next()) {
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
						outputList.map.put(rsmd1.getColumnName(i),
								rs1.getString(i));
					}
					outputList.l.add(outputList.map);
					outputList.map = new HashMap<String, String>();
				}
				outputList.map = new HashMap<String, String>();
			}

			sm = null;
			rs = null;
			/*
			 * System.out.println("size:"+outputList.l.size()); for (int
			 * i=0;i<outputList.l.size();i++)
			 * System.out.println(outputList.l.get(i).get("id"));
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public serviceSearch(int l, int r) {

	}

	public serviceSearch(String s, int l, int r) {

	}
}
