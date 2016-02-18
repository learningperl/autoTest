package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class delCase extends HttpServlet {
	/**
	 * 删除UI用例的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		System.out
				.println("log::delCase:" + request.getRequestURL().toString());
		int str = 0;
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		// 获取要删除的是用例场景还是用例
		if (paramName.equals("name")) {
			str = delByName(request.getParameter(paramName));
			// System.out.println("??");
		} else if (paramName.equals("id"))
			str = delById(request.getParameter(paramName));
		out.print(str);
	}

	private int delById(String id) {// 删除用例
		int rs = 0;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "delete from caseoption where id='" + id + "'";
			rs = sm.executeUpdate(sql);
			sm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//删除后整理order_id
		Mysql.Sort();
		return rs;
	}

	private int delByName(String id) {// 删除用例场景和场景对应的用例
		int rs = 0;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "delete from casescene where casesId='" + id + "'";
			rs = sm.executeUpdate(sql);
			sql = "delete from caseoption where casesId='" + id + "'";
			sm.executeUpdate(sql);
			sm = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;

	}
}
