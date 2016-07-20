package com.test.KeyWord.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.serviceKey;
import com.test.statics.outputList;

public class keySet extends HttpServlet {
	/**
	 * 删除UI用例的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		new serviceKey(true);
		int i=outputList.keySet.size();
		//System.out.println("size=" + i);
		String name = "key";
		ArrayList<String> keySet =null;
		while(i>0){
			keySet = new ArrayList<String>();
			keySet = outputList.keySet.get(i-1);
			//System.out.println(keySet.toString() + ";");
			 Cookie c1=new Cookie(name+i,keySet.toString().substring(1, keySet.toString().length()-1));
			 resp.addCookie(c1);
			i--;
		}
		//resp.sendRedirect("static/UI.jsp");
	}
}
