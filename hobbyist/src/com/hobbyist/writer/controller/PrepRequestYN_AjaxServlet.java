package com.hobbyist.writer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.writer.model.service.WriterService;

/**
 * Servlet implementation class PrepRequestYN_AjaxServlet
 */
@WebServlet("/mypage/prepRequestYN_Ajax.do")
public class PrepRequestYN_AjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrepRequestYN_AjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int writerEnrollNo = Integer.parseInt(request.getParameter("writerEnrollNo"));
		String flagYN = request.getParameter("flagYN");
		
		String writerPrepRequestYN = "";
		if(flagYN.equals("1")) {
			writerPrepRequestYN = "Y";
		} else {
			writerPrepRequestYN = "N";
		}
		
		int result = new WriterService().prepRequestYN(writerEnrollNo,writerPrepRequestYN);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
