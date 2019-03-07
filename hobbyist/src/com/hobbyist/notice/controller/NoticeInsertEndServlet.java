package com.hobbyist.notice.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;
import com.oreilly.servlet.MultipartRequest;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class noticeInsertEndServlet
 */
@WebServlet("/notice/noticeInsertEnd")
public class NoticeInsertEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertEndServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Member logginMember = (Member)request.getSession(false).getAttribute("logginMember");

		if(!ServletFileUpload.isMultipartContent(request)) {
			request.setAttribute("msg", "잘못접근!");
			request.setAttribute("loc", "/notice/noticeInsert");
			request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String root = getServletContext().getRealPath("/upload");
		String filePath = root + File.separator + "notice";
		int maxSize = 1024*1024*10;
		MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		
		String noticeSort = mr.getParameter("noticeSort");
		String noticeTitle = mr.getParameter("noticeTitle");
		String noticeWriter = logginMember.getMemberNickname();
		String noticeContent = mr.getParameter("noticeContent");
		
		//날짜 변경 로직
		Date noticeDate = null;
		if(!mr.getParameter("noticeDate").equals("noData")) {
			noticeDate = Date.valueOf(mr.getParameter("noticeDate"));
		} else {
			long currentTime = System.currentTimeMillis();
			noticeDate = new Date(currentTime);
		}
		
		String noticeFilenameOriginal = mr.getOriginalFileName("noticeFilenameOriginal");
		String noticeFilenameRenamed = mr.getFilesystemName("noticeFilenameOriginal");
		String noticeImgnameOriginal = mr.getOriginalFileName("noticeImgnameOriginal");
		String noticeImgnameRenamed = mr.getFilesystemName("noticeImgnameOriginal");
		
		Notice no = new Notice();
		no.setNoticeSort(noticeSort);
		no.setNoticeTitle(noticeTitle);
		no.setNoticeWriter(noticeWriter);
		no.setNoticeContent(noticeContent);
		no.setNoticeDate(noticeDate);
		no.setNoticeFilenameOriginal(noticeFilenameOriginal);
		no.setNoticeFilenameRenamed(noticeFilenameRenamed);
		no.setNoticeImgnameOriginal(noticeImgnameOriginal);
		no.setNoticeImgnameRenamed(noticeImgnameRenamed);

		int result = new NoticeService().insertNotice(no);
		
		Notice noList = new NoticeService().searchNo(no);

		//작가 신청 공지 DB 테이블 저장
		if(noticeSort.equals("sortWriterEnroll")) {
			
			Date weNoticeStartdate = Date.valueOf(mr.getParameter("weNoticeStartdate"));
			Date weNoticeEnddate = Date.valueOf(mr.getParameter("weNoticeEnddate"));
			String weNoticeYear = mr.getParameter("weNoticeYear");
			String weNoticeQuarter = mr.getParameter("weNoticeQuarter");
			
			WeNotice wn = new WeNotice(); 
			
			wn.setNoticeNo(noList.getNoticeNo());
			wn.setWeQuarter(weNoticeYear +","+ weNoticeQuarter);
			wn.setWeNoticeStartdate(weNoticeStartdate);
			wn.setWeNoticeEnddate(weNoticeEnddate);
			
			int wnResult = new NoticeService().insertWn(wn);  
		}
		
		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="공지사항 등록 성공";
			loc="/notice/noticeList";
		}
		else {
			msg="공지사항 등록 실패";
			loc="/notice/noticeInsert";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loc", loc);
		request.getRequestDispatcher(view).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
