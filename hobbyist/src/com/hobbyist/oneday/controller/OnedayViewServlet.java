package com.hobbyist.oneday.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;

@WebServlet("/oneday/onedayView")
public class OnedayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OnedayViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클래스 정보 가져오기
		int no = Integer.parseInt(request.getParameter("no"));
		Oneday oneday = new OnedayService().selectOne(no);
		
		request.setAttribute("Oneday", oneday);
		request.getRequestDispatcher("/views/oneday/onedayView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
