package com.test.KeyWord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class delKeyWords extends HttpServlet{
	/**
	 * 删除关键字
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		System.out.println("log::delKeyWords:"+request.getRequestURL().toString());
		String paramName = "";
		int str=0;
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		if (paramName.equals("id")) {
			str = delById(request.getParameter(paramName));
			//System.out.println("??");
		} else 
			System.out.print("方法不支持。");
		out.print(str);
	}

	private int delById(String id) {
		int rs=0;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql="";
			sql="delete from KeyWords where id='"+id+"'";
			rs = sm.executeUpdate(sql);
			sm=null;
		}catch (Exception e){
			e.printStackTrace();
		}
		return rs;
	}
}
