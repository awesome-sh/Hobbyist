package com.hobbyist.notice.controller;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeModifyServlet
 */
@WebServlet("/notice/noticeModify")
public class NoticeModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeModifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		// 수정페이지는 조회수 카운트 안함
		boolean hasRead = true;
		Notice notice = new NoticeService().selectOne(noticeNo, hasRead);
		
		// 작성자 프로필 이미지 가져오기
		String writer = notice.getNoticeWriter();
		String profileImg = new NoticeService().writerImg(writer);
		
		
		//현재 시간 구하기
		long currentTime = System.currentTimeMillis();
		Date cuTime = new Date(currentTime);
		
		request.setAttribute("profileImg", profileImg);
		request.setAttribute("notice", notice);
		request.setAttribute("cuTime", cuTime);
		request.getRequestDispatcher("/views/notice/noticeModify.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
