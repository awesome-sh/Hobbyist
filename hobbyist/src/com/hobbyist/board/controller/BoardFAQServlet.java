package com.hobbyist.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;
import com.hobbyist.board.model.vo.BoardFAQ;

/**
 * Servlet implementation class BoardDirectQuestionServlet
 */
@WebServlet("/board/boardFAQ")
public class BoardFAQServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardFAQServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (Exception e) {
			cPage = 1;
		}
		int numPerPage = 10;
		
		int totalCount = new BoardService().selectFAQCount();
		int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		List<BoardFAQ> list = new BoardService().selectFAQList(cPage, numPerPage);
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo == 1) {
			pageBar += "<span>◀</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQ?cPage=" + (pageNo-1) + "&numPerPage" + numPerPage + "'>◀</a>";
		}
		
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			
			if(cPage == pageNo) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQ?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
	
		if(pageNo > totalPage) {
			pageBar += "<span>▶</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardFAQ?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>▶</a>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/board/boardFAQ.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
