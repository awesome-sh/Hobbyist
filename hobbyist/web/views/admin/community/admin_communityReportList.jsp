<%@page import="com.hobbyist.board.model.vo.BoardReport"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<%
	List<BoardReport> list = (List<BoardReport>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
	if(searchType == null) searchType = "all";
	
	int i = 0;
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
		</div>
		<br>
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div id="admin_right">
			<div class="admin_main">
				<form class='searchForm' name="searchFrm" action="<%=request.getContextPath()%>/admin/community/adminCommunitySearch" method="POST">
					<div class="sort">
						<input type="hidden" name="searchType" value="<%=searchType%>"/>
						<%-- <input type="hidden" name="cPage" value="<%=cPage%>"/> --%>
						<input type="hidden" name="numPerPage" value="<%=numPerPage%>"/>
						<div id="all" onclick="sortBtn(this)">전체 신고물</div>
						<div id="yes" onclick="sortBtn(this)">처리된 신고물</div>
						<div id="no" onclick="sortBtn(this)">미처리 신고물</div>
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
						<th><input id="allchecked" type="checkbox"/></th>
						<th style="width: 60px;">번호</th>
						<th style="width: 370px;">제목</th>
						<th style="width: 100px;">작성자</th>
						<th style="width: 70px;">파일</th>
						<th style="width: 130px;">작성일</th>
						<th style="width: 100px;">조회수</th>
						<th style="width: 80px;">존재유무</th>
						<th style="width: 100px;">추천수</th>
					</tr>
					<%for(BoardReport b : list) { %>
					<tr>
						<td><%=b.getBoardNo()%></td>
						<td style="padding-left: 25px; padding-top: 9px;"><a href="<%=request.getContextPath() %>/admin/community/adminCommunityRevise?boardNo=<%= b.getBoardNo()%>"><%=b.getBoardTitle()%><%if(b.getBoardCommentCount() != 0) {%> <span class="comment"><%="[" + b.getBoardCommentCount() + "]"%></span><% } %></a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%if(b.getBoardReNamedFileName()!=null) {%><img src="<%=request.getContextPath()%>/images/file.png" width="16px"/><%}%></td>
						<td><%=b.getBoardDate()%></td>
						<input type="hidden" name="fname<%=i%>" value="<%=b.getBoardReNamedFileName()%>"/>
						<input type="hidden" name="no<%=i%>" value="<%=b.getBoardNo()%>"/>
						<td><input type='text' name="readCount<%=i%>" value='<%=b.getBoardReadCount()%>' size='1px'/></td>
						<td><input type='text' name="status<%=i%>" value='<%=b.getStatus()%>' size='1px'/></td>
						<td><input type='text' name="like<%=i%>" value='<%=b.getBoardLike()%>' size='1px'/></td>
					</tr>
					<% } %>
					<tr>
						<td class="tableBottom" colspan="6" style="text-align: left; padding-left:20px;"><%=pageBar%></td>
						<td class="tableBottom" colspan="2" style="text-align: right; padding-right:20px;">
							<input id="btnsave" type='button' value="저장"/>
						</td>
						<td class="tableBottom" colspan="1" style="text-align: right; padding-right:20px;">
							<input id="btndelete" type='button' value="삭제"/>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</section>

<script>

	function sortBtn(e) {
		if(e.innerText == '전체 신고물') {
			$('[name = searchType]').val('yes');
			$('[name = searchFrm]').submit();
		} else if( e.innerText == '처리된 신고물') {
			$('[name = searchType]').val('delete');
			$('[name = searchFrm]').submit();
		} else if( e.innerText == '미리된 신고물') {
			$('[name = searchType]').val('exist');
			$('[name = searchFrm]').submit();
		}
	}
	
	
</script>



<%@ include file="/views/common/footer.jsp" %>