package com.test.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class delInterfaceCase extends HttpServlet {
	/**
	 * 删除接口用例
	 */
	private static final long serialVersionUID = -5857352318639105714L;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		System.out.println("log::delInterfaceCase:"
				+ request.getRequestURL().toString());
		int str = 0;
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		if (paramName.equals("name")) {
			str = delByName(request.getParameter(paramName));
			// System.out.println("??");
		} else if (paramName.equals("id"))
			str = delById(request.getParameter(paramName));
		out.print(str);
		resp.sendRedirect("static/Interface.jsp");
	}

	private int delById(String id) {
		int rs = 0;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "delete from interfacecase where id='" + id + "'";
			rs = sm.executeUpdate(sql);
			sm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Mysql.SortInterface();
		return rs;
	}

	private int delByName(String id) {
		int rs = 0;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "delete from interfacescene where sceneId='" + id + "'";
			rs = sm.executeUpdate(sql);
			sql = "delete from interfacecase where sceneId='" + id + "'";
			sm.executeUpdate(sql);
			sm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
}
