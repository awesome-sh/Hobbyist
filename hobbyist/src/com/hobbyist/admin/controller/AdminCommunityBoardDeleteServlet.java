package com.hobbyist.admin.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.Board;

/**
 * Servlet implementation class AdminCommunityBoardDeleteServlet
 */
@WebServlet("/admin/community/adminCommunityBoardDelete")
public class AdminCommunityBoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityBoardDeleteServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String noArr = request.getParameter("noArr");
		String[] no = noArr.split(",");
		
		String fnameArr = request.getParameter("fnameArr");
		String [] fname = fnameArr.split(",");
		
		String dir = getServletContext().getRealPath("/");
		String filePath = dir + File.separator + "upload" + File.separator + "board";
		
		String loc = "/admin/community/adminCommunityList";
		String view = "/views/common/msg.jsp";
		
		for(int i = 0; i < no.length; i++) {
			
			int result = new BoardService().deleteBoardDetail(Integer.parseInt(no[i]));
			if(result <= 0) {
				request.setAttribute("msg", "삭제오류!");
				request.setAttribute("loc", loc);
				request.getRequestDispatcher(view).forward(request, response);
				break;
			}
		}
		
		for(int i = 0; i < no.length; i++) {
			File deleteFile = new File(filePath + "/" + fname[i]);
			deleteFile.delete();
		}
		
		request.setAttribute("msg", "삭제완료");
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
