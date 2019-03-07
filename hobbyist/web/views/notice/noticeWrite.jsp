<%@page import="java.sql.Date"%>
<%@page import="java.util.List"%>
<%@page import="com.hobbyist.notice.model.vo.WeNotice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<%
	List<WeNotice> wn = (List<WeNotice>)request.getAttribute("wn");
	String cuYear = (String)request.getAttribute("cuYear");
	String cuQu = (String)request.getAttribute("cuQu");
	Date minTime = (Date)request.getAttribute("minTime");
	Date cuTime = (Date)request.getAttribute("cuTime");
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

<section id="noticeWrite">
	<div class="noticeWriteBox">
		<form action="<%= request.getContextPath() %>/notice/noticeInsertEnd" method="post" id="noticeInsertFrm" autocomplete="off" enctype="multipart/form-data">
			<div class="noticeWrite_top">
				<ul>
					<li onclick="location.href='<%= request.getContextPath() %>/notice/noticeList'">
						<img src="<%= request.getContextPath() %>/images/back.png" width="18px">
					</li>
					<li>
						공지사항 글쓰기
					</li>
				</ul>
			</div><br>
			<div class="noticeWrite_Mid">
				<div class="noticeWriteDate noticeWriteRow">
					<div class="noticeWriteAttr">공지 날짜</div>
					<div class="noticeWriteVal">
						<input type="radio" name="noticeDate" id="noticeDate2" value="noData" checked/>바로 공지
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="radio" name="noticeDate" id="noticeDate3"/>
						날짜 지정&nbsp;&nbsp;<input type="date" id="noticeDate3_1" min="<%= cuTime %>"/>
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
					<div class="noticeWriteAttr">세부설정</div>
					<div class="noticeWriteVal">
						<div>
							<div><%= cuYear %> 년도 &nbsp;&nbsp;<%= cuQu %> 차</div>
							<input type="hidden" name="weNoticeYear" value="<%= cuYear %>"/>
							<input type="hidden" name="weNoticeQuarter" value="<%= cuQu %>"/>
						</div>
						<div>
							<div>
								시작날짜
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="date" name="weNoticeStartdate" min="<%= minTime %>" value="<%= minTime %>"/>
							</div>
							<div>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								종료날짜
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="date" name="weNoticeEnddate" min="<%= minTime %>"/>
							</div>
						</div>
					</div>
				</div>

				<div class="noticeWriteTitle noticeWriteRow">
					<div class="noticeWriteAttr">제목</div>
					<div class="noticeWriteVal">
						<input name="noticeTitle" class="inputText" placeholder="제목을 입력하세요." />
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
									<img src="<%= request.getContextPath() %>/images/uploadIcon.png" />
									<div class="imageTitle"></div>
								</div>
								<input type="file" name="noticeImgnameOriginal" class="inputImageFile" />
							</div>
							<button type="button" class="fileImgDelBtn" onclick="fn_fileImgDelBtn()">x</button>
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
						<textarea id="noticeContent_editor" name="noticeContent"></textarea>
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
							<input type="file" name="noticeFilenameOriginal" class="input_file" title="파일찾기">
						</div>
						<!--input box-->
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="text" class="upload_text" readonly="readonly">
						
						<button type="button" class="fileDelBtn" onclick="fn_fileDelBtn()">x</button>
						<script>
							function fn_fileDelBtn() {
								$("input[name='noticeFilenameOriginal']").val("");
								$(".upload_text").val("파일명");
							}
						</script>

					</div>
				</div>
			</div>
			<div class="noticeWrite_bottom">
				<button type="button" class="button1" onclick="fn_noticeInsert()">완료</button>
			</div>
		</form>
	</div>

	<script>
		// 시작 날짜와 종료 날짜 관련 로직
		$(function() {
			$("input[name='weNoticeStartdate']").change(function() {
				console.log($("input[name='weNoticeStartdate']").val());
				$("input[name='weNoticeEnddate']").attr("min",$("input[name='weNoticeStartdate']").val());
			})
		})

		// submit 전 공백 관련 로직
		function fn_noticeInsert() {
			if($("input[name='noticeSort']").val()=="sortNotice") {
				var ckeditor = CKEDITOR.instances['noticeContent_editor'];
				if($("input[name='noticeTitle']").val()=="") {
					alert("제목을 입력하세요.");
					$('html, body').animate({ scrollTop: 0 }, 400);
					$("input[name='noticeTitle']").focus();
				}
				else if(ckeditor.getData()=="") {
					alert("내용을 입력하세요");
					ckeditor.focus();
					$('html, body').animate({ scrollTop: 500 }, 400);
				}
				else {
					$("#noticeInsertFrm").submit();
				}
			}
			else if($("input[name='noticeSort']").val()=="sortEvent") {
				var ckeditor = CKEDITOR.instances['noticeContent_editor'];
				if($("input[name='noticeTitle']").val()=="") {
					alert("제목을 입력하세요.");
					$('html, body').animate({ scrollTop: 0 }, 400);
					$("input[name='noticeTitle']").focus();
				}
				else if(ckeditor.getData()=="") {
					alert("내용을 입력하세요");
					ckeditor.focus();
					$('html, body').animate({ scrollTop: 500 }, 400);

				}
				else {
					$("#noticeInsertFrm").submit();
				}
			}
			else if($("input[name='noticeSort']").val()=="sortWriterEnroll") {
				var ckeditor = CKEDITOR.instances['noticeContent_editor'];
				if($("input[name='noticeTitle']").val()=="") {
					alert("제목을 입력하세요.");
					$('html, body').animate({ scrollTop: 0 }, 400);
					$("input[name='noticeTitle']").focus();
				}
				else if(ckeditor.getData()=="") {
					alert("내용을 입력하세요");
					ckeditor.focus();
					$('html, body').animate({ scrollTop: 500 }, 400);
				}
				else if($("input[name='weNoticeStartdate']").val()==""||$("input[name='weNoticeEnddate']").val()=="") {
					alert("작가신청 기간을 설정하세요");
					$('html, body').animate({ scrollTop: 0 }, 400);
					if($("input[name='weNoticeStartdate']").val()=="") {
						$("input[name='weNoticeStartdate']").focus();
					}
					else if($("input[name='weNoticeEnddate']").val()=="") {
						$("input[name='weNoticeEnddate']").focus();
					}
				}
				else if($("input[name='weNoticeYear']").val()==""||$("input[name='weNoticeQuarter']").val()=="") {
					alert("작가신청 분기를 정하세요");
					$('html, body').animate({ scrollTop: 0 }, 400);
					if($("input[name='weNoticeYear']").val()=="") {
						$("input[name='weNoticeYear']").focus();
						return;
					}
					else if($("input[name='weNoticeQuarter']").val()=="") {
						$("input[name='weNoticeQuarter']").focus();
						return;
					}
				}
				else {
					$("#noticeInsertFrm").submit();
				}
			}
			else {
				alert("공지 분류항목을 선택하세요");
			}
		}
		
		//분류 항목 시작과 동시에 '공지'에 체크되기
		$(function () {
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
		})
		
		// 공지날짜 값 넣기
		$(function() {
			var selectLoc = $("#noticeDate3");
			$($("#noticeDate3_1")).change(function() {
				console.log("??");	
				selectLoc.val($(this).val());
				selectLoc.attr("checked","checked");
			})
		})

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
				$('.noticeWrite_top').css("top", "180px");
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
			$('.upload_text').val('파일명');
			$('.input_file').change(function () {
				var fileName = $(this).val();
				$('.upload_text').val(fileName.substring(fileName.lastIndexOf('\\') + 1));
			});
		});
	</script>
</section>

<%@ include file="/views/common/footer.jsp" %>