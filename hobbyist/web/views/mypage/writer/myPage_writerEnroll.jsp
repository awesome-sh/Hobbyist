<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@page import="com.hobbyist.writer.model.vo.WriterEnroll"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<WriterEnroll> list = (List)request.getAttribute("list");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
	Notice cuNotice = (Notice)request.getAttribute("cuNotice");
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if (<%= logginMember != null %>) {
	} else {
		alert('접근 오류');
		location.href = history.back(-1);
	}
</script>

<section id="myPage">
	<div class="myPage_content">
		<div class="myPage_top" id="myPage_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>'">
					<img src="<%= request.getContextPath() %>/images/back.png" width="18px">
				</li>
				<li>Member | 작가신청</li>
				<li>마이페이지 > 작가관리</li>
			</ul>

		</div><br>

		<!-- 마이페이지메뉴는 중복되기때문에 myPage_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/mypage/myPage_menu.jsp" %>
		<!-- 마이페이지 메뉴 인클루드 끝 -->


		<div class="myPage_right">
			<div id="mypage_writerEnroll">

				<div class="tableBox">
					<div class="tableBox_top">
						<form id="searchFrm" name="searchFrm" action="javascript:fn_searchFrm();" method="POST">
							<div class="tal_top">
								<div class="sort">
									<input type="hidden" id="sort" name="sort" value="<%= sort!=null? sort : "" %>" />
									<div id="sort1" class="sortBtn" name="descEnroll" value="descEnroll"
										onclick="fn_sortBtn(this)">최근등록순</div>
									<div id="sort2" class="sortBtn" name="ascEnroll" value="ascEnroll"
										onclick="fn_sortBtn(this)">이전등록순</div>
								</div>
								<div class="searchForm">
									닉네임명&nbsp;&nbsp;&nbsp;<input type="search" autocomplete="off"
										placeholder="Search..." name="keyword" id="keyword"
										value="<%= !keyword.equals("")? keyword : "" %>">
									<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png"
											width="70%"></button>
									<div id="ajaxSearch"></div>
								</div>
							</div>
							<div class="tal_Title">
								<div class="talT_WeNo">번호</div>
								<div class="talT_WeQuarter">분기</div>
								<!-- <div class="talT_NickName">닉네임</div> -->
								<div class="talT_WeDate">등록일</div>
								<div class="talT_Email">처리상태</div>
								<div class="talT_Name">합격여부</div>
								<div class="talT_Birthday">예비작가 신청</div>
							</div>
						</form>
						<script>
						</script>
					</div>

					<script>

						/* 처음 페이지 들어올때 ajax로 목록 띠우기(최신등록순으로..) */
						var keyword = $('#keyword');
						$(function () {
							$.ajax({
								url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "descEnroll", "keyword": keyword.val() },
								success: function (data) {
									var adminWEAjax = $('#adminWE-Ajax');
									adminWEAjax.empty();
									adminWEAjax.html(data);
									$('#sort1').addClass('selected');
									$('#sort2').removeClass('selected');
								}
							})
						})

						function fn_searchFrm() {
							var text = $('.selected')[0].innerText;
							if (text == '최근등록순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "descEnroll", "keyword": keyword.val() },
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
									}
								})
							}
							else if (text == '이전등록순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "ascEnroll", "keyword": keyword.val() },
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
										$('#sort2').addClass('selected');
										$('#sort1').removeClass('selected');
									}
								})
							}
						}

						function fn_sortBtn(e) {
							if (e.innerText == '최근등록순') {
								$('#sort').val('');
								$('#sort').val('descEnroll');
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "descEnroll", "keyword": keyword.val() },
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
									}
								});
							} else if (e.innerText == '이전등록순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									type: 'POST',
									dataType: 'text',
									data: { "sort": "ascEnroll", "keyword": keyword.val() },
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
										$('#sort2').addClass('selected');
										$('#sort1').removeClass('selected');
									}
								});
							}
						}

						function fn_ListAjax(pageNo) {
							var text = $('.selected')[0].innerText;

							if (text == '최근등록순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									data: 'sort=descEnroll&cPage=' + pageNo,
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
									}
								});
							} else if (text == '이전등록순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
									data: 'sort=ascEnroll&cPage=' + pageNo,
									success: function (data) {
										var adminWEAjax = $('#adminWE-Ajax');
										adminWEAjax.empty();
										adminWEAjax.html(data);
									}
								});
							}
						}
					</script>

					<div class="tableBox_middle" id="adminWE-Ajax">
						<!-- <% if(list!=null) {%>
						<% for(WriterEnroll we : list) {%>
							<div class="tal_Content" onclick="fn_WEViewAjax(<%= we.getWriterEnrollNo() %>)">
								<div class="talC_WeNo"><%= we.getWriterEnrollNo() %></div>
								<div class="talC_WeQuarter"><%= we.getWriterEnrollQuarter() %></div>
								<div class="talC_NickName"><%= we.getMemberNickname() %></div>
								<div class="talC_Email"><%= we.getMemberEmail() %></div>
								<div class="talC_Name"><%= we.getMemberName() %></div>
								<div class="talC_Birthday"><%= we.getMemberBirthday() %></div>
								<div class="talC_WeDate"><%= we.getWriterEnrolldate() %></div>
								<div class="talC_WePassYN">
									<% if(we.getWriterPassYN().equals("N")) { %>
									<p class="talC_WePassYN_textN">처리전</p>
									<% } else { %>
									<p class="talC_WePassYN_textY">처리완료</p>
									<% } %>
								</div>
							</div>
						<% } %>
						<% } else { %>
						<div class="tal_Content">
							<div class="tal_text">현재 조회된 리스트가 없습니다.</div>
						</div>
						<% } %>
						<div class="shop_bottom">
							<%= pageBar %>
						</div> -->
					</div>
				</div>

				<div id="pagePreviewModal" class="pagePreviewModal">
					<div class="pagePreviewModalBox">
						<div class="wEPreviewBody">
							<div class="wEnrollPreAreaI">
								<div class="wEnrollPreMemberImageBox">
									<img id="EP_memberProfileImg"
										src="<%= request.getContextPath() %>/images/profileDefault.png"
										alt="Profile Image">
								</div>
							</div>
							<div class="wEnrollPreAreaT">
								<div class="wEnrollPreTitle">
									<div>
										회원 기본정보
									</div>
								</div>
								<div class="wEnrollPreBox">
									<div class="wEnrollPreValueBox">
										<div class="wEnrollPreAttr">닉네임</div>
										<div id="EP_memberNickname" class="wEnrollPreVal"></div>
									</div>
									<div class="wEnrollPreValueBox">
										<div class="wEnrollPreAttr">이메일</div>
										<div id="EP_memberEmail" class="wEnrollPreVal"></div>
									</div>
									<div class="wEnrollPreValueBox">
										<div class="wEnrollPreAttr">이름</div>
										<div id="EP_memberName" class="wEnrollPreVal"></div>
									</div>
									<div class="wEnrollPreValueBox">
										<div class="wEnrollPreAttr">생일</div>
										<div id="EP_memberBirthday" class="wEnrollPreVal"></div>
									</div>
									<div class="wEnrollPreValueBox">
										<div class="wEnrollPreAttr">전화번호</div>
										<div id="EP_memberPhone" class="wEnrollPreVal"></div>
									</div>
								</div>
							</div>
							<div class="wEnrollPreAreaT">
								<div class="wEnrollPreTitle">
									<div>
										작가 신청 정보
									</div>
								</div>
								<div class="wEnrollPreBox ">
									<div class="wEP_Category wEnrollPreValueBox">
										<div class="wEnrollPreAttr">희망하는 분야</div>
										<div id="EP_writerCategory" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_Address wEnrollPreValueBox">
										<div class="wEnrollPreAttr">주소(SNS,홈페이지,입점되어 있는 마켓 등)</div>
										<div id="EP_writerAddress" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_Reason wEnrollPreValueBox">
										<div class="wEnrollPreAttr">작가가 되고 싶은 이유</div>
										<div id="EP_writerReason" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_MajorImgfile wEnrollPreValueBox">
										<div class="wEnrollPreAttr">대표 작품 사진 파일</div>
										<div id="EP_writerMajorImgfileOriginal" class="wEnrollPreVal">
											<div class="inputImage1">
												<img src="<%=request.getContextPath()%>/images/uploadIcon.png" />
												<div class="imageTitle1"></div>
											</div>
										</div>
									</div>
									<div class="wEP_ScheduleYN wEnrollPreValueBox">
										<div class="wEnrollPreAttr">
											하비풀 작가로 선정되면, 기획안 작성(2주 기한)과 영상 촬영(1일)에 참여가 필요합니다.
										</div>
										<div id="EP_writerScheduleYN" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ContractYN wEnrollPreValueBox">
										<div class="wEnrollPreAttr">
											하비풀 클래스를 제작하면, '디자인 라이센스' 계약에 따라 '러닝 개런티'가 지급됩니다. 각 클래스 판매금액의 3%씩 누적되어
											정산되며,
											판매량이 특정
											구간을
											달성할 때마다 인센티브를 드립니다.
										</div>
										<div id="EP_writerContractYN" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_Month wEnrollPreValueBox">
										<div class="wEnrollPreAttr">
											희망하는 클래스 오픈 '월'
										</div>
										<div id="EP_writerWishMonth" class="wEnrollPreVal"></div>
									</div>
								</div>
							</div>
							<div class="wEnrollPreAreaT">
								<div class="wEnrollPreTitle">
									<div>
										예상 클래스 기획
									</div>
								</div>
								<div class="wEnrollPreBox">
									<div class="wEP_ClassName wEnrollPreValueBox">
										<div class="wEnrollPreAttr">클래스 이름</div>
										<div id="EP_writerClassName" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ClassSelectReason wEnrollPreValueBox">
										<div class="wEnrollPreAttr">클래스를 선택한 이유</div>
										<div id="EP_writerClassSelectReason" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ClassLevel wEnrollPreValueBox">
										<div class="wEnrollPreAttr">클래스 난이도</div>
										<div id="EP_writerClassLevel" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ProductTime wEnrollPreValueBox">
										<div class="wEnrollPreAttr">제품 완성 소요시간</div>
										<div id="EP_writerProductTime" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ClassKitWarningPoint wEnrollPreValueBox">
										<div class="wEnrollPreAttr">온라인 클래스로 만들었을때 유의할 점</div>
										<div id="EP_writerClassKitWarningPoint" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ClassKitPart wEnrollPreValueBox">
										<div class="wEnrollPreAttr">KIT의 구성품</div>
										<div id="EP_writerClassKitPart" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_ClassImgfile wEnrollPreValueBox">
										<div class="wEnrollPreAttr">클래스의 제품사진 파일</div>
										<div id="EP_classImgfileOriginal" class="wEnrollPreVal">
											<div class="inputImage1">
												<img src="<%= request.getContextPath() %>/images/uploadIcon.png" />
												<div class="imageTitle1"></div>
											</div>
										</div>
									</div>
									<div class="wEP_PrepRequestYN wEnrollPreValueBox">
										<div class="wEnrollPreAttr">예비작가 신청 여부</div>
										<div id="EP_writerPrepRequestYN" class="wEnrollPreVal"></div>
									</div>
									<div class="wEP_FinalPoint wEnrollPreValueBox">
										<div class="wEnrollPreAttr">하비스트에게 하고 싶은 말</div>
										<div id="EP_writerFinalPoint" class="wEnrollPreVal"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="wEPreviewFooter">
							<div>
								<button type="button" id="wEmodifyMypage" class="button1">수정하기</button>
								<button type="button" class="pagePreviewClose button1">닫기</button>
							</div>
						</div>
					</div>
				</div>

				<script>
					function fn_wEmodifyMypageY(index) {
						alert("접수기간입니다. 수정이 가능합니다.");
						location.href = "<%= request.getContextPath() %>/mypage/mypageWEmodify?writerEnrollNo=" + index;
					}
					function fn_wEmodifyMypageN(index) {
						alert("접수기간이 지났습니다. 수정이 불가능합니다.");
					}

					function fn_prepRequestYN(index,flagYN) {
						$.ajax({
							url: '<%= request.getContextPath() %>/mypage/prepRequestYN_Ajax.do',
							type: 'POST',
							dataType: 'text',
							data: { "writerEnrollNo" : index, "flagYN" : flagYN },
							success: function (data) {
								console.log(data);
							}
						})

						var text = $('.selected')[0].innerText;
						if (text == '최근등록순') {
							$.ajax({
								url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "descEnroll", "keyword": keyword.val() },
								success: function (data) {
									var adminWEAjax = $('#adminWE-Ajax');
									adminWEAjax.empty();
									adminWEAjax.html(data);
									$('#sort1').addClass('selected');
									$('#sort2').removeClass('selected');
								}
							})
						}
						else if (text == '이전등록순') {
							$.ajax({
								url: '<%= request.getContextPath() %>/mypage/mypageWEAjax.do',
								type: 'POST',
								dataType: 'text',
								data: { "sort": "ascEnroll", "keyword": keyword.val() },
								success: function (data) {
									var adminWEAjax = $('#adminWE-Ajax');
									adminWEAjax.empty();
									adminWEAjax.html(data);
									$('#sort2').addClass('selected');
									$('#sort1').removeClass('selected');
								}
							})
						}
					}

					function fn_WEViewAjax(index, weQuarter) {
						$(".pagePreviewModal").css("display", "flex");
						$(".wEPreviewBody").animate({ scrollTop: 0 }, 0);
						$.ajax({
							url: "<%= request.getContextPath() %>/admin/adminWEViewAjax.do",
							type: "post",
							data: { "index": index },
							dataType: "json",
							success: function (data) {
								$("#EP_memberProfileImg").attr("src", "<%= request.getContextPath() %>/upload/member/" + data["memberProfileImg"]);
								$("#EP_memberNickname").html(data["memberNickname"]);
								$("#EP_memberEmail").html(data["memberEmail"]);
								$("#EP_memberName").html(data["memberName"]);
								$("#EP_memberBirthday").html(data["memberBirthday"]);
								$("#EP_memberPhone").html(data["memberPhone"]);
								$("#EP_writerCategory").html(data["writerCategory"]);
								$("#EP_writerAddress").html(data["writerAddress"]);
								$("#EP_writerReason").html(data["writerReason"]);
								/* $("#EP_writerMajorImgfileOriginal").html(data["writerMajorImgfileOriginal"]); */
								$("#EP_writerScheduleYN").html(data["writerScheduleYN"]);
								$("#EP_writerContractYN").html(data["writerContractYN"]);
								$("#EP_writerWishMonth").html(data["writerWishMonth"]);
								$("#EP_writerClassName").html(data["writerClassName"]);
								$("#EP_writerClassSelectReason").html(data["writerClassSelectReason"]);
								$("#EP_writerClassLevel").html(data["writerClassLevel"]);
								$("#EP_writerProductTime").html(data["writerProductTime"]);
								$("#EP_writerClassKitWarningPoint").html(data["writerClassKitWarningPoint"]);
								$("#EP_writerClassKitPart").html(data["writerClassKitPart"]);
								/* $("#EP_classImgfileOriginal").html(data["classImgfileOriginal"]); */
								$("#EP_writerPrepRequestYN").html(data["writerPrepRequestYN"]);
								$("#EP_writerFinalPoint").html(data["writerFinalPoint"]);

								$(".inputImage1 img").attr("src", "<%= request.getContextPath() %>/upload/writer/"+data["writerMajorImgfileRenamed"]);
								$(".inputImage1 img").css({"width":"300px","height":"200px"});
								$(".inputImage2 img").attr("src", "<%= request.getContextPath() %>/upload/writer/"+data["classImgfileRenamed"]);
								$(".inputImage2 img").css({"width":"300px","height":"200px"});
								$(".imageTitle1").text("파일명 : " + data["writerMajorImgfileOriginal"]);
								$(".imageTitle2").text("파일명 : " + data["classImgfileOriginal"]);

								if (weQuarter == data["writerEnrollQuarter"]) {
									$("#wEmodifyMypage").attr("onclick", "fn_wEmodifyMypageY(" + index + ")");
								} else {
									$("#wEmodifyMypage").attr("onclick", "fn_wEmodifyMypageN(" + index + ")");
								}
							}
						})
					}

					$(function () {
						$(".pagePreviewClose").click(function () {
							$(".pagePreviewModal").css("display", "none");
						})
					})
				</script>

			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>