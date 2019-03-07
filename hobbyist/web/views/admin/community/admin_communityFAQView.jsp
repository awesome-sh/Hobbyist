<%@page import="com.hobbyist.board.model.vo.BoardFAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<%
	BoardFAQ b = (BoardFAQ)request.getAttribute("b");
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

		<div id="admin_right" class="admin_FAQright">
			<div class="FAQView_top">
				<%=b.getBoardFAQTitle()%>
			</div>
			<div class="FAQView_middle">
				<div id="info_head">
						카테고리 : <%=b.getBoardFAQCategory()%>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
						작성일 : <%=b.getBoardFAQDate()%>
				</div>
				<div id="FAQview_content">
					<%=b.getBoardFAQContent()%>
				</div>
				
				<div id="FAQview_bottom">
					<button onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQRevise?boardFAQNo=<%=b.getBoardFAQNo()%>'">수정</button>
					<button onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQDelete?boardFAQNo=<%=b.getBoardFAQNo()%>'">삭제</button>
				</div>
			</div>
		</div>
	</div>

</section>


<%@ include file="/views/common/footer.jsp" %>