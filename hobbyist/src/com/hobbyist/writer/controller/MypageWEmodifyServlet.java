package com.hobbyist.writer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class MypageWEmodifyServlet
 */
@WebServlet("/mypage/mypageWEmodify")
public class MypageWEmodifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MypageWEmodifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int writerEnrollNo = Integer.parseInt(request.getParameter("writerEnrollNo"));
		
		WriterEnroll we = new WriterEnroll();
		we.setWriterEnrollNo(writerEnrollNo);
		
		WriterEnroll weList = new WriterService().selectOne(we);
		weList.setWriterReason(weList.getWriterReason().replaceAll("<br>", "\r\n"));
		weList.setWriterClassSelectReason(weList.getWriterClassSelectReason().replaceAll("<br>", "\r\n"));
		weList.setWriterClassKitWarningPoint(weList.getWriterClassKitWarningPoint().replaceAll("<br>", "\r\n"));
		weList.setWriterClassKitPart(weList.getWriterClassKitPart().replaceAll("<br>", "\r\n"));
		weList.setWriterFinalPoint(weList.getWriterFinalPoint().replaceAll("<br>", "\r\n"));
		
		boolean weFlag = true;

		request.setAttribute("weList", weList);
		request.setAttribute("weFlag", weFlag);
		request.getRequestDispatcher("/views/mypage/writer/myPage_writerEnrollModify.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
