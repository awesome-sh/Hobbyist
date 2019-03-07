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
 * Servlet implementation class LoginNicknameCheckServlet
 */
@WebServlet("/nickname.do")
public class LoginNicknameCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginNicknameCheckServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberNickname = request.getParameter("memberNickname");
		Member m = new Member();
		m.setMemberNickname(memberNickname);
		
		int result = new MemberService().nicknameCheck(memberNickname);
		
		response.getWriter().println(result);
		System.out.println("서블릿닉네임 : "+result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
