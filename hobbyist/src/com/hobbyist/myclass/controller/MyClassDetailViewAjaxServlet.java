package com.hobbyist.myclass.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.myclass.model.service.MyClassService;
import com.hobbyist.myclass.model.vo.Lecture;


@WebServlet("/myClassDetailView")
public class MyClassDetailViewAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MyClassDetailViewAjaxServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int no = Integer.parseInt(request.getParameter("no"));
		System.out.println("받은 숫자 : " + no);
		Lecture lecture = new MyClassService().selectLectureOne(no);
		
		String html = "";
		
		html += "<tr>";
		html += "<th colspan='2' style='text-align: left;'><h3>" + lecture.getLectureTitle() + "</h3></th>";
		html += "</tr>";
		
		html += "<tr>";
		
		html += "<td style='width: 760px; text-align: left;'>";
		html += "<img src='" + request.getContextPath() + "/images/video.jpg' width='720px'>";
		html += "</td>";
		
		html += "<td style='width: 210px; vertical-align: top; text-align: left;'>";
		html += "<h3>작가정보</h3>";
		html += "<img src='" + request.getContextPath() + "/images/people.png' width='100px'>";
		html += "<p>먹민아</p>";
		html += "<p>분야 : 가죽공예 장인</p>";
		html += "<p>보유클래스 : 14개</p>";
		html += "<button>이 작가의 다른 클래스 보러가기</button>";
		html += "</td>";
		
		html += "</tr>";
		
		html += "<tr>";
		html += "<td colspan='2'><button onclick='fn_progress(" + lecture.getLectureClass() +")'>봤어요!</button></td>";
		html += "</tr>";
		
		response.setContentType("text/html; charst=UTF-8");
		response.getWriter().print(html);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
