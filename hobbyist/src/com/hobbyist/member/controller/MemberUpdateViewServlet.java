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
 * Servlet implementation class MemberUpdateViewServlet
 */
@WebServlet("/memberUpdateView.do")
public class MemberUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdateViewServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		
		Member result = new MemberService().selectOne(m);
		
		System.out.println("회원정보 수정 서블릿 값 확인 >>>  " + result);
		
		String msg ="";
		String loc="";
		String view="";
		if(result==null) {
			msg="등록된 회원이 없습니다.";
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
		} else {
			view="/views/mypage/updateInfo.jsp";
			request.setAttribute("result", result);
		}
		request.getRequestDispatcher(view).forward(request, response);
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
