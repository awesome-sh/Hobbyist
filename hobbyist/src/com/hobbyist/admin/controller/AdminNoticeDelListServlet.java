package com.hobbyist.admin.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.vo.Notice;

/**
 * Servlet implementation class AdminNoticeDelListServlet
 */
@WebServlet("/admin/adminNoticeDelList")
public class AdminNoticeDelListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminNoticeDelListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//현재 시간 구하기
		long currentTime = System.currentTimeMillis();
		Date cuTime = new Date(currentTime);
		
		List<Notice> list = null;
		String sort = "";
		int cPage = 0;
		int numPerPage = 0;
		String pageBar = "";
		String keyword = "";
		request.setAttribute("list", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.setAttribute("cuTime", cuTime);
		request.getRequestDispatcher("/views/admin/notice/admin_notice_delList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
