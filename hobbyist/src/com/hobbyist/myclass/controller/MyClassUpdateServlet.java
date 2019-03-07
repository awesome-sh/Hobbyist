package com.hobbyist.myclass.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.myclass.model.service.MyClassService;

@WebServlet("/myClassUpdate")
public class MyClassUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyClassUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		String member = request.getParameter("member");
		
		/* int result = new MyClassService().updateClass(no, member); */
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
