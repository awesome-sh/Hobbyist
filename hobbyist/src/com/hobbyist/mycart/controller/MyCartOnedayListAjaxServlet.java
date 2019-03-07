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
import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/myCartOnedayAjaxList")
public class MyCartOnedayListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCartOnedayListAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String member = request.getParameter("member");
		String cartCate = request.getParameter("cartCate");
		
		System.out.println("취미바구니 -> 원데이클래스 버튼 클릭");
		System.out.println("받은 카테고리 : " + cartCate);
		
		List<MyCart> list = new MyCartService().selectCartList(member, cartCate);
		List<Oneday> oneday = new OnedayService().selectAll();
		
		String html = "";

		if(!list.isEmpty()) {
			html += "<tr>";
			html += "<th style=\"width: 80px;\">선택</th>";
			html += "<th style=\"width: 100px;\">카테고리</th>";
			html += "<th style=\"width: 120px;\"></th>";
			html += "<th style=\"width: 390px;\">클래스</th>";
			html += "<th style=\"width: 170px;\">옵션</th>";
			html += "<th style=\"width: 100px;\">가격</th>";
			html += "</tr>";
			
			for(MyCart mc : list) {
				for(Oneday od : oneday) {
					if(mc.getMyCartClass()==od.getOnedayNo()) {
						html += "<tr><td>";
						html += "<input type='checkbox' name='checkbox' value='" + mc.getMyCartNo() +"'>";
						html += "<input type='hidden' name='classNo' value='" + od.getOnedayNo() + "'/>";
						html += "</td>";
						html += "<td>" + od.getOnedayCate() + "</td>";
						html += "<td><img src='" + request.getContextPath() + "/upload/oneday/images/" + od.getOnedayImage1() + "' width='110px'></td>";
						html += "<td style='text-align: left;  padding-left: 15px;'>";
						html += "<h3><a href='" + request.getContextPath() + "/oneday/onedayView?no=" + od.getOnedayNo() + "'>" + od.getOnedayName() + "</a></h3>" +  od.getOnedayInfo() + "</td>";
						html += "<td>";
						html += "<input type=\"hidden\" id=\"classOption\"name=\"classOption\" value=\"" + mc.getMyCartClassOption() + "\">" + mc.getMyCartClassOption() + "</td>";
						html += "<td class='price'>" + od.getOnedayPrice() + "</td>";
						html += "</tr>";
					}
				}
			}
		} else {
			html += "<tr>";
			html += "<td colspan='6' style='width: 970px;'><h3>장바구니에 담긴 상품이 없습니다</h3></td>";
			html += "</tr>";
		}
		
		html += "<tr>";
		html += "<td colspan='3' id='priceResult' class='result'></td>";
		html += "<td colspan='3' class='result' style='text-align: right;'>";
		html += "<button type=\"button\" onclick=\"fn_delete(\'oneday\')\">선택삭제</button>";
		html += "<button type='button' onclick='fn_orderOneday()'>선택주문</button>";
		html += "</td>";
		html += "</tr>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
