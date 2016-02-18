package com.test.service;

import com.test.statics.Mysql;
import com.test.statics.outputList;

import java.util.HashMap;
import java.sql.*;

public class interfaceSearchservice {
	
	
	public interfaceSearchservice(){		//接口测试用例查询类
		outputList.l.clear();
		outputList.map = new HashMap<String,String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			ResultSet rs = sm.executeQuery("select * from interfacescene");	//查询用例场景
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					outputList.map.put(rsmd.getColumnName(i),rs.getString(i));
				}
				outputList.l.add(outputList.map);
				outputList.map = new HashMap<String,String>();
				outputList.map = new HashMap<String,String>();
				Statement sm1 = Mysql.ct.createStatement();
				//查询场景对应对的用例，并排序
				sql="select * from interfacecase where sceneId='" + rs.getString(1) +"' order by order_id";
				ResultSet rs1 = sm1.executeQuery(sql);
				while (rs1.next()) {
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
						outputList.map.put(rsmd1.getColumnName(i),rs1.getString(i));
					}
					outputList.l.add(outputList.map);
					outputList.map = new HashMap<String,String>();
				}
				outputList.map = new HashMap<String,String>();
			}
			/*
			System.out.println("size:"+outputList.l.size());
			for (int i=0;i<outputList.l.size();i++)
				System.out.println(outputList.l.get(i).get("id"));
				*/
				
			sm=null;
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public interfaceSearchservice(String p){		//搜索对应的用例场景
		outputList.l.clear();
		outputList.map = new HashMap<String,String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			sql = "SELECT * FROM interfacescene WHERE sceneDescription LIKE '%"+p+"%'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					outputList.map.put(rsmd.getColumnName(i),rs.getString(i));
				}
				outputList.l.add(outputList.map);
				outputList.map = new HashMap<String,String>();
				outputList.map = new HashMap<String,String>();
				Statement sm1 = Mysql.ct.createStatement();
				sql="select * from interfacecase where sceneId='" + rs.getString(1) +"' order by order_id";
				ResultSet rs1 = sm1.executeQuery(sql);
				while (rs1.next()) {
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
						outputList.map.put(rsmd1.getColumnName(i),rs1.getString(i));
					}
					outputList.l.add(outputList.map);
					outputList.map = new HashMap<String,String>();
				}
				outputList.map = new HashMap<String,String>();
			}
			
			sm=null;
			rs=null;
			/*
			System.out.println("size:"+outputList.l.size());
			for (int i=0;i<outputList.l.size();i++)
				System.out.println(outputList.l.get(i).get("id"));
				*/
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(sql);
			e.printStackTrace();
		}
	}
	
	public interfaceSearchservice(int l,int r){
		
	}
	
	public interfaceSearchservice(String s,int l,int r){
		
	}
}
