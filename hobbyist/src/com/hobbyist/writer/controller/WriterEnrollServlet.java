package com.hobbyist.writer.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.WeNotice;
import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class WriterEnrollServlet
 */
@WebServlet("/writer/writerEnroll")
public class WriterEnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriterEnrollServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인한 회원만 작가신청 페이지로 들어갈 수 있는 분기문 변수
		HttpSession session = request.getSession();
		String msg = "";
		String loc = "";
		String view = "";
		
		//작가신청 관련 작가신청 기간이 아닐때 막는 로직 변수
		String weQuarter = request.getParameter("weQuarter");
		WeNotice wnList = new NoticeService().cuWeSelectOne();
		
		//작가신청 기간 중복 신청 막는 변수
		List<WriterEnroll> we = new WriterService().selectQuarter(weQuarter);
		
		System.out.println(weQuarter);
		if(session.getAttribute("logginMember")==null) {
			msg="작가신청을 하기 위해서는 로그인이 필요합니다.";
			loc="/notice/noticeList";
			view="/views/common/msg.jsp";
		}
		else if(!wnList.getWeQuarter().equals(weQuarter)) {
			
			//작가신청 관련 작가신청 기간이 아닐때 막는 로직
			msg="작가신청기간이 아닙니다.";
			loc="/notice/noticeList";
			view="/views/common/msg.jsp";
		} else if(we.size()!=0) {
			//작가신청 기간 중복 신청 막는 로직
			msg="이번 신청기간에 이미 작가신청을 하셨습니다.";
			loc="/mypage/mypage_writerEnrollList";
			view="/views/common/msg.jsp";
		} else {
			view="/views/writer/writerEnroll.jsp";
		}
		

		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.setAttribute("weQuarter", weQuarter);
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
