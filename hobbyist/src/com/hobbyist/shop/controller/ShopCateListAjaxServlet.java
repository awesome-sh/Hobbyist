package com.hobbyist.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Cate;
import com.hobbyist.shop.model.vo.Shop;

@WebServlet("/shop/shopCateListAjax")
public class ShopCateListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public ShopCateListAjaxServlet() {
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
		
		// Sort 값 파라미터값 확인
		System.out.println("전달된 SORT 값 : " + sort);

		// 기본값 -> 검색어 초기화
		String keyword = "";
		if(request.getParameter("keyword")==null) {
			keyword = "";
		} else {
			keyword = request.getParameter("keyword");
		}

		// 리스트 초기화
		List<Shop> list = null;

		if(sort.equals("descEnroll")) {
			System.out.println("카테고리 DESC ENROLL 진입");
			// 기본값 등록일순 정렬
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().descEnrollCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈 : " + list.size());
		} else if (sort.equals("descPrice")) {
			System.out.println("카테고리 DESC PRICE 진입");
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().descPriceCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈 : " + list.size());
		} else if(sort.equals("ascPrice")) {
			System.out.println("카테고리 ASC PRICE 진입");
			totalCount = new ShopService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new ShopService().ascPriceCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈 : " + list.size());
		}

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";

		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;    
		int pageEnd = pageNo+pageBarSize-1;

		if(pageNo==1) {
			pageBar += "<button type='button'><< 이전</button>";
		} else {
			pageBar += "<button type='button' onclick='fn_ListAjax("+ (pageNo-1) + ")'><<</button>";
		}

		while(!(pageNo>totalPage || pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<button type='button' style='color:#fff; background-color:#3b4f6a'>" + pageNo + "</button>";
			} else {
				pageBar += "<button type='button' onclick='fn_ListAjax("+ pageNo + ")'>" + pageNo + "</button>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<button type='button'>다음 >></button>";
		} else {
			pageBar += "<button type='button' onclick='fn_ListAjax("+ pageNo + ")'>>></button>";
		}
		
		// 리스트 담기
		String html = "";
		
		double cnt = 0.1;
		double cnt3 = 0;
		
		
		for(int i=0; i<list.size();i++) {
			// 0.3 을 넘지않게 초기화
			if(cnt3>=0.3) {
				cnt3 = 0;
			}
			html += "<div class='item wow fadeInUp' data-wow-delay='" + (cnt3+=cnt) +"s'>";
			html += "<p>" + list.get(i).getShopCate() + "</p>";
			html += "<a href='" + request.getContextPath() + "/shop/shopView?no=" + list.get(i).getShopNo() + "'><img src='" + request.getContextPath() + "/upload/shop/images/" + list.get(i).getShopImage1() + "' width='100%'></a><br>";
			html += "<ul>";
			html += "<li class='title'><a href='" + request.getContextPath() + "/shop/shopView?no=" + list.get(i).getShopNo() + "'>" + list.get(i).getShopName() + "</a></li>";
			html += "<li><p>" + list.get(i).getShopInfo() + "</p></li>";
			html += "<li>가격" + list.get(i).getShopPrice()+ "</li>";
			html += "</ul>";
			html += "<button onclick='location.href='" + request.getContextPath() + "/shop/shopView?no=" + list.get(i).getShopNo() + "'>VIEW</button>";
			html += "</div>";
		}
		
		html += "<div class='shop_bottom'>";
		html +=  pageBar;
		html += "</div>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
