package com.hobbyist.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hobbyist.member.model.vo.Member;
import com.hobbyist.notice.model.service.NoticeService;
import com.hobbyist.notice.model.vo.Notice;
import com.hobbyist.notice.model.vo.WeNotice;

/**
 * Servlet implementation class NoticeAjaxServlet
 */
@WebServlet("/notice/noticeAjax.do")
public class NoticeAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NoticeAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 페이징처리
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}

		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5;
		}

		// 정렬방법&검색결과 적용 전체클래스 갯수, 페이지 갯수
		int totalCount = 0;
		int totalPage = 0;

		// 기본값 -> 등록일순 정렬로 초기화(전체보기)
		String sort = "";
		if (request.getParameter("sort") == null) {
			sort = "sortAll";
		} else {
			sort = request.getParameter("sort");
		}

		// 기본값 -> 검색어 초기화
		String keyword = "";
		if (request.getParameter("keyword") == null) {
			keyword = "";
		} else {
			keyword = request.getParameter("keyword");
		}

		// 리스트 초기화
		List<Notice> list = null;

		if (sort.equals("sortAll")) {
			System.out.println("SELECE ALL 진입");
			// 기본값 등록일순 정렬
			totalCount = new NoticeService().searchCount(keyword);
			System.out.println("리스트1 totalCount : " + totalCount);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new NoticeService().selectAll(keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈1 : " + list.size());
		} else if (sort.equals("sortNotice")) {
			System.out.println("SELECE NOTICE 진입");
			totalCount = new NoticeService().searchCountSort(sort, keyword);
			System.out.println("리스트2 totalCount : " + totalCount);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new NoticeService().selectSort(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈2 : " + list.size());
		} else if (sort.equals("sortEvent")) {
			System.out.println("SELECE EVENT 진입");
			totalCount = new NoticeService().searchCount(keyword);
			System.out.println("리스트3 totalCount : " + totalCount);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new NoticeService().selectSort(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈3 : " + list.size());
		} else if (sort.equals("sortWriterEnroll")) {
			System.out.println("SELECE WRITERENROLL 진입");
			totalCount = new NoticeService().searchCount(keyword);
			System.out.println("리스트4 totalCount : " + totalCount);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new NoticeService().selectSort(sort, keyword, cPage, numPerPage);
			System.out.println("리스트 사이즈4 : " + list.size());
		}

		// 페이지네이션
		int pageBarSize = 5;
		String pageBar = "";

		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;

		if (pageNo == 1) {
			pageBar += "<span>이전</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + (pageNo - 1) + ")'>이전</a>";
		}

		while (!(pageNo > totalPage || pageNo > pageEnd)) {
			if (pageNo == cPage) {
				pageBar += "<span style='color:#8e9181'>" + pageNo + "</span>";
			} else {
				pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>" + pageNo + "</a>";
			}
			pageNo++;
		}

		if (pageNo > totalPage) {
			pageBar += "<span>다음</span>";
		} else {
			pageBar += "<a href='javascript:fn_ListAjax(" + pageNo + ")'>다음</a>";
		}

		// 리스트 담기
		String html = "";

		if (list.size() != 0) {

			String writer = "";
			String profileImg = "";
			Member logginMember = null;
			
			// 현시간 작가신청 하고 있는 공지글 가져오기
			WeNotice wnList = new NoticeService().cuWeSelectOne();
			if (wnList != null) {

				boolean hasRead = true;
				Notice cuNotice = new NoticeService().selectOne(wnList.getNoticeNo(), hasRead);

				if (cuNotice.getNoticeStatus().equals("Y")) {
					html += "<div class='tal_ContentBox'>";
					html += "<div class='weNoticeColor tal_Content' onclick='fn_noticeView(" + cuNotice.getNoticeNo()
							+ ")'>";
					html += "<div class='talC_noticeNo'>" + cuNotice.getNoticeNo() + "</div>";
					html += "<div class='talC_noticeSort'>";

					switch (cuNotice.getNoticeSort()) {
					case "sortNotice":
						html += "<div class='nSTextNotice'>공지</div>";
						break;
					case "sortEvent":
						html += "<div class='nSTextEvent'>이벤트</div>";
						break;
					case "sortWriterEnroll":
						html += "<div class='nSTextWriterEnroll'>작가신청</div>";
						break;
					}

					html += "</div>";
					html += "<div class='talC_noticeTitle'>" + cuNotice.getNoticeTitle() + "</div>";

					html += "<div class='talC_noticeWriter'>";

					// 작성자 프로필 이미지 가져오기
					writer = cuNotice.getNoticeWriter();
					profileImg = new NoticeService().writerImg(writer);

					html += "<img alt='프로필이미지' src='" + request.getContextPath() + "/upload/member/" + profileImg
							+ "'/>";
					html += cuNotice.getNoticeWriter();
					html += "</div>";

					html += "<div class='talC_noticeDate'>" + cuNotice.getNoticeDate() + "</div>";
					html += "<div class='talC_noticeReadcount'>" + cuNotice.getNoticeReadcount() + "</div>";
					html += "</div>";

					// 관리자일때만 삭제버튼 띠우기
					logginMember = (Member) request.getSession().getAttribute("logginMember");
					if (logginMember != null && logginMember.getMemberEmail().equals("admin")) {
						html += "<button type='button' class='noticeListDel' onclick='fn_noticeListDel("
								+ cuNotice.getNoticeNo() + ")'>X</button>";
					}

					html += "</div>";
				}
			}

			for (int i = 0; i < list.size(); i++) {
				html += "<div class='tal_ContentBox'>";
				html += "<div class='tal_Content' onclick='fn_noticeView(" + list.get(i).getNoticeNo() + ")'>";
				html += "<div class='talC_noticeNo'>" + list.get(i).getNoticeNo() + "</div>";
				html += "<div class='talC_noticeSort'>";

				switch (list.get(i).getNoticeSort()) {
				case "sortNotice":
					html += "<div class='nSTextNotice'>공지</div>";
					break;
				case "sortEvent":
					html += "<div class='nSTextEvent'>이벤트</div>";
					break;
				case "sortWriterEnroll":
					html += "<div class='nSTextWriterEnroll'>작가신청</div>";
					break;
				}

				html += "</div>";
				html += "<div class='talC_noticeTitle'>" + list.get(i).getNoticeTitle() + "</div>";

				html += "<div class='talC_noticeWriter'>";

				// 작성자 프로필 이미지 가져오기
				writer = list.get(i).getNoticeWriter();
				profileImg = new NoticeService().writerImg(writer);

				html += "<img alt='프로필이미지' src='" + request.getContextPath() + "/upload/member/" + profileImg + "'/>";
				html += list.get(i).getNoticeWriter();
				html += "</div>";

				html += "<div class='talC_noticeDate'>" + list.get(i).getNoticeDate() + "</div>";
				html += "<div class='talC_noticeReadcount'>" + list.get(i).getNoticeReadcount() + "</div>";
				html += "</div>";

				// 관리자일때만 삭제버튼 띠우기
				logginMember = (Member) request.getSession().getAttribute("logginMember");
				if (logginMember != null && logginMember.getMemberEmail().equals("admin")) {
					html += "<button type='button' class='noticeListDel' onclick='fn_noticeListDel("
							+ list.get(i).getNoticeNo() + ")'>X</button>";
				}

				html += "</div>";
			}
		} else {
			html += "<div class='tal_Content'>";
			html += "<div class='tal_text'>현재 조회된 리스트가 없습니다.</div>";
			html += "</div>";
		}
		html += "<div class='talBox_bottom'>" + pageBar + "</div>";

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
