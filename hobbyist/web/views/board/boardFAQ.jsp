<%@page import="com.hobbyist.board.model.vo.BoardFAQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	List<BoardFAQ> list = (List<BoardFAQ>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
	
	int secondNum = 0;
	int titleNum = 0;
	int contentNum = 0;
%>

<section id="board">

	<div class="board_content">
	
		<div id="board_left">
			<div id="board_sub">
				<h3>Board Menu</h3>
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardList'" style="cursor: pointer;"> · 자유게시판</li>
					<li style="cursor: pointer;"> · 리뷰/후기</li>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'" style="cursor: pointer;"> · FAQ 자주 묻는 질문</li>
					<li onclick="directQuestion()" style="cursor: pointer;"> · 1:1문의</li>
					<script>
						function directQuestion() {
							<%if(logginMember != null) {%>
							location.href='<%=request.getContextPath()%>/board/boardDirectQuestion?NickName=<%=logginMember.getMemberNickname()%>';
							<%} else {%>
								alert('로그인 후 이용가능합니다.');
							<%}%>
						}
					</script>
				</ul>
			</div>
		</div>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			접<br>
			기<br>
		</div>

		<div id="board_right">
			<h3>COMMUNITY</h3><p>메인 > 커뮤니티 > 자주묻는 질문 (FAQ)</p>
			<form name = searchFrm action="<%=request.getContextPath()%>/board/boardFAQSearch" method="POST">
					<div class="FAQsearchForm">
						<img src="<%=request.getContextPath()%>/images/faq.png">
						<select id="searchType" name="searchType">
							<option value="all" <%="all".equals(searchType)?"selected":""%>>전체검색</option>
							<option value="changeAndCancel" <%="changeAndCancel".equals(searchType)?"selected":""%>>변경/취소</option>
							<option value="depositAndDelivery" <%="depositAndDelivery".equals(searchType)?"selected":""%>>입금/배송</option>
							<option value="exchangeAndReturn" <%="exchangeAndReturn".equals(searchType)?"selected":""%>>교환/반품</option>
							<option value="therInquiries" <%="therInquiries".equals(searchType)?"selected":""%>>기타문의</option>
							<option value="saleAndBenefits" <%="saleAndBenefits".equals(searchType)?"selected":""%>>할인/혜택</option>
							<option value="memberAndBenefits" <%="memberAndBenefits".equals(searchType)?"selected":""%>>회원/혜택</option>
							<option value="orderAndLookup" <%="orderAndLookup".equals(searchType)?"selected":""%>>주문/조회</option>
							<option value="paymentInquiry" <%="paymentInquiry".equals(searchType)?"selected":""%>>결제문의</option>
							<option value="soldOutAndWearing" <%="soldOutAndWearing".equals(searchType)?"selected":""%>>품절/입고</option>
							<option value="contactUs" <%="contactUs".equals(searchType)?"selected":""%>>상품문의</option>
						</select>
						<input type="search" placeholder="Search..." name="searchKeyword" value='<%=searchKeyword%>'/>
						<button type="submit">
							<img src="<%= request.getContextPath() %>/images/search.png" width="70%">
						</button>
					</div>
				<div class="board_top" style="border-bottom: none;">
				
					<div class="sort">
						<div id="all" onclick="sortBtn(this)">전체검색</div>
						<div id="changeAndCancel" onclick="sortBtn(this)">변경/취소</div>
						<div id="depositAndDelivery" onclick="sortBtn(this)">입금/배송</div>
						<div id="exchangeAndReturn" onclick="sortBtn(this)">교환/반품</div>
						<div id="therInquiries" onclick="sortBtn(this)">기타문의</div>
						<div id="saleAndBenefits" onclick="sortBtn(this)">할인/혜택</div>
						<div id="memberAndBenefits" onclick="sortBtn(this)">회원/혜택</div>
						<div id="orderAndLookup" onclick="sortBtn(this)">주문/조회</div>
						<div id="paymentInquiry" onclick="sortBtn(this)">결제문의</div>
						<div id="soldOutAndWearing" onclick="sortBtn(this)">품절/입고</div>
						<div id="contactUs" onclick="sortBtn(this)">상품문의</div>
					</div>
				
				</div>
			</form>
			<div class="board_middle">
				<div id="tbl_list">
					<ul>
						<li><p align="center">분류제목</p></li>
						<%for(BoardFAQ b : list) {
							secondNum++;
							titleNum++;
							contentNum++;%>
						<li class="second a<%=secondNum%>"><div class="categoryMiddle"><%=b.getBoardFAQCategory()%></div><div class="titleMiddle"><%=b.getBoardFAQTitle()%></div></li>
						<li class="title b<%=titleNum%>"><img src="<%=request.getContextPath()%>/images/Q.png" width="30px"><div><%=b.getBoardFAQTitle()%></div></li>
						<li class="content c<%=contentNum%>"><img src="<%=request.getContextPath()%>/images/A.png" width="30px"><div><%=b.getBoardFAQContent()%></div></li>
						<%}%>
					</ul>
				</div>
			</div>
			<div class="board_FAQ_bottom">
				<%=pageBar%>
			</div>
		</div>
		<script>
			var boardRight = $('#board_right');
			var slidebtn = $('#slideButton');
			var width = $('#board_left').width();

			/* $(window).scroll(function () {
				var height = $(document).scrollTop();

				if(height>1100) {
					$('#board_left').css({ "transition": "500ms", "width": "0px" });
						boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
						slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#b6b6a8", "color": "#fff" });
						slidebtn.html('메<br>뉴<br>열<br>기<br>>');
				} else if (height==0) {
					$('#board_left').css({ "transition": "500ms", "width": "210px" });
					boardRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
					slidebtn.html('메<br>뉴<br>접<br>기<br><');
				} else {
					$('#board_left').css({ "transition": "500ms", "width": "0px" });
						boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
						slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#b6b6a8", "color": "#fff" });
						slidebtn.html('메<br>뉴<br>열<br>기<br>>');
				}
			});  */
			
			function slide() {
				var width = $('#board_left').width();

				if (width == 0) {
					// 서브메뉴가 열린상태
					$('#board_left').css({"z-index":"999", "transition": "500ms", "width": "210px","border-right":"1px solid #c2c2c2"});
					//shopRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
					slidebtn.html('메<br>뉴<br>접<br>기<br><');
				} else {
					// 서브메뉴가 접힌상태
					$('#board_left').css({ "transition": "500ms", "width": "0px", "border":"none"});
					boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#fff", "color": "#333" });
					slidebtn.html('메<br>뉴<br>열<br>기<br>>');
				}
			}
			
			function sortBtn(e) {
				if(e.innerText == '전체검색') {
					$('[name = searchType]').val("all");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "변경/취소") {
					$('[name = searchType]').val("changeAndCancel");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "입금/배송") {
					$('[name = searchType]').val("depositAndDelivery");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "교환/반품") {
					$('[name = searchType]').val("exchangeAndReturn");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "기타문의") {
					$('[name = searchType]').val("therInquiries");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "할인/혜택") {
					$('[name = searchType]').val("saleAndBenefits");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "회원/혜택") {
					$('[name = searchType]').val("memberAndBenefits");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "주문/조회") {
					$('[name = searchType]').val("orderAndLookup");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "결제문의") {
					$('[name = searchType]').val("paymentInquiry");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "품절/입고") {
					$('[name = searchType]').val("soldOutAndWearing");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "상품문의") {
					$('[name = searchType]').val("contactUs");
					$('[name = searchFrm]').submit();
				}
			}
			
			$(document).ready(function(){
				  $(".a1").click(function(){
				    $(".b1").slideToggle();
				    $(".c1").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a2").click(function(){
				    $(".b2").slideToggle();
				    $(".c2").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a3").click(function(){
				    $(".b3").slideToggle();
				    $(".c3").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a4").click(function(){
				    $(".b4").slideToggle();
				    $(".c4").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a5").click(function(){
				    $(".b5").slideToggle();
				    $(".c5").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a6").click(function(){
				    $(".b6").slideToggle();
				    $(".c6").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a7").click(function(){
				    $(".b7").slideToggle();
				    $(".c7").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a8").click(function(){
				    $(".b8").slideToggle();
				    $(".c8").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a9").click(function(){
				    $(".b9").slideToggle();
				    $(".c9").slideToggle();
				  });
			});
			$(document).ready(function(){
				  $(".a10").click(function(){
				    $(".b10").slideToggle();
				    $(".c10").slideToggle();
				  });
			});
			
		</script>
	</div>

</section>

<%@ include file="/views/common/footer.jsp" %>