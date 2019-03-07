<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.shop.model.vo.Shop, com.hobbyist.shop.model.vo.Review"%>

<%
	Shop shop = (Shop)request.getAttribute("Shop");
	int reviewCount = (Integer)request.getAttribute("Review");
%>

<%@ include file="/views/common/header.jsp"%>

<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>

<section id="shopView">
	<div class="shopView_content">
		<div class="view_right">
			<div class="view_top_info">
				작가명 :
				<%= shop.getShopWriter() %>
				| 클래스코드 :
				<%= shop.getShopNo() %>
			</div>
			<div class="view_top_left">
				<img
					src="<%=request.getContextPath()%>/upload/shop/images/<%= shop.getShopImage1() %>"
					width="520px">
			</div>
			<div class="view_top_right">
				<table>
					<tr>
						<td colspan="2">
							<h2><%= shop.getShopName() %></h2>
							<p><%= shop.getShopInfo() %></p>
						</td>
					</tr>
					<tr>
						<td colspan="2"><a href="#review">(<%= reviewCount %>개의
								클래스리뷰)
						</a></td>
					</tr>
					<tr>
						<td>가격</td>
						<td class="tdclass"><%= shop.getShopPrice() %>원</td>
					</tr>
					<tr>
						<td>적립마일리지</td>
						<td class="tdclass"><%= shop.getShopPoint() %>점</td>
					</tr>
					<tr>
						<td>배송비</td>
						<td class="tdclass">하비스트 전상품 무료배송</td>
					</tr>
					<tr>
						<td>옵션</td>
						<td class="tdclass">
							<select id="cartOption">
								<option>선택</option>
								<option><%= shop.getShopOption1() %></option>
								<option><%= shop.getShopOption2() %></option>
								<option><%= shop.getShopOption3() %></option>
								<option><%= shop.getShopOption4() %></option>
								<option><%= shop.getShopOption5() %></option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<button class="addcart-btn" onclick="fn_addCart()">
							취미바구니 담기</button>
							<script>
								function fn_addCart() {
									var classOp = $('#cartOption').val();
									if(classOp=='선택') {
										alert('옵션을 선택하셔야 합니다!');
									} else {
									
										if(<%= logginMember != null %>) {
											location.href="<%=request.getContextPath()%>/myCartInsert?member=<%= logginMember != null? logginMember.getMemberEmail() : 'a'%>&classNo=<%=shop.getShopNo()%>&cartCate=classshop&cartOption=" + classOp;
										} else {
											alert('로그인 후 취미바구니 이용이 가능합니다');
										}
									}
								}
							</script>
						</td>
					</tr>
					<tr>
					<td colspan="2">
							<button class="api" id="kakao-link-btn" onclick="javascript:;">
							</button> 
							<button class="api" onclick="fn_shortUrl()">
							</button>
					</td>
							<!-- ------------------ 카카오톡 공유 api ---------------- -->
							<script type='text/javascript'>
							  //<![CDATA[
							    // // 사용할 앱의 JavaScript 키를 설정해 주세요.
							    Kakao.init('90bce1ec4b05bd5a83a7026725712442');
							    // // 카카오링크 버튼을 생성합니다. 처음 한번만 호출하면 됩니다.
							    Kakao.Link.createDefaultButton({
							      container: '#kakao-link-btn',
							      objectType: 'feed',
							      content: {
							        title: '<%= shop.getShopName() %>',
							        description: '<%= shop.getShopInfo() %>',
							        imageUrl: '<%= request.getContextPath() %>/upload/shop/images/<%= shop.getShopImage1() %>',
							        link: {
							          mobileWebUrl: '<%= request.getContextPath() %>/shop/shopView?no=<%= shop.getShopNo() %>' ,
							          webUrl: '<%= request.getContextPath() %>/shop/shopView?no=<%= shop.getShopNo() %>'
							        }
							      }
							    });
							  //]]>
							</script>
					</tr>
				</table>

				<!-- -------------공유/짧은글주소 등 api 를 위한 스크립트	----------->
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
				<!-- -------------공유/짧은글주소 등 api 를 위한 스크립트 끝	----------->

			</div>
			<br>
			<Br>
			<!-- 중간 고정메뉴 -->
			<div class="view_menu" id="view_menu">
				<ul>
					<li
						onclick="location.href='<%=request.getContextPath()%>/shop/shopList';">←
						다른상품보기</li>
					<li id="sub1" onclick="location.href='#';">구매/장바구니</li>
					<li id="sub2" onclick="location.href='#about';">클래스</li>
					<li id="sub3" onclick="location.href='#review';">클래스평 (<%=reviewCount%>)
					</li>
					<li id="sub4" onclick="location.href='#policy';">배송/교환/환불</li>
				</ul>
			</div>

			<!-- 상세내용시작 -->
			<div id="about">
				<%=shop.getShopContent()%>
			</div>
			<div id="review">
				<h3>
					클래스 리뷰 (<%=reviewCount%>)
				</h3>
				<div class="review-container">
					<form id="reviewFrm">
						<table>
							<tr>
								<td rowspan="2" style="width: 200px;"><h3>R E V I E W</h3></td>
								<th style="width: 100px;">별점</th>
								<td style="width: 400px; height: 45px; text-align: left;">
									<input type="radio" id="r1" name="review_count" value="1"><label for="r1">★</label> 
									<input type="radio" id="r2" name="review_count" value="2"><label for="r2">★★</label>
									<input type="radio" id="r3" name="review_count" value="3"><label for="r3">★★★</label>
									<input type="radio" id="r4" name="review_count" value="4"><label for="r4">★★★★</label>
									<input type="radio" id="r5" name="review_count" value="5"><label for="r5">★★★★★</label>
								</td>
								<td rowspan="2" style="width: 250px;"><button type="button"
										onclick="fn_reviewSubmit()">작성</button>
							</tr>
							<tr>
								<th>리뷰내용</th>
								<td style="text-align: left;"><textarea
										name="review_content" id="review_content" placeholder="리뷰내용을 작성해주세요....."
										style="width: 100%; height: 70px;"></textarea></td>
							</tr>
						</table>
						<input type="hidden" name="review_class"
							value="<%=shop.getShopNo()%>" /> <input type="hidden"
							name="review_writer"
							value="<%=logginMember != null ? logginMember.getMemberEmail() : ""%>" />

					</form>
				</div>
				<table id="review_tbl">

				</table>
				<script>
					var rc = $('#review_content');
					
					rc.on('click', function() {
						if(<%=logginMember != null%>) {
							
						} else {
							alert('로그인 후 댓글을 작성하실 수 있습니다')	;
							rc.attr("readonly"," ");
						}
					});
				
				$.ajax({
					url: '<%=request.getContextPath()%>/shop/shopReviewListAjax',
					data: 'review_class=<%=shop.getShopNo()%>',
					success: function (data) {
						$('#review_tbl').html(data);
					}
				})
				
						function fn_reviewSubmit() {
							var rvContnt = $('[name=review_content]');
							if(rvContnt.val().trim().length==0) {
								alert('리뷰내용을 작성해주세요');
								rvContnt.focus();
								return false;
							} else {
								$.ajax({
								url: '<%=request.getContextPath()%>/shop/shopReviewWrite',
								data : $('#reviewFrm').serialize(),
								success: function (data) {
									$('#review_tbl').empty();
									$('#review_tbl').html(data);
								}
								})
							}
							
						}
						function fn_reviewList(pageNo) {
							$.ajax({
								url: '<%=request.getContextPath()%>/shop/shopReviewListAjax',
								data : 'review_class=<%=shop.getShopNo()%>&rPage='+ pageNo,
									success : function(data) {
										$('#review_tbl').empty();
										$('#review_tbl').html(data);
									}
								});
					}
						function fn_reviewDelete(no, rc) {
							if(confirm('정말 삭제하시겠습니까?')) {
								$.ajax({
									url: '<%= request.getContextPath() %>/shop/shopReviewDeleteAjax',
									data : 'no=' + no + '&review_class=' + rc,
									success: function(data) {
										$('#review_tbl').empty();
										$('#review_tbl').html(data);
									}
								})
							} else {
								return;
							}
							
						}
				</script>
			</div>
			<div id="policy">
				<%=shop.getShopPolicy1()%>
				<%=shop.getShopPolicy2()%>
				<%=shop.getShopPolicy3()%>
			</div>
		</div>
	</div>
</section>

<script>
	var sub1 = $('#sub1');
	var sub2 = $('#sub2');
	var sub3 = $('#sub3');
	var sub4 = $('#sub4');

	var about = $('#about').offset().top - 100;
	var review = $('#review').offset().top - 100;
	var policy = $('#policy').offset().top;

	$(window).scroll(function() {
		var scrollTo = $(document).scrollTop();
		if (scrollTo > 400) {
			$('#view_menu').css({
				"z-index" : "9999",
				"top" : "180px",
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
			sub4.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		} else if (scrollTo >= about && scrollTo < review) {
			sub1.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub2.css({
				"background-color" : "#575b69",
				"color" : "#fff"
			});
			sub3.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub4.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
		} else if (scrollTo >= review && scrollTo < policy - 100) {
			sub1.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub2.css({
				"background-color" : "#fff",
				"color" : "#252525"
			});
			sub3.css({
				"background-color" : "#575b69",
				"color" : "#fff"
			});
			sub4.css({
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
			sub4.css({
				"background-color" : "#575b69",
				"color" : "#fff"
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

<%@ include file="/views/common/footer.jsp"%>