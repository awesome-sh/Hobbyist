package com.hobbyist.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;

/**
 * Servlet implementation class AdminCommunityBoardUpdateServlet
 */
@WebServlet("/admin/community/adminCommunityBoardUpdate")
public class AdminCommunityBoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityBoardUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String noArr = request.getParameter("noArr");
		String readCountArr = request.getParameter("readCountArr");
		String statusArr = request.getParameter("statusArr");
		String likeArr = request.getParameter("likeArr");
		
		String[] no = noArr.split(",");
		String[] readCount = readCountArr.split(",");
		String[] status = statusArr.split(",");
		String[] like = likeArr.split(",");
		
		String loc = "/admin/community/adminCommunityList";
		String view = "/views/common/msg.jsp";
		
		for(int i = 0; i < no.length; i++) {
			Board b = new Board();
			b.setBoardNo(Integer.parseInt(no[i]));
			b.setBoardReadCount(Integer.parseInt(readCount[i]));
			b.setStatus(status[i]);
			b.setBoardLike(Integer.parseInt(like[i]));
			
			int result = new BoardService().updateBoardDetail(b);
			if(result <= 0) {
				request.setAttribute("msg", "저장오류!");
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
				break;
			}
		}
		
//		for(int i = 0; i < result.length; i++) {
//			if(result[i] <= 0) {
//				request.setAttribute("msg", "저장오류!");
//				request.setAttribute("loc", loc);
//				request.getRequestDispatcher(view).forward(request, response);
//				
//				break;
//			}
//		}
		
		request.setAttribute("msg", "저장완료");
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
