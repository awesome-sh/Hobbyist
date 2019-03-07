package com.hobbyist.order.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

@WebServlet("/order/orderKakao")
public class OrderKakaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderKakaoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rep = request.getParameter("imp_uid");
		JSONObject object = new JSONObject();
		object.put("rep", rep);
		System.out.println(object);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
