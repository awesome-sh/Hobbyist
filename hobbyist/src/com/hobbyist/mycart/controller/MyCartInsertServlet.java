package com.hobbyist.mycart.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.mycart.model.service.MyCartService;
import com.hobbyist.mycart.model.vo.MyCart;

@WebServlet("/myCartInsert")
public class MyCartInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCartInsertServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		int classNo = Integer.parseInt(request.getParameter("classNo"));
		String cartCate = request.getParameter("cartCate");
		String cartOption = request.getParameter("cartOption");
		
		MyCart mc = new MyCart();
		mc.setMyCartMember(member);
		mc.setMyCartClass(classNo);
		mc.setMyCartCate(cartCate);
		mc.setMyCartClassOption(cartOption);
		
		System.out.println("장바구니 등록 중 옵션값 : " + cartOption);
		
		int result = new MyCartService().insertCart(mc);
		
		String msg = "장바구니에 담겼습니다! 장바구니로 이동하시겠습니까?";
		String loc = "";
		String view = "/views/common/confirm.jsp";
		
		if(result>0) {
			loc = "/myCart?member=" + member;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("classNo", classNo);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
