package com.hobbyist.oneday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Cate;

@WebServlet("/oneday/onedayWrite")
public class OnedayWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OnedayWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cate> list = new OnedayService().CateList();
		
		request.setAttribute("Cate", list);
		request.getRequestDispatcher("/views/oneday/onedayWrite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
