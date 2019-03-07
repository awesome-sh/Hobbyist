package com.hobbyist.notice.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;

/**
 * Servlet implementation class NewLatestNoticeAjaxServlet
 */
@WebServlet("/notice/newLatestNoticeAjax.do")
public class NewLatestNoticeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewLatestNoticeAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Notice> list = new NoticeService().newLatestList();

		Date from = list.get(0).getNoticeDate();
		SimpleDateFormat transFormat = new SimpleDateFormat("[yyyy/MM/dd]");
		String to = transFormat.format(from);

		String html = "";

		for (int i = 0; i < list.size(); i++) {
			if (i < 4) {
				html += "<li><a href='" + request.getContextPath() + "/notice/noticeView?noticeNo="
						+ list.get(i).getNoticeNo() + "'>" + to + "&nbsp;&nbsp;" + list.get(i).getNoticeTitle()
						+ "</a></li>";
			}
		}

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
