package com.hobbyist.admin.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.oneday.model.service.OnedayService;
import com.hobbyist.shop.model.service.ShopService;


@WebServlet("/admin/adminOnedayDelete")
public class AdminOnedayDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminOnedayDeleteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		
		int result = new OnedayService().deleteOneday(no);
		
		String msg = "";
		if(result>0) {
			msg = "성공적으로 삭제되었습니다.";
		} else {
			msg = "삭제실패!";
		}
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().print(msg);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
