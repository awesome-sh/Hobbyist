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
 * Servlet implementation class ChangePwdServlet
 */
@WebServlet("/changePwd.do")
public class ChangePwdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChangePwdServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberPassword = request.getParameter("memberPassword");
		String memberPassword_new = request.getParameter("memberPassword_new");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		m.setMemberPassword(memberPassword);
		
		Member result = new MemberService().selectOne(m);
		System.out.println(memberEmail+memberPassword+memberPassword_new);
		
		String msg="";
		String loc ="";
		String view="/views/common/msg.jsp";
		
		
		
		System.out.println("서블릿 기존 : "+result);
		if(result.getMemberPassword().equals(memberPassword)) {
			m.setMemberPassword(memberPassword_new);
			int result_new = new MemberService().updatePwd(m);
			System.out.println("서블릿 new : "+result_new);
			if(result_new > 0) {
				msg="비밀번호 변경이 성공되었습니다.";
				loc="/";
				
			} else {
				msg="비밀번호 변경이 실패되었습니다. 다시 진행해주세요.";
				loc="/";
			}
		} else {
			msg="현재 비밀번호가 일치하지 않습니다.";
			loc="/views/mypage/updatePwd.jsp?member="+result.getMemberEmail();
			
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
