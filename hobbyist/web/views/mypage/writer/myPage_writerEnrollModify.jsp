<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%
	WriterEnroll weList = (WriterEnroll)request.getAttribute("weList");
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
				<li onclick="location.href='<%= request.getContextPath() %>'"><img
						src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Member</li>
				<li>마이 페이지 > 작가신청 > 수정하기</li>
			</ul>

		</div><br>

		<!-- 마이페이지메뉴는 중복되기때문에 myPage_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/mypage/myPage_menu.jsp" %>
		<!-- 마이페이지 메뉴 인클루드 끝 -->

		<div class="myPage_right">
			<div id="writerEnrollModify">
				<form action="<%= request.getContextPath() %>/writer/writerEnrollModifyEnd" method="post" id="writerEnrollFrm"
					autocomplete="off" enctype="multipart/form-data">
					<div class="wEnrollPage">
						<div class="wEnrollbody">
							<div id="qBoxLocation1" class="qBoxLocation" style="height: 0px;"></div>
							<div class="qBox">
								<div class="qNumber"><span>1</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">주로 작업하는 분야를 적어주세요</div>
										<div class="qEx">예) 프랑스자수, 위빙, 드로잉, 가죽공예, 향수 등</div>
									</div>
									<input type="text" class="inputText" name="writerCategory"
										placeholder="여기에 답변을 입력하십시오..." value="<%= weList.getWriterCategory() %>"/>
									<button type="button" id="qBoxBtn1" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation2" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>2</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">SNS, 홈페이지, 입점되어 있는 마켓 등이 있다면 주소를 적어주세요.</div>
									</div>
									<input type="text" class="inputText" name="writerAddress"
										placeholder="여기에 답변을 입력하십시오..." value="<%= weList.getWriterAddress() %>"/>
									<button type="button" id="qBoxBtn2" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation3" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>3</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">하비스트 작가가 되고 싶은 이유가 무엇인가요?</div>
									</div>
									<textarea class="qTextarea" name="writerReason" placeholder="여기에 답변을 입력하십시오..."
										rows="1"><%= weList.getWriterReason() %></textarea>
									<button type="button" id="qBoxBtn3" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation4" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>4</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">작가님의 대표 작품 사진을 보고싶어요.</div>
										<div class="qEx">자랑하고 싶은 작업 사진을 맘껏 업로드해주세요. (사진은 압축해서 올려주세요.)</div>
									</div>
									<div class="inputImageBox">
										<div class="inputImageback">
											<div class="inputImage">
												<img src="<%= request.getContextPath() %>/upload/writer/<%= weList.getWriterMajorImgfileRenamed() %>"/>
												<div class="imageTitle"><%= weList.getWriterMajorImgfileOriginal() %></div>
											</div>
											<input type="file" name="writerMajorImgfileOriginal"
												class="inputImageFile" value="<%= weList.getWriterMajorImgfileOriginal() %>"/>
											<input type="hidden" name="old_wmImgO" value="<%= weList.getWriterMajorImgfileOriginal() %>"/>
											<input type="hidden" name="old_wmImgR" value="<%= weList.getWriterMajorImgfileRenamed() %>"/>
											<button type="button" class="fileImgDelBtn" onclick="fn_wmImgDelBtn()">x</button>
											<input type="hidden" name="wmImgDelFlag"/>
											<script>
												function fn_wmImgDelBtn() {
													$(".inputImage img").attr("src", "<%= request.getContextPath() %>/images/uploadIcon.png");
													$(".inputImage img").css({"width":"100px","height":"100px"});
													$("input[name='writerMajorImgfileOriginal']").val("");
													$(".imageTitle").text("");
												}
											</script>
										</div>
									</div>
									<script>
										$(function () {
											$("input[name='writerMajorImgfileOriginal']").change(function (event) {
												$(".inputImage img").attr("src", URL.createObjectURL(event.target.files[0]));
												$(".inputImage img").css({"width":"300px","height":"200px"});
												$(".inputImage3 img").attr("src", URL.createObjectURL(event.target.files[0]));
												$(".inputImage3 img").css({"width":"300px","height":"200px"});
												var fileName = $("input[name='writerMajorImgfileOriginal']").val();
												$(".imageTitle").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
												$(".imageTitle3").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
												if (fileName != "") {
													$("#qBoxBtn4").css("opacity", 1);
												}
												else {
													$("#qBoxBtn4").css("opacity", 0);
												}
											})
										})
									</script>
									<button type="button" id="qBoxBtn4" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation5" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>5</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">하비스트 온라인 클래스 신청하기</div>
										<div class="qEx">
											작가님의 선호 일정에 맞추어
											하비스트 하반기 온라인 클래스 제작 기간과 갯수를 '모두' 선택해주세요.
											가능한 스케쥴을 모두 적어주시면 협의를 통해 확정됩니다.

											기존에 작업하신 작품 또는 오프라인 클래스로 진행하시던 작품으로
											온라인 클래스화하면 더 빠르고 효율적으로 제작할 수 있습니다.
										</div>
									</div>
									<button type="button" id="qBoxBtn5" class="buttonYN">계속</button>
								</div>
							</div>
							<div id="qBoxLocation51" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span></span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">하비스트 작가로 선정되면, 기획안 작성(2주 기한)과 영상 촬영(1일)에 참여가 필요합니다.</div>
									</div>
									<div class="buttonYNBox">
										<div>
											<button type="button" id="qBoxBtn51Y" class="buttonYN">A : 네, 가능해요.</button>
											<button type="button" id="qBoxBtn51N" class="buttonYN">B : 어려울 것
												같아요.</button>
											<input type="hidden" id="writerScheduleYN" name="writerScheduleYN" value="<%= weList.getWriterScheduleYN() %>"/>
											<script>
												$(function() {
													$(window).scroll(function () {
														if($("input[name='writerScheduleYN']").val()=="Y") {
															$("#qBoxBtn51Y").addClass("buttonYN1");
															$("#qBoxBtn51Y").removeClass("buttonYN");
														}
														else if($("input[name='writerScheduleYN']").val()=="N") {
															$("#qBoxBtn51N").addClass("buttonYN1");
															$("#qBoxBtn51N").removeClass("buttonYN");
														}
													})
												})
											</script>
										</div>
									</div>
								</div>
							</div>
							<div id="qBoxLocation6" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>6</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">
											하비스트 클래스를 제작하면, '디자인 라이센스' 계약에 따라 '러닝 개런티'가 지급됩니다.
											각 클래스 판매금액의 3%씩 누적되어 정산되며, 판매량이 특정 구간을 달성할 때마다 인센티브를 드립니다.
										</div>
									</div>
									<button type="button" id="qBoxBtn6Y" class="buttonYN">A : 네, 가능해요.</button>
									<input type="hidden" id="writerContractYN" name="writerContractYN" value="<%= weList.getWriterContractYN() %>"/>
									<script>
										$(function() {
											$(window).scroll(function () {
												if($("input[name='writerContractYN']").val()=="Y") {
													$("#qBoxBtn6Y").addClass("buttonYN1");
													$("#qBoxBtn6Y").removeClass("buttonYN");
												}
											})
										})
									</script>
								</div>
							</div>
							<div id="qBoxLocation7" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>7</span></div>
								<div class="qContent">
									<div>
										<div class="qTitle">희망하는 클래스 오픈 '월'을 적어주세요. (여러개 입력 가능)</div>
										<div class="qEx">
											오픈일을 기준으로 클래스 제작에 소요되는 일정을 참고해 가능한 '월'을 적어주세요.
										</div>
									</div>
									<input type="text" class="inputText" name="writerWishMonth"
										placeholder="여기에 답변을 입력하십시오..." value="<%= weList.getWriterWishMonth() %>"/>
									<button type="button" id="qBoxBtn7" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation8" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span></span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">클래스 예시 만들어보기</div>
										<div class="qEx">
											작가님의 작품 중 하비스트과 함께 온라인 클래스로 만들어보고 싶은 제품을 선택하여 뽐내주세요.
										</div>
									</div>
									<button type="button" id="qBoxBtn8" class="buttonYN">계속</button>
								</div>
							</div>
							<div id="qBoxLocation9" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>8</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">클래스 이름은 무엇인가요?</div>
										<div class="qEx"></div>
									</div>
									<input type="text" class="inputText" name="writerClassName"
										placeholder="여기에 답변을 입력하십시오..." value="<%= weList.getWriterClassName() %>"/>
									<button type="button" id="qBoxBtn9" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation10" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>9</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">이 클래스를 선택한 이유는 무엇인가요?</div>
										<div class="qEx"></div>
									</div>
									<textarea class="qTextarea" name="writerClassSelectReason"
										placeholder="여기에 답변을 입력하십시오..." rows="1"><%= weList.getWriterClassSelectReason() %></textarea>
									<button type="button" id="qBoxBtn10" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation11" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>10</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">클래스의 난이도를 골라주세요.</div>
										<div class="qEx">초급자 기준으로 알려주세요.</div>
									</div>
									<div class="writerClassLevelBox">
										<div class="writerClassLevelBtn">
											<div id="writerClassLevel1" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(1)">1</div>
											<div id="writerClassLevel2" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(2)">2</div>
											<div id="writerClassLevel3" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(3)">3</div>
											<div id="writerClassLevel4" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(4)">4</div>
											<div id="writerClassLevel5" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(5)">5</div>
											<div id="writerClassLevel6" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(6)">6</div>
											<div id="writerClassLevel7" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(7)">7</div>
											<div id="writerClassLevel8" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(8)">8</div>
											<div id="writerClassLevel9" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(9)">9</div>
											<div id="writerClassLevel10" class="writerClassLevelNum"
												onclick="fn_writerClassLevel(10)">10</div>
										</div>
										<div class="writerClassLevelContent">
											<div>매우쉬움</div>
											<div>매우어려움</div>
										</div>
									</div>
									<input type="hidden" id="writerClassLevel" name="writerClassLevel" value="<%= weList.getWriterClassLevel() %>"/>
									<script>
										$(function () {
											$(window).scroll(function () {
												var loc = $("#writerClassLevel" + "<%= weList.getWriterClassLevel() %>");
												loc.addClass("writerClassLevelNum1");
											})
										})
									</script>
								</div>
							</div>
							<div id="qBoxLocation12" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>11</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">초보자가 제품을 완성하는데 걸리는 예상 소요시간을 적어주세요.</div>
										<div class="qEx"></div>
									</div>
									<input type="text" class="inputText" name="writerProductTime"
										placeholder="여기에 답변을 입력하십시오..." value="<%= weList.getWriterProductTime() %>"/>
									<button type="button" id="qBoxBtn12" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation13" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>12</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">이 클래스를 온라인 클래스와 KIT로 만든다면, 어떤 점을 유의해야 할까요?</div>
										<div class="qEx">최소 3가지를 적어주세요.</div>
									</div>
									<textarea class="qTextarea" name="writerClassKitWarningPoint"
										placeholder="여기에 답변을 입력하십시오..." rows="1"><%= weList.getWriterClassKitWarningPoint() %></textarea>
									<button type="button" id="qBoxBtn13" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation14" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>13</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">KIT에는 어떤 구성품들이 들어가나요?</div>
										<div class="qEx">예) 실, 바늘, 천 등</div>
									</div>
									<textarea class="qTextarea" name="writerClassKitPart"
										placeholder="여기에 답변을 입력하십시오..." rows="1"><%= weList.getWriterClassKitPart() %></textarea>
									<button type="button" id="qBoxBtn14" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation15" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>14</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">클래스 제품 사진이 궁금해요!</div>
										<div class="qEx">실제 클래스화 되었을 때 가장 유사한 형태의 사진을 보여주세요. (사진은 압축해서 올려주세요.)</div>
									</div>
									<div class="inputImageback">
										<div class="inputImage1">
											<img src="<%= request.getContextPath() %>/upload/writer/<%= weList.getClassImgfileRenamed() %>" />
											<div class="imageTitle1"><%= weList.getClassImgfileOriginal() %></div>
										</div>
										<input type="file" name="classImgfileOriginal" class="inputClassImageFile" value="<%= weList.getClassImgfileOriginal() %>"/>
										<input type="hidden" name="old_cImgO" value="<%= weList.getClassImgfileOriginal() %>"/>
										<input type="hidden" name="old_cImgR" value="<%= weList.getClassImgfileRenamed() %>"/>
										<button type="button" class="fileImgDelBtn" onclick="fn_cImgDelBtn()">x</button>
										<input type="hidden" name="cImgDelFlag"/>
										<script>
											function fn_cImgDelBtn() {
												$(".inputImage1 img").attr("src", "<%= request.getContextPath() %>/images/uploadIcon.png");
												$(".inputImage1 img").css({"width":"100px","height":"100px"});
												$("input[name='classImgfileOriginal']").val("");
												$(".imageTitle1").text("");
											}
										</script>
									</div>
									<script>
										$(function () {
											$("input[name='classImgfileOriginal']").change(function (event) {
												$(".inputImage1 img").attr("src", URL.createObjectURL(event.target.files[0]));
												$(".inputImage1 img").css({"width":"300px","height":"200px"});
												$(".inputImage4 img").attr("src", URL.createObjectURL(event.target.files[0]));
												$(".inputImage4 img").css({"width":"300px","height":"200px"});
												var fileName = $("input[name='classImgfileOriginal']").val();
												$(".imageTitle1").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
												$(".imageTitle4").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
												if (fileName != "") {
													$("#qBoxBtn15").css("opacity", 1);
												}
												else {
													$("#qBoxBtn15").css("opacity", 0);
												}
											})
										})
									</script>
									<button type="button" id="qBoxBtn15" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation16" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>15</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">만약 이번 기간에 함께하지 못하게 되시더라도 계속해서 신청하실 의향이 있으신가요?</div>
										<div class="qEx">의향이 있으시다면 예비작가로 등록하여, 다음 모집 기간 때 알림 이메일을 보내드리도록 하겠습니다.
										</div>
									</div>
									<div class="buttonYNBox">
										<div>
											<button type="button" id="qBoxBtn16Y" class="buttonYN">A : 네</button>
											<button type="button" id="qBoxBtn16N" class="buttonYN">B : 아니오</button>
											<input type="hidden" id="writerPrepRequestYN" name="writerPrepRequestYN" value="<%= weList.getWriterPrepRequestYN() %>"/>
											<script>
												$(function() {
													$(window).scroll(function () {
														if($("input[name='writerPrepRequestYN']").val()=="Y") {
															$("#qBoxBtn16Y").addClass("buttonYN1");
															$("#qBoxBtn16Y").removeClass("buttonYN");
														}
														else if($("input[name='writerPrepRequestYN']").val()=="N") {
															$("#qBoxBtn16N").addClass("buttonYN1");
															$("#qBoxBtn16N").removeClass("buttonYN");
														}
													})
												})
											</script>
										</div>
									</div>
								</div>
							</div>
							<div id="qBoxLocation17" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span>16</span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">마지막으로 하비스트에게 제안 혹은 하고싶은 말이 있다면 남겨주세요.</div>
									</div>
									<textarea class="qTextarea" name="writerFinalPoint" placeholder="여기에 답변을 입력하십시오..."
										rows="1"><%= weList.getWriterFinalPoint() %></textarea>
									<button type="button" id="qBoxBtn17" class="button1">확인 √</button>
								</div>
							</div>
							<div id="qBoxLocation18" class="qBoxLocation"></div>
							<div class="qBox">
								<div class="qNumber"><span></span></div>
								<div class="qContent">
									<div class="qText">
										<div class="qTitle">참여해주셔서 감사합니다!</div>
										<div class="qEx">
											선정결과는 상시적으로 개별 연락드릴 예정입니다.<br />
											더 궁금한 점이 있으시다면, help@hobbyful.co.kr 로 문의해주세요.
										</div>
										<button type="button" id="qBoxBtn18" class="buttonYN">확인 √</button>
									</div>
								</div>
							</div>
							<div id="qBoxLocation19" class="qBoxLocation"></div>
						</div>

						<div class="wEnrollFooter">
							<div class="wEnrollFooterBox1">
								<div class="qBoxS">
									<div class="qNumber"><span></span></div>
									<div class="qContent">
										<div class="qText">
											<button type="button" id="pagePreview" class="button2">미리보기</button>
											<button type="button" id="writerEnrollSubmit" class="button2">제출</button>
										</div>
									</div>
								</div>
							</div>
							<div class="wEnrollFooterBox2">
								<div class="commitPoint">
									<div class="commitLineBox">
										<div class="commitLine"></div>
									</div>
									<div class="commitLineText">
										<div class="commitContent"> 16 개 중 <span id="commitContentNum">0</span> 개 답변됨
										</div>
										<button type="button" id="commitNoBtn" class="button3">미답변 바로가기</button>
									</div>
								</div>
								<div class="qSelectBtn">
									<button type="button" id="buttonUp" class="buttonUpDown">▲</button>
									<button type="button" id="buttonDown" class="buttonUpDown">▼</button>
								</div>
							</div>
						</div>
					</div>
					<div id="pagePreviewModal" class="pagePreviewModal">
						<div class="pagePreviewModalBox">
							<div class="wEPreviewBody">
								<div class="wEnrollPreAreaI">
									<div class="wEnrollPreMemberImageBox">
										<img src="<%= request.getContextPath() %>/images/<%= logginMember.getMemberRenamedImage() %>"
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
										<div>
											<div class="wEnrollPreAttr">닉네임</div>
											<div class="wEnrollPreVal"><%= logginMember.getMemberNickname() %></div>
										</div>
										<div>
											<div class="wEnrollPreAttr">이메일</div>
											<div class="wEnrollPreVal"><%= logginMember.getMemberEmail() %></div>
										</div>
										<div>
											<div class="wEnrollPreAttr">이름</div>
											<div class="wEnrollPreVal"><%= logginMember.getMemberName() %></div>
										</div>
										<div>
											<div class="wEnrollPreAttr">생일</div>
											<div class="wEnrollPreVal"><%= logginMember.getMemberBirthday() %></div>
										</div>
										<div>
											<div class="wEnrollPreAttr">전화번호</div>
											<div class="wEnrollPreVal"><%= logginMember.getMemberPhone() %></div>
										</div>
									</div>
								</div>
								<div class="wEnrollPreAreaT">
									<div class="wEnrollPreTitle">
										<div>
											작가 신청 정보
										</div>
									</div>
									<div class="wEnrollPreBox">
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
												<div class="inputImage3">
													<img src="<%= request.getContextPath() %>/upload/writer/<%= weList.getClassImgfileRenamed() %>" />
													<div class="imageTitle3"></div>
												</div>
											</div>
										</div>
										<div class="wEP_ScheduleYN wEnrollPreValueBox">
											<div class="wEnrollPreAttr">
												하비스트 작가로 선정되면, 기획안 작성(2주 기한)과 영상 촬영(1일)에 참여가 필요합니다.
											</div>
											<div id="EP_writerScheduleYN" class="wEnrollPreVal"></div>
										</div>
										<div class="wEP_ContractYN wEnrollPreValueBox">
											<div class="wEnrollPreAttr">
												하비스트 클래스를 제작하면, '디자인 라이센스' 계약에 따라 '러닝 개런티'가 지급됩니다. 각 클래스 판매금액의 3%씩 누적되어
												정산되며, 판매량이 특정
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
												<div class="inputImage4">
													<img src="<%= request.getContextPath() %>/upload/writer/<%= weList.getClassImgfileRenamed() %>"/>
													<div class="imageTitle4"></div>
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
									<button type="button" id="writerEnrollFrmPre" class="button2">제출하기</button>
									<button type="button" id="pagePreviewClose" class="button2">닫기</button>
								</div>
							</div>
						</div>
					</div>
					<input type="hidden" name="memberNo" value="<%= logginMember.getMemberNo() %>" />
					<input type="hidden" name="memberProfileImg" value="<%= logginMember.getMemberRenamedImage() %>" />
					<input type="hidden" name="memberNickname" value="<%= logginMember.getMemberNickname() %>" />
					<input type="hidden" name="memberEmail" value="<%= logginMember.getMemberEmail() %>" />
					<input type="hidden" name="memberName" value="<%= logginMember.getMemberName() %>" />
					<input type="hidden" name="memberBirthday" value="<%= logginMember.getMemberBirthday() %>" />
					<input type="hidden" name="memberPhone" value="<%= logginMember.getMemberPhone() %>" />
					<input type="hidden" name="writerEnrollNo" value="<%= weList.getWriterEnrollNo() %>" />
					<input type="hidden" name="writerEnrollQuarter" value="<%= weList.getWriterEnrollQuarter() %>" />


				</form>

				<script>

					// 모달창 열기 닫기 기능 구현
					$(function () {
						$("#pagePreview").click(function () {
							//미리보기에 작성한 내용 채우기
							var writerCategory = $("input[name='writerCategory']").val();
							var writerAddress = $("input[name='writerAddress']").val();
							var writerReason = $("textarea[name='writerReason']").val().replace(/\n/g, "<br>");
							var writerMajorImgfileOriginal = $("input[name='writerMajorImgfileOriginal']").val();
							var writerScheduleYN = $("input[name='writerScheduleYN']").val();
							var writerContractYN = $("input[name='writerContractYN']").val();
							var writerWishMonth = $("input[name='writerWishMonth']").val();
							var writerClassName = $("input[name='writerClassName']").val();
							var writerClassSelectReason = $("textarea[name='writerClassSelectReason']").val().replace(/\n/g, "<br>");
							var writerClassLevel = $("input[name='writerClassLevel']").val();
							var writerProductTime = $("input[name='writerProductTime']").val();
							var writerClassKitWarningPoint = $("textarea[name='writerClassKitWarningPoint']").val().replace(/\n/g, "<br>");
							var writerClassKitPart = $("textarea[name='writerClassKitPart']").val().replace(/\n/g, "<br>");
							var classImgfileOriginal = $("input[name='classImgfileOriginal']").val();
							var writerPrepRequestYN = $("input[name='writerPrepRequestYN']").val();
							var writerFinalPoint = $("textarea[name='writerFinalPoint']").val().replace(/\n/g, "<br>");

							$("#EP_writerCategory").html(writerCategory);
							$("#EP_writerAddress").html(writerAddress);
							$("#EP_writerReason").html(writerReason);
							// $("#EP_writerMajorImgfileOriginal").html(writerMajorImgfileOriginal);
							$("#EP_writerScheduleYN").html(writerScheduleYN);
							$("#EP_writerContractYN").html(writerContractYN);
							$("#EP_writerWishMonth").html(writerWishMonth);
							$("#EP_writerClassName").html(writerClassName);
							$("#EP_writerClassSelectReason").html(writerClassSelectReason);
							$("#EP_writerClassLevel").html(writerClassLevel);
							$("#EP_writerProductTime").html(writerProductTime);
							$("#EP_writerClassKitWarningPoint").html(writerClassKitWarningPoint);
							$("#EP_writerClassKitPart").html(writerClassKitPart);
							// $("#EP_classImgfileOriginal").html(classImgfileOriginal);
							$("#EP_writerPrepRequestYN").html(writerPrepRequestYN);
							$("#EP_writerFinalPoint").html(writerFinalPoint);

							//모달창 띄우기
							$(".pagePreviewModal").css("display", "flex");
						})
						$("#pagePreviewClose").click(function () {
							$(".pagePreviewModal").css("display", "none");
						})

						$("#writerEnrollSubmit").click(function () {

							$("textarea[name='writerReason']").val($("textarea[name='writerReason']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassSelectReason']").val($("textarea[name='writerClassSelectReason']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassKitWarningPoint']").val($("textarea[name='writerClassKitWarningPoint']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassKitPart']").val($("textarea[name='writerClassKitPart']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerFinalPoint']").val($("textarea[name='writerFinalPoint']").val().replace(/\n/g, "<br>"));

							//submit 버튼 위치
							$("#writerEnrollFrm").submit();
						})
						$("#writerEnrollFrmPre").click(function () {

							$("textarea[name='writerReason']").val($("textarea[name='writerReason']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassSelectReason']").val($("textarea[name='writerClassSelectReason']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassKitWarningPoint']").val($("textarea[name='writerClassKitWarningPoint']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerClassKitPart']").val($("textarea[name='writerClassKitPart']").val().replace(/\n/g, "<br>"));
							$("textarea[name='writerFinalPoint']").val($("textarea[name='writerFinalPoint']").val().replace(/\n/g, "<br>"));

							// 미리보기 submit 버튼 위치
							$(".pagePreviewModal").css("display", "none");
							$("#writerEnrollFrm").submit();
						})

					})

					var qBoxLocation1 = 0;
					var qBoxLocation2 = Math.floor($("#qBoxLocation2").offset().top) - 150;
					var qBoxLocation3 = Math.floor($("#qBoxLocation3").offset().top) - 150;
					var qBoxLocation4 = Math.floor($("#qBoxLocation4").offset().top) - 80;
					var qBoxLocation5 = Math.floor($("#qBoxLocation5").offset().top) - 80;
					var qBoxLocation51 = Math.floor($("#qBoxLocation51").offset().top) - 150;
					var qBoxLocation6 = Math.floor($("#qBoxLocation6").offset().top) - 150;
					var qBoxLocation7 = Math.floor($("#qBoxLocation7").offset().top) - 150;
					var qBoxLocation8 = Math.floor($("#qBoxLocation8").offset().top) - 150;
					var qBoxLocation9 = Math.floor($("#qBoxLocation9").offset().top) - 150;
					var qBoxLocation10 = Math.floor($("#qBoxLocation10").offset().top) - 150;
					var qBoxLocation11 = Math.floor($("#qBoxLocation11").offset().top) - 150;
					var qBoxLocation12 = Math.floor($("#qBoxLocation12").offset().top) - 150;
					var qBoxLocation13 = Math.floor($("#qBoxLocation13").offset().top) - 150;
					var qBoxLocation14 = Math.floor($("#qBoxLocation14").offset().top) - 150;
					var qBoxLocation15 = Math.floor($("#qBoxLocation15").offset().top) - 80;
					var qBoxLocation16 = Math.floor($("#qBoxLocation16").offset().top) - 150;
					var qBoxLocation17 = Math.floor($("#qBoxLocation17").offset().top) - 150;
					var qBoxLocation18 = Math.floor($("#qBoxLocation18").offset().top) - 150;
					var qBoxLocation19 = Math.floor($("#qBoxLocation19").offset().top) - 150;

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
					})

					// textarea 자동 높이 조절
					$("textarea.qTextarea").on('keydown keyup', function () {
						$(this).height(1).height($(this).prop('scrollHeight'));
					});

					// input,textarea,button 엔터키 이벤트 제한
					$(function () {
						$('textarea').on('keydown', function (event) {
							if (event.keyCode == 13) {
								if (!event.shiftKey) {
									event.preventDefault();
								}
							}
						});
						$('input').on('keydown', function (event) {
							if (event.keyCode == 13) {
								if (!event.shiftKey) {
									event.preventDefault();
								}
							}
						});
						$('button').on('keydown', function (event) {
							if (event.keyCode == 13) {
								if (!event.shiftKey) {
									event.preventDefault();
								}
							}
						})
					});

					// qBoxLocation11 value값 input에 담기
					function fn_writerClassLevel(vl) {
						var loc = $("#writerClassLevel" + vl);
						if ($("#writerClassLevel").val() != vl) {
							$("#writerClassLevel").attr("value", vl);
							for (var i = 1; i <= 10; i++) {
								$("#writerClassLevel" + i).addClass("writerClassLevelNum");
								$("#writerClassLevel" + i).removeClass("writerClassLevelNum1");
							}
							loc.addClass("writerClassLevelNum1");
							$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
						}
						else {
							$("#writerClassLevel").attr("value", "");
							loc.addClass("writerClassLevelNum");
							loc.removeClass("writerClassLevelNum1");
						}
					}

					//footer 위아래 버튼 기능 구현
					$(function () {
						$("#buttonUp").click(function () {
							if ($(window).scrollTop() <= qBoxLocation2) {
								$('html, body').animate({ scrollTop: qBoxLocation1 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation3) {
								$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation4) {
								$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation5) {
								$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation51) {
								$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation6) {
								$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation7) {
								$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation8) {
								$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation9) {
								$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation10) {
								$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation11) {
								$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation12) {
								$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation13) {
								$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation14) {
								$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation15) {
								$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation16) {
								$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation17) {
								$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation18) {
								$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
							}
							else if ($(window).scrollTop() <= qBoxLocation19) {
								$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
							}
							else {
								$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
							}
						})
						$("#buttonDown").click(function () {
							if ($(window).scrollTop() < qBoxLocation2 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation3 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation4 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation5 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation51 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation6 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation7 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation8 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation9 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation10 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation11 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation12 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation13 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation14 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation15 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation16 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation17 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation18 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
							}
							else if ($(window).scrollTop() < qBoxLocation19 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
							}
						})

					})

					// 키감지로 이벤트 구현
					document.onkeydown = function (e) {

						// qBoxLocation11 value값 input에 담기(숫자키)
						if (qBoxLocation11 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation12 - 1) {
							if ($("#writerClassLevel").val() == "") {
								switch (e.key) {
									case "1": var vl = 1; break;
									case "2": var vl = 2; break;
									case "3": var vl = 3; break;
									case "4": var vl = 4; break;
									case "5": var vl = 5; break;
									case "6": var vl = 6; break;
									case "7": var vl = 7; break;
									case "8": var vl = 8; break;
									case "9": var vl = 9; break;
									case "0": var vl = 10; break;
									default: var vl = ""; break;
								}
								var loc = $("#writerClassLevel" + vl);
								if ($("#writerClassLevel").val() != vl) {
									$("#writerClassLevel").attr("value", vl);
									for (var i = 1; i <= 10; i++) {
										$("#writerClassLevel" + i).addClass("writerClassLevelNum");
										$("#writerClassLevel" + i).removeClass("writerClassLevelNum1");
									}
									loc.addClass("writerClassLevelNum1");
									$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
								}
								else {
									$("#writerClassLevel").attr("value", "");
									loc.addClass("writerClassLevelNum");
									loc.removeClass("writerClassLevelNum1");
								}
							}
						}

						//enter키 반응
						if (e.key == "Enter") {
							qBoxLocation1 = 0;
							qBoxLocation2 = Math.floor($("#qBoxLocation2").offset().top) - 150;
							qBoxLocation3 = Math.floor($("#qBoxLocation3").offset().top) - 150;
							qBoxLocation4 = Math.floor($("#qBoxLocation4").offset().top) - 80;
							qBoxLocation5 = Math.floor($("#qBoxLocation5").offset().top) - 80;
							qBoxLocation51 = Math.floor($("#qBoxLocation51").offset().top) - 150;
							qBoxLocation6 = Math.floor($("#qBoxLocation6").offset().top) - 150;
							qBoxLocation7 = Math.floor($("#qBoxLocation7").offset().top) - 150;
							qBoxLocation8 = Math.floor($("#qBoxLocation8").offset().top) - 150;
							qBoxLocation9 = Math.floor($("#qBoxLocation9").offset().top) - 150;
							qBoxLocation10 = Math.floor($("#qBoxLocation10").offset().top) - 150;
							qBoxLocation11 = Math.floor($("#qBoxLocation11").offset().top) - 150;
							qBoxLocation12 = Math.floor($("#qBoxLocation12").offset().top) - 150;
							qBoxLocation13 = Math.floor($("#qBoxLocation13").offset().top) - 150;
							qBoxLocation14 = Math.floor($("#qBoxLocation14").offset().top) - 150;
							qBoxLocation15 = Math.floor($("#qBoxLocation15").offset().top) - 80;
							qBoxLocation16 = Math.floor($("#qBoxLocation16").offset().top) - 150;
							qBoxLocation17 = Math.floor($("#qBoxLocation17").offset().top) - 150;
							qBoxLocation18 = Math.floor($("#qBoxLocation18").offset().top) - 150;
							qBoxLocation19 = Math.floor($("#qBoxLocation19").offset().top) - 150;

							if ($(window).scrollTop() < qBoxLocation2 - 1) {
								if ($("input[name='writerCategory']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
								}
							}
							else if (qBoxLocation2 - 1 <= $(window).scrollTop() && $(window).scrollTop() < qBoxLocation3 - 1) {
								if ($("input[name='writerAddress']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
								}
							}
							else if (qBoxLocation3 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation4 - 1) {
								var tHeight = $(window).scrollTop();
								if (!event.shiftKey) {
									if ($("textarea[name='writerReason']").val() != "") {
										$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
									}
								} else {
									tHeight += 24;
									$('html, body').animate({ scrollTop: tHeight }, 400);
								}
							}
							else if (qBoxLocation4 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation5 - 1) {
								if ($("input[name='writerMajorImgfileOriginal']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
								}
							}
							else if (qBoxLocation5 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation51 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
							}
							else if (qBoxLocation51 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation6 - 1) {
								if ($("input[name='writerScheduleYN']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
								}
							}
							else if (qBoxLocation6 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation7 - 1) {
								if ($("input[name='writerContractYN']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
								}
							}
							else if (qBoxLocation7 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation8 - 1) {
								if ($("input[name='writerWishMonth']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
								}
							}
							else if (qBoxLocation8 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation9 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
							}
							else if (qBoxLocation9 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation10 - 1) {
								if ($("input[name='writerClassName']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
								}
							}
							else if (qBoxLocation10 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation11 - 1) {
								var tHeight = $(window).scrollTop();
								if (!event.shiftKey) {
									if ($("textarea[name='writerClassSelectReason']").val() != "") {
										$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
									}
								} else {
									tHeight += 24;
									$('html, body').animate({ scrollTop: tHeight }, 400);
								}
							}
							else if (qBoxLocation11 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation12 - 1) {
								if ($("input[name='writerClassLevel']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
								}
							}
							else if (qBoxLocation12 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation13 - 1) {
								if ($("input[name='writerProductTime']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
								}
							}
							else if (qBoxLocation13 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation14 - 1) {
								var tHeight = $(window).scrollTop();
								if (!event.shiftKey) {
									if ($("textarea[name='writerClassKitWarningPoint']").val() != "") {
										$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
									}
								} else {
									tHeight += 24;
									$('html, body').animate({ scrollTop: tHeight }, 400);
								}
							}
							else if (qBoxLocation14 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation15 - 1) {
								var tHeight = $(window).scrollTop();
								if (!event.shiftKey) {
									if ($("textarea[name='writerClassKitPart']").val() != "") {
										$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
									}
								} else {
									tHeight += 24;
									$('html, body').animate({ scrollTop: tHeight }, 400);
								}
							}
							else if (qBoxLocation15 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation16 - 1) {
								if ($("input[name='classImgfileOriginal']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
								}
							}
							else if (qBoxLocation16 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation17 - 1) {
								if ($("input[name='writerPrepRequestYN']").val() != "") {
									$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
								}
							}
							else if (qBoxLocation17 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation18 - 1) {
								var tHeight = $(window).scrollTop();
								if (!event.shiftKey) {
									if ($("textarea[name='writerFinalPoint']").val() != "") {
										$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
									}
								} else {
									tHeight += 24;
									$('html, body').animate({ scrollTop: tHeight }, 400);
								}
							}
							else if (qBoxLocation18 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation19 - 1) {
								$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
							}
							else {
								e.preventDefault();
							}
						}

						// 방향키로 지정 좌표 화면 보이기(위,아래)
						switch (e.key) {
							case 'ArrowUp':
								if ($(window).scrollTop() <= qBoxLocation2) {
									$('html, body').animate({ scrollTop: qBoxLocation1 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation3) {
									$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation4) {
									$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation5) {
									$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation51) {
									$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation6) {
									$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation7) {
									$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation8) {
									$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation9) {
									$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation10) {
									$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation11) {
									$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation12) {
									$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation13) {
									$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation14) {
									$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation15) {
									$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation16) {
									$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation17) {
									$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation18) {
									$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
								}
								else if ($(window).scrollTop() <= qBoxLocation19) {
									$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
								}
								else {
									$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
								}
								break;
							case 'ArrowDown':
								if ($(window).scrollTop() < qBoxLocation2 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation3 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation4 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation5 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation51 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation6 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation7 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation8 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation9 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation10 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation11 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation12 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation13 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation14 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation15 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation16 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation17 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation18 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
								}
								else if ($(window).scrollTop() < qBoxLocation19 - 1) {
									$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
								}
								break;

							// a키 b키로 예 아니오 결정
							case 'a':
								if (qBoxLocation51 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation6 - 1) {
									if ($("#writerScheduleYN").val() != "Y") {
										$("#writerScheduleYN").attr("value", "Y");
										$("#qBoxBtn51Y").addClass("buttonYN1");
										$("#qBoxBtn51Y").removeClass("buttonYN");
										$("#qBoxBtn51N").addClass("buttonYN");
										$("#qBoxBtn51N").removeClass("buttonYN1");
										$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
									}
									else {
										$("#writerScheduleYN").attr("value", "");
										$("#qBoxBtn51Y").addClass("buttonYN");
										$("#qBoxBtn51Y").removeClass("buttonYN1");
									}
								}
								if (qBoxLocation6 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation7 - 1) {
									if ($("#writerContractYN").val() != "Y") {
										$("#writerContractYN").attr("value", "Y");
										$("#qBoxBtn6Y").addClass("buttonYN1");
										$("#qBoxBtn6Y").removeClass("buttonYN");
										$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);

									}
									else {
										$("#writerContractYN").attr("value", "");
										$("#qBoxBtn6Y").addClass("buttonYN");
										$("#qBoxBtn6Y").removeClass("buttonYN1");
									}
								}
								if (qBoxLocation16 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation17 - 1) {
									if ($("#writerPrepRequestYN").val() != "Y") {
										$("#writerPrepRequestYN").attr("value", "Y");
										$("#qBoxBtn16Y").addClass("buttonYN1");
										$("#qBoxBtn16Y").removeClass("buttonYN");
										$("#qBoxBtn16N").addClass("buttonYN");
										$("#qBoxBtn16N").removeClass("buttonYN1");
										$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
									}
									else {
										$("#writerPrepRequestYN").attr("value", "");
										$("#qBoxBtn16Y").addClass("buttonYN");
										$("#qBoxBtn16Y").removeClass("buttonYN1");
									}
								}
								break;

							case 'b':
								if (qBoxLocation51 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation6 - 1) {
									if ($("#writerScheduleYN").val() != "N") {
										$("#writerScheduleYN").attr("value", "N");
										$("#qBoxBtn51Y").addClass("buttonYN");
										$("#qBoxBtn51Y").removeClass("buttonYN1");
										$("#qBoxBtn51N").addClass("buttonYN1");
										$("#qBoxBtn51N").removeClass("buttonYN");
										$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
									}
									else {
										$("#writerScheduleYN").attr("value", "");
										$("#qBoxBtn51N").addClass("buttonYN");
										$("#qBoxBtn51N").removeClass("buttonYN1");
									}
								}
								if (qBoxLocation16 - 1 <= $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation17 - 1) {
									if ($("#writerPrepRequestYN").val() != "N") {
										$("#writerPrepRequestYN").attr("value", "N");
										$("#qBoxBtn16Y").addClass("buttonYN");
										$("#qBoxBtn16Y").removeClass("buttonYN1");
										$("#qBoxBtn16N").addClass("buttonYN1");
										$("#qBoxBtn16N").removeClass("buttonYN");
										$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
									}
									else {
										$("#writerPrepRequestYN").attr("value", "");
										$("#qBoxBtn16N").addClass("buttonYN");
										$("#qBoxBtn16N").removeClass("buttonYN1");
									}
								}
								break;
						}
					};

					// 확인 키 기능 및 value값 추가
					$(function () {
						$("#qBoxBtn1").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
						})
						$("#qBoxBtn2").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
						})
						$("#qBoxBtn3").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
						})
						$("#qBoxBtn4").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
						})
						$("#qBoxBtn5").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation51 }, 400);
						})
						$("#qBoxBtn51Y").click(function () {
							if ($("#writerScheduleYN").val() != "Y") {
								$("#writerScheduleYN").attr("value", "Y");
								$("#qBoxBtn51Y").addClass("buttonYN1");
								$("#qBoxBtn51Y").removeClass("buttonYN");
								$("#qBoxBtn51N").addClass("buttonYN");
								$("#qBoxBtn51N").removeClass("buttonYN1");
								$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
							}
							else {
								$("#writerScheduleYN").attr("value", "");
								$("#qBoxBtn51Y").addClass("buttonYN");
								$("#qBoxBtn51Y").removeClass("buttonYN1");
							}
						})
						$("#qBoxBtn51N").click(function () {
							if ($("#writerScheduleYN").val() != "N") {
								$("#writerScheduleYN").attr("value", "N");
								$("#qBoxBtn51Y").addClass("buttonYN");
								$("#qBoxBtn51Y").removeClass("buttonYN1");
								$("#qBoxBtn51N").addClass("buttonYN1");
								$("#qBoxBtn51N").removeClass("buttonYN");
								$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
							}
							else {
								$("#writerScheduleYN").attr("value", "");
								$("#qBoxBtn51N").addClass("buttonYN");
								$("#qBoxBtn51N").removeClass("buttonYN1");
							}
						})
						$("#qBoxBtn6Y").click(function () {
							if ($("#writerContractYN").val() != "Y") {
								$("#writerContractYN").attr("value", "Y");
								$("#qBoxBtn6Y").addClass("buttonYN1");
								$("#qBoxBtn6Y").removeClass("buttonYN");
								$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
							}
							else {
								$("#writerContractYN").attr("value", "");
								$("#qBoxBtn6Y").addClass("buttonYN");
								$("#qBoxBtn6Y").removeClass("buttonYN1");
							}
						})
						$("#qBoxBtn7").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation8 }, 400);
						})
						$("#qBoxBtn8").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
						})
						$("#qBoxBtn9").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
						})
						$("#qBoxBtn10").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
						})
						$("#qBoxBtn12").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
						})
						$("#qBoxBtn13").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
						})
						$("#qBoxBtn14").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
						})
						$("#qBoxBtn15").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
						})
						$("#qBoxBtn16Y").click(function () {
							if ($("#writerPrepRequestYN").val() != "Y") {
								$("#writerPrepRequestYN").attr("value", "Y");
								$("#qBoxBtn16Y").addClass("buttonYN1");
								$("#qBoxBtn16Y").removeClass("buttonYN");
								$("#qBoxBtn16N").addClass("buttonYN");
								$("#qBoxBtn16N").removeClass("buttonYN1");
								$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
							}
							else {
								$("#writerPrepRequestYN").attr("value", "");
								$("#qBoxBtn16Y").addClass("buttonYN");
								$("#qBoxBtn16Y").removeClass("buttonYN1");
							}
						})
						$("#qBoxBtn16N").click(function () {
							if ($("#writerPrepRequestYN").val() != "N") {
								$("#writerPrepRequestYN").attr("value", "N");
								$("#qBoxBtn16Y").addClass("buttonYN");
								$("#qBoxBtn16Y").removeClass("buttonYN1");
								$("#qBoxBtn16N").addClass("buttonYN1");
								$("#qBoxBtn16N").removeClass("buttonYN");
								$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
							}
							else {
								$("#writerPrepRequestYN").attr("value", "");
								$("#qBoxBtn16N").addClass("buttonYN");
								$("#qBoxBtn16N").removeClass("buttonYN1");
							}
						})
						$("#qBoxBtn17").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
						})
						$("#qBoxBtn18").click(function () {
							$('html, body').animate({ scrollTop: qBoxLocation19 }, 400);
						})
					})

					// 숨긴 확인키 나오게 하기
					$(function () {
						$("input[name='writerCategory']").keyup(function () {
							if ($("input[name='writerCategory']").val() != "") {
								$("#qBoxBtn1").css("opacity", 1);
							}
							else {
								$("#qBoxBtn1").css("opacity", 0);
							}
						})
						$("input[name='writerAddress']").keyup(function () {
							if ($("input[name='writerAddress']").val() != "") {
								$("#qBoxBtn2").css("opacity", 1);
							}
							else {
								$("#qBoxBtn2").css("opacity", 0);
							}
						})
						$("textarea[name='writerReason']").keyup(function () {
							if ($("textarea[name='writerReason']").val() != "") {
								$("#qBoxBtn3").css("opacity", 1);
							}
							else {
								$("#qBoxBtn3").css("opacity", 0);
							}
						})
						$("input[name='writerWishMonth']").keyup(function () {
							if ($("input[name='writerWishMonth']").val() != "") {
								$("#qBoxBtn7").css("opacity", 1);
							}
							else {
								$("#qBoxBtn7").css("opacity", 0);
							}
						})
						$("input[name='writerClassName']").keyup(function () {
							if ($("input[name='writerClassName']").val() != "") {
								$("#qBoxBtn9").css("opacity", 1);
							}
							else {
								$("#qBoxBtn9").css("opacity", 0);
							}
						})
						$("textarea[name='writerClassSelectReason']").keyup(function () {
							if ($("textarea[name='writerClassSelectReason']").val() != "") {
								$("#qBoxBtn10").css("opacity", 1);
							}
							else {
								$("#qBoxBtn10").css("opacity", 0);
							}
						})
						$("input[name='writerProductTime']").keyup(function () {
							if ($("input[name='writerProductTime']").val() != "") {
								$("#qBoxBtn12").css("opacity", 1);
							}
							else {
								$("#qBoxBtn12").css("opacity", 0);
							}
						})
						$("textarea[name='writerClassKitWarningPoint']").keyup(function () {
							if ($("textarea[name='writerClassKitWarningPoint']").val() != "") {
								$("#qBoxBtn13").css("opacity", 1);
							}
							else {
								$("#qBoxBtn13").css("opacity", 0);
							}
						})
						$("textarea[name='writerClassKitPart']").keyup(function () {
							if ($("textarea[name='writerClassKitPart']").val() != "") {
								$("#qBoxBtn14").css("opacity", 1);
							}
							else {
								$("#qBoxBtn14").css("opacity", 0);
							}
						})
						$("textarea[name='writerFinalPoint']").keyup(function () {
							if ($("textarea[name='writerFinalPoint']").val() != "") {
								$("#qBoxBtn17").css("opacity", 1);
							}
							else {
								$("#qBoxBtn17").css("opacity", 0);
							}
						})
					})

					// footer 답변 완료 count 하기, 미리보기 제출 div 열기
					$(window).scroll(function () {
						var qCount = 2;
						if ($("input[name='writerCategory']").val() != "") {
							qCount++;
						}
						if ($("input[name='writerAddress']").val() != "") {
							qCount++;
						}
						if ($("textarea[name='writerReason']").val() != "") {
							qCount++;
						}
						if ($("#writerScheduleYN").val() != "") {
							qCount++;
						}
						if ($("#writerContractYN").val() != "") {
							qCount++;
						}
						if ($("input[name='writerWishMonth']").val() != "") {
							qCount++;
						}
						if ($("input[name='writerClassName']").val() != "") {
							qCount++;
						}
						if ($("textarea[name='writerClassSelectReason']").val() != "") {
							qCount++;
						}
						if ($("input[name='writerClassLevel']").val() != "") {
							qCount++;
						}
						if ($("input[name='writerProductTime']").val() != "") {
							qCount++;
						}
						if ($("textarea[name='writerClassKitWarningPoint']").val() != "") {
							qCount++;
						}
						if ($("textarea[name='writerClassKitPart']").val() != "") {
							qCount++;
						}
						if ($("input[name='writerPrepRequestYN']").val() != "") {
							qCount++;
						}
						if ($("textarea[name='writerFinalPoint']").val() != "") {
							qCount++;
						}
						var linePercent = qCount / 16 * 100;
						$(".commitLine").css("width", linePercent + "%");
						$("#commitContentNum").html(qCount);
						if (qCount == 16) {
							$(".wEnrollFooter").css("height", "120px");
							setTimeout(function () {
								$(".wEnrollFooterBox1").css("display", "flex");
							}, 400)

							// footer 자동고정하기
							// console.log($(window).scrollTop());
							if ($(window).scrollTop() >= qBoxLocation19 - 352) {
								$(".wEnrollFooter").css("position", "static");
							}
							else {
								$(".wEnrollFooter").css("position", "fixed");
							}
						}
						else {
							$(".wEnrollFooter").css("height", "80px");
							$(".wEnrollFooterBox1").css("display", "none");

							// footer 자동고정하기
							if ($(window).scrollTop() >= qBoxLocation19 - 447) {
								$(".wEnrollFooter").css("position", "static");
							}
							else {
								$(".wEnrollFooter").css("position", "fixed");
							}
						}
					})

					// footer 미답변 바로가기 버튼 기능 구현
					$("#commitNoBtn").click(function () {
						if ($("input[name='writerCategory']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation1 }, 400);
						}
						else if ($("input[name='writerAddress']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation2 }, 400);
						}
						else if ($("textarea[name='writerReason']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation3 }, 400);
						}
						else if ($("input[name='writerMajorImgfileOriginal']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation4 }, 400);
						}
						else if ($("#writerScheduleYN").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation5 }, 400);
						}
						else if ($(".writerContractYN").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation6 }, 400);
						}
						else if ($("input[name='writerWishMonth']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation7 }, 400);
						}
						else if ($("input[name='writerClassName']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation9 }, 400);
						}
						else if ($("textarea[name='writerClassSelectReason']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation10 }, 400);
						}
						else if ($("input[name='writerClassLevel']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation11 }, 400);
						}
						else if ($("input[name='writerProductTime']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation12 }, 400);
						}
						else if ($("textarea[name='writerClassKitWarningPoint']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation13 }, 400);
						}
						else if ($("textarea[name='writerClassKitPart']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation14 }, 400);
						}
						else if ($("input[name='classImgfileOriginal']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation15 }, 400);
						}
						else if ($("input[name='writerPrepRequestYN']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation16 }, 400);
						}
						else if ($("textarea[name='writerFinalPoint']").val() == "") {
							$('html, body').animate({ scrollTop: qBoxLocation17 }, 400);
						}
						else {
							$('html, body').animate({ scrollTop: qBoxLocation18 }, 400);
						}
					})

					//스크롤에 따라 포커스 변경
					$(window).scroll(function () {
						if ($(window).scrollTop() < qBoxLocation2 - 1) {
							$("input[name='writerCategory']").focus();
						}
						if (qBoxLocation2 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation3 - 1) {
							$("input[name='writerAddress']").focus();
						}
						if (qBoxLocation3 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation4 - 1) {
							$("textarea[name='writerReason']").focus();
						}
						if (qBoxLocation4 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation5 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation5 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation51 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation51 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation6 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation6 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation7 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation7 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation8 - 1) {
							$("input[name='writerWishMonth']").focus();
						}
						if (qBoxLocation8 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation9 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation9 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation10 - 1) {
							$("input[name='writerClassName']").focus();
						}
						if (qBoxLocation10 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation11 - 1) {
							$("textarea[name='writerClassSelectReason']").focus();
						}
						if (qBoxLocation11 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation12 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation12 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation13 - 1) {
							$("input[name='writerProductTime']").focus();
						}
						if (qBoxLocation13 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation14 - 1) {
							$("textarea[name='writerClassKitWarningPoint']").focus();
						}
						if (qBoxLocation14 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation15 - 1) {
							$("textarea[name='writerClassKitPart']").focus();
						}
						if (qBoxLocation15 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation16 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation16 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation17 - 1) {
							$(':focus').blur();
						}
						if (qBoxLocation17 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation18 - 1) {
							$("textarea[name='writerFinalPoint']").focus();
						}
						if (qBoxLocation18 - 1 < $(window).scrollTop() && $(window).scrollTop() <= qBoxLocation19 - 1) {
							$(':focus').blur();
						}
					})

				</script>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>