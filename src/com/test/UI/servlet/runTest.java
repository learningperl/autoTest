package com.test.UI.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.servicerunTest;

public class runTest extends HttpServlet {

	/**
	 * 执行用例场景的servlet
	 */
	private static final long serialVersionUID = 101;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::runTest:"+request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		String[] check = request.getParameterValues("check");
		try {
			if (servicerunTest.state.equals("初始化"))
				new servicerunTest();
		} catch (Exception e) {
			new servicerunTest();
		}
		if (check == null || check.length < 1) {
			out.print("您还没有选择用例。");
		} else {
			if (!servicerunTest.runable) {
				out.print("当前有用例正在执行。请稍后再调试。");
			} else {
				servicerunTest.run(check);
				System.out.println(servicerunTest.state);
				out.print(servicerunTest.state);
			}
		}
		// resp.sendRedirect("static/charts.jsp");
		return;
	}
}
