package com.test.UI.servlet;

import com.test.service.serviceSearch;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.test.statics.outputList;
import com.test.tools.encodeType;

public class servletSearch extends HttpServlet {
	
	/**
	 * 查询显示UI用例的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::servletSearch:"+request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
			// System.out.println(paramName + " = " +
			// request.getParameter(paramName));
			paramName = request.getParameter(paramName);
			try{
				//System.out.println(encodeType.getEncoding(paramName));
				//System.out.println(paramName.getBytes(encodeType.getEncoding(paramName)));
				paramName = new String(paramName.getBytes(encodeType.getEncoding(paramName)), encodeType.getEncoding(paramName));  
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		//System.out.println(paramName);
		if (paramName.equals("Search") || paramName.equals("")) {
			new serviceSearch();
		} else{
			//System.out.println(paramName);
			new serviceSearch(paramName);
		}
		String str = FlushTable();
		out.print(str);
	}

	private String FlushTable() {
		ArrayList<Map<String, String>> myList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		myList = outputList.l;
		String Text = "";
		if (myList.size() > 0) {
			for (int i = 0; i < myList.size();) {
				map = (Map<String, String>) myList.get(i);
				Text += "<input class=\"checkbox\" type=\"checkbox\" name=\"check\" value="+map.get("casesId")+" />";
				Text += "<details class=\"menu\">";
				Text += "<summary>"
						+ map.get("casesN")
						+ "<a name=\""
						+ map.get("casesId")
						+ "\" class=\"edit_c\" href=\"#\" onclick='GetInfo(\"name\",this.name,this);'>编辑</a>&nbsp&nbsp<a name=\""
						+ map.get("casesId")
						+ "\" class=\"edit_d\" href=\"#\" onclick='delCase(\"name\",this.name);'>删除</a>&nbsp&nbsp<a name=\""
						+ map.get("casesId")
						+ "\" class=\"edit_e\" href=\"#\" onclick='pops(\"pop\");GetInfo(\"idadd\",this.name,this);'>添加</a>"
						+ "<a class=\""+ map.get("runStates") +"\">" + map.get("runStates")
						+"</a></summary>";
				i++;
				if (i < myList.size()) {
					map = (Map<String, String>) myList.get(i);
					while (map.get("id") != null) {
						Text += "<ul>";
						Text += "<li><a class=\"edit_a\">"
								+ map.get("order_id")
								+ "</a> <a class=\"edit_b\">"
								+ map.get("caseDescription")
								+ "</a><a name="
								+ map.get("id")
								+ " class=\"edit_c\" href=\"#\" onclick='GetInfo(\"id\",this.name,this);'>编辑</a>"
								+"&nbsp&nbsp<a name=" + map.get("id")
								+ " class=\"edit_d\" href=\"#\" onclick='delCase(\"id\",this.name);'>删除</a>"
								+ "<img name=\"imgs/"
								+ map.get("imgName")+"\" class=\"edit_img\" src=\"./imgs/"
								+ map.get("imgName")
								+"\" onclick='pops(\"pop_img\");Show_img(this.name);'/>"
								+ "<a class=\""+ map.get("runState") +"\">" + map.get("runState")
								+"</a></li>";
						Text += "</ul>";
						i++;
						if (i < myList.size()) {
							map = (Map<String, String>) myList.get(i);
						} else
							break;
					}
				}
				Text += "</details>";
			}
		}
		;
		return Text;
	}
}
