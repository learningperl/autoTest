package com.test.interfaces.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.InterfacerunService;

public class runInterfaceTest extends HttpServlet {

	/**
	 * 运行接口测试用例
	 */
	private static final long serialVersionUID = -5768583151966933548L;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		System.out.println("log::runInterfaceTest:"
				+ request.getRequestURL().toString());
		PrintWriter out = resp.getWriter();
		String[] check = request.getParameterValues("check");
		try {
			if (InterfacerunService.state.equals("初始化"))
				new InterfacerunService();
		} catch (Exception e) {
			new InterfacerunService();
		}
		if (check == null || check.length < 1) {
			out.print("您还没有选择用例。");
		} else {
			if (!InterfacerunService.runable) {
				out.print("当前有用例正在执行。请稍后再试。");
			} else {
				InterfacerunService.run(check);
				System.out.println(InterfacerunService.state);
				out.print(InterfacerunService.state);
			}
		}
		// resp.sendRedirect("static/charts.jsp");
		return;
	}
}
