<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.order.model.vo.Order, com.hobbyist.shop.model.vo.Cate" %>

<%	
	int income = (Integer)request.getAttribute("income");
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
				<li onclick="location.href='#'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">Settled | 정산관리</li>
				<li onclick="location.href='#'">관리자페이지 > 정산관리</li>
			</ul>
			
		</div>
		<br>
		
		<!-- 관리자메뉴는 중복되기때문에 adminShop_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminShop_right">
			<div id="adminShop_main">
				<div>
					총 매출액<br>
					<%=  income %>
				</div>		
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>