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
 * Servlet implementation class AdminMemberUpdateServlet
 */
@WebServlet("/adminMemberUpdateView.do")
public class AdminMemberUpdateViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminMemberUpdateViewServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		
		Member result = new MemberService().selectOne(m);
		
		request.setAttribute("result", result);
		request.getRequestDispatcher("/views/admin/member/admin_memberUpdate.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
