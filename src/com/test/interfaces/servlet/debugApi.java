package com.test.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.InterfacerunService;
import com.test.statics.responseList;

public class debugApi extends HttpServlet {

	/**
	 * 运行接口测试用例
	 */
	private static final long serialVersionUID = 101L;

	public void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		System.out.println("log::delInterfaceCase:"	+ request.getRequestURL().toString());
		
		InterfacerunService.debug(request.getParameter("url"), request.getParameter("param"), request.getParameter("selectOp"));
		
		out.print(responseList.json);
		
		return;
	}
	
}
