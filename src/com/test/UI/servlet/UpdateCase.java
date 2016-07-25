package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;

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
		String ret = "";
		Map<String,String> map = null;
		map = Mysql.disposeRequest(request);
		try {
			id = map.get("id");
			if (id.equals("NULL") || id.equals(""))
				id = null;
		} catch (Exception e) {
		}

		try {
			casesId = map.get("casesId");
			if (casesId.equals("NULL") || casesId.equals(""))
				casesId = null;
			order_id = map.get("order_id");
		} catch (Exception e) {
		}
		if (id != null && casesId != null) {
			sqlu += "update caseoption set casesId='" + casesId
					+ "', order_id='" + map.get("order_id")
					+ "', optionss='" + map.get("optionss")
					+ "', xPath='" + map.get("xPath")
					+ "', datas='" + map.get("datas")
					+ "', checkName='" + map.get("checkName")
					+ "', checkMethod='" + map.get("checkMethod")
					+ "', expectedRes='" + map.get("expectedRes")
					+ "', actualRes='" + map.get("actualRes")
					+ "', imgName='" + map.get("imgName")
					+ "', caseDescription='"
					+ map.get("Description") + "', runState='"
					+ map.get("runState") + "' where id='" + id
					+ "'";
			sqli += "insert into caseoption values(" + id + "," + casesId + ","
					+ map.get("order_id") + ",'"
					+ map.get("optionss") + "','"
					+ map.get("xPath") + "','"
					+ map.get("datas") + "','"
					+ map.get("checkName") + "','"
					+ map.get("checkMethod") + "','"
					+ map.get("expectedRes") + "','"
					+ map.get("actualRes") + "','"
					+ map.get("imgName") + "','"
					+ map.get("Description") + "','"
					+ map.get("runState") + "')";
			ret = updateCaseSql(sqli, sqlu, id, casesId, order_id);
		} else if (casesId != null) {
			sqlu += "update casescene set casesN='"
					+ map.get("casesN") + "', Browser='"
					+ map.get("Browser") + "', Bpath='"
					+ map.get("Bpath") + "', runStates='"
					+ map.get("runStates") + "' where casesId='"
					+ casesId + "'";
			sqli += "insert into casescene values(" + casesId + ",'"
					+ map.get("casesN") + "','"
					+ map.get("Browser") + "','"
					+ map.get("Bpath") + "','"
					+ map.get("runStates") + "')";
			ret = updateSceneSql(sqli, sqlu, casesId);
		} else{
			ret = "{\"error\":501,\"msg\":\"主键字段不能为空！\"}";
		}
		out.print(ret);
		//resp.sendRedirect("static/UI.jsp");
	}

	private String updateCaseSql(String sqli, String sqlu, String id,
			String casesId, String order_id) {
		String str = "";
		//System.out.println(sqlu);
		//System.out.println(sqli);
		int rs1;
		String ret="";
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
			ret="{\"error\":\"0\"}";
		} catch (Exception e) {
			rs1 = 0;
			ret = "{\"error\":\"501\",\"msg\":\"";
			System.out.println(sqlu);
			//e.printStackTrace();
			String s=e.toString();
			//System.out.println(s);
			s = s.substring(s.indexOf(":")+2, s.length());
			ret = ret + s + "\"}";
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
				//e.printStackTrace();
				System.out.println("排序时出错！");
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
			String sql = "select casesN from casescene where casesId='" + id
					+ "'";
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
