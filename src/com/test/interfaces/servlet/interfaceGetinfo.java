package com.test.interfaces.servlet;

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

public class interfaceGetinfo extends HttpServlet {
	/**
	 * 获取接口测试用例信息
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::interfaceGetinfo:"+request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		String html = "null";
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
		}
		//System.out.println(paramName);
		switch(paramName){
		case "name":
			html = getByName(request.getParameter(paramName));
			out.print(html);
			break;
		case "id":
			html = getById(request.getParameter(paramName));
			out.print(html);
			break;
		case "idadd":
			getByidadd(request.getParameter(paramName));
			out.print(outputList.list);
			break;
		case "nameadd":
			getByNameadd(request.getParameter(paramName));
			out.print(outputList.list);
			break;
		case "getJson":
			getJson(request.getParameter(paramName));
			// System.out.println("??");
			out.print(outputList.list);
			break;
		default:
			outputList.list = "{\"error\":\"方法不支持\"}";
			out.print(outputList.list);
		}
	}

	private void getByNameadd(String id) {
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "SELECT MAX(sceneId) FROM interfacescene";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			int str = 1;
			try {
				str = Integer.parseInt(rs.getString(1)) + 1;
			} catch (Exception e) {
				str = 1;
			}
			outputList.list += "\"sceneId\":\"" + str + "\",";
			outputList.list = outputList.list.substring(0,
					outputList.list.length() - 1);
			outputList.list += "}";
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getByidadd(String id) {
		outputList.list = "{";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "SELECT MAX(id) FROM interfacecase";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			int str = 1;
			try {
				str = Integer.parseInt(rs.getString(1)) + 1;
			} catch (Exception e) {
				str = 1;
			}
			outputList.list += "\"sceneId\":\"" + id + "\",\"id\":\"" + str
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
	
	private void getJson(String id) {
		outputList.list = "";
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select jsonResult from interfacecase where id='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			outputList.list =rs.getString(1);
			sm = null;
			rs = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getById(String id) {
		outputList.list = "{";
		outputList.l = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select * from interfacecase where id='" + id + "'";
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
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("sceneId") != null)
				str = outputList.l.get(i).get("sceneId");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >sceneId</a><input id=\"sceneId\" name=\"sceneId\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("order_id") != null)
				str = outputList.l.get(i).get("order_id");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >order_id</a><input id=\"order_id\" name=\"order_id\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("method") != null)
				str = outputList.l.get(i).get("method");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >method</a><input id=\"method\" name=\"method\" class=\"edit_input_id\" type=\"text\" value=\""
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
			if (outputList.l.get(i).get("url") != null)
				str = outputList.l.get(i).get("url");
			else
				str = "NULL";
			html += "<br><a class=\"inedit_s\" >url</a><input id=\"url\" name=\"url\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("parameter") != null)
				str = outputList.l.get(i).get("parameter");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >parameter</a><input id=\"parameter\" name=\"parameter\" class=\"edit_input_l_id\" type=\"text\" value=\""
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
			if (outputList.l.get(i).get("expRes") != null)
				str = outputList.l.get(i).get("expRes");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >expRes</a><input id=\"expRes\" name=\"expRes\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("actualRes") != null)
				str = outputList.l.get(i).get("actualRes");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >actualRes</a><input id=\"actualRes\" name=\"actualRes\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("jsonResult") != null)
				str = outputList.l.get(i).get("jsonResult");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >jsonResult</a><input id=\"jsonResult\" name=\"jsonResult\" class=\"edit_input_id\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" > </form>";
		}
		html += "<a class=\"edit_save\" href=\"javascript:AjaxSubmit('cases_Edits');\">保存</a>";
		html += "<a class=\"edit_cancle\" href=\"#\" onclick='setInfo(\"id\",this)'>取消</a>";
		//System.out.println(html);
		return html;
	}

	private String getByName(String id) {
		outputList.list = "{";
		outputList.l = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		try {
			Statement sm = Mysql.ct.createStatement();
			String sql = "";
			sql = "select * from interfacescene where sceneId='" + id + "'";
			ResultSet rs = sm.executeQuery(sql);
			rs.next();
			ResultSetMetaData rsmd = rs.getMetaData();
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				map.put(rsmd.getColumnName(i), rs.getString(i).toString());
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
			if (outputList.l.get(i).get("sceneId") != null)
				str = outputList.l.get(i).get("sceneId");
			else
				str = "NULL";
			html += "<form id=\"cases_Edits\" onsubmit=\"AjaxSubmit('cases_Edits')\" method=\"post\"><a class=\"inedit_s\" >sceneId</a><input id=\"sceneId\" name=\"sceneId\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("sceneDescription") != null)
				str = outputList.l.get(i).get("sceneDescription");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >Description</a><input id=\"Description\" name=\"Description\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("runStates") != null)
				str = outputList.l.get(i).get("runStates");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >runStates</a><input id=\"runStates\" name=\"runStates\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" >";
			if (outputList.l.get(i).get("delay") != null)
				str = outputList.l.get(i).get("delay");
			else
				str = "0";
			str = Integer.toString(Integer.parseInt(str));
			//System.out.println(str);
			html += "<br><a>&nbsp&nbsp-&nbsp&nbsp&nbsp</a><a class=\"inedit_s\" >delay</a><input id=\"delay\" name=\"delay\" class=\"edit_input\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='' || this.value=='undefined'){this.value='0'}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='0'}\" >";
			if (outputList.l.get(i).get("url") != null)
				str = outputList.l.get(i).get("url");
			else
				str = "NULL";
			html += "<a class=\"inedit_s\" >url</a><input id=\"url\" name=\"url\" class=\"edit_input_l\" type=\"text\" value=\""
					+ str
					+ "\" onfocus=\"if(this.value=='NULL' || this.value=='undefined'){this.value=''}\" onblur=\"if(this.value=='' || this.value=='undefined'){this.value='NULL'}\" > </form>";
		}
		html += "<a class=\"edit_save\" href=\"javascript:AjaxSubmit('cases_Edits');\">保存</a>";
		html += "<a class=\"edit_cancle\" href=\"#\" onclick='setInfo(\"name\",this)'>取消</a>";
		// System.out.println(html);
		return html;
	}
}
