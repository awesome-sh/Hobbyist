package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.order.model.service.OrderService;
import com.hobbyist.order.model.vo.Pin;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/admin/adminPinList")
public class AdminPinListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminPinListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					numPerPage = 9;
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

				// 기본값 -> 검색어 초기화
				String keyword = "";
				if(request.getParameter("keyword")==null) {
					keyword = "";
				} else {
					keyword = request.getParameter("keyword");
				}

				// 리스트 초기화
				List<Pin> list = new OrderService().selectPinList(cPage, numPerPage);
				totalCount = new OrderService().selectPinCount();
				totalPage = (int)Math.ceil((double)totalCount/numPerPage);
				
				// 상품리스트 받아오기
				List<Shop> shop = new ShopService().selectAll();
				
				// 페이지네이션
				int pageBarSize = 5;
				String pageBar = "";

				int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;    
				int pageEnd = pageNo+pageBarSize-1;

				if(pageNo==1) {
					pageBar += "<span>이전</span>";
				} else {
					pageBar += "<a href='" + request.getContextPath() + "/admin/adminPinList?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "'>이전</a>";
				}

				while(!(pageNo>totalPage || pageNo>pageEnd)) {
					if(pageNo==cPage) {
						pageBar += "<span>" + pageNo + "</span>";
					} else {
						pageBar += "<a href='" + request.getContextPath() + "/admin/adminPinList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
					}
					pageNo++;
				}

				if(pageNo>totalPage) {
					pageBar += "<span>다음</span>";
				} else {
					pageBar += "<a href='" + request.getContextPath() + "/admin/adminPinList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>다음</a>"; 
				}

				request.setAttribute("TotalCount", totalCount);
				request.setAttribute("List", list);
				request.setAttribute("Shop", shop);
				request.setAttribute("sort", sort);
				request.setAttribute("cPage", cPage);
				request.setAttribute("numPerPage", numPerPage);
				request.setAttribute("pageBar", pageBar);
				request.setAttribute("keyword", keyword);
				request.getRequestDispatcher("/views/admin/shop/adminPinList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
