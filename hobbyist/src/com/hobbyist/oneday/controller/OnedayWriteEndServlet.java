package com.hobbyist.oneday.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.oneday.model.vo.Oneday;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.rename.MyFileRenamePolicy;

@WebServlet("/oneday/onedayWriteEnd")
public class OnedayWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OnedayWriteEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("파일찾기 실패");
		} else {
			String root = getServletContext().getRealPath("/");
			String filePath = root + "upload" + File.separator + "oneday" + File.separator + "images";
			
			File f = new File(filePath);
			if(!f.exists()) {
				f.mkdir();
				System.out.println(f);
				System.out.println("새로운 디렉토리가 생성되었습니다!");
			}
			
			int maxSize = 1024*1024*10;
			MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			Oneday oneday = new Oneday();
			
			oneday.setOnedayCate(mr.getParameter("oneday_cate"));
			oneday.setOnedayName(mr.getParameter("oneday_name"));
			oneday.setOnedayInfo(mr.getParameter("oneday_info"));
			oneday.setOnedayWriter(mr.getParameter("oneday_writer"));
			oneday.setOnedayContent(mr.getParameter("oneday_content"));
			oneday.setOnedayPrice(Integer.parseInt(mr.getParameter("oneday_price")));
			oneday.setOnedayOption1(mr.getParameter("oneday_option1"));
			oneday.setOnedayOption2(mr.getParameter("oneday_option2"));
			oneday.setOnedayOption3(mr.getParameter("oneday_option3"));
			oneday.setOnedayOption4(mr.getParameter("oneday_option4"));
			oneday.setOnedayOption5(mr.getParameter("oneday_option5"));
			oneday.setOnedayImage1(mr.getFilesystemName("oneday_image1"));
			oneday.setOnedayPolicy(mr.getParameter("oneday_policy"));
			oneday.setOnedayAddress(mr.getParameter("oneday_address"));
			oneday.setOnedayPeople(Integer.parseInt(mr.getParameter("oneday_people")));
			
			int result = new OnedayService().onedayInsert(oneday);
			
			String msg = "";
			String loc = "";
			String view = "/views/common/msg.jsp";
			
			if(result >0) {
				msg = "원데이클래스 등록 완료";
				loc = "/admin/adminOnedayList";
			} else {
				msg = "원데이클래스 등록 실패";
				loc = "/admin/adminOnedayWrite";
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("loc", loc);
			request.getRequestDispatcher(view).forward(request, response);
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
