package com.hobbyist.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardFAQ;

/**
 * Servlet implementation class BoardFAQSearchServlet
 */
@WebServlet("/board/boardFAQSearch")
public class BoardFAQSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFAQSearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String searchType=request.getParameter("searchType");
		String searchKeyword=request.getParameter("searchKeyword");

		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (Exception e) {
			cPage = 1;
		}
		int numPerPage = 10;
		
		int totalCount = new BoardService().selectFAQSearchCount(searchType, searchKeyword);
		int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		List<BoardFAQ> list = new BoardService().selectFAQSearchList(cPage, numPerPage, searchType, searchKeyword);
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo == 1) {
			pageBar += "<span>◀</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQSearch?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>◀</a>";
		}
		
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			
			if(cPage == pageNo) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
	
		if(pageNo > totalPage) {
			pageBar += "<span>▶</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>▶</a>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.getRequestDispatcher("/views/board/boardFAQ.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
