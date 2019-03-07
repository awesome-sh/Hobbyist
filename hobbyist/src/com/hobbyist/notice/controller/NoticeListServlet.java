package com.hobbyist.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;


/**
 * Servlet implementation class NoticeListServlet
 */
@WebServlet("/notice/noticeList")
public class NoticeListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Notice> list = null;
		String sort = "";
		int cPage = 0;
		int numPerPage = 0;
		String pageBar = "";
		String keyword = "";
		request.setAttribute("list", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/views/notice/notice.jsp").forward(request, response);
		
		
		/*
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
		
		// 기본값 -> 등록일순 정렬로 초기화
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
			totalCount = new NoticeService().searchCount(keyword);
			System.out.println("리스트 totalCount : " + totalCount);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new NoticeService().selectAll(keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈1 : " + list.size());
		}
		
		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";
		
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/notice/noticeList?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "'>이전</a>";
		}
		
		while(!(pageNo>totalPage||pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<span style='color:#8e9181'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/notice/noticeList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/notice/noticeList?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>다음</a>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("/views/notice/notice.jsp").forward(request, response);
		*/
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
