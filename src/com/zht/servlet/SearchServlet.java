package com.zht.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zht.util.EntInfoList;
import com.zht.util.PopupCaptcha;

public class SearchServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of the object.
	 */
	public SearchServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}
	String[] tokenArray = null ;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 tokenArray = PopupCaptcha.getToken().split(",");
		 response.sendRedirect(request.getContextPath()+"/vc.jsp");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String captcha = request.getParameter("captcha");
		String content = EntInfoList.getResult(captcha, tokenArray[0] , tokenArray[1]);
		System.out.println(content);
	}
	
	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
