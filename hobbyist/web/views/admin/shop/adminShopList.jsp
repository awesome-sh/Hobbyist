<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.shop.model.vo.Shop, com.hobbyist.shop.model.vo.Cate" %>

<%	
	List<Shop> list = (List)request.getAttribute("List");
	List<Cate> category = (List)request.getAttribute("Category");
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
				<li onclick="location.href='#'">관리자페이지 > 클래스샵 관리 > 클래스샵 목록</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 adminShop_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminShop_right">
			<div id="adminShop_main">
					<table>
						<tr>
							<td colspan="8"><h3>클래스샵 목록</h3></td>
						</tr>
						<tr>
							<td colspan="8" style="text-align:center;">총 ( <%= totalCount %> ) 건의 클래스 목록</td>
						</tr>
						<tr>
							<th style="width:50px;">번호</th>
							<th style="width:90px;">분류</th>
							<th style="width:315px;">클래스이름</th>
							<th style="width:120px;">작가</th>
							<th style="width:60px;">가격</th>
							<th style="width:68px;">적립포인트</th>
							<th style="width:90px;">등록일</th>
							<th></th>
						</tr>
						<% for(Shop s : list) { %>
							<tr>
								<td><%= s.getShopNo() %></td>
								<td><%= s.getShopCate() %></td>
								<td style="text-align: left; padding-left: 30px;"><a href="<%= request.getContextPath() %>/admin/adminShopView?no=<%= s.getShopNo() %>"><%= s.getShopName() %></a></td>
								<td><%= s.getShopWriter() %></td>
								<td><%= s.getShopPrice() %></td>
								<td><%= s.getShopPoint() %></td>
								<td><%= s.getShopDate() %></td>
								<td>
									<% 
										if(logginMember!=null && logginMember.getMemberEmail().equals("admin")) {
									%>
											<button onclick="fn_delete(<%= s.getShopNo() %>)">X</button>
									<%
										}
									%>
								</td>
							</tr>
						<% } %>
						<tr>
							<td colspan="8" class="pageBar"><%= pageBar %></td>
						</tr>
					</table>
					<script>
						function fn_delete(num) {
							if(confirm('정말 삭제하시겠습니까?')) {
								$.ajax({
									url : '<%= request.getContextPath() %>/admin/adminShopDelete?no=' + num,
									success : function (data) {
										alert(data);
										location.href="<%= request.getContextPath() %>/admin/adminShopList";
									}
								});
							}
						}
					</script>
				</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>