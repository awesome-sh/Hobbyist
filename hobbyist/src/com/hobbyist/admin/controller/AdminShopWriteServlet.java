package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Cate;

@WebServlet("/admin/adminShopWrite")
public class AdminShopWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminShopWriteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cate> list = new ShopService().CateList();
		
		request.setAttribute("Cate", list);
		request.getRequestDispatcher("/views/admin/shop/adminShopWrite.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
