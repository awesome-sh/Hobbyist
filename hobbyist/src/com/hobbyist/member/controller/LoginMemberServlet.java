package com.hobbyist.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class LoginMemberServlet
 */
@WebServlet(name="LoginMemberServlet", urlPatterns="/member/loginMember")
public class LoginMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginMemberServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String email = request.getParameter("loginMemberEmail");
		String password = request.getParameter("memberPassword");
		
		Member m = new Member();
		m.setMemberEmail(email);
		m.setMemberPassword(password);
		
		Member result = new MemberService().selectOne(m);
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		
		if(result.getMemberEmail()!=null) {
			if(result.getMemberPassword().equals(password)) {
				msg = email+"님 환영합니다.";
				loc = "/index.jsp";
				
				// session에 로그인 정보 저장
				HttpSession session = request.getSession();
				session.setAttribute("logginMember", result);

				// cookie에 email 저장
				String saveEmail = request.getParameter("saveEmail");
				if(saveEmail!=null) {
					Cookie c = new Cookie("saveEmail", email);
					c.setMaxAge(7*24*60*60);//7일
					System.out.println(c.getName()+c.getValue());
					response.addCookie(c);
				}
				else {
					Cookie c = new Cookie("saveEmail", email);
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
			else {
				msg = "비밀번호가 일치하지 않습니다.";
				loc = "/member/loginPage?loginBtn=1";
			}
		}
		else {
			msg = "Email이 존재하지 않습니다.";
			loc = "/member/loginPage?loginBtn=1";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
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
