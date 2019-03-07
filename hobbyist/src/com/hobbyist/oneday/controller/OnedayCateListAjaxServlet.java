package com.hobbyist.oneday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;

@WebServlet("/oneday/onedayCateListAjax")
public class OnedayCateListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public OnedayCateListAjaxServlet() {
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
		List<Oneday> list = null;

		if(sort.equals("descEnroll")) {
			System.out.println("카테고리 DESC ENROLL 진입");
			// 기본값 등록일순 정렬
			totalCount = new OnedayService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new OnedayService().descEnrollCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈 : " + list.size());
		} else if (sort.equals("descPrice")) {
			System.out.println("카테고리 DESC PRICE 진입");
			totalCount = new OnedayService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new OnedayService().descPriceCate(cate, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈 : " + list.size());
		} else if(sort.equals("ascPrice")) {
			System.out.println("카테고리 ASC PRICE 진입");
			totalCount = new OnedayService().searchCateCount(cate, keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new OnedayService().ascPriceCate(cate, keyword, cPage, numPerPage);
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
			html += "<ul>";
			html += "<li>" + list.get(i).getOnedayCate() + "</li>";
			html += "<li>";
			html += "<a href='" + request.getContextPath() + "/oneday/onedayView?no=" + list.get(i).getOnedayNo() + "'>";
			html += "<div id=\"img\"><img src='" +request.getContextPath() + "/upload/oneday/images/" + list.get(i).getOnedayImage1() + "' width='250px'></a></div></li>";
			html += "<li class=\"title\">";
			html += "<a href='" + request.getContextPath() + "/oneday/onedayView?no=" + list.get(i).getOnedayNo() + "'>";
			html +=	list.get(i).getOnedayName() + "</a>";
			html += "<p>" + list.get(i).getOnedayInfo() + "</p>";
			html += "</li>";
			html += "<li>";
			html += "가격 : " + list.get(i).getOnedayPrice() + "원<br><br>";
			html += "예약현황 " + list.get(i).getOnedayCurrentPeople() + "/" + list.get(i).getOnedayPeople() + " (명)<br>";
			html += "<b>";
			if(list.get(i).getOnedayReservationStatus().equals("Y")) { 
				html += "[ 예약 가능 ]";   
			} else {
				html += "[ 예약 불가 ]";
			}
			html += "</b><br>";
			html += "<button onclick='location.href='" + request.getContextPath() + "/oneday/onedayView?no=" + list.get(i).getOnedayNo() + "'>예약하기</button></li>";
			html += "</ul>";
			html += "</div>";
		}
		
		html += "<div class='oneday_bottom'>";
		html +=  pageBar;
		html += "</div>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
