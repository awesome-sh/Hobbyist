package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDQDeleteServlet
 */
@WebServlet("/board/boardDQDelete")
public class BoardDQDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDQDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardDQNo = Integer.parseInt(request.getParameter("boardDQNo"));
		
		int result = new BoardService().deleteDQBoard(boardDQNo);
		
		String msg = "";
		String loc = "";
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "문의 내용이 삭제되었습니다.";
			loc = "/board/boardDirectQuestion";
		} else {
			msg = "문의 삭제중 오류발생!";
			loc = "/board/boardDirectQuestionView?boardDQNo" + boardDQNo;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
