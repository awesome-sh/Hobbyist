package com.hobbyist.oneday.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.oneday.model.service.OnedayService;

@WebServlet("/oneday/onedayWriterSearch")
public class OnedayWriterSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public OnedayWriterSearchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String classWriter = request.getParameter("class_writer");

		List<Member> list = new OnedayService().writerSearch(classWriter);
		
		String html = "";
		// 검색 DIV 기본틀
		html += "<div id='sw' style='position:absoulte; width:300px; height:100px;'>";
		html += "<ul>";
		
		// DB에 가지고 있는 작가들 닉네임 가져오고 뿌려주기
		for(int i=0; i<list.size(); i++) {
			html += "<li onclick='fn_searched(this)' style='cursor:pointer'>" + list.get(i).getMemberNickname() + "</li>";
		}

		html += "</ul></div>";
		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
