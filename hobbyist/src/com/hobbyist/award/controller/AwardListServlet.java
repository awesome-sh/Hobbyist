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
 * Servlet implementation class AwardListServlet
 */
@WebServlet("/award/awardList")
public class AwardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AwardListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int cPage=0;
		try {
			cPage=Integer.parseInt(request.getParameter("cPage"));
		}catch(NumberFormatException e) {
			cPage=1;
		}
		int numPerPage=0;
		try {
			numPerPage=Integer.parseInt(request.getParameter("numPerPage"));
		}catch(NumberFormatException e) {
			numPerPage=12;
		}
		
		
		int totalContent=new AwardService().selectCount();
		int totalPage=(int)Math.ceil((double)totalContent/numPerPage);
		List<Award> list=new AwardService().selectList(cPage,numPerPage);
	
		int pageBarSize=8;
		String pageBar="";
		
		int pageNo=((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd=pageNo+pageBarSize-1;
		
		//페이지에 출력해줄 소스코드 작성
				//[이전] 코드작성
				if(pageNo==1) {
					pageBar+="<span>[이전]</span>";
				}
				else {
					pageBar+="<a href='"+request.getContextPath()
					+"/award/awardList?cPage="+(pageNo-1)
					+"&numPerPage="+numPerPage+"'>[이전]</a>";
				}
				//페이지연결 숫자 소스작성
				while(!(pageNo>totalPage||pageNo>pageEnd))
				{
					if(cPage==pageNo)
					{
						pageBar+="<span class='cPage'>"+pageNo+"</span>";
					}
					else 
					{
						pageBar+="<a href='"+request.getContextPath()
						+"/award/awardList?cPage="+pageNo
						+"&numPerPage="+numPerPage+"'>"+pageNo+"</a>";
					}
					pageNo++;
				}
				//[다음] 코드작성
				if(pageNo>totalPage)
				{
					pageBar+="<span>[다음]</span>";
				}
				else {
					pageBar+="<a href='"+request.getContextPath()
					+"/award/awardList?cPage="+pageNo
					
					+"&numPerPage="+numPerPage+"'>[다음]</a>";
				}
				
				for(Award a : list) {
					System.out.println(a.toString());
				}
		request.setAttribute("list", list);
		request.setAttribute("cPage", cPage);
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
