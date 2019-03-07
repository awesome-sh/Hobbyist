package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class BoardReportServlet
 */
@WebServlet("/board/boardReport")
public class BoardReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportServlet() {
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
		
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String loginUser = request.getParameter("loginUser");
		String reportedUser = request.getParameter("reportedUser");
		String reportedContent = request.getParameter("reportedContent");
		String boardMainCategory = request.getParameter("boardMainCategory");
		
		System.out.println(boardNo + " : " + boardMainCategory);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("boardMainCategory", boardMainCategory);
		request.setAttribute("loginUser", loginUser);
		request.setAttribute("reportedUser", reportedUser);
		request.setAttribute("reportedContent", reportedContent);
		request.getRequestDispatcher("/views/board/boardReport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
