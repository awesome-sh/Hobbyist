package com.hobbyist.myclass.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.myclass.model.service.MyClassService;
import com.hobbyist.myclass.model.vo.MyClass;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/myClass")
public class MyClassListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyClassListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		
		List<MyClass> myclass = new MyClassService().selectMyClass(member);
		List<Shop> shop = new ShopService().selectAll();
		
		request.setAttribute("MyClass", myclass);
		request.setAttribute("Shop", shop);
		
		request.getRequestDispatcher("/views/myclass/myClass.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
