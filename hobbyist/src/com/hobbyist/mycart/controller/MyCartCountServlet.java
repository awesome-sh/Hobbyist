package com.hobbyist.mycart.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.mycart.model.service.MyCartService;

@WebServlet("/myCartCount")
public class MyCartCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCartCountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		
		int count = new MyCartService().selectCount(member);
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(count);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
