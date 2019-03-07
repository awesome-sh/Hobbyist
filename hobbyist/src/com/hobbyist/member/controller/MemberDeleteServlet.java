package com.hobbyist.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class MemberDeleteServlet
 */
@WebServlet("/memberDelete.do")
public class MemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MemberDeleteServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("deleteId");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		
		int result = new MemberService().deleteMember(m);
		String msg="";
		String loc="/";
		String view="/views/common/msg.jsp";
		
		if(result>0) {
			msg="회원 탈퇴가 완료되었습니다.";
			request.getSession(false).invalidate();
		} else {
			msg="탈퇴가 되지 않았습니다. 다시 시도해주세요.";
			loc="/memberUpdateView.do?memberEmail="+memberEmail;
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
