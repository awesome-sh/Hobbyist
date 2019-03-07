package com.hobbyist.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.member.model.vo.Member;
import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class MyPageServlet
 */
@WebServlet("/mypage/myPage")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//작가신청 메뉴 : 작가신청 여부 따른 메뉴 출력 판단을 위한 데이터 불러오기
		List<WriterEnroll> we = new WriterService().selectAll(); 
		
		Member logginMember = (Member)request.getSession().getAttribute("logginMember");
		int memberNo = logginMember.getMemberNo();
		boolean weFlag = false;
		for(WriterEnroll weP : we) {
			if(logginMember.getMemberNo() == weP.getMemberNo()) {
				weFlag = true;
			}
		}
		
		request.setAttribute("weFlag", weFlag);
		request.getRequestDispatcher("/views/mypage/myPage.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
