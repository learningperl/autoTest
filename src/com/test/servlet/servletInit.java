package com.test.servlet;

import javax.servlet.http.HttpServlet;

import com.test.statics.Mysql;
import com.test.statics.outputList;
import com.test.tools.ImageTool;

public class servletInit extends HttpServlet{
	/**
	 * 初始化
	 */
	private static final long serialVersionUID = 101;

	public void init() {
		new outputList();	//初始化静态数据类
		new Mysql();		//初始化Mysql类
		new ImageTool();	//初始化图形处理类
		//Mysql.Sort();
		outputList.logs="Server init secc!";
		System.out.println("Init secc!");
		return;
	}
}
