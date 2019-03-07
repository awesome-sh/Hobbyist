package com.hobbyist.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardDQ;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class BoardDirectQuestionServlet
 */
@WebServlet("/board/boardDirectQuestion")
public class BoardDirectQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDirectQuestionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member logginMember=(Member)request.getSession(false).getAttribute("logginMember");
		if(logginMember==null) {
			request.setAttribute("msg", "로그인 후 이용가능 합니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
		}
	
		String nickName = request.getParameter("NickName");
		
		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (Exception e) {
			cPage = 1;
		}
		int numPerPage = 2;
		
		int totalCount = new BoardService().selectDQCount(nickName);
		int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		List<BoardDQ> list = new BoardService().selectDQList(cPage, numPerPage, nickName);
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo == 1) {
			pageBar += "<span>◀</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardDirectQuestion?cPage=" + (pageNo-1) + "&numPerPage" + numPerPage + "'>◀</a>";
		}
		
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			
			if(cPage == pageNo) {
				pageBar += "<span>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='" + request.getContextPath() + "/board/boardDirectQuestion?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</a>";
			}
			pageNo++;
		}
	
		if(pageNo > totalPage) {
			pageBar += "<span>▶</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/board/boardDirectQuestion?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>▶</a>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/board/boardDirectQuestion.jsp").forward(request, response);
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
