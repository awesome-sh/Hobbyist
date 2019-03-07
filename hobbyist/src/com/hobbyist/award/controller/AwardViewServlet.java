package com.hobbyist.award.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;
import com.hobbyist.award.model.vo.AwardComment;


/**
 * Servlet implementation class AwardViewServlet
 */
@WebServlet("/award/awardView")
public class AwardViewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AwardViewServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int awardNo = Integer.parseInt(request.getParameter("awardNo"));

		Cookie[] cookies = request.getCookies();
		String awardCookieVal = "";
		boolean hasRead = false;

		// 클릭시에 아이디값남겨서 조회수 중복방지하기 위해서 쿠키
       	if (cookies != null) {
			output: for (Cookie c : cookies) {
				String name = c.getName();
				String value = c.getValue();

				if ("awardCookie".equals(name)) {
					awardCookieVal = value;
					if (value.contains("|" + awardNo + "|")) {
						hasRead = true;
						break output;
					}
				}
			}
		}

		if (!hasRead) {
			Cookie cookie = new Cookie("awardCookie", awardCookieVal + "|" + awardNo + "|");
			cookie.setMaxAge(-1);
			response.addCookie(cookie);
		}

		
		System.out.println(hasRead);
		Award a = new AwardService().selectOne(awardNo, hasRead);
		
		if(a!=null) {
		List<AwardComment> comments=new AwardService().selectCommentAll(awardNo);
		
		request.setAttribute("comments", comments);
		}
		request.setAttribute("award", a);// ""안에 .jsp속성이랑 맞춰야됨
		
		request.getRequestDispatcher("/views/award/awardView.jsp").forward(request, response);

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
