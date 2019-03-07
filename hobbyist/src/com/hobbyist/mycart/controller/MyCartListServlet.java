package com.hobbyist.mycart.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.mycart.model.service.MyCartService;
import com.hobbyist.mycart.model.vo.MyCart;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/myCart")
public class MyCartListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCartListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		
		List<MyCart> list = new MyCartService().selectCartList(member, "classshop");
		List<Shop> shop = new ShopService().shopList();
		
		request.setAttribute("MyCart", list);
		request.setAttribute("Shop", shop);
		request.getRequestDispatcher("/views/cart/myCart.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
