package com.hobbyist.shop.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.shop.model.service.ShopService;
import com.hobbyist.shop.model.vo.Shop;
import com.hobbyist.shop.model.vo.Study;
import com.oreilly.servlet.MultipartRequest;

import common.rename.MyFileRenamePolicy;

@WebServlet("/shop/shopWriteEnd")
public class ShopWriteEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ShopWriteEndServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ------------------------  클래스 상품 등록
		if(!ServletFileUpload.isMultipartContent(request)) {
			System.out.println("파일찾기 실패");
		} else {
			String root = getServletContext().getRealPath("/");
			String filePath = root + File.separator + "upload" + File.separator + "shop" + File.separator + "images";
			int maxSize = 1024*1024*10;
			MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());

			Shop shop = new Shop();
			
			shop.setShopCate(mr.getParameter("class_cate"));
			shop.setShopName(mr.getParameter("class_name"));
			shop.setShopInfo(mr.getParameter("class_info"));
			shop.setShopWriter(mr.getParameter("class_writer"));
			shop.setShopContent(mr.getParameter("class_content"));
			shop.setShopPrice(Integer.parseInt(mr.getParameter("class_price")));
			shop.setShopPoint(Integer.parseInt(mr.getParameter("class_point")));
			shop.setShopOption1(mr.getParameter("class_option1"));
			shop.setShopOption2(mr.getParameter("class_option2"));
			shop.setShopOption3(mr.getParameter("class_option3"));
			shop.setShopOption4(mr.getParameter("class_option4"));
			shop.setShopOption5(mr.getParameter("class_option5"));
			shop.setShopImage1(mr.getFilesystemName("class_image1"));
			shop.setShopImage2(mr.getFilesystemName("class_image2"));
			shop.setShopImage3(mr.getFilesystemName("class_image3"));
			shop.setShopImage4(mr.getFilesystemName("class_image4"));
			shop.setShopImage5(mr.getFilesystemName("class_image5"));
			shop.setShopPolicy1(mr.getParameter("class_policy1"));
			shop.setShopPolicy2(mr.getParameter("class_policy2"));
			shop.setShopPolicy3(mr.getParameter("class_policy3"));
			
			// 클래스샵 등록직후 생성된 클래스샵번호 받아오기 (시퀀스.CURRVAL 이용)
			int shopNo = new ShopService().insertShop(shop);
			
			// ------------------------ 강좌 등록
			Study study = new Study();
			study.setStudyClass(shopNo);
			study.setStudyWriter(mr.getParameter("class_writer"));
			study.setStudyTitle(mr.getParameter("class_name"));
			study.setStudySubTitle(mr.getParameter("study_subtitle"));
			study.setStudyVideo(mr.getParameter("study_video"));
			study.setStudyContent(mr.getParameter("study_content"));
			
			int result = new ShopService().insertStudy(study);
			
			String msg = "";
			String loc = "";
			String view = "/views/common/msg.jsp";
			
			if(result >0) {
				msg = "클래스 등록 완료";
				loc = "/shop/shopList";
			} else {
				msg = "클래스 등록 실패";
				loc = "/shop/shopWrite";
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
