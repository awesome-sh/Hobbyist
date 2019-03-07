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
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/shop/shopCateList")
public class ShopCateListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ShopCateListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 카테고리 값 받아옴
		String cate = request.getParameter("cate");
		
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

		// 정렬방법&검색결과 적용 전체클래스 개수 , 페이지 갯수
		int totalCount = 0;
		int totalPage = 0;

		// 리스트 초기화
		List<Shop> list = null;

		if(sort.equals("descEnroll")) {
			System.out.println("DESC ENROLL 진입");
			// 기본값 등록일순 정렬
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().descEnrollCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈1 : " + list.size());
		} else if (sort.equals("descPrice")) {
			System.out.println("DESC PRICE 진입");
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().descPriceCate(cate, keyword, cPage, numPerPage);
		} else if(sort.equals("ascPrice")) {
			System.out.println("ASC PRICE 진입");
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().ascPriceCate(cate, keyword, cPage, numPerPage);
		}

		List<Cate> category = new ShopService().CateList();

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";

		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;    
		int pageEnd = pageNo+pageBarSize-1;

		if(pageNo==1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/shop/shopCateList?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "'>이전</a>";
		}

		while(!(pageNo>totalPage || pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<span style='color:#8e9181'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/shop/shopCateList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}

		if(pageNo>totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/shop/shopCateList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>다음</a>"; 
		}


		request.setAttribute("Category", category);
		request.setAttribute("List", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/views/shop/shopCateList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
