package com.hobbyist.oneday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;

@WebServlet("/oneday/onedayCateInsert")
public class OnedayWriteInsertCateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OnedayWriteInsertCateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newCate = request.getParameter("newCate");
		
		int result = new OnedayService().insertCate(newCate);
		
		String msg = "";
		String loc = "";
		String view= "/views/common/msg.jsp";
		
		if(result>0) {
			msg = "카테고리 생성!";
			loc = "/oneday/onedayWrite";
		} else {
			msg = "카테고리 생성 실패";
			loc = "/oneday/onedayWrite";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
