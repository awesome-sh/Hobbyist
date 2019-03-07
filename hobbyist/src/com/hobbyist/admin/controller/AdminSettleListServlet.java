package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Order;


@WebServlet("/admin/adminSettle")
public class AdminSettleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public AdminSettleListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이번달 총 매출
		int income = 0;
		
		List<Order> list = new OrderService().inCome();
		
		for(Order o : list) {
			income += o.getOrderPrice();
		}
		
		request.setAttribute("income", income);
		
		request.getRequestDispatcher("/views/admin/settled/adminSettle.jsp").forward(request, response);
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
