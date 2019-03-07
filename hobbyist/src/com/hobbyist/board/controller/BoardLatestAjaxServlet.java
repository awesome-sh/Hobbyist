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

/**
 * Servlet implementation class BoardLatestAjaxServlet
 */
@WebServlet("/board/boardLatest")
public class BoardLatestAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLatestAjaxServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Board> list = new BoardService().selectAll();
		
		String html = "";
		
		for(int i = 0; i < 4; i++) {
			html += "<li><a href='" + request.getContextPath() + "/board/boardView?boardNo=" + list.get(i).getBoardNo() + "'>&nbsp;·&nbsp;" + list.get(i).getBoardTitle() + "</a></li>";
		}
				
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().write(html);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
