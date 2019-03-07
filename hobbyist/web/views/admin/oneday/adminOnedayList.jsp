<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Oneday, com.hobbyist.oneday.model.vo.Cate" %>

<%	
	List<Oneday> list = (List)request.getAttribute("List");
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
				<li onclick="location.href='#'">ONEDAY CLASS  | 원데이클래스</li>
				<li onclick="location.href='#'">관리자페이지 > 원데이클래스 관리 > 원데이클래스 목록</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminOneday_right">
			<div id="adminOneday_main">
					<table>
						<tr>
							<td colspan="8" style="text-align:center;"><h3>원데이클래스 목록</h3></td>
						</tr>
						<tr>
							<td colspan="8" style="text-align:center;">총 ( <%= totalCount %> ) 건의 원데이클래스 목록</td>
						</tr>
						<tr>
							<th style="width:50px;">번호</th>
							<th style="width:90px;">분류</th>
							<th style="width:380px;">클래스이름</th>
							<th style="width:120px;">작가</th>
							<th style="width:120px;">예약(인원)</th>
							<th style="width:60px;">가격</th>
							<th style="width:100px;">등록일</th>
							<th></th>
						</tr>
						<% for(Oneday s : list) { %>
							<tr>
								<td><%= s.getOnedayNo() %></td>
								<td><%= s.getOnedayCate() %></td>
								<td><a href="<%= request.getContextPath() %>/admin/adminOnedayView?no=<%= s.getOnedayNo() %>"><%= s.getOnedayName() %></a></td>
								<td><%= s.getOnedayWriter() %></td>
								<td><%= s.getOnedayCurrentPeople() %>/<%= s.getOnedayPeople() %></td>
								<td><%= s.getOnedayPrice() %></td>
								<td><%= s.getOnedayDate() %></td>
								<td>
									<% 
										if(logginMember!=null && logginMember.getMemberEmail().equals("admin")) {
									%>
											<button onclick="fn_delete(<%= s.getOnedayNo() %>)">X</button>
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
									url : '<%= request.getContextPath() %>/admin/adminOnedayDelete?no=' + num,
									success : function (data) {
										alert(data);
										location.href="<%= request.getContextPath() %>/admin/adminOnedayList";
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