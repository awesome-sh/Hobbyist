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

@WebServlet("/myCartAjaxList")
public class MyCartListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyCartListAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		String cartCate = request.getParameter("cartCate");
		
		System.out.println("취미바구니 -> 클래스샵 버튼 클릭");
		System.out.println("받은 카테고리 값 : " + cartCate);
		
		List<MyCart> list = new MyCartService().selectCartList(member, cartCate);
		List<Shop> shop = new ShopService().shopList();
		
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
				for(Shop s : shop) {
					if(mc.getMyCartClass()==s.getShopNo()) {
						System.out.println("반복문 담기");
						html += "<tr><td>";
						html += "<input type='checkbox' name='checkbox' value='" + mc.getMyCartNo() +"'>";
						html += "<input type='hidden' name='classNo' value='" + s.getShopNo() + "'/>";
						html += "</td>";
						html += "<td>" + s.getShopCate() + "</td>";
						html += "<td><img src='" + request.getContextPath() + "/upload/shop/images/" + s.getShopImage1() + "' width='110px'></td>";
						html += "<td style='text-align: left;  padding-left: 15px;'>";
						html += "<h3><a href='" + request.getContextPath() + "/shop/shopView?no=" + s.getShopNo() + "'>" + s.getShopName() + "</a></h3>" +  s.getShopInfo() + "</td>";
						html += "<td>";
						html += "<input type=\"hidden\" id=\"classOption\"name=\"classOption\" value=\"" + mc.getMyCartClassOption() + "\">" + mc.getMyCartClassOption() + "</td>";
						html += "<td class=\"price\">" + s.getShopPrice() + "</td>";
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
		html += "<button type=\"button\" onclick=\"fn_delete(\'classshop\');\">선택삭제</button>";
		html += "<button type='button'onclick='fn_order()'>선택주문</button>";
		html += "</td>";
		html += "</tr>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(html);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
