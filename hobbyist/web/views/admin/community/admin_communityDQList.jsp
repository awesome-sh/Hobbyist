<%@page import="com.hobbyist.board.model.vo.BoardDQ"%>
<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>

<%
	List<BoardDQ> list = (List<BoardDQ>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
	if(searchType == null) searchType = "all";
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
				<form name="searchFrm" action="<%=request.getContextPath()%>/admin/community/adminCommunityDQSearch" method="POST">
					<div class="sort">
						<input type="hidden" name="searchType" value="<%=searchType%>"/>
						<input type="hidden" name="cPage" value="<%=cPage%>"/>
						<input type="hidden" name="numPerPage" value="<%=numPerPage%>"/>
						<div id="all" onclick="sortBtn(this)">전체보기</div>
						<div id="statusY" onclick="sortBtn(this)">처리 답변</div>
						<div id="statusN" onclick="sortBtn(this)">미처리 답변</div>
					</div>
				
					<div class="keyword">
						게시물&nbsp;&nbsp;&nbsp;<input type="search" placeholder="Search..." name="searchKeyword" value="<%=searchKeyword%>">
						<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png" width="70%"></button>
					</div>
				</form>
			</div>
				<div class="admin_middle">
					<table>
					<tr>
						<th style="width: 60px;">NO</th>
						<th style="width: 300px;">제목</th>
						<th style="width: 180px;">작성자</th>
						<th style="width: 220px;">작성일</th>
						<th style="width: 160px;">첨부파일</th>
						<th style="width: 180px;">처리상태</th>
						<th></th>
					</tr>
					<%for(BoardDQ b : list) { %>
					<tr>
						<td><%=b.getBoardDQNo()%></td>
						<td style="padding-left: 25px; padding-top: 9px;"><a href="<%=request.getContextPath() %>/board/boardDirectQuestionView?boardDQNo=<%=b.getBoardDQNo()%>"><%=b.getBoardDQTitle()%></a></td>
						<td><%=b.getBoardDQWriter()%></td>
						<td><%=b.getBoardDQDate()%></td>
						<td><%if(b.getBoardDQReNameFileName()!=null) {%><img src="<%=request.getContextPath()%>/images/file.png" width="16px"/><%}%></td>
						<td><%=b.getBoardDQProcess().equals("N")?"처리중":"답변완료"%></td>
					</tr>
					<% } %>
					<tr>
						<td class="tableBottom" colspan="20" style="text-align: left; padding-left:20px;"><%=pageBar%></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</section>

<script>

	function sortBtn(e) {
		if(e.innerText == '전체보기') {
			$('[name = searchType]').val('all');
			$('[name = searchFrm]').submit();
		} else if( e.innerText == '처리 답변') {
			$('[name = searchType]').val('statusY');
			$('[name = searchFrm]').submit();
		} else if( e.innerText == '미처리 답변') {
			$('[name = searchType]').val('statusN');
			$('[name = searchFrm]').submit();
		}
	}

</script>



<%@ include file="/views/common/footer.jsp" %>