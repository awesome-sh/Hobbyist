<%@page import="java.sql.Date"%>
<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
	Notice notice = (Notice)request.getAttribute("notice");
	Date cuTime = (Date)request.getAttribute("cuTime");
%>

<%@ include file="/views/common/header.jsp" %>


<script>
	/* 로그인 안된 상태로 왔을때 접근 막기  */
	if (<%= logginMember != null && logginMember.getMemberEmail().equals("admin") %>) {

	} else {
		alert('관리자만 접근 가능합니다');
		location.href = history.back(-1);
	}
	
	
	// 기존 자료 넣기
	$(function () {

		// 공지 분류 체크
		var nModifySort = "<%= notice.getNoticeSort() %>";
		if (nModifySort == 'sortNotice') {

			//공지관련된 세부내용이 없음
			// $(".noticeWriteSortNotice>div").css({"min-height":"50px","display":"flex"});
			$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
			$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });

			$("#sortEvent").removeClass("sortBtn1");
			$("#sortWriterEnroll").removeClass("sortBtn1");
			$("#sortEvent").addClass("sortBtn");
			$("#sortWriterEnroll").addClass("sortBtn");

			$("#sortNotice").removeClass("sortBtn");
			$("#sortNotice").addClass("sortBtn1");

			$("#noticeSort").val("");
			$("#noticeSort").val("sortNotice");

			$("#sortEvent").css("display","none");
			$("#sortWriterEnroll").css("display","none");
		}
		else if (nModifySort == 'sortEvent') {
			$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
			$(".noticeWriteSortEvent>div").css({ "min-height": "50px", "display": "flex" });
			$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });

			$("#sortNotice").removeClass("sortBtn1");
			$("#sortWriterEnroll").removeClass("sortBtn1");
			$("#sortNotice").addClass("sortBtn");
			$("#sortWriterEnroll").addClass("sortBtn");

			$("#sortEvent").removeClass("sortBtn");
			$("#sortEvent").addClass("sortBtn1");

			$("#noticeSort").val("");
			$("#noticeSort").val("sortEvent");

			$("#sortNotice").css("display","none");
			$("#sortWriterEnroll").css("display","none");
		}
		else if (nModifySort == 'sortWriterEnroll') {
			$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
			$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
			$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "50px", "display": "flex" });

			$("#sortNotice").removeClass("sortBtn1");
			$("#sortEvent").removeClass("sortBtn1");
			$("#sortNotice").addClass("sortBtn");
			$("#sortEvent").addClass("sortBtn");

			$("#sortWriterEnroll").removeClass("sortBtn");
			$("#sortWriterEnroll").addClass("sortBtn1");

			$("#noticeSort").val("");
			$("#noticeSort").val("sortWriterEnroll");

			$("#sortNotice").css("display","none");
			$("#sortEvent").css("display","none");
		}
	})
</script>

<section id="noticeModify">
	<div class="noticeWriteBox">
		<form action="<%= request.getContextPath() %>/notice/noticeModifyEnd" method="post" id="noticeModifyFrm" autocomplete="off" enctype="multipart/form-data">
			<div class="noticeWrite_top">
				<ul>
					<li onclick="location.href='<%= request.getContextPath() %>/notice/noticeList'">
						<img src="<%= request.getContextPath() %>/images/back.png" width="18px">
					</li>
					<li>
						공지사항 수정하기
					</li>
				</ul>
			</div><br>
			<div class="noticeWrite_Mid">
				<div class="noticeWriteDate noticeWriteRow">
					<div class="noticeWriteAttr">공지 날짜</div>
					<div class="noticeWriteVal">
						<input type="radio" name="noticeDate" id="noticeDate1" value="<%= notice.getNoticeDate() %>" checked/>기존날짜
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="noticeDate" id="noticeDate2" value="noData" />현재날짜
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="noticeDate" id="noticeDate3"/>
						<input type="date" id="noticeDate3_1" min="<%= cuTime %>"/>
					</div>
				</div>
				<div class="noticeWriteSort noticeWriteRow">
					<div class="noticeWriteAttr">분류</div>
					<div class="noticeWriteVal">
						<input type="hidden" id="noticeSort" name="noticeSort" />
						<div id="sortNotice" onclick="fn_sortBtn(this)" class="sortBtn">공지</div>
						<div id="sortEvent" onclick="fn_sortBtn(this)" class="sortBtn">이벤트</div>
						<div id="sortWriterEnroll" onclick="fn_sortBtn(this)" class="sortBtn">작가신청</div>
					</div>
				</div>

				<div class="noticeWriteSortNotice noticeWriteRow">
					<div class="noticeWriteAttr">세부설정</div>
					<div class="noticeWriteVal"></div>
				</div>
				<div class="noticeWriteSortEvent noticeWriteRow">
<!-- 					<div class="noticeWriteAttr">세부설정</div>
					<div class="noticeWriteVal">
						<div>
							<div>
								시작날짜
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="date" name="eventNoticeStartdate" />
							</div>
							<div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								종료날짜
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="date" name="eventNoticeEnddate" />
							</div>
						</div>
 						<div>
							<div>쿠폰 발급갯수</div>
							<div class="quantity">
								<input type="number" name="eventNoticeCouponNum" min="0" step="10" value="10" />
							</div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div>쿠폰 할인율 </div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<div class="quantity">
								<input type="number" name="eventNoticeCouponDc" min="5" max="50" step="5" value="5" />
							</div>
						</div>
					</div> -->
				</div>
				<div class="noticeWriteSortWriterEnroll noticeWriteRow">
<!-- 					<div class="noticeWriteAttr">세부설정</div>
					<div class="noticeWriteVal">
						<div>
							시작날짜
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="date" name="wENoticeStartdate" />
						</div>
						<div>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							종료날짜
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="date" name="wENoticeStartdate" />
						</div>
					</div> -->
				</div>

				<div class="noticeWriteTitle noticeWriteRow">
					<div class="noticeWriteAttr">제목</div>
					<div class="noticeWriteVal">
						<input name="noticeTitle" class="inputText" placeholder="제목을 입력하세요." value="<%= notice.getNoticeTitle() %>"/>
					</div>
				</div>
				<div class="noticeWriteWriter noticeWriteRow">
					<div class="noticeWriteAttr">작성자</div>
					<div class="noticeWriteVal"><%= logginMember.getMemberNickname() %></div>
				</div>
				<div class="noticeWriteImg noticeWriteRow">
					<div class="noticeWriteAttr">메인 이미지</div>
					<div class="noticeWriteVal">
						<div class="inputImageBox">
							<div class="inputImageback">
								<div class="inputImage">
								<% if(notice.getNoticeImgnameRenamed()==null) { %>
									<img src="<%= request.getContextPath() %>/images/uploadIcon.png" width="100px" height="100px"/>
									<div class="imageTitle"></div>
								<% } else { %>
									<img src="<%= request.getContextPath() %>/upload/notice/<%= notice.getNoticeImgnameRenamed() %>" width="300px" height="200px" />
									<div class="imageTitle"><%= notice.getNoticeImgnameOriginal() %></div>
								<% } %>
								</div>
								<input type="file" name="noticeImgnameOriginal" class="inputImageFile" value="<%= notice.getNoticeImgnameRenamed() %>"/>
							</div>
							<button type="button" class="fileImgDelBtn" onclick="fn_fileImgDelBtn()">x</button>
							<input type="hidden" name="imgDelFlag"/>
						</div>
						<script>
							$(function () {
								$("input[name='noticeImgnameOriginal']").change(function (event) {
									$(".inputImage img").attr("src", URL.createObjectURL(event.target.files[0]));
									$(".inputImage img").css({"width":"300px","height":"200px"});
									var fileName = $("input[name='noticeImgnameOriginal']").val();
									$(".imageTitle").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
								})
							})
							function fn_fileImgDelBtn() {
								$("input[name='imgDelFlag']").val("true");
								$(".inputImage img").attr("src", "<%= request.getContextPath() %>/images/uploadIcon.png");
								$(".inputImage img").css({"width":"100px","height":"100px"});
								$("input[name='noticeImgnameOriginal']").val("");
								$(".imageTitle").text("");
							}
						</script>
					</div>
				</div>
				<div class="noticeWriteContent noticeWriteRow">
					<div class="noticeWriteAttr">내용</div>
					<div class="noticeWriteVal">
						<textarea id="noticeContent_editor" name="noticeContent"><%= notice.getNoticeContent() %></textarea>
					</div>
				</div>

				<div class="noticeWriteFile noticeWriteRow">
					<div class="noticeWriteAttr">첨부파일</div>
					<div class="noticeWriteVal">
						<!--button-->
						<div class="upload-btn_wrap">
							<button type="button" title="파일찾기">
								<span>파일찾기</span>
							</button>
							<input type="file" name="noticeFilenameOriginal" class="input_file" title="파일찾기" value="<%= notice.getNoticeFilenameRenamed() %>">
						</div>
						<!--input box-->
						<input type="text" class="upload_text" readonly="readonly" value="<%= notice.getNoticeFilenameOriginal()==null?"":notice.getNoticeFilenameOriginal() %>">

						<button type="button" class="fileDelBtn" onclick="fn_fileDelBtn()">x</button>
						<input type="hidden" name="fileDelFlag"/>
						<script>
							function fn_fileDelBtn() {
								$("input[name='fileDelFlag']").val("true");
								$("input[name='noticeFilenameOriginal']").val("");
								$(".upload_text").val("파일명");
							}
						</script>
					</div>
				</div>
			</div>
			<div class="noticeWrite_bottom">
				<button type="button" class="button1" onclick="fn_noticeModify()">수정하기</button>
			</div>
			
			<!-- 서버에서 불러온 파일 저장 -->
			<input type="hidden" name="noticeNo" value="<%= notice.getNoticeNo() %>"/>
			<input type="hidden" name="old_nDate" value="<%= notice.getNoticeDate() %>"/>
			<input type="hidden" name="old_nFileO" value="<%= notice.getNoticeFilenameOriginal() %>"/>
			<input type="hidden" name="old_nFileR" value="<%= notice.getNoticeFilenameRenamed() %>"/>
			<input type="hidden" name="old_nImgO" value="<%= notice.getNoticeImgnameOriginal() %>"/>
			<input type="hidden" name="old_nImgR" value="<%= notice.getNoticeImgnameRenamed() %>"/>
			
		</form>
	</div>

	<script>
		
		// 공지날짜 값 넣기
		$(function() {
			var selectLoc = $("#noticeDate3");
			$($("#noticeDate3_1")).change(function() {
				selectLoc.val($(this).val());
				selectLoc.attr("checked","checked");
			})
		})


		function fn_noticeModify() {
			$("#noticeModifyFrm").submit();
		}
		
		// input number 기능 구현
		jQuery('<div class="quantity-nav"><div class="quantity-button quantity-up">+</div><div class="quantity-button quantity-down">-</div></div>').insertAfter('.quantity input');
		jQuery('.quantity').each(function () {
			var spinner = jQuery(this),
				input = spinner.find('input[type="number"]'),
				btnUp = spinner.find('.quantity-up'),
				btnDown = spinner.find('.quantity-down'),
				min = input.attr('min'),
				max = input.attr('max'),
				step = parseFloat(input.attr('step'));

			btnUp.click(function () {
				var oldValue = parseFloat(input.val());
				if (oldValue >= max) {
					var newVal = oldValue;
				} else {
					var newVal = oldValue + step;
				}
				spinner.find("input").val(newVal);
				spinner.find("input").trigger("change");
			});
			btnDown.click(function () {
				var oldValue = parseFloat(input.val());
				if (oldValue <= min) {
					var newVal = oldValue;
				} else {
					var newVal = oldValue - step;
				}
				spinner.find("input").val(newVal);
				spinner.find("input").trigger("change");
			});
		});

		//sortBtn 기능 구현
		function fn_sortBtn(e) {
			if (e.innerText == '공지') {

				//공지관련된 세부내용이 없음
				// $(".noticeWriteSortNotice>div").css({"min-height":"50px","display":"flex"});
				$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
				$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });

				$("#sortEvent").removeClass("sortBtn1");
				$("#sortWriterEnroll").removeClass("sortBtn1");
				$("#sortEvent").addClass("sortBtn");
				$("#sortWriterEnroll").addClass("sortBtn");

				$("#sortNotice").removeClass("sortBtn");
				$("#sortNotice").addClass("sortBtn1");

				$("#noticeSort").val("");
				$("#noticeSort").val("sortNotice");
			}
			else if (e.innerText == '이벤트') {
				$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
				$(".noticeWriteSortEvent>div").css({ "min-height": "50px", "display": "flex" });
				$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });

				$("#sortNotice").removeClass("sortBtn1");
				$("#sortWriterEnroll").removeClass("sortBtn1");
				$("#sortNotice").addClass("sortBtn");
				$("#sortWriterEnroll").addClass("sortBtn");

				$("#sortEvent").removeClass("sortBtn");
				$("#sortEvent").addClass("sortBtn1");
				
				$("#noticeSort").val("");
				$("#noticeSort").val("sortEvent");
			}
			else if (e.innerText == '작가신청') {
				$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
				$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
				$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "50px", "display": "flex" });

				$("#sortNotice").removeClass("sortBtn1");
				$("#sortEvent").removeClass("sortBtn1");
				$("#sortNotice").addClass("sortBtn");
				$("#sortEvent").addClass("sortBtn");

				$("#sortWriterEnroll").removeClass("sortBtn");
				$("#sortWriterEnroll").addClass("sortBtn1");

				$("#noticeSort").val("");
				$("#noticeSort").val("sortWriterEnroll");
			}
		}

		$(window).scroll(function () {
			var target = $('.noticeWrite_top');
			var scrollTo = $(window).scrollTop();
			if (scrollTo != 0) {
				$('.noticeWrite_top').css("top", "97px");
			} else {
				$('.noticeWrite_top').css("top", "213px");
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

		//textarea css 가져오기
		CKEDITOR.replace('noticeContent_editor');
		CKEDITOR.add

		// file input 기능 구현	
		$(function () {
			$('.input_file').change(function () {
				var fileName = $(this).val();
				$('.upload_text').val(fileName.substring(fileName.lastIndexOf('\\') + 1));
			});
		});
	</script>
</section>

<%@ include file="/views/common/footer.jsp" %>