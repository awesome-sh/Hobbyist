<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%
	/* 자료 받아오기 */
	List<Notice> list = (List)request.getAttribute("list");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if (<%= logginMember != null && logginMember.getMemberEmail().equals("admin") %>) {

	} else {
		alert('관리자만 접근 가능합니다');
		location.href = history.back(-1);
	}
</script>

<section id="admin">
	<div class="admin_content">
		<div class="admin_top" id="admin_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'"><img
						src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">NOTICE & EVENT | 공지&이벤트</li>
				<li onclick="location.href='#'">관리자페이지 > 공지&이벤트 관리 > 예약 목록</li>
			</ul>

		</div><br>

		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %>
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="admin_right">
			<div id="admin_noticePre">
				<div class="notice_content">
					<div class="notice_TalBox">
					
					<!-- 
						<div class="notice_Title">
							<h3>N O T I C E & E V E N T</h3>
							<p>메인 > 공지사항</p>
						</div>
					 -->
					 
						<div class="talBox_top">
							<form id="searchFrm" name="searchFrm" action="javascript:fn_searchFrm();" method="POST">
								<div class="tal_top">
									<div class="sort">
										<input type="hidden" id="sort" name="sort"
											value="<%= sort!=null? sort : "" %>" />
										<div id="sort1" class="sortBtn" name="sortAll" value="sortNotice"
											onclick="fn_sortBtn(this)">전체</div>
										<div id="sort2" class="sortBtn" name="sortNotice" value="sortNotice"
											onclick="fn_sortBtn(this)">공지</div>
										<div id="sort3" class="sortBtn" name="sortEvent" value="sortEvent"
											onclick="fn_sortBtn(this)">이벤트</div>
										<div id="sort4" class="sortBtn" name="sortWriterEnroll" value="sortWriterEnroll"
											onclick="fn_sortBtn(this)">작가신청</div>
									</div>
									<div class="searchForm">
										제목명&nbsp;&nbsp;&nbsp;<input type="search" autocomplete="off"
											placeholder="Search..." name="keyword" id="keyword"
											value="<%= !keyword.equals("")? keyword : "" %>">
										<button type="submit"><img
												src="<%= request.getContextPath() %>/images/search.png"
												width="70%"></button>
										<div id="ajaxSearch"></div>
									</div>
								</div>
								<div class="tal_Title">
									<div class="talT_noticeNo">번호</div>
									<div class="talT_noticeSort">분류</div>
									<div class="talT_noticeTitle">제목</div>
									<div class="talT_noticeDate">작성일</div>
									<div class="talT_noticeReadcount">조회수</div>
									<div class="talT_noticeWriter">공지 남은기간</div>
								</div>
							</form>
						</div>
						<div class="talBox_middle" id="noticeAjax">
						</div>
					</div>

				</div>
				<script>
					var adminSelectWindowVal = "pre";
					/* 처음 페이지 들어올때 ajax로 목록 띠우기(최신등록순으로..) */
					var keyword = $('#keyword');
					$(function () {
						$.ajax({
							url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
							type: 'POST',
							dataType: 'text',
							data: { "sort": "sortAll", "keyword": keyword.val() },
							success: function (data) {
								var noticeAjax = $("#noticeAjax");
								noticeAjax.empty();
								noticeAjax.html(data);
								$('#sort1').addClass('selected');
								$('#sort2').removeClass('selected');
								$('#sort3').removeClass('selected');
								$('#sort4').removeClass('selected');
							}
						})
					})

					// 페이징처리 링크 기능 구현
					function fn_ListAjax(pageNo) {
						var text = $('.selected')[0].innerText;
						if (text == '전체') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								data: 'sort=sortAll&cPage=' + pageNo,
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
								}
							});
						} else if (text == '공지') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								data: 'sort=sortNotice&cPage=' + pageNo,
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
								}
							});
						} else if (text == '이벤트') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								data: 'sort=sortEvent&cPage=' + pageNo,
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
								}
							});
						} else if (text == '작가신청') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								data: 'sort=sortWriterEnroll&cPage=' + pageNo,
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
								}
							});
						}
					}

					// sortBtn 기능 구현
					function fn_sortBtn(e) {
						if (e.innerText == '전체') {
							$('#sort').val('');
							$('#sort').val('sortAll');
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortAll", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $("#noticeAjax");
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').addClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (e.innerText == '공지') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortNotice", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $("#noticeAjax");
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').addClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (e.innerText == '이벤트') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortEvent", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $("#noticeAjax");
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').addClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (e.innerText == '작가신청') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortWriterEnroll", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $("#noticeAjax");
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').addClass('selected');
								}
							})
						}
					}

					// 키워드 검색 기능 구현
					function fn_searchFrm() {
						var text = $('.selected')[0].innerText;
						if (text == '전체') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortAll", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').addClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (text == '공지') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortNotice", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').addClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (text == '이벤트') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortEvent", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').addClass('selected');
									$('#sort4').removeClass('selected');
								}
							})
						}
						else if (text == '작가신청') {
							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "sortWriterEnroll", "keyword": keyword.val() },
								success: function (data) {
									var noticeAjax = $('#noticeAjax');
									noticeAjax.empty();
									noticeAjax.html(data);
									$('#sort1').removeClass('selected');
									$('#sort2').removeClass('selected');
									$('#sort3').removeClass('selected');
									$('#sort4').addClass('selected');
								}
							})
						}
					}

					// noticeListRe 복구 버튼 기능 구현
					function fn_noticeListRe(index) {
						var flag = confirm("'복구'하시겠습니까?");
						if (flag) {
							$(".defaultViewModal").css("display", "none");

							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticeReAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "noticeNo": index },
								success: function (data) {
									alert("복구 완료 했습니다.");
								}
							})
							
							var text = $('.selected')[0].innerText;
							if (text == '전체') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortAll", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '공지') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortNotice", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').addClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '이벤트') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortEvent", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').addClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '작가신청') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortWriterEnroll", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').addClass('selected');
									}
								})
							}
						}
					}

					// noticeListDel 삭제 버튼 기능 구현
					function fn_noticeDB_Del(index) {
						var flag = confirm("'완전삭제'하시겠습니까?");
						if (flag) {
							$(".defaultViewModal").css("display", "none");

							$.ajax({
								url: '<%= request.getContextPath() %>/notice/noticeDB_DelAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "noticeNo": index },
								success: function (data) {
									alert("'완전삭제' 완료 했습니다.");
								}
							})
							
							var text = $('.selected')[0].innerText;
							if (text == '전체') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortAll", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '공지') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortNotice", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').addClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '이벤트') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortEvent", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').addClass('selected');
										$('#sort4').removeClass('selected');
									}
								})
							}
							else if (text == '작가신청') {
								$.ajax({
									url: '<%= request.getContextPath() %>/notice/noticePreListAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "sortWriterEnroll", "keyword": keyword.val() },
									success: function (data) {
										var noticeAjax = $('#noticeAjax');
										noticeAjax.empty();
										noticeAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
										$('#sort4').addClass('selected');
									}
								})
							}
						}
					}


				</script>

			</div>
		</div>
	</div>
</section>

<!-- 모달창 View -->
<%@ include file="/views/admin/notice/admin_notice_preList_view.jsp" %>


<!-- 모달창 Modify -->
<%@ include file="/views/admin/notice/admin_notice_modify.jsp" %>


