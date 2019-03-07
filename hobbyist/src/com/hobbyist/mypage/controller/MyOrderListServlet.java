package com.hobbyist.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;
import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Order;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/mypage/myOrderList")
public class MyOrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MyOrderListServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String member = request.getParameter("member");
		
		// 페이징처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}

		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 8;
		}

		// 정렬방법&검색결과 적용 전체클래스 개수 , 페이지 갯수
		int totalCount = 0;
		int totalPage = 0;

		// 기본값 -> 등록일순 정렬로 초기화
		String sort = "";
		if(request.getParameter("sort")==null) {
			sort = "descEnroll";
		} else {
			sort = request.getParameter("sort");
		}


		// 리스트 초기화
		List<Order> list = null;

		if(sort.equals("descEnroll")) {
			totalCount = new OrderService().searchOrderCount(member);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new OrderService().myOrderList(cPage, numPerPage, member);
			System.out.println("리스트 사이즈 : " + list.size());
		}
		
		List<Shop> shop = new ShopService().selectAll();
		List<Oneday> oneday = new OnedayService().selectAll();
		
		System.out.println("샵리스트 사이즈 : " + shop.size());
		
		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";

		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;

		if(pageNo==1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/mypage/myOrderList?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "&member=" + member + "'>이전</a>";
		}

		while(!(pageNo>totalPage || pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/mypage/myOrderList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&member=" + member + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}

		if(pageNo>totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/mypage/myOrderList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&member=" + member + "'>다음</a>"; 
		}

		request.setAttribute("TotalCount", totalCount);
		request.setAttribute("Order", list);
		request.setAttribute("Shop", shop);
		request.setAttribute("Oneday", oneday);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);

		request.getRequestDispatcher("/views/mypage/order/myOrderList.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
