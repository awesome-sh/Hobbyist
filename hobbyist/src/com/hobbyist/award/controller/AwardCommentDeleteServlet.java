package com.hobbyist.award.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;

/**
 * Servlet implementation class AwardCommentDeleteServlet
 */
@WebServlet("/award/awardCommentDelete")
public class AwardCommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwardCommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int deleteNo=Integer.parseInt(request.getParameter("deleteNo"));
	int awardNo=Integer.parseInt(request.getParameter("awardNo"));
	int result=new AwardService().deleteComment(deleteNo);
	String msg="";
	String loc="/award/awardView?awardNo="+awardNo;//다시 어워드 게시물로 보낼거임
	String view="/views/common/msg.jsp";
	if(result>0) {
		msg="댓글삭제 완료했습니다";
	}else {
		msg="댓글삭제 실패했습니다";
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
