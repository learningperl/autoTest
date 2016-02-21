package com.test.KeyWord.servlet;

import com.test.service.serviceKey;
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

public class SearchKey extends HttpServlet {
	/**
	 * 查询显示关键字
	 */
	private static final long serialVersionUID = 101;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::SearchKey:"+request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		Enumeration<?> en = request.getParameterNames();
		String paramName = "";
		while (en.hasMoreElements()) {
			paramName = (String) en.nextElement();
			// //System.out.println(paramName + " = " +
			// request.getParameter(paramName));
			paramName = request.getParameter(paramName);
			try {
				// //System.out.println(encodeType.getEncoding(paramName));
				// //System.out.println(paramName.getBytes(encodeType.getEncoding(paramName)));
				paramName = new String(paramName.getBytes(encodeType
						.getEncoding(paramName)),
						encodeType.getEncoding(paramName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		////System.out.println(paramName);
		if (paramName.equals("Search") || paramName.equals("")) {
			new serviceKey();
		} else {
			// //System.out.println(paramName);
			new serviceKey(paramName);
		}
		String str = FlushTable();
		out.print(str);
	}

	private String FlushTable() {
		ArrayList<Map<String, String>> myList = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		myList = outputList.l_key;
		int t = -1;
		int type = 0;
		String d = "";
		String Text = "";
		//System.out.println(myList.size());
		if (myList.size() > 0) {
			//System.out.println("size大于0");
			map = (Map<String, String>) myList.get(0);
			type = Integer.parseInt(map.get("type").toString());
			for (int i = 0; i < myList.size();) {
				//System.out.println(type);
				if (type > t) {
					//System.out.println("type大于-1");
					t = type;
					switch (t) {
					case 0:
						d = "UI界面操作";
						break;
					case 1:
						d = "UI结果处理";
						break;
					case 2:
						d = "接口测试操作";
						break;
					case 3:
						d = "接口结果处理";
						break;
					default:
						d = "请在SearchKey的servlet中添加type=" + t + "的描述。";
						System.out.println("请在SearchKey的servlet中添加type=" + t
								+ "的描述。");
					}
					Text += "<details class=\"menu\" open>";
					Text += "<summary>"
							+ d
							+ "</summary>";
					//System.out.println("出了一个");
				}
				if (map.get("id") != null) {
					Text += "<ul>";
					Text += "<li><a class=\"edit_a\">"
							+ map.get("id")
							+ "</a> <a class=\"edit_b\">"
							+ map.get("keyName")
							+ "</a><a name="
							+ map.get("id")
							+ " class=\"edit_c\" href=\"#\" onclick='pops(\"pop1\");GetKey(\"id\",this.name);'>编辑</a>"
							+ "&nbsp&nbsp<a name="
							+ map.get("id")
							+ " class=\"edit_d\" href=\"#\" onclick='delKey(\"id\",this.name);'>删除</a>"
							+ "</li>";
					Text += "</ul>";
				}
				i++;
				if(i<myList.size()){
					map = (Map<String, String>) myList.get(i);
					type = Integer.parseInt(map.get("type").toString());
				}else
					break;
				if (type > t) {
					Text += "</details>";
				}
			}
			Text += "</details>";
		}
		//System.out.println(Text);
		return Text;
	}
}
