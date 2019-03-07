package com.hobbyist.admin.controller;

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

@WebServlet("/admin/adminOnedayUpdate")
public class AdminOnedayUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOnedayUpdateServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ------------------------  클래스 상품 등록
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("파일찾기 실패");
		} else {
			String root = getServletContext().getRealPath("/");
			String filePath = root + File.separator + "upload" + File.separator + "oneday" + File.separator + "images";
			int maxSize = 1024*1024*10;
			MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			Oneday oneday = new Oneday();
			oneday.setOnedayNo(Integer.parseInt(mr.getParameter("oneday_no")));
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
			oneday.setOnedayImage2(mr.getFilesystemName("oneday_image2"));
			oneday.setOnedayImage3(mr.getFilesystemName("oneday_image3"));
			oneday.setOnedayImage4(mr.getFilesystemName("oneday_image4"));
			oneday.setOnedayImage5(mr.getFilesystemName("oneday_image5"));
			oneday.setOnedayPolicy(mr.getParameter("oneday_policy"));
			oneday.setOnedayPeople(Integer.parseInt(mr.getParameter("oneday_people")));
			oneday.setOnedayAddress(mr.getParameter("oneday_address"));

			File f = mr.getFile("oneday_image1");
			
			if(f!=null && f.length()>0) {
				File deleteFile = new File(filePath + "/" + mr.getParameter("old_file"));
				boolean resul = deleteFile.delete();
				System.out.println(resul?"제대로지워짐":"안지워졌어");
			} else {
				oneday.setOnedayImage1(mr.getParameter("old_file"));
			}
			
			
			int result = new OnedayService().updateOneday(oneday);

			
			String msg = "";
			String loc = "";
			String view = "/views/common/msg.jsp";
			
			if(result >0) {
				msg = "클래스 수정완료";
				loc = "/admin/adminOnedayList";
			} else {
				msg = "클래스 수정실패";
				loc = "/admin/adminOnedayView?no=" + oneday.getOnedayNo();
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
