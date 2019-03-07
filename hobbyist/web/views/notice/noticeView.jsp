<%@page import="com.hobbyist.notice.model.vo.WeNotice"%>
<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
	/* 자료 받아오기 */
	Notice notice = (Notice)request.getAttribute("notice");
	String profileImg = (String)request.getAttribute("profileImg");
	WeNotice wnList = (WeNotice)request.getAttribute("wnList");
	String weYear = (String)request.getAttribute("weYear");
	String weQu = (String)request.getAttribute("weQu");
	
	/* 쿠키값을 이용한 조회수 출력 판단 */
	Cookie[] noticeCookies = request.getCookies();
	String noticeCookieVal = "";
	boolean readCountText = false;
	if (noticeCookies != null) {
		output: for (Cookie c : noticeCookies) {
			String name = c.getName();
			String value = c.getValue();
			if ("noticeCookie".equals(name)) {
				noticeCookieVal = value;
				if (value.contains("|" + notice.getNoticeNo() + "|")) {
					readCountText = true;
					break output;
				}
			}
		}
	}

%>

<%@ include file="/views/common/header.jsp" %>


<section id="noticeView">
	<div class="noticeView_content">
		<div class="noticeView_contentBox">
			<div class="notice_Title">
			</div>
			<div class="contentBox_top">
				<ul>
					<li onclick="location.href='<%= request.getContextPath() %>/notice/noticeList'">
						<img src="<%= request.getContextPath() %>/images/back.png" width="18px">
					</li>
					<li>
						메인 > 공지사항 > 상세보기
					</li>
				</ul>
			</div>
			<div class="contentBox_mid">
				<div class="noticeViewTitleBox">

					<% if(notice.getNoticeSort().equals("sortNotice")) { %>
					<div class='nSTextNotice'>공지</div>
					<div class="noticeViewTitle"><%= notice.getNoticeTitle() %></div>
					
					<% } else if(notice.getNoticeSort().equals("sortEvent")) { %>
					<div class='nSTextEvent'>이벤트</div>
					<div class="noticeViewTitle"><%= notice.getNoticeTitle() %></div>
					<% } else if(notice.getNoticeSort().equals("sortWriterEnroll")) { %>
					<div class='nSTextWriterEnroll'>작가신청</div>
					<div class="noticeViewTitle"><%= notice.getNoticeTitle() %></div>
					<% } %>
				</div>
				<div class="noticeViewInforBox">
					<div class="noticeViewWriter">
						작성자 :
						<img alt="프로필이미지" src="<%= request.getContextPath() %>/upload/member/<%= profileImg %>" />
						<%= notice.getNoticeWriter() %>
					</div>
					<div class="noticeViewInforBoxRight">
						<div class="noticeViewDate">작성일 : <%= notice.getNoticeDate() %></div>

						<% if(readCountText) {%>
						<div class="noticeViewReadcount">조회수 : <%= notice.getNoticeReadcount() %></div>
						<% } else { %>
						<div class="noticeViewReadcount">조회수 : <%= notice.getNoticeReadcount()+1 %></div>
						<% } %>
					</div>
				</div>
				<% if(notice.getNoticeSort().equals("sortWriterEnroll")) { %>
					<div class="weNoticeView">
						<div class="weNoticeViewText">[ <%= weYear %> 년도 &nbsp;&nbsp;<%= weQu %> 차 ] &nbsp;&nbsp;</div>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<div class="weNoticeViewTextS"><%= wnList.getWeNoticeStartdate() %></div>
						<div class="weNoticeViewText">에서</div>
						&nbsp;&nbsp;
						<div class="weNoticeViewTextE"><%= wnList.getWeNoticeEnddate() %></div>
						<div class="weNoticeViewText">까지</div>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;
						<button type="button" class="writerEnrollBtn" onclick="fn_writerEnrollBtn()">작가신청하기</button>
					</div>
				<% } %>
				
				<% if(notice.getNoticeImgnameRenamed()!=null) { %>
				<div class="noticeViewImgBox">
					<div class="noticeViewImg">
						<img alt="공지 이미지"
							src="<%= request.getContextPath() %>/upload/notice/<%= notice.getNoticeImgnameRenamed() %>" />
					</div>
					<% if(logginMember!=null&&logginMember.getMemberEmail().equals("admin")) { %>
					<div class="noticeViewImgName"><%= notice.getNoticeImgnameOriginal() %></div>
					<% } %>
				</div>
				<% } %>
				
				<div class="noticeViewContent"><%= notice.getNoticeContent() %></div>
				<div class="noticeViewFileBox">
					<div class="noticeViewFileTitle">첨부파일</div>
					<% if(notice.getNoticeFilenameOriginal()!=null) { %>
					<div class="noticeViewFile"
						onclick="fn_fileDown('<%= notice.getNoticeFilenameRenamed() %>','<%= notice.getNoticeFilenameOriginal() %>')">
						<img alt="첨부파일" src="<%= request.getContextPath() %>/images/fileDefaultImg.png" width="20px"
							height="20px" />
						<div class="noticeViewFileName"><%= notice.getNoticeFilenameOriginal() %></div>
					</div>
					<script>
						function fn_fileDown(rName, oName) {
							rName = encodeURIComponent(rName);
							oName = encodeURIComponent(oName);
							location.href = "<%= request.getContextPath() %>/notice/noticeFileDownload?rName=" + rName + "&oName=" + oName;
						};
					</script>
					<% } %>
				</div>
			</div>
			<% if(logginMember!=null&&logginMember.getMemberEmail().equals("admin")) { %>
			<div class="contentBox_bottom">
				<button class="button1" onclick="fn_noticeModify(<%= notice.getNoticeNo() %>)">수정하기</button>
				<button class="button1" onclick="fn_noticeDel(<%= notice.getNoticeNo() %>)">삭제하기</button>
			</div>
			<% } %>
		</div>
		<% if(wnList!=null) { %>
			<form action="<%= request.getContextPath() %>/writer/writerEnroll" type="post" id="noticeViewHidden">
				<input type="hidden" name="weQuarter" value="<%= wnList.getWeQuarter() %>"/>
			</form>
		<% } %>
		
	</div>
	<script>
		// 작가신청 버튼 기능 구현
		function fn_writerEnrollBtn() {
			$("#noticeViewHidden").submit();
		}

		// noticeModify 수정 버튼 기능 구현
		function fn_noticeModify(index) {
			var flag = confirm("수정하시겠습니까?");
			if (flag) {
				location.href = "<%= request.getContextPath() %>/notice/noticeModify?noticeNo=" + index;
			}

		}

		// noticeListDel 삭제 버튼 기능 구현
		function fn_noticeDel(index) {
			var flag = confirm("삭제하시겠습니까?");
			if (flag) {
				location.href = "<%= request.getContextPath() %>/notice/noticeDel?noticeNo=" + index;
			}

		}

		$(window).scroll(function () {
			var target = $('.contentBox_top');
			var scrollTo = $(window).scrollTop();
			if (scrollTo != 0) {
				$('.contentBox_top').css("top", "97px");
			} else {
				$('.contentBox_top').css("top", "180px");
			}
		});
		//메인 헤더 숨기기
		$(window).scroll(function () {
			if ($(window).scrollTop() != 0) {
				$("#header-middle").css("transition", "500ms");
				$("#header-middle").css("height", "0px");
				$("#header-middle .middle").css("display", "none");
			} else {
				$("#header-middle").css("height", "100px");
				setTimeout(function () {
					$("#header-middle .middle").css("display", "inline-block");
				}, 500)
			}
		});
	</script>

</section>

<%@ include file="/views/common/footer.jsp" %>