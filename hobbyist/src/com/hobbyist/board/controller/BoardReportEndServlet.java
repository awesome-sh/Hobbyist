package com.hobbyist.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardReport;

/**
 * Servlet implementation class BoardReportEndServlet
 */
@WebServlet("/board/boardReportEnd")
public class BoardReportEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardReportEndServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		String loginUser = request.getParameter("loginUser");
		String reportedUser = request.getParameter("reportedUser");
		String reportCategory = request.getParameter("reportCategory");
		String reportContent = request.getParameter("reportContent");
		String reportedContent = request.getParameter("reportedContent");
		String boardMainCategory = request.getParameter("boardMainCategory");
		
		BoardReport b = new BoardReport();
		
		b.setBoardNo(boardNo);
		b.setBoardMainCategory(boardMainCategory);
		b.setBoardWriter(reportedUser);
		b.setBoardReporter(loginUser);
		b.setBoardContent(reportedContent);
		b.setBoardReportContent(reportContent);
		b.setBoardReportCategory(reportCategory);
		
		int result = new BoardService().insertBoardReport(b);
		String msg = "";
		String loc = "/";
		String view = "/views/common/msg.jsp";
	
		if(result > 0) msg = "신고가 접수되었습니다. 감사합니다.";
		else msg = "신고중 오류발생";
		
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
