package com.hobbyist.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;

/**
 * Servlet implementation class SearchPwdServlet
 */
@WebServlet("/searchId.do")
public class SearchIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchIdServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberName = request.getParameter("memberName");
		String memberPhone = request.getParameter("memberPhone");
		
		Member m = new Member();
		
		m.setMemberName(memberName);
		m.setMemberPhone(memberPhone);
		
		Member result = new MemberService().selectMemberName(m);
		
		String view="/views/common/msg.jsp";
		String msg="";
		/*String view="/views/member/searchId.jsp";*/

		if(result.getMemberName()!=null && result.getMemberPhone()!=null) {
			msg="아이디는"+result.getMemberEmail()+"입니다.";
			System.out.println("아이디 찾음");
		} else {
			msg="아이디가 없습니다.";
			System.out.println("아이디없음");
		}
		
		JSONObject json = new JSONObject();
    	json.put("msg", msg);

		response.setContentType("application/json; charset=UTF-8");
    	response.getWriter().print(msg);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
