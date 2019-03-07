package com.hobbyist.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardFAQ;

/**
 * Servlet implementation class AdminCommunityFAQInsertEndServlet
 */
@WebServlet("/admin/community/adminCommunityFAQInsertEnd")
public class AdminCommunityFAQInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityFAQInsertEndServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		BoardFAQ b = new BoardFAQ();
		
		b.setBoardFAQTitle(title);
		b.setBoardFAQCategory(category);
		b.setBoardFAQContent(content);
		
		int result = new BoardService().insertBoardFAQ(b);
		
		String msg = "";
		String loc = "/admin/community/adminCommunityFAQList";
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "FAQ추가 등록 되었습니다.";
		} else {
			msg = "FAQ추가 등록에 실패하였습니다.";
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
