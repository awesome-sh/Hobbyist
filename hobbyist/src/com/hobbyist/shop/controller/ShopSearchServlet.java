package com.hobbyist.shop.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

@WebServlet("/shop/shopSearch")
public class ShopSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ShopSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String keyword = request.getParameter("keyword");
		
		List<Shop> list = new ShopService().ajaxSearch(keyword);
		
		JSONObject json = new JSONObject();
		
		for(int i=0;i<list.size();i++) {
			json.put("subject" + i, list.get(i).getShopName());
		}

		String result = json.toJSONString();
		
		PrintWriter out = response.getWriter();
		out.print(result);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
