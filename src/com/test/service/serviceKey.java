package com.test.service;

import com.test.statics.Mysql;
import com.test.statics.outputList;

import java.util.HashMap;
import java.sql.*;

public class serviceKey {
	
	
	public serviceKey(){				//查询关键字
		outputList.l_key.clear();
		outputList.map = new HashMap<String,String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			ResultSet rs = sm.executeQuery("select DISTINCT type from KeyWords  order by type");
			while (rs.next()) {
				Statement sm1 = Mysql.ct.createStatement();
				//System.out.println("+1");
				sql="select * from KeyWords where type='" + rs.getString(1) +"' order by id";
				ResultSet rs1 = sm1.executeQuery(sql);
				while (rs1.next()) {
					ResultSetMetaData rsmd1 = rs1.getMetaData();
					for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
						outputList.map.put(rsmd1.getColumnName(i),rs1.getString(i));
					}
					outputList.l_key.add(outputList.map);
					outputList.map = new HashMap<String,String>();
				}
			}
			/*
			System.out.println("size:"+outputList.l_key.size());
			for (int i=0;i<outputList.l_key.size();i++)
				System.out.println(outputList.l_key.get(i).get("id"));
				*/
			sm=null;
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public serviceKey(String p){		//按条件查询关键字
		outputList.l_key.clear();
		outputList.map = new HashMap<String,String>();
		String sql = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			sql = "SELECT id FROM KeyWords WHERE describes LIKE '%"+p+"%' or keyName LIKE '%"+p+"%'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				Statement sm2 = Mysql.ct.createStatement();
				ResultSet rs2 = sm2.executeQuery("select DISTINCT type from KeyWords order by type");
				while (rs2.next()) {
					Statement sm1 = Mysql.ct.createStatement();
					sql="select * from KeyWords where id='"+rs.getString(1)+"' and type='" + rs2.getString(1) +"' order by id";
					ResultSet rs1 = sm1.executeQuery(sql);
					while (rs1.next()) {
						ResultSetMetaData rsmd1 = rs1.getMetaData();
						for (int i = 1; i <= rsmd1.getColumnCount(); i++) {
							outputList.map.put(rsmd1.getColumnName(i),rs1.getString(i));
						}
						outputList.l_key.add(outputList.map);
						outputList.map = new HashMap<String,String>();
					}
				}
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
	
	public serviceKey(int l,int r){
		
	}
	
	public serviceKey(String s,int l,int r){
		
	}
}
