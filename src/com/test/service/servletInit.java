package com.test.service;

import javax.servlet.http.HttpServlet;

import com.test.statics.Mysql;
import com.test.statics.outputList;
import com.test.statics.responseList;
import com.test.tools.ImageTool;
import com.test.tools.property;

public class servletInit extends HttpServlet{
	/**
	 * 初始化
	 */
	private static final long serialVersionUID = 101;

	public void init() {
		new outputList();	//初始化静态数据类
		new Mysql();		//初始化Mysql类
		new responseList(); //初始化接口数据静态类
		//Mysql.Sort();
		property pro =new property();
		if(pro.readRcErpURL("DISPLAY").toString().equals("true"))
			new ImageTool();	//初始化图形处理类
		outputList.logs="Server init secc!";
		System.out.println("log:info::Init secc!");
		return;
	}
}
