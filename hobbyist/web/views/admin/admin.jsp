<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

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

		<div class="admin_right">
			<div id="admin_main">
				<div class="image" onclick="location.href='<%=request.getContextPath()%>/adminMemberList.do'"><img src="<%= request.getContextPath() %>/images/admin1.jpg"></div>
				<div class="image" onclick="location.href='<%= request.getContextPath()%>/admin/adminWriterEnroll'"><img src="<%= request.getContextPath() %>/images/admin2.jpg"></div>
				<div class="image" onclick="location.href='<%= request.getContextPath() %>/admin/adminNoticePreList'"><img src="<%= request.getContextPath() %>/images/admin3.jpg"></div><br>
				<div class="image" onclick="location.href='<%= request.getContextPath() %>/admin/adminShopList'"><img src="<%= request.getContextPath() %>/images/admin4.jpg"></div>
				<div class="image" onclick="location.href='<%= request.getContextPath() %>/admin/adminOnedayList'"><img src="<%= request.getContextPath() %>/images/admin5.jpg"></div>
				<div class="image" onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityList'"><img src="<%= request.getContextPath() %>/images/admin6.jpg"></div>
				<div class="image" onclick="location.href='<%= request.getContextPath() %>/admin/adminOrderList'"><img src="<%= request.getContextPath() %>/images/admin7.jpg"></div>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>