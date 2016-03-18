package com.test.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class getOptions extends HttpServlet {
	/**
	 * 删除接口用例
	 */
	private static final long serialVersionUID = -5857352318639105714L;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String l = "";
		System.out.println("log::getOptions:"
				+ request.getRequestURL().toString());
		l = queryOptions();
		out.print(l);
	}
	
	public String queryOptions(){
		String l = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			ResultSet rs = sm.executeQuery("select keyName from keywords where type=2");	//查询用例场景
			while (rs.next()) {
				l+=rs.getString(1)+",";
			}
			
			System.out.println(l);
			l=l.substring(0,l.length()-1);
			sm=null;
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
	}
}