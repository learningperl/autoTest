package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.statics.Mysql;
import com.test.statics.outputList;

public class servletGetinfo extends HttpServlet {
	/**
	 * 获取用例信息的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::servletGetinfo:"+request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		String html = "null";
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		if (paramName.equals("name")) {
			html = getByName(request.getParameter(paramName));
			// System.out.println("??");
			out.print(html);
		} else if (paramName.equals("id")){
			html = getById(request.getParameter(paramName));
			out.print(html);
		}
		else if (paramName.equals("idadd")) {
			getByidadd(request.getParameter(paramName));
			out.print(outputList.list);
		} else if (paramName.equals("nameadd")) {
			getByNameadd(request.getParameter(paramName));
			out.print(outputList.list);
		} else
			outputList.list = "{\"error\":\"方法不支持\"}";
	}

	private void getByNameadd(String id) {//添加用例场景，获取最新场景id
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "SELECT MAX(casesId) FROM casescene";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			int str = 1;
			try {
				str = Integer.parseInt(rs.getString(1)) + 1;
			} catch (Exception e) {
				str = 1;
			}
			outputList.list += "\"casesId\":\"" + str + "\",";
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getByidadd(String id) {//添加用例，获取最新用例id
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "SELECT MAX(id) FROM caseoption";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			int str = 1;
			try {
				str = Integer.parseInt(rs.getString(1)) + 1;
			} catch (Exception e) {
				str = 1;
			}
			outputList.list += "\"casesId\":\"" + id + "\",\"id\":\"" + str
					+ "\",";
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getById(String id) {//编辑时，获取对应id的用例
		outputList.list = "{";
		outputList.l = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select * from caseoption where id='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				try {
					outputList.list += "\"" + rsmd.getColumnName(i) + "\":\""
							+ rs.getString(i).replaceAll("\"", "'") + "\",";
					map.put(rsmd.getColumnName(i), rs.getString(i).replaceAll("\"", "'"));
				} catch (Exception e) {
					outputList.list += "\"" + rsmd.getColumnName(i) + "\":\""
							+ rs.getString(i) + "\",";
					map.put(rsmd.getColumnName(i), rs.getString(i));
				}
			}
			// 如果输入里面有双引号，会被替换掉，显示不了双引号，请注意了。
			outputList.l.add(map);
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String html = "";
		String str = null;
		if (outputList.l.size() > 0) {
			int i = 0;
			if (outputList.l.get(i).get("id") != null)
				str = outputList.l.get(i).get("id");
			else
				str = "NULL";
			html += "<form id=\"cases_Edits\" onsubmit=\"AjaxSubmit('cases_Edits')\" method=\"post\"><a class=\"inedit_s\" >id</a><input id=\"id\" name=\"id\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\"  disabled=\"true\">";
			if (outputList.l.get(i).get("casesId") != null)
				str = outputList.l.get(i).get("casesId");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >casesId</a><input id=\"casesId\" name=\"casesId\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\"  disabled=\"true\">";
			if (outputList.l.get(i).get("order_id") != null)
				str = outputList.l.get(i).get("order_id");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >order_id</a><input id=\"order_id\" name=\"order_id\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("optionss") != null)
				str = outputList.l.get(i).get("optionss");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >optionss</a><input id=\"optionss\" name=\"optionss\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("caseDescription") != null)
				str = outputList.l.get(i).get("caseDescription");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >Description</a><input id=\"Description\" name=\"Description\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("runState") != null)
				str = outputList.l.get(i).get("runState");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >runState</a><input id=\"runState\" name=\"runState\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("xPath") != null)
				str = outputList.l.get(i).get("xPath");
			else
				str = "NULL";
			html += "<br><a class=\"inedit_s\" >xPath</a><input id=\"xPath\" name=\"xPath\" class=\"edit_input_l_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("datas") != null)
				str = outputList.l.get(i).get("datas");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >datas</a><input id=\"datas\" name=\"datas\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("checkName") != null)
				str = outputList.l.get(i).get("checkName");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >checkName</a><input id=\"checkName\" name=\"checkName\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("checkMethod") != null)
				str = outputList.l.get(i).get("checkMethod");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >checkMethod</a><input id=\"checkMethod\" name=\"checkMethod\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("expectedRes") != null)
				str = outputList.l.get(i).get("expectedRes");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >expectedRes</a><input id=\"expectedRes\" name=\"expectedRes\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("actualRes") != null)
				str = outputList.l.get(i).get("actualRes");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >actualRes</a><input id=\"actualRes\" name=\"actualRes\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("imgName") != null)
				str = outputList.l.get(i).get("imgName");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >imgName</a><input id=\"imgName\" name=\"imgName\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" > </form>";
		}
		html += "<a class=\"edit_save\" href=\"javascript:AjaxSubmit('cases_Edits');\">保存</a>";
		html += "<a class=\"edit_cancle\" href=\"#\" onclick='setInfo(\"id\",this)'>取消</a>";
		//System.out.println(html);
		return html;
	}

	private String getByName(String id) {//编辑时，获取对应id场景
		outputList.list = "{";
		outputList.l = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select * from casescene where casesId='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				map.put(rsmd.getColumnName(i), rs.getString(i));
				outputList.list += "\"" + rsmd.getColumnName(i) + "\":\""
						+ rs.getString(i) + "\",";
			}
			outputList.l.add(map);
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		String html = "";
		String str = null;
		if (outputList.l.size() > 0) {
			int i = 0;
			if (outputList.l.get(i).get("casesId") != null)
				str = outputList.l.get(i).get("casesId");
			else
				str = "NULL";
			html += "<form id=\"cases_Edits\" onsubmit=\"AjaxSubmit('cases_Edits')\" method=\"post\"><a class=\"inedit_s\" >casesId</a><input id=\"casesId\" name=\"casesId\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" disabled=\"true\">";
			if (outputList.l.get(i).get("casesN") != null)
				str = outputList.l.get(i).get("casesN");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >casesN</a><input id=\"casesN\" name=\"casesN\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("Browser") != null)
				str = outputList.l.get(i).get("Browser");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >Browser</a><input id=\"Browser\" name=\"Browser\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("Bpath") != null)
				str = outputList.l.get(i).get("Bpath");
			else
				str = "NULL";
			html += "<br><a>&nbsp&nbsp-&nbsp&nbsp&nbsp</a><a class=\"inedit_s\" >Bpath</a><input id=\"Bpath\" name=\"Bpath\" class=\"edit_input_l\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("runStates") != null)
				str = outputList.l.get(i).get("runStates");
			else
				str = "NULL";
			html += "<a>&nbsp&nbsp-&nbsp&nbsp&nbsp</a><a class=\"inedit_s\" >runStates</a><input id=\"runStates\" name=\"runStates\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" > </form>";
		}
		html += "<a class=\"edit_save\" href=\"javascript:AjaxSubmit('cases_Edits');\">保存</a>";
		html += "<a class=\"edit_cancle\" href=\"#\" onclick='setInfo(\"name\",this)'>取消</a>";
		// System.out.println(html);
		return html;
	}
}
