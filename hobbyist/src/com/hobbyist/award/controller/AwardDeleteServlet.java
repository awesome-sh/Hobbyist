package com.hobbyist.award.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;

/**
 * Servlet implementation class AwardDeleteServlet
 */
@WebServlet("/award/awardDelete")
public class AwardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AwardDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int deleteNo=Integer.parseInt(request.getParameter("deleteNo"));
		String deleteFile=request.getParameter("deleteFile");
		
		Award a=new Award();
		a.setAwardNo(deleteNo);
		
		String dir=getServletContext().getRealPath("/");
		String filePath=dir+File.separator+"upload"+File.separator+"award";
		
		int result=new AwardService().deleteAward(a);
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			File deleteFile2=new File(filePath+"/"+deleteFile);
			deleteFile2.delete();
			msg="어워드 게시글 삭제 완료. 다음에 다시 도전해 보세요~";
			loc="/award/awardList";
		}else {
			msg="어워드 게시글 삭제 실패";
			loc="/award/awardView?awardno="+deleteNo;
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
