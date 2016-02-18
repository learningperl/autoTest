package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.servicerunTest;
import com.test.statics.Mysql;

public class debugCase extends HttpServlet {

	/**
	 * 调试UI用例的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String id = request.getParameter("id");
		System.out.println("log::debugCase:"+request.getRequestURL().toString());
		System.out.println(id);
		if (id.equals("") || id == null)
			out.print("id不存在，请重新输入。");
		else {
			if(!servicerunTest.runable){
				out.print("当前有用例正在执行。请稍后再调试。");
			}else{
			try {
				Statement sm = Mysql.ct.createStatement();
				//根据caseId查询用例
				String sql = "select * from caseoption where id=" + id;
				System.out.println(sql);
				ResultSet rs = sm.executeQuery(sql);
				servicerunTest.map=null;
				servicerunTest.map = new HashMap<String, String>();
				rs.next();
				ResultSetMetaData rsmd = rs.getMetaData();
				for (int j = 1; j <= rsmd.getColumnCount(); j++)
					servicerunTest.map.put(rsmd.getColumnName(j), rs.getString(j));
				System.out.println("正在运行。。。");
				sm=null;
				//运行用例
				servicerunTest.runCase();
				out.print("正在运行。。。");

			} catch (SQLException e) {
				e.printStackTrace();
				out.print("该用例不存在，请重新输入。");
			}
		}
		}
		//resp.sendRedirect("static/UI.jsp");
		return;
	}
}
