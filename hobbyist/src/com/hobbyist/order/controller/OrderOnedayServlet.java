package com.hobbyist.order.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;
import com.hobbyist.mycart.model.vo.MyCart;
import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;
import com.hobbyist.order.model.service.OrderDataService;
import com.hobbyist.order.model.vo.OrderData;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/order/orderOneday")
public class OrderOnedayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OrderOnedayServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cartNo = request.getParameter("cartNoVal2");
		String cartNoArr[] = cartNo.split(",");
		System.out.println(cartNo);
		String member = request.getParameter("memberVal2");
		System.out.println(member);
		String selectNo = request.getParameter("selectNoVal2");
		String selectNoArr[] = selectNo.split(",");
		System.out.println(selectNo);
		String classOption = request.getParameter("classOptionVal2");
		String classOptionArr[] = classOption.split(",");
		System.out.println(classOption);
		
		// 회원정보를 가져오기 위해 멤버객체 생성
		Member m = new Member();
		m.setMemberEmail(member);
		Member mb = new MemberService().selectOne(m);
		
		// 해당클래스 선택갯수만큼 반복문 돌려
		List<Oneday> orderList = new ArrayList<Oneday>();
		for(int i=0; i<selectNoArr.length; i++) {
			Oneday oneday = new OnedayService().selectOne(Integer.parseInt(selectNoArr[i]));
			oneday.setOnedayOption1(classOptionArr[i]);
			orderList.add(oneday);
		}
		
		// 카트번호 담아주기
		List<MyCart> cartNum = new ArrayList<MyCart>();
		for(int i=0; i<cartNoArr.length;i++) {
			MyCart mc = new MyCart();
			mc.setMyCartNo(Integer.parseInt(cartNoArr[i]));
			cartNum.add(mc);
		}
		
		
		// 멤버정보 보내주기
		request.setAttribute("Member", mb);
		// 카트번호 보내주기
		request.setAttribute("CartNum", cartNum);
		// 주문정보리스트 보내주기
		request.setAttribute("OrderList", orderList);
		
		request.getRequestDispatcher("/views/order/orderOneday.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
