<%@page import="com.hobbyist.order.model.vo.Order"%>
<%@page import="com.hobbyist.shop.model.vo.Shop"%>
<%@page import="com.hobbyist.oneday.model.vo.Oneday"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%
	List<Order> order = (List)request.getAttribute("Order");
	List<Shop> shop = (List)request.getAttribute("Shop");
	List<Oneday> oneday = (List)request.getAttribute("Oneday");
%>

<%@ include file="/views/common/header.jsp" %>

<%
	int cPage = (int)(request.getAttribute("cPage"));
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
%>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if(<%= logginMember!=null %>) {
		
	} else {
		alert('접근 오류');
		location.href=history.back(-1);
	}
</script>

<section id="myPage">
	<div class="myPage_content">
		<div class="myPage_top" id="myPage_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>/mypage/myPage'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Member</li>
				<li>마이 페이지에 오신 걸 환영합니다.</li>
			</ul>
			
		</div><br>
		
		<!-- 마이페이지메뉴는 중복되기때문에 myPage_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/mypage/myPage_menu.jsp" %> 
		<!-- 마이페이지 메뉴 인클루드 끝 -->

		<div class="myPage_board_right">
			<div id="myPage_main">
				<h2>내 주문내역</h2>
				<table>
					<tr>
						<th style="width: 80px;">분류</th>
						<th style="width: 220px;">상품명</th>
						<th style="width: 80px;">가격</th>
						<th style="width: 60px;">옵션</th>
						<th style="width: 130px;">주문일자</th>
						<th style="width: 80px;">결제방법</th>
						<th></th>
					</tr>
					
					<%
						for(Order o : order) {
							for(Shop s : shop) {
								if(o.getOrderClass()==s.getShopNo()) {
					%>
					<tr>
						<td><%= o.getOrderCate() %></td>
						<td><%= s.getShopName() %></td>
						<td><%= s.getShopPrice() %></td>
						<td><%= o.getOrderClassOption() %></td>
						<td><%= o.getOrderDate()%></td>
						<td><%= o.getOrderType().equals("kakao") ? "카카오페이" : "" %></td>
						<td><button style="width: 80px" onclick="fn_cancle('<%= o.getOrderNo() %>')">주문취소</button></td>
					</tr>
					<% }}}%>
					<tr>
						<td class="tableBottom" colspan="7" style="text-align: center;"><%=pageBar%></td>
					</tr>
				</table>
				<script>
					function fn_cancle(num) {
							if(confirm('해당상품을 주문취소 하시겠습니까?')) {
								$.ajax({
									url:'<%=request.getContextPath() %>/admin/adminOrderDelete?no=' + num,
									success: function (data) {
										alert(data);
										location.href="<%=request.getContextPath() %>/mypage/myOrderList?member=<%= logginMember.getMemberEmail() %>";
									}
								});
							} else {
								return;
							}
					}
				</script>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>