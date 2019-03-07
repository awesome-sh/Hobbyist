package com.hobbyist.mypage.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;
import com.hobbyist.writer.model.vo.WriterEnroll;

@WebServlet("/mypage/mypage_writerEnrollList")
public class Mypage_writerEnrollListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Mypage_writerEnrollListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//현시간 작가신청 하고 있는 공지글 가져오기
		WeNotice wnList = new NoticeService().cuWeSelectOne();
		Notice cuNotice = null;
		if(wnList!=null) {
		boolean hasRead = true;
		cuNotice = new NoticeService().selectOne(wnList.getNoticeNo(), hasRead);
		}
		List<WriterEnroll> list = null;
		String sort = "";
		int cPage = 0;
		int numPerPage = 0;
		String pageBar = "";
		String keyword = "";
		boolean weFlag = true;
		request.setAttribute("list", list);
		request.setAttribute("sort", sort);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("keyword", keyword);
		request.setAttribute("weFlag", weFlag);
		request.getRequestDispatcher("/views/mypage/writer/myPage_writerEnroll.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
