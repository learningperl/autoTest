package com.test.KeyWord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;
import com.test.statics.outputList;

public class GetKeyWords extends HttpServlet {
	/**
	 * 获取关键字信息
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		System.out.println("log::GetKeyWords:"
				+ request.getRequestURL().toString());
		String paramName = "";
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		if (paramName.equals("id")) {
			getById(request.getParameter(paramName));
			// System.out.println("??");
		} else if (paramName.equals("idadd"))
			getByidadd();
		else
			outputList.list = "{\"error\":\"方法不支持\"}";
		out.print(outputList.list);
	}

	private void getByidadd() {
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "SELECT MAX(id) FROM KeyWords";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			int str = 1;
			try {
				str = Integer.parseInt(rs.getString(1)) + 1;
			} catch (Exception e) {
				str = 1;
			}
			outputList.list += "\"id\":\"" + str + "\"";
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getById(String id) {
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select * from KeyWords where id='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					outputList.list += "\"" + rsmd.getColumnName(i) + "\":\""
							+ rs.getString(i) + "\",";
				}
				// 如果输入里面有双引号，会被替换掉，显示不了双引号，请注意了。
			}
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
