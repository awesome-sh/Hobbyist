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
import com.oreilly.servlet.MultipartRequest;

import common.rename.MyFileRenamePolicy;

/**
 * Servlet implementation class NoticeModifyAdminEndServlet
 */
@WebServlet("/notice/noticeDelListModifyAdminEnd")
public class NoticeDelListModifyAdminEndServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDelListModifyAdminEndServlet() {
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
			request.setAttribute("loc", "/notice/noticeList");
			request.getRequestDispatcher("views/common/msg.jsp").forward(request, response);
			return;
		}
		
		String root = getServletContext().getRealPath("/upload");
		String filePath = root + File.separator + "notice";
		int maxSize = 1024*1024*10;
		MultipartRequest mr = new MultipartRequest(request, filePath, maxSize, "UTF-8", new MyFileRenamePolicy());
		int noticeNo = Integer.parseInt(mr.getParameter("noticeNo"));
		String noticeSort = mr.getParameter("noticeSort");
		String noticeTitle = mr.getParameter("noticeTitle");
		String noticeWriter = logginMember.getMemberNickname();
		String noticeContent = mr.getParameter("noticeContent");
		
		//날짜 변경 로직
		Date noticeDate = null;
		if(mr.getParameter("noticeDate").equals("noData")) {
			long currentTime = System.currentTimeMillis();
			noticeDate = new Date(currentTime);
		}
		else if (mr.getParameter("noticeDate").equals("oldDate")) {
			//기존 날짜값 가져오기
			boolean hasRead = true;
			Notice notice = new NoticeService().selectOne(noticeNo, hasRead);
			noticeDate = notice.getNoticeDate();
		}
		else {
			noticeDate = Date.valueOf(mr.getParameter("noticeDate"));
		}
		
		String noticeFilenameOriginal = mr.getOriginalFileName("noticeFilenameOriginal");
		String noticeFilenameRenamed = mr.getFilesystemName("noticeFilenameOriginal");
		String noticeImgnameOriginal = mr.getOriginalFileName("noticeImgnameOriginal");
		String noticeImgnameRenamed = mr.getFilesystemName("noticeImgnameOriginal");
		
		
		//기존 파일 삭제
		File file = mr.getFile("noticeFilenameOriginal");
		File img = mr.getFile("noticeImgnameOriginal");

		if(file!=null&&file.length()>0) {
			File delFile=new File(filePath+"/"+mr.getParameter("old_nFileR"));
			boolean resul = delFile.delete();
			System.out.println(resul?"제대로 지워짐":"안지워졌어!");
		} else if(mr.getParameter("fileDelFlag").equals("true")){
			noticeFilenameOriginal = "";
			noticeFilenameRenamed = "";
		} else {
			noticeFilenameOriginal = mr.getParameter("old_nFileO");
			noticeFilenameRenamed = mr.getParameter("old_nFileR");
		}
		
		if(img!=null&&img.length()>0) {
			File delFile=new File(filePath+"/"+mr.getParameter("old_nImgR"));
			boolean resul = delFile.delete();
			System.out.println(resul?"제대로 지워짐":"안지워졌어!");
		}else if(mr.getParameter("imgDelFlag").equals("true")){
			noticeImgnameOriginal = "";
			noticeImgnameRenamed = "";
		} else {
			noticeImgnameOriginal = mr.getParameter("old_nImgO");
			noticeImgnameRenamed = mr.getParameter("old_nImgR");
		}
		
		Notice no = new Notice();
		no.setNoticeNo(noticeNo);
		no.setNoticeSort(noticeSort);
		no.setNoticeTitle(noticeTitle);
		no.setNoticeWriter(noticeWriter);
		no.setNoticeContent(noticeContent);
		no.setNoticeDate(noticeDate);
		no.setNoticeFilenameOriginal(noticeFilenameOriginal);
		no.setNoticeFilenameRenamed(noticeFilenameRenamed);
		no.setNoticeImgnameOriginal(noticeImgnameOriginal);
		no.setNoticeImgnameRenamed(noticeImgnameRenamed);
		
		int result = new NoticeService().updateNotice(no);
		

		String msg="";
		String loc="";
		String view="/views/common/msg.jsp";
		if(result>0) {
			msg="공지사항 수정 성공";
			loc="/admin/adminNoticeDelList";
		}
		else {
			msg="공지사항 수정 실패";
			loc="/admin/adminNoticeDelList";
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
