package com.test.service;

import javax.servlet.http.HttpServlet;

import com.test.statics.Mysql;
import com.test.statics.outputList;
import com.test.statics.property;
import com.test.statics.responseList;
import com.test.tools.ImageTool;

public class servletInit extends HttpServlet{
	/**
	 * 初始化
	 */
	private static final long serialVersionUID = 101;

	public void init() {
		new property();
		new outputList();	//初始化静态数据类
		new Mysql();		//初始化Mysql类
		new responseList(); //初始化接口数据静态
		//Mysql.Sort();
		if(property.readRcErpURL("DISPLAY").toString().equals("true"))
			new ImageTool();	//初始化图形处理类
		outputList.logs="Server init secc!";
		System.out.println("log:info::Init secc!");
		return;
	}
}
