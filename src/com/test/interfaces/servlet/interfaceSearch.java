package com.test.interfaces.servlet;

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

import com.test.service.interfaceSearchservice;
import com.test.statics.outputList;
import com.test.tools.encodeType;

public class interfaceSearch extends HttpServlet {
	/**
	 * 查询和显示接口用例	
	 */
	private static final long serialVersionUID = 101;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::interfaceSearch:"+request.getRequestURL().toString());
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
			new interfaceSearchservice();
		} else{
			//System.out.println(paramName);
			new interfaceSearchservice(paramName);
		}
		String str = FlushTable();
		out.print(str);
		return;
	}

	private String FlushTable() {
		ArrayList<Map<String, String>> myList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		myList = outputList.l;
		String Text = "";
		String url="";
		if (myList.size() > 0) {
			for (int i = 0; i < myList.size();) {
				map = (Map<String, String>) myList.get(i);
				try{
					url=map.get("url").substring(0,90);
				}catch(Exception e){
					url=map.get("url");
				}
				Text += "<input class=\"checkbox\" type=\"checkbox\" name=\"check\" value="+map.get("sceneId")+" />";
				Text += "<details class=\"menu\">";
				Text += "<summary>"
						+ map.get("sceneDescription") + "&nbsp&nbsp("
						+ url
						+ ")<a name=\""
						+ map.get("sceneId")
						+ "\" class=\"edit_c\" href=\"#\" onclick='interfaceGetInfo(\"name\",this.name,this);'>编辑</a>&nbsp&nbsp<a name=\""
						+ map.get("sceneId")
						+ "\" class=\"edit_d\" href=\"#\" onclick='delInterfaceCase(\"name\",this.name);'>删除</a>&nbsp&nbsp<a name=\""
						+ map.get("sceneId")
						+ "\" class=\"edit_e\" href=\"#\" onclick='pops(\"pop\");interfaceGetInfo(\"idadd\",this.name,this);'>添加</a>"
						+ "<a class=\""+ map.get("runStates") +"\">" + map.get("runStates")
						+"</a></summary>";
				i++;
				if (i < myList.size()) {
					map = (Map<String, String>) myList.get(i);
					while (map.get("id") != null) {
						try{
							url=map.get("url").substring(0,70);
						}catch(Exception e1){
							url=map.get("url");
						}
						Text += "<ul>";
						Text += "<li><a class=\"edit_a\">"
								+ map.get("order_id")
								+ "</a> <a class=\"edit_b\">"
								+ map.get("caseDescription")
								+ "</a>"
								+ "<a class=\"edit_b\">("
								+ url
								+")</a><a name="
								+ map.get("id")
								+ " class=\"edit_c\" href=\"#\" onclick='interfaceGetInfo(\"id\",this.name,this);'>编辑</a>"
								+"&nbsp&nbsp<a name=" + map.get("id")
								+ " class=\"edit_d\" href=\"#\" onclick='delInterfaceCase(\"id\",this.name);'>删除</a>"
								+ "<img name=\"imgsjson\" id=\""
								+ map.get("id")
								+"\" class=\"edit_img\" src=\"/WebTester/static/Images/Icons/16/imgjson.png"
								+"\" onclick='pops(\"pop_json\");Show_json(this.id);'/>"
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
