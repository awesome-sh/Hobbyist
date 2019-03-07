<%@page import="oracle.net.aso.b"%>
<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<Board> list = (List<Board>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String searchType = (String)request.getAttribute("searchType");
	String searchKeyword = (String)request.getAttribute("searchKeyword");
	if(searchKeyword == null) searchKeyword = "";
	if(searchType == null) searchType = "newUp";
%>
<%@ include file="/views/common/header.jsp" %>

<section id="board">

	<div class="board_content">
	
		<div id="board_left">
			<div id="board_sub">
				<h3>Board Menu</h3>
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardList'" style="cursor: pointer;"> · 자유게시판</li>
					<li style="cursor: pointer;"> · 리뷰/후기</li>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'" style="cursor: pointer;"> · FAQ 자주 묻는 질문</li>
					<li onclick="directQuestion()" style="cursor: pointer;"> · 1:1 문의</li>
					<script>
						function directQuestion() {
							<%if(logginMember != null) {%>
							location.href='<%=request.getContextPath()%>/board/boardDirectQuestion?NickName=<%=logginMember.getMemberNickname()%>';
							<%} else {%>
								alert('로그인 후 이용가능합니다.');
							<%}%>
						}
					</script>
				</ul>
			</div>
		</div><br>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			접<br>
			기<br>
		</div>
		
		<div id="board_right">
			<h3>COMMUNITY</h3><p>메인 > 커뮤니티 > 자유게시판</p>
			<div class="board_top">
				<form name="searchFrm" action="<%=request.getContextPath()%>/board/boardSearch" method="POST">
					<div class="sort">
						<input type="hidden" name="searchType" value="<%=searchType%>"/>
						<%-- <input type="hidden" name="cPage" value="<%=cPage%>"/> --%>
						<input type="hidden" name="numPerPage" value="<%=numPerPage%>"/>
						<div id="newUp" onclick="sortBtn(this)">최근 등록순</div>
						<div id="viewsUp" onclick="sortBtn(this)">조회수 높은순</div>
						<div id="LikeUp" onclick="sortBtn(this)">추천 높은순</div>
					</div>
				
					<div class="searchForm">
						게시물&nbsp;&nbsp;&nbsp;<input type="search" placeholder="Search..." name="searchKeyword" value="<%=searchKeyword%>">
						<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png" width="70%"></button>
					</div>
				</form>
			</div>
			
			<div class="board_middle">
				<table>
					<tr>
						<th style="width: 60px;">번호</th>
						<th style="width: 370px;">제목</th>
						<th style="width: 100px;">작성자</th>
						<th style="width: 70px;">파일</th>
						<th style="width: 130px;">작성일</th>
						<th style="width: 100px;">조회수</th>
						<th></th>
					</tr>
					<%for(Board b : list) { %>
					<tr>
						<td><%=b.getBoardNo()%></td>
						<td style="padding-left: 25px; padding-top: 9px;"><a href="<%=request.getContextPath() %>/board/boardView?boardNo=<%= b.getBoardNo()%>"><%=b.getBoardTitle()%><%if(b.getBoardCommentCount() != 0) {%> <span class="comment"><%="[" + b.getBoardCommentCount() + "]"%></span><% } %></a></td>
						<td><%=b.getBoardWriter()%></td>
						<td><%if(b.getBoardReNamedFileName()!=null) {%><img src="<%=request.getContextPath()%>/images/file.png" width="16px"/><%}%></td>
						<td><%=b.getBoardDate()%></td>
						<td><%=b.getBoardReadCount()%></td>
						<td>
							<% if(logginMember!=null && logginMember.getMemberEmail().equals("admin")) { %>
							&nbsp;&nbsp;<button type="button" onclick="deleteBoard(<%=b.getBoardNo()%>)"/>X</button>&nbsp;&nbsp;
							<% } %>
						</td>
					</tr>
					<% } %>
					<tr>
						<td class="tableBottom" colspan="3" style="text-align: left; padding-left:20px;"><%=pageBar%></td>
						<td class="tableBottom" colspan="4" style="text-align: right; padding-right:20px;">
						<% if(logginMember!=null) { %>
							<input type='button' value="글쓰기" onclick="location.href='<%=request.getContextPath()%>/board/boardWriteForm'"/>
						<% } %>
						</td>
					</tr>
				</table>
		
		<script>
			var boardRight = $('#board_right');
			var slidebtn = $('#slideButton');
			var width = $('#board_left').width();

			function slide() {
				var width = $('#board_left').width();

				if (width == 0) {
					// 서브메뉴가 열린상태
					$('#board_left').css({"z-index":"999", "transition": "500ms", "width": "210px","border-right":"1px solid #c2c2c2"});
					//shopRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
					slidebtn.html('메<br>뉴<br>접<br>기<br><');
				} else {
					// 서브메뉴가 접힌상태
					$('#board_left').css({ "transition": "500ms", "width": "0px", "border":"none"});
					boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#fff", "color": "#333" });
					slidebtn.html('메<br>뉴<br>열<br>기<br>>');
				}
			}
		
			function sortBtn(e) {
				if(e.innerText == '최근 등록순') {
					$('[name = searchType]').val("newUp");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "조회수 높은순") {
					$('[name = searchType]').val("viewsUp");
					$('[name = searchFrm]').submit();
				} else if( e.innerText == "추천 높은순") {
					$('[name = searchType]').val("LikeUp");
					$('[name = searchFrm]').submit();
				}
			}
			
			function deleteBoard(boardNo) {
				var boardNo = boardNo;
				if(confirm("정말 게시물을 삭제하시겠습니까?")) {
					location.href='<%=request.getContextPath()%>/board/boardDelete?boardNo=' + boardNo;
				} else {
					
				}
			}
			
		</script>
	</div>

</section>

<%@ include file="/views/common/footer.jsp" %>