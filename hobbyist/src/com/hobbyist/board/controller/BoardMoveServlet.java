package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;

/**
 * Servlet implementation class BoardMoveServlet
 */
@WebServlet("/board/boardMove")
public class BoardMoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardMoveServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int flag = Integer.parseInt(request.getParameter("flag"));
		
		Cookie[] cookies = request.getCookies();
		String boardCookieVal = "";
		boolean hasRead = false;
		
		if(cookies != null) {
			output:
			for(Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();
				
				if("boardCookie".equals(name)) {
					boardCookieVal = value;
					if(value.contains("|" + boardNo + "|")) {
						hasRead = true;
						break output;
					}
				}
			}
		}
		
		if(!hasRead) {
			Cookie cookie = new Cookie("boardCookie", boardCookieVal + "|" + boardNo + "|");
			cookie.setMaxAge(1*24*60*60);
			response.addCookie(cookie);
		}

		Board b = null;
		int moveNo = 0;
		
		if(flag > 0) {
			moveNo = new BoardService().selectNextOne(boardNo);
			
			if(moveNo > 0) {
				b = new BoardService().selectOne(moveNo, hasRead);
			} else {
				request.setAttribute("msg", "더 이상의 게시판이 없습니다.");
				request.setAttribute("loc", "/board/boardView?boardNo=" + boardNo);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			}
			
		} else {
			moveNo = new BoardService().selectPrevOne(boardNo);
			
			if(moveNo > 0) {
				b = new BoardService().selectOne(moveNo, hasRead);
			} else {
				request.setAttribute("msg", "더 이상의 게시판이 없습니다.");
				request.setAttribute("loc", "/board/boardView?boardNo=" + boardNo);
				request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);;
			}
		}
		
		request.setAttribute("b", b);
		request.getRequestDispatcher("/views/board/boardView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
