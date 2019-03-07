package com.hobbyist.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet("/member/loginPage")
public class LoginPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginPageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// loginFlag : 로그인페이지 출력 분기처리 (메인페이지에서 로그인 클릭 했을 때 로그인 페이지 출력)
		// signupWindow : 로그인페이지에서 회원가입창 띄우기
		boolean loginFlag = false;
		boolean signupWindow = false;
		if(request.getParameter("loginBtn")!=null) {
			loginFlag = true;
		}
		if(request.getParameter("signupWindow")!=null) {
			signupWindow = true;
		}
		request.setAttribute("loginFlag", loginFlag);
		request.setAttribute("signupWindow", signupWindow);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
