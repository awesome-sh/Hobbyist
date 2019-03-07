package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Cate;
import com.hobbyist.oneday.model.vo.Oneday;

@WebServlet("/admin/adminOnedayView")
public class AdminOnedayViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AdminOnedayViewServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클래스 정보 가져오기
		int no = Integer.parseInt(request.getParameter("no"));
		Oneday oneday = new OnedayService().selectOne(no);

		request.setAttribute("Oneday", oneday);
		request.getRequestDispatcher("/views/admin/oneday/adminOnedayView.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
