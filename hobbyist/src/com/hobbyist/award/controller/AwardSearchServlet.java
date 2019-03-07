package com.hobbyist.award.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;

/**
 * Servlet implementation class AwardSearchServlet
 */
@WebServlet("/award/awardSearch")
public class AwardSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AwardSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String searchType= request.getParameter("searchType");
	if(searchType == null) searchType = "newUp";
	
	String searchKeyword=request.getParameter("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
	
	int cPage=0;
	try {
		cPage=Integer.parseInt(request.getParameter("cPage"));
	}catch(Exception e) {
		cPage=1;
	}
	int numPerPage=8;
	
	int totalCount = new AwardService().selectSearchCount(searchType,searchKeyword);
	List<Award> list=new AwardService().selectSearchList(cPage,numPerPage,searchType,searchKeyword);
	
	System.out.println("totalCount : "+ totalCount);
	System.out.println("list : "+ list);
	
	int totalPage = (int)Math.ceil((double)totalCount/numPerPage);
	String pageBar="";
	int pageBarSize=8;
	int pageNo = (cPage-1)/pageBarSize*pageBarSize+1;
	int pageEnd = pageNo+pageBarSize-1;
	
	if(pageNo == 1) {
		pageBar += "<span>이전</span>";
	}else {
		pageBar += "<a href='" + request.getContextPath() + "/award/awardSearch?cPage=" + (pageNo-1) + "&numPerPage=" + numPerPage  + "&searchKeyword=" + searchKeyword + "&searchType=" + searchType + "'>이전</a>";
	}
	
	while(!(pageNo > totalPage || pageNo > pageEnd)) {
		
		if(cPage == pageNo) {
			pageBar += "<span>" + pageNo + "</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/award/awardSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchKeyword=" + searchKeyword + "&searchType=" + searchType + "'>" + pageNo + "</a>";
		}
		pageNo++;
	}

	if(pageNo > totalPage) {
		pageBar += "<span>다음</span>";
	} else {
		pageBar += "<a href='" + request.getContextPath() + "/award/awardSearch?cPage=" + pageNo + "&numPerPage=" + numPerPage + "&searchKeyword=" + searchKeyword + "&searchType=" + searchType + "'>다음</a>";
	}
	
	
	request.setAttribute("list", list);
	request.setAttribute("cPage", cPage);
	request.setAttribute("searchKeyword", searchKeyword);
	request.setAttribute("numPerPage", numPerPage);
	request.setAttribute("pageBar", pageBar);
	request.getRequestDispatcher("/views/award/awardList.jsp").forward(request, response);
	
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
