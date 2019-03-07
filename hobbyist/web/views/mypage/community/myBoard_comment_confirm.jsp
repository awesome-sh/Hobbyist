<%@page import="com.hobbyist.board.model.vo.BoardComment"%>
<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%@ include file="/views/common/header.jsp" %>

<%
	List<BoardComment> list = (List<BoardComment>)request.getAttribute("list");
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

		<div class="myPage_right">
			<div id="myPage_main">
				<h2>나의 댓글</h2>
				<br>
				<%if(!list.isEmpty()) { %>
				<table>
					<tr>
						<th style="width: 60px;">번호</th>
						<th style="width: 300px;">작성내용</th>
						<th style="width: 100px;">작성자</th>
						<th style="width: 130px;">작성일</th>
						<th></th>
					</tr>
					
					<%for(BoardComment b : list) { %>
					<tr>
						<td><%=b.getBoardRef()%></td>
						<td style="padding-left: 25px;"><a href="<%=request.getContextPath() %>/board/boardView?boardNo=<%= b.getBoardRef()%>"><p style=" width: 400px; overflow:hidden; text-overflow:ellipsis; white-space:nowrap; display: inline-block;"><%=b.getBoardCommentContent()%></p></a></td>
						<td><%=b.getBoardCommentWriter()%></td>
						<td><%=b.getBoardCommentDate()%></td>
					</tr>
					<% } %>
					<tr>
						<td class="tableBottom" colspan="4" style="text-align: center; padding-left:20px;"><%=pageBar%></td>
					</tr>
				</table>
				<% } else { %>
					<p style="text-align: center; font-size: 16px;">본인이 작성한 댓글이 없습니다!</p>
				<% } %>
			</div>
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>