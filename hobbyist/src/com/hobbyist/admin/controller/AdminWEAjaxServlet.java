package com.hobbyist.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class AdminWEAjaxServlet
 */
@WebServlet("/admin/adminWEAjax.do")
public class AdminWEAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminWEAjaxServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// 페이징처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch ( NumberFormatException e) {
			cPage = 1;
		}
		
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch ( NumberFormatException e) {
			numPerPage = 5;
		}
		
		// 정렬방법&검색결과 적용 전체클래스 갯수, 페이지 갯수
		int totalCount = 0;
		int totalPage = 0;
		
		// 기본값 -> 등록일순 정렬로 초기화
		String sort = "";
		if(request.getParameter("sort")==null) {
			sort = "descEnroll";
		} else {
			sort = request.getParameter("sort");
		}
		
		// 기본값  -> 검색어 초기화
		String keyword = "";
		if(request.getParameter("keyword")==null) {
			keyword = "";
		} else {
			keyword = request.getParameter("keyword");
		}
		
		// 리스트 초기화
		List<WriterEnroll> list = null;
		
		if(sort.equals("descEnroll")) {
			System.out.println("DESC ENROLL 진입");
			// 기본값 등록일순 정렬
			totalCount = new WriterService().searchCount(keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new WriterService().descEnroll(keyword, cPage, numPerPage);
		}
		else if(sort.equals("ascEnroll")) {
			System.out.println("ASC ENROLL 진입");
			totalCount = new WriterService().searchCount(keyword);
			totalPage = (int)Math.ceil((double)totalCount/numPerPage);
			list = new WriterService().ascEnroll(keyword, cPage, numPerPage);
		}

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";
		
		int pageNo = ((cPage-1)/pageBarSize)*pageBarSize+1;
		int pageEnd = pageNo+pageBarSize-1;
		
		if(pageNo==1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + (pageNo-1) + ")'>이전</a>";
		}
		
		while(!(pageNo>totalPage||pageNo>pageEnd)) {
			if(pageNo==cPage) {
				pageBar += "<span style='color:#8e9181'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>" + pageNo + "</a>";
			}
			pageNo++;
		}
		
		if(pageNo>totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>다음</a>";
		}
		
		// 리스트 담기
		String html = "";
		
		if(list.size() != 0) {
			for(int i=0;i<list.size();i++) {
					html += "<div class='tal_Content' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ")'>";
					html += "<div class='talC_WeNo'>" + list.get(i).getWriterEnrollNo() + "</div>";
					
					html += "<div class='talC_WeQuarter'>";
					
					//분기 출력 변환
					String[] weQuarter = list.get(i).getWriterEnrollQuarter().split(",");
					String weYear = weQuarter[0];
					String weQu = weQuarter[1];
					html += weYear + "년  / " + weQu + "차";
					
					html += "</div>";
					html += "<div class='talC_NickName'>" + list.get(i).getMemberNickname() + "</div>";
					html += "<div class='talC_Email'>" + list.get(i).getMemberEmail() + "</div>";
					html += "<div class='talC_Name'>" + list.get(i).getMemberName() + "</div>";
					html += "<div class='talC_Birthday'>" + list.get(i).getMemberBirthday() + "</div>";
					html += "<div class='talC_WeDate'>" + list.get(i).getWriterEnrolldate() + "</div>";
					html += "<div class='talC_WePassYN'>";
					if(list.get(i).getWriterPassYN().equals("N")) {
						html += "<p class='talC_WePassYN_textN'>처리전</p>";
					} else {
						html += "<p class='talC_WePassYN_textY'>처리완료</p>";
					}
					html += "</div>";
					html += "</div>";
			}
		}
		else {
			html += "<div class='tal_Content'>";
			html += "<div class='tal_text'>현재 조회된 리스트가 없습니다.</div>";
			html += "</div>";
		}
		html += "<div class='tal_bottom'>" + pageBar + "</div>";

		
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
