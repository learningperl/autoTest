package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.statics.Mysql;

public class UpdateCase extends HttpServlet {
	/**
	 * 更新UI用例的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::UpdateCase:"
				+ request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		String id = "";
		String sqli = "";
		String sqlu = "";
		String casesId = "NULL";
		String order_id = "";
		int ret = 0;
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
			casesId = request.getParameter("casesId");
			if (casesId.equals("NULL") || casesId.equals(""))
				casesId = null;
			order_id = request.getParameter("order_id");
		} catch (Exception e) {
		}
		if (id != null && casesId != null) {
			sqlu += "update caseoption set casesId='" + casesId
					+ "', order_id='" + request.getParameter("order_id")
					+ "', optionss='" + request.getParameter("optionss")
					+ "', xPath='" + request.getParameter("xPath")
					+ "', datas='" + request.getParameter("datas")
					+ "', checkName='" + request.getParameter("checkName")
					+ "', checkMethod='" + request.getParameter("checkMethod")
					+ "', expectedRes='" + request.getParameter("expectedRes")
					+ "', actualRes='" + request.getParameter("actualRes")
					+ "', imgName='" + request.getParameter("imgName")
					+ "', caseDescription='"
					+ request.getParameter("Description") + "', runState='"
					+ request.getParameter("runState") + "' where id='" + id
					+ "'";
			sqli += "insert into caseoption values(" + id + "," + casesId + ","
					+ request.getParameter("order_id") + ",'"
					+ request.getParameter("optionss") + "','"
					+ request.getParameter("xPath") + "','"
					+ request.getParameter("datas") + "','"
					+ request.getParameter("checkName") + "','"
					+ request.getParameter("checkMethod") + "','"
					+ request.getParameter("expectedRes") + "','"
					+ request.getParameter("actualRes") + "','"
					+ request.getParameter("imgName") + "','"
					+ request.getParameter("Description") + "','"
					+ request.getParameter("runState") + "')";
			ret = updateCaseSql(sqli, sqlu, id, casesId, order_id);
		} else if (casesId != null) {
			sqlu += "update casescene set casesN='"
					+ request.getParameter("casesN") + "', Browser='"
					+ request.getParameter("Browser") + "', Bpath='"
					+ request.getParameter("Bpath") + "', runStates='"
					+ request.getParameter("runStates") + "' where casesId='"
					+ casesId + "'";
			sqli += "insert into casescene values(" + casesId + ",'"
					+ request.getParameter("casesN") + "','"
					+ request.getParameter("Browser") + "','"
					+ request.getParameter("Bpath") + "','"
					+ request.getParameter("runStates") + "')";
			ret = updateSceneSql(sqli, sqlu, casesId);
		} else
			System.out.println("方法不支持。");
		out.print(ret);
		resp.sendRedirect("static/UI.jsp");
	}

	private int updateCaseSql(String sqli, String sqlu, String id,
			String casesId, String order_id) {
		String str = "";
		// System.out.println(sqlu);
		// System.out.println(sqli);
		int rs1;
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "select casesId from caseoption where id='" + id + "'";
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
		} catch (Exception e) {
			rs1 = 0;
			System.out.println(sqlu);
			e.printStackTrace();
		}

		if (rs1 == 1) {
			String sql = "update caseoption set order_id=order_id+1 where casesId="
					+ casesId + " and id<>" + id + " and order_id>=" + order_id;
			try {
				// System.out.println(sql);
				Statement sm = Mysql.ct.createStatement();
				sm.executeUpdate(sql);
				sm = null;
				Mysql.Sort();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return rs1;
	}

	private int updateSceneSql(String sqli, String sqlu, String id) {
		String str = "";
		int rs1;
		// System.out.println(sqlu);
		// System.out.println(sqli);
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "select casesN from casescene where casesId='" + id
					+ "'";
			ResultSet rs = sm.executeQuery(sql);
			if (rs.next())
				str = rs.getString(1);
			rs = null;
			sm = null;
			Statement sm1 = Mysql.ct.createStatement();
			if (str == "") {
				rs1 = sm1.executeUpdate(sqli);
			} else {
				rs1 = sm1.executeUpdate(sqlu);
			}
		} catch (Exception e) {
			rs1 = 0;
			e.printStackTrace();
		}

		return rs1;

	}

}
