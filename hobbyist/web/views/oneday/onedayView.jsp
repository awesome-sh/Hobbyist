<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Oneday" %>

<%
	Oneday oneday = (Oneday)request.getAttribute("Oneday");
%>

<%@ include file="/views/common/header.jsp" %>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<section id="onedayView">
	<div class="onedayView_content">
	<input type="hidden" id="hidden_member" value="<%= logginMember!=null ? logginMember.getMemberEmail() : "a" %>"/>
		<div class="view_right">
			<div class="view_top_info">작가명 :
				<%= oneday.getOnedayWriter() %> | 클래스코드 :
				<%= oneday.getOnedayNo() %>
				<Br>
			</div>
			<div class="view_top_left">
			<b><%= oneday.getOnedayName() %></b><Br><br><%= oneday.getOnedayInfo() %><br>
			<img src="<%=request.getContextPath()%>/upload/oneday/images/<%= oneday.getOnedayImage1() %>"
				 width="400px"></div>
			<div class="view_top_right">
				<table>
					<tr>
						<td colspan="2">
							<br>
						</td>
					</tr>
					<tr>
						<td>장소/시간</td>
						<td class="tdclass"><%= oneday.getOnedayAddress() %></td>
					</tr>
					<tr>
						<td>예약</td>
						<td class="tdclass">
							<input type="hidden" id="reservationStatus" value="<%= oneday.getOnedayReservationStatus() %>">
							예약현황<%= oneday.getOnedayCurrentPeople() %>/<b><%= oneday.getOnedayPeople() %></b> (명)
							<%= oneday.getOnedayReservationStatus().equals("Y") ?  "[예약가능]" : "[예약불가]" %>
						</td>
					</tr>
					<tr>
						<td>클래스 비용</td>
						<td class="tdclass">
							<%= oneday.getOnedayPrice() %>원</td>
					</tr>
					<tr>
						<td>클래스 옵션</td>
						<td class="tdclass">
						<select name="class_option" id="class_option">
							<option>선택</option>
							<option><%= oneday.getOnedayOption1() %></option>
							<option><%= oneday.getOnedayOption2() %></option>
							<option><%= oneday.getOnedayOption3() %></option>
							<option><%= oneday.getOnedayOption4() %></option>
							<option><%= oneday.getOnedayOption5() %></option>
						</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" style="height: 50px;">옵션을 선택하지 않으시면,<br> 랜덤(색상 및 스타일)키트가 준비됩니다</td>
					</tr>
					<tr>
					<td colspan="2">
					<button class="api" id="kakao-link-btn" onclick="javascript:;"></button> 
					<button class="api" onclick="fn_shortUrl()"></button>
				</td>
				<script>
					function fn_shortUrl() {
						// 현재 페이지 주소 변수에 담아링~
						var CurrentUrl = window.location.href;
						// Ajax로 짧은글주소 서블릿에 현재주소를 넘겨줌
						$.ajax({
							url: '<%=request.getContextPath()%>/shop/shopShortUrl?urlText=' + CurrentUrl,
							dataType: 'json',
							success: function (data) {
								console.log(data);
								window.prompt("짧은글 주소로 변환되었습니다. \nCtrl + C 복사해서 사용하세요 \n ",  data.result['url']);
							}
						})
					}
				</script>
				<script type='text/javascript'>
							    Kakao.init('90bce1ec4b05bd5a83a7026725712442');
							    Kakao.Link.createDefaultButton({
							      container: '#kakao-link-btn',
							      objectType: 'feed',
							      content: {
							        title: '<%= oneday.getOnedayName() %>',
							        description: '<%= oneday.getOnedayInfo() %>',
							        imageUrl: '<%= request.getContextPath() %>/upload/oneday/images/<%= oneday.getOnedayImage1() %>',
							        link: {
							          mobileWebUrl: '<%= request.getContextPath() %>/oneday/onedayView?no=<%= oneday.getOnedayNo() %>' ,
							          webUrl: '<%= request.getContextPath() %>/oneday/onedayView?no=<%= oneday.getOnedayNo() %>'
							        }
							      }
							    });
				</script>
					</tr>
					<tr>
						<td colspan="2">
							<button class="addcart-btn" onclick="fn_addCart()">취미바구니 담기 </button>
							<script>
								function fn_addCart() {
									var classOp = $('#class_option').val();
									if(classOp=='선택') {
										alert('옵션을 선택하셔야 합니다!');
									} else {
										if(<%= logginMember != null %>) {
											if($('#reservationStatus').val()=='Y') {
												
												$.ajax({
													url: '<%=request.getContextPath()%>/myCartOnedayInsert?member=<%= logginMember != null? logginMember.getMemberEmail() : 'a'%>&classNo=<%=oneday.getOnedayNo()%>&cartCate=oneday&cartOption=' + classOp,
													success: function (data) {
														if(confirm(data)){
															location.href='<%=request.getContextPath()%>/myCart?member=<%= logginMember != null? logginMember.getMemberEmail() : 'a'%>';
														} else {
															location.href='<%=request.getContextPath()%>/oneday/onedayView?no=<%= oneday.getOnedayNo()%>';
														}
													}
												});
											} else {
												alert('예약정원 초과! 예약이 불가능합니다');
											}
										} else {
											alert('로그인 후 취미바구니 이용이 가능합니다');
										}
									}
								}
							</script>
						</td>
					</tr>
				</table>
			</div>
			<br><br>
			<!-- 중간 고정메뉴 -->
			<div class="view_menu" id="view_menu">
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/oneday/onedayList';">←다른 원데이클래스 보기</li>
					<li id="sub1" onclick="location.href='#';">예약하기</li>
					<li id="sub2" onclick="location.href='#about';">원데이클래스 소개</li>
					<li id="sub3" onclick="location.href='#policy';">예약안내</li>
				</ul>
			</div>
			
			<!-- 상세내용시작 -->
			<div id="about">
				<%= oneday.getOnedayContent() %>
			</div>
			
			<div id="policy">
					<%= oneday.getOnedayPolicy() %>
			</div>
		</div>
	</div>
</section>


<script>
	var sub1 = $('#sub1');
	var sub2 = $('#sub2');
	var sub3 = $('#sub3');

	var about = $('#about').offset().top - 100;
	var policy = $('#policy').offset().top;

	$(window).scroll(function() {
		var scrollTo = $(document).scrollTop();
		if (scrollTo > 400) {
			$('#view_menu').css({
				"z-index" : "9999",
				"top" : "190px",
				"position" : "fixed"
			});
		} else {
			$('#view_menu').css({
				"z-index" : "",
				"top" : "",
				"position" : ""
			});
		}

		if (scrollTo < about) {
			sub1.css({
				"background-color" : "#575b69",
				"color" : "#fff"
			});
			sub2.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub3.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		} else if (scrollTo >= policy - 100) {
			sub1.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub2.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub3.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		}
	})
	
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

<%@ include file="/views/common/footer.jsp" %>