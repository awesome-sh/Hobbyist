package com.hobbyist.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;
import com.hobbyist.board.model.vo.BoardComment;

/**
 * Servlet implementation class MyBoardCommentConfirmServlet
 */
@WebServlet("/mypage/myBoardCommentConfirm")
public class MyBoardCommentConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardCommentConfirmServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String nickName = request.getParameter("nickName");
		
		int cPage = 0;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (Exception e) {
			cPage = 1;
		}
		int numPerPage = 0;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (Exception e) {
			numPerPage = 5;
		}
		
		int totalCount = new BoardService().selectMyPageBoardCommentCount(nickName);
		int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
		List<BoardComment> list = new BoardService().selectMyPageBoardCommentList(cPage, numPerPage, nickName);
		
		String pageBar = "";
		int pageBarSize = 5;
		int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo == 1) {
			pageBar += "<button>이전</button>";
		} else {
			pageBar += "<button onclick=location.href='" + request.getContextPath() + "/mypage/myBoardCommentConfirm?cPage=" + (pageNo-1) + "&numPerPage" + numPerPage + "'>이전</button>";
		}
		int i = 1;
		while(!(pageNo > totalPage || pageNo > pageEnd)) {
			
			if(cPage == pageNo) {
				pageBar += "<button class='current'>" + pageNo + "</button>";
			} else {
				pageBar += "<button onclick=location.href='" + request.getContextPath() + "/mypage/myBoardCommentConfirm?cPage=" + pageNo + "&numPerPage=" + numPerPage + "'>" + pageNo + "</button>";
			}
			i++;
			pageNo++;
		}
	
		if(pageNo > totalPage) {
			pageBar += "<button>다음</button>";
		} else {
			pageBar += "<button onclick=location.href='" + request.getContextPath() + "/mypage/myBoardCommentConfirm?cPage=" + pageNo + "&numPerPage=" + numPerPage + "/'>다음</button>";
		}
		
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.getRequestDispatcher("/views/mypage/community/myBoard_comment_confirm.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
