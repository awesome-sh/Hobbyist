package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.member.model.vo.Member;


/**
 * Servlet implementation class AdminMemberListServlet
 */
@WebServlet("/adminMemberList.do")
public class AdminMemberListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public AdminMemberListServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*//멤버 전체 띄워주는 서블릿 
		
		Member logginMember = (Member)request.getSession(false).getAttribute("logginMember");
		if(logginMember==null || !"admin".equals(logginMember.getMemberEmail())) {
			request.setAttribute("msg", "잘못된 경로로 이동하셨습니다.");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			System.out.println(logginMember);
		} else {*/
			int cPage; //현재 보고있는 페이지
			try {
				cPage=Integer.parseInt(request.getParameter("cPage"));
			} catch(NumberFormatException e) {
				cPage=1;
			}
			int numPerPage; //화면에 출력할 자료수 
			try {
				numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
			} catch(NumberFormatException e) {
				numPerPage=5;
			}
			
			//총페이지의 갯수 구하기
			int totalContent=new MemberService().selectMemberCount();
			int totalPage=(int)Math.ceil((double)totalContent/numPerPage);
			
			//보여줄 자료 가져오기
			List<Member> list = new MemberService().memberList(cPage,numPerPage);
			
			//pageBar 구성
			int pageBarSize=5; //bar에 출력할 페이지 수
			String pageBar=""; //bar를 만든 소스 코드(html)
			
			//시작지점 1,6,11,16,21... 등등 //기준 : cPage 1~5 : 1 / cPage 6~10 : 6
			int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;  
			int pageEnd=pageNo+pageBarSize;
			
			//페이지에 출력해줄 소스코드 작성
			//[이전] 코드 작성
			if(pageNo==1) {
				pageBar+="<span> < </span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/adminMemberList.do?cPage="+(pageNo-1)
						+"&numPerPage="+numPerPage+"'> < </a>";
			}
			
			//페이지 연결 숫자 소스 작성
			while(!(pageNo > pageEnd || pageNo > totalPage)) {
				if(cPage==pageNo) {
					pageBar+="<span class='cpage'>"+pageNo+"</span>";
				} else {
					pageBar+="<a href='"+request.getContextPath()+"/adminMemberList.do?cPage="+pageNo+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
				} 
				pageNo++;
			}		
			//[다음] 코드 작성
			if(pageNo > totalPage) {
				pageBar+="<span> > </span>";
			} else {
				pageBar+="<a href='"+request.getContextPath()+"/adminMemberList.do?cPage="+pageNo+"&numPerPage="+"'> > </a>";
			}

		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("list", list);
		request.setAttribute("totalContent", totalContent);
		request.getRequestDispatcher("/views/admin/member/admin_member.jsp").forward(request, response);
		}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
