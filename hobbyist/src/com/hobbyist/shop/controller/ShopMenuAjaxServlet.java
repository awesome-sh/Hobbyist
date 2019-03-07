package com.hobbyist.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Cate;

@WebServlet("/shop/shopMenuAjax")
public class ShopMenuAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopMenuAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Cate> list = new ShopService().CateList();
		
		
		String html = "";
		
		for(int i=0; i<list.size();i++) {
			html += "<li onclick=\"location.href='" + request.getContextPath() + "/shop/shopCateList?cate=" + list.get(i).getCateTitle()  + "\';\"> · " + list.get(i).getCateTitle()  + "</li>";
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
