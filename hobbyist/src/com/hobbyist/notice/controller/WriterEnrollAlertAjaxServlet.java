package com.hobbyist.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;

/**
 * Servlet implementation class WriterEnrollAlertAjaxServlet
 */
@WebServlet("/notice/writerEnrollAlertAjax.do")
public class WriterEnrollAlertAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterEnrollAlertAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// 현시간 작가신청 하고 있는 공지글 가져오기
		WeNotice wnList = new NoticeService().cuWeSelectOne();
		Notice cuNotice = null;
		
		if (wnList != null) {
			boolean hasRead = true;
			cuNotice = new NoticeService().selectOne(wnList.getNoticeNo(), hasRead);
		}

		String html="";
		
		if(wnList != null) {
			html += "<div>[현재 작가모집중]</div>";
			html += "<ul><li><a href='"+ request.getContextPath() +"/notice/noticeView?noticeNo="+ cuNotice.getNoticeNo() +"'>새로운 하비스트 작가님을 모십니다</a></li></ul>";
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
