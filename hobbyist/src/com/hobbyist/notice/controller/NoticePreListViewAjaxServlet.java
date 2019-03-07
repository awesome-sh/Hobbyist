package com.hobbyist.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;

/**
 * Servlet implementation class NoticePreListViewAjaxServlet
 */
@WebServlet("/notice/noticePreListViewAjax.do")
public class NoticePreListViewAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticePreListViewAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));

		// 관리자페이지view는 조회수 카운트 안함
		boolean hasRead = true;
		Notice notice = new NoticeService().selectOne(noticeNo, hasRead);
		// 작성자 프로필 이미지 가져오기
		String writer = notice.getNoticeWriter();
		String profileImg = new NoticeService().writerImg(writer);
		
		// 작가신청 관련 자료 가져오기
		WeNotice wnList = new NoticeService().weSelectOne(noticeNo);
		String weYear = "";
		String weQu = "";
		if(wnList!=null) {
			String[] weQuarter = wnList.getWeQuarter().split(",");
			weYear = weQuarter[0];
			weQu = weQuarter[1];
		}

		
		
		// notice자료 담기
		String html = "";

		html += "<div class='noticeView_content'>";
		html += "<div class='noticeView_contentBox'>";
		html += "<div class='notice_Title'></div>";

		html += "<div class='contentBox_mid'>";
		html += "<div class='noticeViewTitleBox'>";

		if (notice.getNoticeSort().equals("sortNotice")) {
			html += "<div class='nSTextNotice'>공지</div>";
		} else if (notice.getNoticeSort().equals("sortEvent")) {
			html += "<div class='nSTextEvent'>이벤트</div>";
		} else if (notice.getNoticeSort().equals("sortWriterEnroll")) {
			html += "<div class='nSTextWriterEnroll'>작가신청</div>";
		}

		html += "<div class='noticeViewTitle'>" + notice.getNoticeTitle() + "</div>";
		html += "</div>";
		html += "<div class='noticeViewInforBox'>";
		html += "<div class='noticeViewWriter'>작성자 : ";
		html += "<img alt='프로필이미지' src='" + request.getContextPath() + "/upload/member/" + profileImg + "'/>";
		html += notice.getNoticeWriter();
		html += "</div>";
		html += "<div class='noticeViewInforBoxRight'>";
		html += "<div class='noticeViewDate'>작성일 : " + notice.getNoticeDate() + "</div>";

		html += "<div class='noticeViewReadcount'>조회수 : " + notice.getNoticeReadcount() + "</div>";
		html += "</div>";
		html += "</div>";

		if (notice.getNoticeSort().equals("sortWriterEnroll")) {
			html += "<div class='weNoticeView'>";
			html += "<div class='weNoticeViewText'>[ " + weYear + " 년도 &nbsp;&nbsp;" + weQu + " 차 ] &nbsp;&nbsp;</div>";
			html += "&nbsp;&nbsp;&nbsp;&nbsp;";
			html += "<div class='weNoticeViewTextS'>" + wnList.getWeNoticeStartdate() + "</div>";
			html += "<div class='weNoticeViewText'>에서</div>";
			html += "&nbsp;&nbsp;";
			html += "<div class='weNoticeViewTextE'>" + wnList.getWeNoticeEnddate() + "</div>";
			html += "<div class='weNoticeViewText'>까지</div>";
			html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			html += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
			html += "<button type='button' class='writerEnrollBtn' onclick='fn_writerEnrollBtn()'>작가신청하기</button>";
			html += "</div>";
		}

		if (notice.getNoticeImgnameRenamed() != null) {
			html += "<div class='noticeViewImgBox'>";
			html += "<div class='noticeViewImg'>";
			html += "<img alt='공지 이미지' src='" + request.getContextPath() + "/upload/notice/"
					+ notice.getNoticeImgnameRenamed() + "'/>";
			html += "</div>";
			html += "<div class='noticeViewImgName'>" + notice.getNoticeImgnameOriginal() + "</div>";
			html += "</div>";
		}

		html += "<div class='noticeViewContent'>" + notice.getNoticeContent() + "</div>";
		html += "<div class='noticeViewFileBox'>";
		html += "<div class='noticeViewFileTitle'>첨부파일</div>";

		if (notice.getNoticeFilenameOriginal() != null) {
			html += "<div class='noticeViewFile'";
			html += "onclick=\"fn_fileDown('" + notice.getNoticeFilenameRenamed() + "','"
					+ notice.getNoticeFilenameOriginal() + "')\">";
			html += "<img alt='첨부파일' src='" + request.getContextPath()
					+ "/images/fileDefaultImg.png' width='20px' height='20px' />";
			html += "<div class='noticeViewFileName'>" + notice.getNoticeFilenameOriginal() + "</div>";
			html += "</div>";
		}

		html += "</div>";
		html += "</div>";

		html += "</div>";
		html += "</div>";

		response.setContentType("text/html; charset=UTF-8");
		response.getWriter().write(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
