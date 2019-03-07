package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardComment;

/**
 * Servlet implementation class BoardCommentInserServlet
 */
@WebServlet("/board/boardCommentInsert")
public class BoardCommentInserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardCommentInserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardCommentLevel = Integer.parseInt(request.getParameter("boardCommentLevel"));
		String boardCommentWriter = request.getParameter("boardCommentWriter");
		String boardCommentContent = request.getParameter("chat_content");
		int boardRef = Integer.parseInt(request.getParameter("boardRef"));
		int boardCommentRef = Integer.parseInt(request.getParameter("boardCommentRef"));

		BoardComment bc = new BoardComment();
		bc.setBoardCommentWriter(boardCommentWriter);
		bc.setBoardCommentContent(boardCommentContent);
		bc.setBoardCommentLevel(boardCommentLevel);
		bc.setBoardRef(boardRef);
		bc.setBoardCommentRef(boardCommentRef);
		
		int result = new BoardService().insertComment(bc);
		
		String msg="";
		String loc="/board/boardView?boardNo=" + boardRef;
		String view="/views/common/msg.jsp";
		
		if(result>0) msg = "등록성공";
		else msg = "등록실패";
		
		request.setAttribute("msg",msg);
		request.setAttribute("loc",loc);
		request.getRequestDispatcher(view).forward(request, response);	
	
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
