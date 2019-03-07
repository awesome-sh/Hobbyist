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
 * Servlet implementation class ChangePwdViewServlet
 */
@WebServlet("/changePwdView.do")
public class ChangePwdViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public ChangePwdViewServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		
		Member result = new MemberService().selectOne(m);
		
		String loc="";
		String view="/views/mypage/updatePwd.jsp";
		if(result!=null) {
			loc="/";
		} else {
			loc="/";
		}
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
