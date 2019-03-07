package com.hobbyist.board.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardLike;

/**
 * Servlet implementation class BoardLikeServlet
 */
@WebServlet("/board/boardLike")
public class BoardLikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardLikeServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String userId = request.getParameter("userId");
		boolean flag = false;
		
		String users = new BoardService().selectLikeUsers(boardNo);
		String[] temp;
		List<String> userlist = new ArrayList();
		if(users != null) {
			temp = users.split(",");
			
			for(int i = 0; i < temp.length; i++) {
				userlist.add(temp[i]);
			}
		}
		
		String msg = "";
		String loc = "/board/boardView?boardNo=" + boardNo;
		String view = "/views/common/msg.jsp";
		
		for(int i = 0; i < userlist.size(); i++) {
			if(userlist.get(i).equals(userId)) {
				userlist.remove(i);
				flag = true;
				break;
			}
		}
		if(flag) {
			users = "";
			for(int i = 0; i < userlist.size(); i++) {
				if(i == 0) users = userlist.get(i);
				else users += "," + userlist.get(i);
			}
			
			new BoardService().updateLikeCountSub(boardNo);
			new BoardService().updateLikeUsers(boardNo, users);
			
			request.setAttribute("msg", "추천을 취소합니다.");
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
		
		if(!flag) {			if(users == null) users = userId;
			else users += "," + userId;
			
			new BoardService().updateLikeCountAdd(boardNo);
			new BoardService().updateLikeUsers(boardNo, users);
			
			request.setAttribute("msg", "추천되었습니다.");
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
