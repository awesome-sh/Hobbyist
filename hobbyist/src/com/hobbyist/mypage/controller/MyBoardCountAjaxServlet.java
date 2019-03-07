package com.hobbyist.mypage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class MyBoardCountServlet
 */
@WebServlet("/mypage/myBoardCount")
public class MyBoardCountAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyBoardCountAjaxServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Member logginMember = (Member)request.getSession().getAttribute("logginMember");
		
		int selectMyPageBoardCount = new BoardService().selectMyPageBoardCount(logginMember.getMemberNickname());
		int selectMyPageBoardCommentCount = new BoardService().selectMyPageBoardCommentCount(logginMember.getMemberNickname());
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().println(selectMyPageBoardCount + "," + selectMyPageBoardCommentCount);
	
	
	
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
