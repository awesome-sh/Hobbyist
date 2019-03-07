package com.hobbyist.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;

@WebServlet("/adminMemberUpdate.do")
public class AdminMemberUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public AdminMemberUpdateServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberEmail = request.getParameter("memberEmail");
		String memberNickname = request.getParameter("memberNickname");
		String memberGrade = request.getParameter("memberGrade");		
		String memberPhone = request.getParameter("memberPhone");
		String memberAddress = request.getParameter("memberAddress");
		String memberMemo = request.getParameter("memberMemo");
		
		Member m = new Member();
		m.setMemberEmail(memberEmail);
		m.setMemberNickname(memberNickname);
		m.setMemberGrade(memberGrade);
		m.setMemberPhone(memberPhone);
		m.setMemberAddress(memberAddress);
		m.setMemberMemo(memberMemo);
		
		int result = new MemberService().updateAdmin(m);
		System.out.println("result : "+result);
		
		System.out.println(memberEmail+" "+memberNickname+" "+memberPhone+" "+memberGrade+" "+memberAddress+" "+memberMemo);
		
		String msg = "";
		String loc = "";
		String view= "/views/common/msg.jsp";
		if(result>0) {
			System.out.println("회원수정성공");
			msg="회원수정 성공";
			loc="/adminMemberList.do";
		} else {
			System.out.println("회원수정 실패!!!!!");
			msg="회원수정 실패!!!";
			loc="/";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
