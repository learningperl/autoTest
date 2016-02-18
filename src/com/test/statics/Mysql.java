package com.test.statics;

import java.sql.*;

public class Mysql {

	public static Connection ct;

	public Mysql() {	//Mysql地址配置
		try {
			Class.forName("com.mysql.jdbc.Driver");
			ct = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/mytestcase?useUnicode=true&amp;characterEncoding=utf8", "root", "");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void Sort(){	//处理UI用例order_id不连续
		String id,casesId = "",sql;
		int count=0;
		try {
			Statement sm = Mysql.ct.createStatement();
			ResultSet rs = sm.executeQuery("select casesId from casescene");
			while (rs.next()) {
				rs.getMetaData();
				casesId = rs.getString(1);
				Statement sm1 = Mysql.ct.createStatement();
				ResultSet rs1 =null;
				
				sql="SELECT COUNT(id) FROM caseoption WHERE casesId="+casesId;
				rs1 = sm1.executeQuery(sql);
				rs1.next();
				try {
					count = Integer.parseInt(rs1.getString(1));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					count=0;
				}
				rs1=null;
				sql="SELECT MAX(order_id) FROM caseoption WHERE casesId="+casesId;
				rs1 = sm1.executeQuery(sql);
				rs1.next();
				try {
					Integer.parseInt(rs1.getString(1));
				} catch (NumberFormatException e) {
				}
				//System.out.println("max:"+max+"  count:"+count);
				if(count>1){
					rs1=null;
					sql="SELECT id FROM caseoption WHERE casesId="+casesId+" ORDER BY order_id DESC";
					rs1 = sm1.executeQuery(sql);
					while (rs1.next()) {
						id=rs1.getString(1);
						sql="update caseoption set order_id="+count+" where id="+id;
						Statement sm2 = Mysql.ct.createStatement();
						sm2.executeUpdate(sql);
						count--;
					}
				}
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
	
	public static void SortInterface(){	//处理接口测试用例order_id不连续
		String id,sceneId = "",sql;
		int count=0;
		try {
			Statement sm = Mysql.ct.createStatement();
			ResultSet rs = sm.executeQuery("select sceneId from interfacescene");
			while (rs.next()) {
				rs.getMetaData();
				sceneId = rs.getString(1);
				Statement sm1 = Mysql.ct.createStatement();
				ResultSet rs1 =null;
				
				sql="SELECT COUNT(id) FROM interfacecase WHERE sceneId="+sceneId;
				rs1 = sm1.executeQuery(sql);
				rs1.next();
				try {
					count = Integer.parseInt(rs1.getString(1));
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					count=0;
				}
				rs1=null;
				sql="SELECT MAX(order_id) FROM interfacecase WHERE sceneId="+sceneId;
				rs1 = sm1.executeQuery(sql);
				rs1.next();
				try {
					Integer.parseInt(rs1.getString(1));
				} catch (NumberFormatException e) {
				}
				//System.out.println("max:"+max+"  count:"+count);
				if(count>1){
					rs1=null;
					sql="SELECT id FROM interfacecase WHERE sceneId="+sceneId+" ORDER BY order_id DESC";
					rs1 = sm1.executeQuery(sql);
					while (rs1.next()) {
						id=rs1.getString(1);
						sql="update interfacecase set order_id="+count+" where id="+id;
						Statement sm2 = Mysql.ct.createStatement();
						sm2.executeUpdate(sql);
						count--;
					}
				}
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
}
