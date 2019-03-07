package com.hobbyist.award.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class AwardFormEndServlet
 */
@WebServlet("/award/awardFormEnd")
public class AwardFormEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AwardFormEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (!ServletFileUpload.isMultipartContent(request)) {// 넘어온 request로 판단한다 (false일때 if문에 들어가라)
			// 제대로 전송되지 않았을 때
			request.setAttribute("msg", "오류");
			request.setAttribute("loc", "/");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}  else {
	         String dir = getServletContext().getRealPath("/");
	         dir += "upload/award/images";//파일 저장경로
	         int maxSize = 1024*1024*10;
	         MultipartRequest mr = new MultipartRequest(request, dir, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	         String title = mr.getParameter("title");
	         String writer = mr.getParameter("writer");
	         String content = mr.getParameter("content");
	         String file2 = mr.getOriginalFileName("up_file");
	         String file = mr.getFilesystemName("up_file");
	         
	         Award a=new Award();
	         a.setAwardName(title);
	         a.setAwardWriter(writer);
	         a.setAwardContent(content);
	         a.setAwardOriginalFilename(file2);
	         a.setAwardRenamedFilename(file);
		int result=new AwardService().insertAward(a);
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
	
		if(result>0) {
			msg="어워드 참여 성공";
			loc="/award/awardView?awardNo="+result;
		}else {
			msg="어워드 참여 실패";
			loc="/award/awardForm";
		}
		request.setAttribute("msg", msg);
        request.setAttribute("loc", loc);
        request.getRequestDispatcher(view).forward(request, response);
		}
     
		
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
