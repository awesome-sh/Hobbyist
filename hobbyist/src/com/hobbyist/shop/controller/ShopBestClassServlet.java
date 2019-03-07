package com.hobbyist.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/shop/shopBestClass")
public class ShopBestClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopBestClassServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Shop> list = new ShopService().bestClass();
		
		String html = "";
		
		for(int i=0; i<4;i++) {
			html += "<li><div id=\"bestHover\"></div><a href='" + request.getContextPath() + "/shop/shopView?no=" + list.get(i).getShopNo() + "'> <img src=\"" + request.getContextPath() + "/upload/shop/images/" +  list.get(i).getShopImage1() + "\" width=\"150px\"></a></li>";
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
