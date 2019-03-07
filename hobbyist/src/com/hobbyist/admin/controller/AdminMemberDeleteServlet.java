package com.hobbyist.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class AdminMemberDeleteServlet
 */
@WebServlet("/adminMemberDelete.do")
public class AdminMemberDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminMemberDeleteServlet() {
        super();
      
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmailAdmin");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		
		int result = new MemberService().deleteMember(m);
		
		String msg="";
		String loc="/";
		String view="/views/common/msg.jsp";
		
		if(result>0) {
			msg="회원 탈퇴가 완료되었습니다.";
			loc="/adminMemberList.do";
		} else {
			msg="탈퇴가 되지 않았습니다. 다시 시도해주세요.";
			loc="/adminMemberList.do";
			System.out.println("서블릿에서 이메일 : "+memberEmail);
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
