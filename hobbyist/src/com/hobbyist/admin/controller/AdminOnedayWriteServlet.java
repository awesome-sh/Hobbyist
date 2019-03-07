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

@WebServlet("/admin/adminOnedayWrite")
public class AdminOnedayWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOnedayWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cate> list = new OnedayService().CateList();
		
		request.setAttribute("Cate", list);
		request.getRequestDispatcher("/views/admin/oneday/adminOnedayWrite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
