package com.hobbyist.writer.controller;

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
import com.hobbyist.writer.model.service.WriterService;
import com.hobbyist.writer.model.vo.WriterEnroll;

/**
 * Servlet implementation class MypageWEAjaxServlet
 */
@WebServlet("/mypage/mypageWEAjax.do")
public class MypageWEAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MypageWEAjaxServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Member logginMember = (Member) request.getSession().getAttribute("logginMember");
		int memberNo = logginMember.getMemberNo();

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

		// 기본값 -> 등록일순 정렬로 초기화
		String sort = "";
		if (request.getParameter("sort") == null) {
			sort = "descEnroll";
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
		List<WriterEnroll> list = null;

		if (sort.equals("descEnroll")) {
			System.out.println("DESC ENROLL 진입");
			// 기본값 등록일순 정렬
			totalCount = new WriterService().myPageSearchCount(keyword, memberNo);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new WriterService().myPageDescEnroll(keyword, memberNo, cPage, numPerPage);
		} else if (sort.equals("ascEnroll")) {
			System.out.println("ASC ENROLL 진입");
			totalCount = new WriterService().myPageSearchCount(keyword, memberNo);
			totalPage = (int) Math.ceil((double) totalCount / numPerPage);
			list = new WriterService().myPageAscEnroll(keyword, memberNo, cPage, numPerPage);
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

		String html = "";

		// 현시간 작가신청 하고 있는 공지글 가져오기
		WeNotice wnList = new NoticeService().cuWeSelectOne();
		Notice cuNotice = null;
		if (wnList != null) {
			boolean hasRead = true;
			cuNotice = new NoticeService().selectOne(wnList.getNoticeNo(), hasRead);
		}

		// 리스트 담기
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				if (wnList != null) {
					html += "<div class='tal_Content'>";
					html += "<div class='talC_WeNo' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ",\""
							+ wnList.getWeQuarter() + "\")'>";
					html += list.get(i).getWriterEnrollNo();
					html += "</div>";

					html += "<div class='talC_WeQuarter' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo()
							+ ",\"" + wnList.getWeQuarter() + "\")'>";

					// 분기 출력 변환
					String[] weQuarter = list.get(i).getWriterEnrollQuarter().split(",");
					String weYear = weQuarter[0];
					String weQu = weQuarter[1];
					html += weYear + "년  / " + weQu + "차";

					html += "</div>";

//					html += "<div class='talC_NickName'>" + list.get(i).getMemberNickname() + "</div>";
					html += "<div class='talC_WeDate'>";
					html += list.get(i).getWriterEnrolldate();
					html += "</div>";

					html += "<div class='talC_WeDate' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ",\""
							+ wnList.getWeQuarter() + "\")'>";
					if (list.get(i).getWriterPassYN().equals("N")) {
						html += "<p class='talC_WePassYN_textN'>처리전</p>";
					} else {
						html += "<p class='talC_WePassYN_textY'>처리완료</p>";
					}
					html += "</div>";

					html += "<div class='talC_Name' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ",\""
							+ wnList.getWeQuarter() + "\")'>";
					if (list.get(i).getWriterPassYN().equals("P")) {
						html += "<div class='talC_WePassYN_text1'>합격</div>";
					} else if (list.get(i).getWriterPassYN().equals("N")) {
						html += "<div class='talC_WePassYN_text3'>확인중</div>";
					} else {
						html += "<div class='talC_WePassYN_text2'>불합격</div>";
					}
					html += "</div>";

					html += "<div class='talC_Birthday' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo()
							+ ",\"" + wnList.getWeQuarter() + "\")'>";
					if (list.get(i).getWriterPrepRequestYN().equals("Y")) {
						html += "<p class='talC_WePassYN_textY' style='color:black;'>수락</p>";
					} else {
						html += "<p class='talC_WePassYN_textN' style='color:black;'>거부</p>";
					}
					html += "</div>";

					html += "<div class='talC_noticeStatus'>";

					if(i==0) {
					html += "<button type='button' class='noticeListRe' onclick='fn_prepRequestYN("
							+ list.get(i).getWriterEnrollNo() + ", 1)'>신청</button>";
					html += "<button type='button' class='noticeListDel' onclick='fn_prepRequestYN("
							+ list.get(i).getWriterEnrollNo() + ", 2)'>거부</button>";
					}
					
					html += "</div>";

					html += "</div>";
					
				} else {
					
					html += "<div class='tal_Content'>";
					html += "<div class='talC_WeNo' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ")'>";
					html += list.get(i).getWriterEnrollNo();
					html += "</div>";

					html += "<div class='talC_WeQuarter' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo()
							+ ")'>";

					// 분기 출력 변환
					String[] weQuarter = list.get(i).getWriterEnrollQuarter().split(",");
					String weYear = weQuarter[0];
					String weQu = weQuarter[1];
					html += weYear + "년  / " + weQu + "차";

					html += "</div>";

//						html += "<div class='talC_NickName'>" + list.get(i).getMemberNickname() + "</div>";
					html += "<div class='talC_WeDate'>";
					html += list.get(i).getWriterEnrolldate();
					html += "</div>";

					html += "<div class='talC_WeDate' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ")'>";
					if (list.get(i).getWriterPassYN().equals("N")) {
						html += "<p class='talC_WePassYN_textN'>처리전</p>";
					} else {
						html += "<p class='talC_WePassYN_textY'>처리완료</p>";
					}
					html += "</div>";

					html += "<div class='talC_Name' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo() + ")'>";
					if (list.get(i).getWriterPassYN().equals("P")) {
						html += "<div class='talC_WePassYN_text1'>합격</div>";
					} else if (list.get(i).getWriterPassYN().equals("N")) {
						html += "<div class='talC_WePassYN_text3'>확인중</div>";
					} else {
						html += "<div class='talC_WePassYN_text2'>불합격</div>";
					}
					html += "</div>";

					html += "<div class='talC_Birthday' onclick='fn_WEViewAjax(" + list.get(i).getWriterEnrollNo()
							+ ")'>";
					if (list.get(i).getWriterPrepRequestYN().equals("Y")) {
						html += "<p class='talC_WePassYN_textY' style='color:black;'>수락</p>";
					} else {
						html += "<p class='talC_WePassYN_textN' style='color:black;'>거부</p>";
					}
					html += "</div>";

					html += "<div class='talC_noticeStatus'>";
					
					if(i==0) {
					html += "<button type='button' class='noticeListRe' onclick='fn_prepRequestYN("
							+ list.get(i).getWriterEnrollNo() + ", 1)'>신청</button>";
					html += "<button type='button' class='noticeListDel' onclick='fn_prepRequestYN("
							+ list.get(i).getWriterEnrollNo() + ", 2)'>거부</button>";
					}

					html += "</div>";

					html += "</div>";
				}
			}
		} else {
			html += "<div class='tal_Content'>";
			html += "<div class='tal_text'>현재 조회된 리스트가 없습니다.</div>";
			html += "</div>";
		}
		html += "<div class='tal_bottom'>" + pageBar + "</div>";

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
