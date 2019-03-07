package com.hobbyist.award.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.award.model.service.AwardService;
import com.hobbyist.award.model.vo.Award;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

/**
 * Servlet implementation class AwardUpdateEndServlet
 */
@WebServlet("/award/updateEnd")
public class AwardUpdateEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AwardUpdateEndServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "삐빅 오류!");
			request.setAttribute("loc", "/award/awardList");
			request.getRequestDispatcher("/views/common/msg.jsp").forward(request, response);
			return;
		}else {

			String root=getServletContext().getRealPath("/");
			String filePath=root+File.separator+"upload"+File.separator+"award"+File.separator+"images";
			int maxSize=1024*1024*10;
			MultipartRequest mr=new MultipartRequest(request, filePath,maxSize,"UTF-8",new DefaultFileRenamePolicy());
			
			Award a=new Award();
			a.setAwardNo(Integer.parseInt(mr.getParameter("awardNo")));
			a.setAwardName(mr.getParameter("title"));
			a.setAwardWriter(mr.getParameter("writer"));
			a.setAwardContent(mr.getParameter("content"));
			String fileName=mr.getFilesystemName("up_file");//새로 받을 파일은 filesysname으로 

			File f=mr.getFile("up_file");
			if(f!=null&&f.length()>0) {
				File deleFile=new File(filePath+"/"+mr.getParameter("old_file"));
				boolean flag = deleFile.delete();
			} else {
				fileName=mr.getParameter("old_file");//기존에 있던 파일 그대로 받기
			}
			a.setAwardOriginalFilename(fileName);

		
			int result = new AwardService().updateAward(a);
			
			String msg="";
			String loc="";
			String view="/views/common/msg.jsp";
			
			if(result>0) {
				msg="어워드 수정 완료";
				loc="/award/awardView?awardNo=" + a.getAwardNo();
			} else {
				msg="어워드 파일 수정 실패";
				loc="/award/awardView?awardNo=" + a.getAwardNo();
			}

			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
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
