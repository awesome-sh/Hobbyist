package com.hobbyist.admin.controller;

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
@WebServlet("/admin/community/adminCommunityFAQSearch")
public class AdminCommunityFAQSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityFAQSearchServlet() {
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
			pageBar += "<button>이전</button>";
		} else {
			pageBar += "<button onclick=location.href='" + request.getContextPath() + "/admin/community/adminCommunityFAQSearch?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>이전</button>";
		}
		
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			
			if(cPage == pageNo) {
				pageBar += "<button class='current'>" + pageNo + "</button>";
			} else {
				pageBar += "<button onclick=location.href='" + request.getContextPath() + "/admin/community/adminCommunityFAQSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>" + pageNo + "</button>";
			}
			pageNo++;
		}
	
		if(pageNo > totalPage) {
			pageBar += "<button>다음</button>";
		} else {
			pageBar += "<button onclick=location.href='" + request.getContextPath() + "/admin/community/adminCommunityFAQSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchType=" + searchType + "&searchKeyword=" + searchKeyword + "'>다음</button>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("searchType", searchType);
		request.setAttribute("searchKeyword", searchKeyword);
		request.getRequestDispatcher("/views/admin/community/admin_communityFAQList.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
