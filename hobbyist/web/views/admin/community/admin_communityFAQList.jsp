<%@page import="com.hobbyist.board.model.vo.BoardFAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%
	List<BoardFAQ> list = (List<BoardFAQ>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType=(String)request.getAttribute("searchType");
	String searchKeyword=(String)request.getAttribute("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if(<%= logginMember!=null && logginMember.getMemberEmail().equals("admin") %>) {
		
	} else {
		alert('관리자만 접근 가능합니다');
		location.href=history.back(-1);
	}
</script>

<section id="admin">
	<div class="admin_content">
		<div class="admin_top" id="admin_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Administrator</li>
				<li>오늘방문자 : 4205　　　어제방문자 : 8243　　　전체방문자 : 52483094</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div id="admin_right">
			<div class="admin_main">
				<form name = searchFrm action="<%=request.getContextPath()%>/admin/community/adminCommunityFAQSearch" method="POST">
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
						<input type="search" placeholder="Search..." name="searchKeyword" value='<%=searchKeyword%>'>
						<button type="submit">
							<img src="<%= request.getContextPath() %>/images/search.png" width="70%">
						</button>
					</div>
				
					<div class="faq_sort">
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
				
			</form>
			<div class="admin_FAQmiddle">
				<table>				
					<tr>
						<td>분류</td>
						<td>제목</td>
					</tr>
					<%for(BoardFAQ b : list) {%>
					<tr>
						<td><%=b.getBoardFAQCategory()%></td>
						<td><a href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQView?boardFAQNo=<%=b.getBoardFAQNo()%>'><%=b.getBoardFAQTitle()%></a></td>
					</tr>
					<%}%>
					<tr>
						<td colspan="2" class="tableBottom" style="float: left; padding-left:20px;"><%=pageBar%>
						<p align="right">
							<input type='button' value="추가하기" onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQInsert'"/></p>
						</td>
					</tr>
				</table>
			</div>
				
			</div>
		</div>
	</div>

</section>

<script>

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
	
	

</script>



<%@ include file="/views/common/footer.jsp" %>