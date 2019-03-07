package com.hobbyist.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;

/**
 * Servlet implementation class AdminCommunityFAQDeleteServlet
 */
@WebServlet("/admin/community/adminCommunityFAQDelete")
public class AdminCommunityFAQDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityFAQDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int boardFAQNo = Integer.parseInt(request.getParameter("boardFAQNo"));
		
		int result = new BoardService().deleteFAQBoard(boardFAQNo);
		
		String msg = "";
		String loc = "";
		String view = "/views/common/msg.jsp";
		if(result > 0) {
			msg = "삭제되었습니다.";
			loc = "/admin/community/adminCommunityFAQList";
		} else {
			msg = "삭제 실패하였습니다.";
			loc = "/admin/community/adminCommunityFAQView?boardFAQNo=" + boardFAQNo;
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
