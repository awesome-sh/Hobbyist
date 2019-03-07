package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDQAnswerUpdateServlet
 */
@WebServlet("/board/boardDQAnswerComplete")
public class BoardDQAnswerCompleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDQAnswerCompleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardDQNo = Integer.parseInt(request.getParameter("boardDQNo"));
		String boardDQContent = request.getParameter("boardDQContent");
		
		int result = new BoardService().updateDQAnswerBoard(boardDQNo, boardDQContent);
		
		String msg = "";
		String loc = "";
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "1:1 문의 답변되었습니다.";
			loc = "/admin/community/adminCommunityDQList";
		} else {
			msg = "답변중 오류 발생";
			loc = "/board/boardDirectQuestionView?boardDQNo=" + boardDQNo;
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
