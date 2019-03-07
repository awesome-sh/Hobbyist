package com.hobbyist.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDelAjaxServlet
 */
@WebServlet("/notice/noticeDelListAjax.do")
public class NoticeDelListAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDelListAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 페이징처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch ( NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch ( NumberFormatException e) {
			numPerPage = 5;
		}
		
		// 정렬방법&검색결과 적용 전체클래스 갯수, 페이지 갯수
		int totalCount = 0;
		int totalPage = 0;
		
		// 기본값 -> 등록일순 정렬로 초기화(전체보기)
		String sort = "";
		if(request.getParameter("sort")==null) {
			sort = "sortAll";
		} else {
			sort = request.getParameter("sort");
		}
		
		// 기본값  -> 검색어 초기화
		String keyword = "";
		if(request.getParameter("keyword")==null) {
			keyword = "";
		} else {
			keyword = request.getParameter("keyword");
		}
		
		// 리스트 초기화
		List<Notice> list = null;
		
		if(sort.equals("sortAll")) {
			System.out.println("SELECE ALL 진입");
			// 기본값 등록일순 정렬
			totalCount = new NoticeService().searchCountDel(keyword);
			System.out.println("리스트1 totalCount : " + totalCount);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new NoticeService().selectAllDel(keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈1 : " + list.size());
		}
		else if(sort.equals("sortNotice")) {
			System.out.println("SELECE NOTICE 진입");
			totalCount = new NoticeService().searchCountSortDel(sort, keyword);
			System.out.println("리스트2 totalCount : " + totalCount);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new NoticeService().selectSortDel(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈2 : " + list.size());
		}
		else if(sort.equals("sortEvent")) {
			System.out.println("SELECE EVENT 진입");
			totalCount = new NoticeService().searchCountDel(keyword);
			System.out.println("리스트3 totalCount : " + totalCount);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new NoticeService().selectSortDel(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈3 : " + list.size());
		}
		else if(sort.equals("sortWriterEnroll")) {
			System.out.println("SELECE WRITERENROLL 진입");
			totalCount = new NoticeService().searchCountDel(keyword);
			System.out.println("리스트4 totalCount : " + totalCount);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new NoticeService().selectSortDel(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈4 : " + list.size());
		}

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";
		
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + (pageNo-1) + ")'>이전</a>";
		}
		
		while(!(pageNo>totalPage||pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<span style='color:#8e9181'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>" + pageNo + "</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>다음</a>";
		}
		
		// 리스트 담기
		String html = "";
		if(list.size() != 0) {
			for(int i=0;i<list.size();i++) {
				html += "<div class='tal_ContentBox'>";
				html += "<div class='tal_Content'>";
				html += "<div class='talC_noticeNo'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>" + list.get(i).getNoticeNo() + "</div>";
				html += "<div class='talC_noticeSort'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>";
				
				switch(list.get(i).getNoticeSort()) {
					case "sortNotice" : 
						html +="<div class='nSTextNotice'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>공지</div>"; break;
					case "sortEvent" : 
						html +="<div class='nSTextEvent'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>이벤트</div>"; break;
					case "sortWriterEnroll" : 
						html +="<div class='nSTextWriterEnroll'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>작가신청</div>"; break;
				}
				
				html += "</div>";
				html += "<div class='talC_noticeTitle'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'><span style='color:red'>[삭제상태] </span>" + list.get(i).getNoticeTitle() + "</div>";
				html += "<div class='talC_noticeDate'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>" + list.get(i).getNoticeDate() + "</div>";
				html += "<div class='talC_noticeReadcount'onclick='fn_defaultViewAjax("+ list.get(i).getNoticeNo() +")'>" + list.get(i).getNoticeReadcount() + "</div>";
				
				html += "<div class='talC_noticeStatus'>";
				html += "<button type='button' class='noticeListRe' onclick='fn_noticeListRe("+ list.get(i).getNoticeNo() +")'>복구</button>";
				html += "<button type='button' class='noticeListDel' onclick='fn_noticeDB_Del("+ list.get(i).getNoticeNo() +")'>삭제</button>";
				html += "</div>";
				
				html += "</div>";
				html += "</div>";
			}
		}
		else {
			html += "<div class='tal_Content'>";
			html += "<div class='tal_text'>현재 조회된 리스트가 없습니다.</div>";
			html += "</div>";
		}
		html += "<div class='talBox_bottom'>" + pageBar + "</div>";

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
