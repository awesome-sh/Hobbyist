package com.hobbyist.shop.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Review;


@WebServlet("/shop/shopReviewWrite")
public class ShopReviewWriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopReviewWriteServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 리뷰작성, 리뷰리스트 받기 동시작업
		String review_writer = request.getParameter("review_writer");
		int review_class = Integer.parseInt(request.getParameter("review_class"));
		int review_count = Integer.parseInt(request.getParameter("review_count"));
		String review_content = request.getParameter("review_content");

		Review rv = new Review();
		rv.setReviewWriter(review_writer);
		rv.setReviewClass(review_class);
		rv.setReviewCount(review_count);
		rv.setReviewContent(review_content);

		// 리뷰 페이징처리
		int rPage;
		try {
			rPage = Integer.parseInt(request.getParameter("rPage"));
		} catch (NumberFormatException e) {
			rPage = 1;
		}

		int rPerPage;
		try {
			rPerPage = Integer.parseInt(request.getParameter("rPerPage"));
		} catch (NumberFormatException e) {
			rPerPage = 5;
		}
		
		// 리뷰작성 -> 리뷰 DB 입력 -> 커밋후 -> 리스트 돌려받음
		List<Review> list = new ShopService().reviewInsert(rv, rPage, rPerPage);

		// 정렬방법&검색결과 적용 전체클래스 개수 , 페이지 갯수
		int totalCount = new ShopService().reviewCount(review_class);
		int totalPage =  (int)Math.ceil((double)totalCount/rPerPage);

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";

		int pageNo = ((rPage-1)/pageBarSize)*pageBarSize+1;    
		int pageEnd = pageNo+pageBarSize-1;

		if(pageNo==1) {
			pageBar += "<button type='button'><<</button>";
		} else {
			pageBar += "<button type='button' onclick='fn_reviewList("+ (pageNo-1) + ")'><<</button>";
		}

		while(!(pageNo>totalPage || pageNo>pageEnd)) {
			if(pageNo==rPage) {
				pageBar += "<button type='button' id='curPage'>" + pageNo + "</button>";
			} else {
				pageBar += "<button type='button' onclick='fn_reviewList("+ pageNo + ")'>" + pageNo + "</button>";
			}
			pageNo++;
		}

		if(pageNo>totalPage) {
			pageBar += "<button type='button'>>></button>";
		} else {
			pageBar += "<button type='button' onclick='fn_reviewList("+ pageNo + ")'>>></button>";
		}

		// 세션 불러오기
		HttpSession session = request.getSession();
		Member logginMember = (Member)session.getAttribute("logginMember");
		// REVIEW_TBL 테이블 안에 띄워줄 HTML 작성
		String html = "";

		html += "<tr>";
		html += "<form id='reviewListFrm'>";
		html += "<th style='width:150px;'>별점</th>";
		html += "<th style='width:550px;'>리뷰</th>";
		html += "<th style='width:200px;'>작성자 / 작성일</th>";
		html += "<input type='hidden' name='review_class' value='" + review_class + "'>";
		html += "<input type='hidden' name='rPage' value='" + rPage + "'>";
		html += "<input type='hidden' name='rPerPage' value='" + rPerPage + "'>";
		html += "</form>";
		html += "</tr>";

		for(int i=0; i<list.size();i++) {
			html += "<tr><td>";

			html += list.get(i).getReviewCount()  == 1 ? "★" :  list.get(i).getReviewCount()  == 2 ? "★★" :  list.get(i).getReviewCount()  == 3 ? "★★★" :  list.get(i).getReviewCount()  == 4 ? "★★★★" : "★★★★★";
			html += "</td>";
			html += "<td style='text-align:left;'>";
			html +=  list.get(i).getReviewContent() + " </td>";
			html += "<td style='text-align:left; font-size:12px;'>";
			html += "작성자 : " + list.get(i).getReviewWriter() + "<br>";
			html += "작성일 : " + list.get(i).getReviewDate() + "<br>";
			if(logginMember!=null) {
				if(logginMember.getMemberEmail().equals("admin") || logginMember.getMemberEmail().equals(list.get(i).getReviewWriter())) {
					html += "<button type='button' onclick='fn_reviewDelete("+ list.get(i).getReviewNo() + "," + list.get(i).getReviewClass() + ")'>X</button></td>";
				}
			} else {
			}
		}

		html += "<tr>";
		html += "<td colspan='3'>";
		html += pageBar; 
		html += "</td>";
		html +=  "</tr>";

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
