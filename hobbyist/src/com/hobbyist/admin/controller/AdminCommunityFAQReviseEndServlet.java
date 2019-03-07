package com.hobbyist.admin.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.board.model.service.BoardService;
import com.hobbyist.board.model.vo.BoardFAQ;

/**
 * Servlet implementation class AdminCommunityFAQReviseEndServlet
 */
@WebServlet("/admin/community/adminCommunityFAQReviseEnd")
public class AdminCommunityFAQReviseEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminCommunityFAQReviseEndServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardFAQNo = Integer.parseInt(request.getParameter("boardFAQNo"));
		String title = request.getParameter("title");
		String category = request.getParameter("category");
		String content = request.getParameter("content");
		
		switch (category) {
			case "all": category = ""; break;
			case "changeAndCancel": category = "변경/취소"; break;
			case "depositAndDelivery": category = "입금/배송"; break;
			case "exchangeAndReturn": category = "교환/반품"; break;
			case "therInquiries": category = "기타문의"; break;
			case "saleAndBenefits": category = "할인/혜택"; break;
			case "memberAndBenefits": category = "회원/혜택"; break;
			case "orderAndLookup": category = "주문/조회"; break;
			case "paymentInquiry": category = "결제문의"; break;
			case "soldOutAndWearing": category = "품절/입고"; break;
			case "contactUs": category = "상품문의"; break;
		}
		
		BoardFAQ b = new BoardFAQ();
		
		b.setBoardFAQNo(boardFAQNo);
		b.setBoardFAQCategory(category);
		b.setBoardFAQTitle(title);
		b.setBoardFAQContent(content);
		
		int result = new BoardService().updateFAQBoard(b);
		
		String msg = "";
		String loc = "/admin/community/adminCommunityFAQView?boardFAQNo=" + boardFAQNo;
		String view = "/views/common/msg.jsp";
		
		if(result > 0) {
			msg = "수정완료";
		} else {
			msg = "수정실패";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
