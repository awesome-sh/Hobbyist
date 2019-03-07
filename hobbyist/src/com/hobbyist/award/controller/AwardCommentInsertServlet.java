package com.hobbyist.award.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.AwardComment;

/**
 * Servlet implementation class AwardCommentInsertServlet
 */
@WebServlet("/award/awardCommentInsert")
public class AwardCommentInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwardCommentInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int awardRef = Integer.parseInt(request.getParameter("awardRef"));
	String awardCommentWriter=request.getParameter("awardCommentWriter");
	String awardCommentContent =request.getParameter("awardCommentContent");
	int awardCommentLevel=Integer.parseInt(request.getParameter("awardCommentLevel"));
	int awardCommentRef=Integer.parseInt(request.getParameter("awardCommentRef"));
	
	AwardComment comment =new AwardComment();
	comment.setAwardRef(awardRef);
	comment.setAwardCommentContent(awardCommentContent);
	comment.setAwardCommentLevel(awardCommentLevel);
	comment.setAwardCommentRef(awardCommentRef);
	comment.setAwardCommentWriter(awardCommentWriter);
	int result=new AwardService().insertComment(comment);
	String msg="";
	String view="/views/common/msg.jsp";
	String loc="/award/awardView?awardNo="+awardRef;
	
	if(result>0) {
		msg="등록성공";
	}else {
		msg="등록실패";
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
