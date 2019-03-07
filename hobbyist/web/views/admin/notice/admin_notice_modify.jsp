<%@page import="com.hobbyist.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<%
	Date cuTime = (Date)request.getAttribute("cuTime");
%>


<div id="defaultModifyModal" class="defaultViewModal">
	<div class="defaultViewModalBox">
		<button type="button" class="defaultViewClose">X</button>
		<div class="defaultViewHeader">
			<div>
			</div>
		</div>
		<div class="defaultViewBody" id="noticeDelListModify">
			
		<div class="noticeWriteBox">
		<form action="" method="post" id="noticeModifyFrm" autocomplete="off" enctype="multipart/form-data">
			<div class="noticeWrite_Mid">
				<div class="noticeWriteDate noticeWriteRow">
					<div class="noticeWriteAttr">공지 날짜</div>
					<div class="noticeWriteVal">
						<input type="radio" name="noticeDate" id="noticeDate1" value="oldDate" checked/>기존날짜
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
						<div id="sortNotice" class="sortBtn">공지</div>
						<div id="sortEvent" class="sortBtn">이벤트</div>
						<div id="sortWriterEnroll" class="sortBtn">작가신청</div>
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
						<input name="noticeTitle" class="inputText" placeholder="제목을 입력하세요." value=""/>
					</div>
				</div>
				<div class="noticeWriteWriter noticeWriteRow">
					<div class="noticeWriteAttr">작성자</div>
					<div class="noticeWriteVal"></div>
				</div>
				<div class="noticeWriteImg noticeWriteRow">
					<div class="noticeWriteAttr">메인 이미지</div>
					<div class="noticeWriteVal">
						<div class="inputImageBox">
							<div class="inputImageback">
								<div class="inputImage">

									<img src="<%= request.getContextPath() %>/images/uploadIcon.png" width="100px" height="100px"/>
									<div class="imageTitle"></div>

								</div>
								<input type="file" name="noticeImgnameOriginal" class="inputImageFile" value=""/>
							</div>
							<button type="button" class="fileImgDelBtn" onclick="fn_fileImgDelBtn()">x</button>
							<input type="hidden" name="imgDelFlag"/>
						</div>
						<script>
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
						<div id='nWModifyContent' style='vertical-align: top;width: 100%;height: 100%;'></div>
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
							<input type="file" name="noticeFilenameOriginal" class="input_file" title="파일찾기" value="">
						</div>
						<!--input box-->
						<input type="text" class="upload_text" readonly="readonly" value=""/>

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
			
			<!-- 서버에서 불러온 파일 저장 -->
			<input type="hidden" name="noticeNo" value=""/>
			<input type="hidden" name="old_nDate" value=""/>
			<input type="hidden" name="old_nFileO" value=""/>
			<input type="hidden" name="old_nFileR" value=""/>
			<input type="hidden" name="old_nImgO" value=""/>
			<input type="hidden" name="old_nImgR" value=""/>
			
		</form>
	</div>

		
			
			
		</div>
		<div class="defaultViewfooter">
			<div>
				<button type="button" class="button1" id="defaultViewfooterSave">완료</button>
				<button type="button" class="button1" id="defaultViewfooterExit">이전</button>
			</div>
		</div>
	</div>
</div>
<script>

	function fn_noticeListMoEnd(index) {
			$("#noticeModifyFrm").submit();	
	}
	
	function fn_noticeListMoExit(index) {
		$(".defaultViewModal").css("display", "none");
	}

	
	// 수정하기 버튼 ajax로 불러오기
	function fn_noticeListMo(index) {
		$.ajax({
			url: '<%= request.getContextPath() %>/notice/noticeDelListModifyAjax.do',
			type: 'POST',
			dataType: 'JSON',
			data: { "noticeNo": index },
			success: function (data) {
				if(data["noticeSort"]=="sortNotice") {
					
					$("#sortNotice").removeClass("sortBtn");
					$("#sortNotice").addClass("sortBtn1");
					
					$("#sortNotice").css("display","inline-block");
					$("#sortEvent").css("display","none");
					$("#sortWriterEnroll").css("display","none");
					
					$("input[name='noticeSort']").val(data["noticeSort"]);
					
					$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
					$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });
				}
				else if(data["noticeSort"]=="sortEvent") {

					$("#sortEvent").removeClass("sortBtn");
					$("#sortEvent").addClass("sortBtn1");
					
					$("#sortNotice").css("display","none");
					$("#sortEvent").css("display","inline-block");
					$("#sortWriterEnroll").css("display","none");
					
					$("input[name='noticeSort']").val(data["noticeSort"]);
					
					$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
					$(".noticeWriteSortEvent>div").css({ "min-height": "50px", "display": "flex" });
					$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "0px", "display": "none" });
				}
				else if(data["noticeSort"]=="sortWriterEnroll") {

					$("#sortWriterEnroll").removeClass("sortBtn");
					$("#sortWriterEnroll").addClass("sortBtn1");
					
					$("#sortNotice").css("display","none");
					$("#sortEvent").css("display","none");
					$("#sortWriterEnroll").css("display","inline-block");
					
					$("input[name='noticeSort']").val(data["noticeSort"]);
					
					$(".noticeWriteSortNotice>div").css({ "min-height": "0px", "display": "none" });
					$(".noticeWriteSortEvent>div").css({ "min-height": "0px", "display": "none" });
					$(".noticeWriteSortWriterEnroll>div").css({ "min-height": "50px", "display": "flex" });
				}
				
				//제목 담기
				$("input[name='noticeTitle']").val(data["noticeTitle"]);

				//작성자 담기
				$(".noticeWriteWriter .noticeWriteVal").html(data["noticeWriter"]);

				//내용 담기
				$("#nWModifyContent").html(data["noticeContent"]);
				$("#noticeContent_editor").html(data["noticeContent"]);
				$("#noticeContent_editor").css("display","none");

				//이미지 넣기
				if(data["noticeImgnameRenamed"]!=null) {
					$(".inputImage img").attr("src","<%= request.getContextPath() %>/upload/notice/" + data["noticeImgnameRenamed"]);
					$(".inputImage img").css({"width":"300px","height":"200px"});
				}

				$(".imageTitle").html(data["noticeImgnameOriginal"]);
				$("input[name='noticeImgnameOriginal']").attr("value",data["noticeImgnameRenamed"]);
				
				//파일 넣기
				$("input[name='noticeFilenameOriginal']").attr("value",data["noticeFilenameRenamed"]);
				$(".upload_text").val(data["noticeFilenameOriginal"]);
				
				//hidden 변수 값 넣기
				$("input[name='noticeNo']").val(data["noticeNo"]);
				$("input[name='old_nDate']").val(data["noticeDate"]);
				$("input[name='old_nFileO']").val(data["noticeFilenameOriginal"]);
				$("input[name='old_nFileR']").val(data["noticeFilenameRenamed"]);
				$("input[name='old_nImgO']").val(data["noticeImgnameOriginal"]);
				$("input[name='old_nImgR']").val(data["noticeImgnameRenamed"]);
				


				$("#defaultViewfooterSave").attr("onclick","fn_noticeListMoEnd("+ index +")");
				$("#defaultViewfooterExit").attr("onclick","fn_noticeListMoExit("+ index +")");
				
				if(adminSelectWindowVal=="del") {
					$("#noticeModifyFrm").attr("action","<%= request.getContextPath() %>/notice/noticeDelListModifyAdminEnd");
				}
				else if (adminSelectWindowVal=="pre") {
					$("#noticeModifyFrm").attr("action","<%= request.getContextPath() %>/notice/noticePreListModifyAdminEnd");
				}
			}
		})
		
		$("#defaultViewModal").css("display", "none");
		$("#defaultModifyModal").css("display", "flex");
		$(".defaultViewBody").animate({ scrollTop: 0 }, 0);
	}

	$(function () {
		$(".defaultViewClose").click(function () {
			$(".defaultViewModal").css("display", "none");
		})
	})


			
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
		
		//메인이미지 기능 관련 
		$(function () {
			$("input[name='noticeImgnameOriginal']").change(function (event) {
				$(".inputImage img").attr("src", URL.createObjectURL(event.target.files[0]));
				$(".inputImage img").css({"width":"300px","height":"200px"});
				var fileName = $("input[name='noticeImgnameOriginal']").val();
				$(".imageTitle").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
			})
		})

		$(function() {
			$(".noticeWriteContent").click(function() {
				$("#nWModifyContent").css("display","none");
				$("#noticeContent_editor").css("display","inline-block");
				//textarea css 가져오기
				CKEDITOR.replace('noticeContent_editor');
				CKEDITOR.add
			})
		})
		
		// file input 기능 구현	
		$(function () {
			$('.input_file').change(function () {
				var fileName = $(this).val();
				$('.upload_text').val(fileName.substring(fileName.lastIndexOf('\\') + 1));
			});
		});
 
</script>