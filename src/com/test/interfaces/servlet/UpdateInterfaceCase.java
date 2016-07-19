package com.test.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class UpdateInterfaceCase extends HttpServlet {
	/**
	 * 更新接口测试用例
	 */
	private static final long serialVersionUID = 101;

	public void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::UpdateInterfaceCase:"
				+ request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		String id = "";
		String sqli = "";
		String sqlu = "";
		String sceneId = "NULL";
		String order_id = "";
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
			sceneId = request.getParameter("sceneId");
			if (sceneId.equals("NULL") || sceneId.equals(""))
				sceneId = null;
			order_id = request.getParameter("order_id");
		} catch (Exception e) {
		}
		if (id != null && sceneId != null) {
			sqlu += "update interfacecase set sceneId='" + sceneId
					+ "', order_id='" + request.getParameter("order_id")
					+ "', method='" + request.getParameter("method")
					+ "', url='" + request.getParameter("url")
					+ "', parameter='" + request.getParameter("parameter")
					+ "', checkName='" + request.getParameter("checkName")
					+ "', checkMethod='" + request.getParameter("checkMethod")
					+ "', expRes='" + request.getParameter("expRes")
					+ "', actualRes='" + request.getParameter("actualRes")
					+ "', jsonResult='" + request.getParameter("jsonResult")
					+ "', caseDescription='"
					+ request.getParameter("Description") + "', runState='"
					+ request.getParameter("runState") + "' where id='" + id
					+ "'";
			sqli += "insert into interfacecase values(" + id + ","
					+ request.getParameter("order_id") + "," + sceneId + ",'"
					+ request.getParameter("method") + "','"
					+ request.getParameter("url") + "','"
					+ request.getParameter("parameter") + "','"
					+ request.getParameter("checkName") + "','"
					+ request.getParameter("checkMethod") + "','"
					+ request.getParameter("expRes") + "','"
					+ request.getParameter("actualRes") + "','"
					+ request.getParameter("runState") + "','"
					+ request.getParameter("jsonResult") + "','"
					+ request.getParameter("Description") + "')";
			ret = updateCaseSql(sqli, sqlu, id, sceneId, order_id);
		} else if (sceneId != null) {
			sqlu += "update interfacescene set sceneDescription='"
					+ request.getParameter("Description") + "', url='"
					+ request.getParameter("url") + "', delay="
					+ request.getParameter("delay") + ", runStates='"
					+ request.getParameter("runStates") + "' where sceneId='"
					+ sceneId + "'";
			sqli += "insert into interfacescene values(" + sceneId + ",'"
					+ request.getParameter("Description") + "','"
					+ request.getParameter("url") + "',"
					+ request.getParameter("delay") + ",'"
					+ request.getParameter("runStates") + "')";
			ret = updateSceneSql(sqli, sqlu, sceneId);
		} else{
			ret = "{\"error\":501,\"msg\":\"主键不能为空！\"}";;
		}
		out.print(ret);
		//resp.sendRedirect("static/Interface.jsp");
	}

	private String updateCaseSql(String sqli, String sqlu, String id,
			String sceneId, String order_id) {
		String str = "";
		String ret="";
		int rs1;
		// System.out.println(sqlu);
		// System.out.println(sqli);
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "select sceneId from interfacecase where id='" + id
					+ "'";
			ResultSet rs = sm.executeQuery(sql);
			while (rs.next()) {
				str = rs.getString(1);
			}
			rs = null;
			sm = null;
			Statement sm1 = Mysql.ct.createStatement();
			if (str == "") {
				rs1 = sm1.executeUpdate(sqli);
			} else {
				rs1 = sm1.executeUpdate(sqlu);
			}
			ret="{\"error\":\"0\"}";
		} catch (Exception e) {
			rs1 = 0;
			ret = "{\"error\":\"501\",\"msg\":\"";
			System.out.println(sqlu);
			e.printStackTrace();
			String s=e.toString();
			s = s.substring(s.indexOf(":")+2, s.length());
			ret = ret + s + "\"}";
		}

		if (rs1 == 1) {
			String sql = "update interfacecase set order_id=order_id+1 where sceneId="
					+ sceneId + " and id<>" + id + " and order_id>=" + order_id;
			try {
				// System.out.println(sql);
				Statement sm = Mysql.ct.createStatement();
				sm.executeUpdate(sql);
				sm = null;
				Mysql.Sort();
			} catch (Exception e) {
				System.out.println("排序时出错！");
				//e.printStackTrace();
			}
		}

		return ret;
	}

	private String updateSceneSql(String sqli, String sqlu, String id) {
		String str = "";
		String ret="";
		// System.out.println(sqlu);
		// System.out.println(sqli);
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "select sceneDescription from interfacescene where sceneId='"
					+ id + "'";
			ResultSet rs = sm.executeQuery(sql);
			if (rs.next())
				str = rs.getString(1);
			rs = null;
			sm = null;
			Statement sm1 = Mysql.ct.createStatement();
			if (str == "") {
				sm1.executeUpdate(sqli);
			} else {
				sm1.executeUpdate(sqlu);
			}
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
