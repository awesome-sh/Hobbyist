package com.hobbyist.writer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.service.MemberService;
import com.hobbyist.writer.model.service.WriterService;

/**
 * Servlet implementation class AdminWEPassAjaxServlet
 */
@WebServlet("/admin/adminWEPassAjax.do")
public class AdminWEPassAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminWEPassAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int writerEnrollNo = Integer.parseInt(request.getParameter("index"));
		String writerPassYn = request.getParameter("passFlag");
		String memberEmail = request.getParameter("memberEmail");
		
		int result = 0;
		int resultM = 0;
		if (writerPassYn.equals("pass")) {
			result = new WriterService().passWriterEnroll(writerEnrollNo);
			resultM = new MemberService().writerPassUpdate(memberEmail);
		}
		else if (writerPassYn.equals("fail")) {
			result = new WriterService().failWriterEnroll(writerEnrollNo);
			resultM = new MemberService().writerFailUpdate(memberEmail);
		}
		else if (writerPassYn.equals("return")) {
			result = new WriterService().returnWriterEnroll(writerEnrollNo);
			resultM = new MemberService().writerReUpdate(memberEmail);
		}
		
		if(result>0) {
			System.out.println("writerEnrollNo : "+ writerEnrollNo + "  '작가신청 테이블 합격 여부 정상 처리 완료'");
		} else {
			System.out.println("writerEnrollNo : "+ writerEnrollNo + "  '작가신청 테이블 합격 여부 정상 처리 실패'");
		}
		
		if(resultM>0) {
			System.out.println("writerEnrollNo : "+ writerEnrollNo + "  '맴버 테이블 합격 여부 정상 처리 완료'");
		} else {
			System.out.println("writerEnrollNo : "+ writerEnrollNo + "  '맴버 테이블 합격 여부 정상 처리 실패'");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
