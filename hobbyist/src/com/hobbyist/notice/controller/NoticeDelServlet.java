package com.hobbyist.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDelServlet
 */
@WebServlet("/notice/noticeDel")
public class NoticeDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
		
		int result = new NoticeService().delNotice(noticeNo);
		int wnResult = new NoticeService().wnDelNotice(noticeNo);
		

		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0&&wnResult>0) {
			msg="공지사항 삭제 성공";
			loc="/notice/noticeList";
		}
		else {
			msg="공지사항 삭제 실패";
			loc="/notice/noticeList";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
