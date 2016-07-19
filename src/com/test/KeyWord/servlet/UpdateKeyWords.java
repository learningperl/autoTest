package com.test.KeyWord.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class UpdateKeyWords extends HttpServlet {
	/**
	 * 更新关键字
	 */
	private static final long serialVersionUID = 101;

	public void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::UpdateKeyWords:"
				+ request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		String id = "";
		String sqli = "";
		String sqlu = "";
		String type = "NULL";
		String ret = "";
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			id = request.getParameter("id");
			if (id.equals("NULL") || id.equals(""))
				id = null;
		} catch (Exception e) {
		}

		try {
			type = request.getParameter("type");
			if (type.equals("NULL") || type.equals(""))
				type = null;
		} catch (Exception e) {
		}
		if (id != null && type != null) {
			sqlu += "update keywords set id=" + id + ", type=" + type
					+ ", keyName='" + request.getParameter("keyName")
					+ "', describes='" + request.getParameter("describes")
					+ "', useCase='" + request.getParameter("useCase")
					+ "' where id='" + id + "'";
			sqli += "insert into keywords values(" + id + "," + type + ",'"
					+ request.getParameter("keyName") + "','"
					+ request.getParameter("describes") + "','"
					+ request.getParameter("useCase") + "')";
			ret = updateKeyWords(sqli, sqlu, id);
		} else{
			//System.out.println("关键字信息有误，请检查。");
			ret = "{\"error\":501,\"msg\":\"id或者type不能为空！\"}";
		}
		out.print(ret);
		//resp.sendRedirect("index.jsp");
	}

	private String updateKeyWords(String sqli, String sqlu, String id) {
		String str = "";
		String ret = "";
		// System.out.println(sqli);
		// System.out.println(sqlu);
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "select id from keywords where id='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				str = rs.getString(1);
			}
			if (str == "") {
				sm.executeUpdate(sqli);
			} else {
				sm.executeUpdate(sqlu);
			}
			rs = null;
			sm = null;
			ret="{\"error\":\"0\"}";
		} catch (Exception e) {
			ret = "{\"error\":\"501\",\"msg\":\"";
			System.out.println(sqlu);
			e.printStackTrace();
			String s=e.toString();
			s = s.substring(s.indexOf(":")+2, s.length());
			ret = ret + s + "\"}";
		}
		return ret;
	}

}
