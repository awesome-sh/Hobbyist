<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.shop.model.vo.Shop, com.hobbyist.shop.model.vo.Cate, com.hobbyist.order.model.vo.Pin" %>

<%	
	List<Pin> list = (List)request.getAttribute("List");
	List<Shop> shop = (List)request.getAttribute("Shop");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	int totalCount = (Integer)request.getAttribute("TotalCount");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
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
				<li onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'">
				<img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">CLASS SHOP | 클래스샵</li>
				<li onclick="location.href='#'">관리자페이지 > 클래스샵 관리 > 클래스 핀코드(PINCODE) 관리</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminShopPin_right">
			<div id="adminShopPin_main">
				<div class="main-top">
					<table>
						<tr>
							<td colspan="5"><h3>핀코드(PIN CODE) 관리</h3></td>
						</tr>
						<tr>
							<td colspan="5" style="text-align:center;">총 ( <%= totalCount %> ) 건의 핀코드 목록</td>
						</tr>
						<tr>
							<th style="width:100px;">핀코드</th>
							<th style="width:300px;">클래스이름</th>
							<th style="width:150px;">보유한 회원</th>
							<th style="width:100px;">사용여부</th>
							<th style="width:100px;">생성일</th>
						</tr>
						<% 
							for(Pin p : list) {
								for(Shop s : shop) {
									if(p.getPinClass()==s.getShopNo()) {
						%>
							<tr>
								<td><%= p.getPinCode() %></td>
								<td><%= s.getShopName() %></td>
								<td><%= p.getPinMember() %></td>
								<td><%= p.getPinStatus().equals("Y") ? "사용안함" : "사용함" %></td>
								<td><%= p.getPinDate() %></td>
							</tr>
						<% }}} %>
						<tr>
							<td colspan="5" class="pageBar"><%= pageBar %></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>