<%@page import="java.util.List"%>
<%@page import="com.hobbyist.board.model.vo.BoardDQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	BoardDQ b = (BoardDQ)request.getAttribute("b");
%>

<section id="board">

	<div class="board_content">
	
		<div id="board_left">
			<div id="board_sub">
				<h3>Board Menu</h3>
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardList'" style="cursor: pointer;"> · 자유게시판</li>
					<li style="cursor: pointer;"> · 리뷰/후기</li>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'" style="cursor: pointer;"> · FAQ 자주 묻는 질문</li>
					<li onclick="directQuestion()"> · 1:1문의</li>
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
		</div>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			접<br>
			기<br>
		</div>
		
		<div id="board_right">
			<h3>COMMUNITY</h3><p>메인 > 커뮤니티 > 1:1문의</p>
			<div class="board_top">
				<h2>1:1 문의</h2>
			</div>
			<div class="board_middle">
				<div id="view_head">
					<h3><%=b.getBoardDQTitle()%></h3>
				</div>
				<div id="info_head">
					<dl>
						<div id="left">
							<dt>작성자&nbsp;&nbsp;|&nbsp;&nbsp;</dt>
							<dd><%=b.getBoardDQWriter()%></dd>
						</div>
						<div id="right">
							<dt>작성일&nbsp;&nbsp;|&nbsp;&nbsp;</dt>
							<dd><%=b.getBoardDQDate()%></dd>
						</div>
					</dl>
				</div>
				<div id="view_content">
					<% if(b.getBoardDQReNameFileName()!=null) { %>
					<img src="<%=request.getContextPath()%>/upload/board/<%=b.getBoardDQReNameFileName()%>" width="500px" />
					<% } %>
					<p><%=b.getBoardDQContent()%></p>
				</div>
				<%if(b.getBoardDQAnswer() != null) {%>
				<div id="view_answer_content">
					<h2>답변</h2>
					<br/>
					<p><%=b.getBoardDQAnswer()%></p>
				</div>
				<%}%>
				<br/>
				<br/>
				<hr/>
				<div class="view_DQ_page">
					<%if(logginMember != null && (logginMember.getMemberNickname().equals(b.getBoardDQWriter()) || logginMember.getMemberNickname().equals("Hobbyist"))) {%>
						<%if(logginMember.getMemberNickname().equals("Hobbyist")) {%>
						<button onclick='location.href="<%=request.getContextPath()%>/board/boardDQAnswer?boardDQNo=<%=b.getBoardDQNo()%>"'>답변하기</button>
						<%}%>
						<button onclick='location.href="<%=request.getContextPath()%>/board/boardDQUpdate?boardDQNo=<%=b.getBoardDQNo()%>"'>수정</button>
						<button onclick='location.href="<%=request.getContextPath()%>/board/boardDQDelete?boardDQNo=<%=b.getBoardDQNo()%>"'>삭제</button>
					<% } %>
				</div>
			</div>
		</div>
	</div>

</section>

<script>

	var boardRight = $('#board_right');
	var slidebtn = $('#slideButton');
	var width = $('#board_left').width();

	/* $(window).scroll(function () {
		var height = $(document).scrollTop();

		if(height>1100) {
			$('#board_left').css({ "transition": "500ms", "width": "0px" });
				boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
				slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#b6b6a8", "color": "#fff" });
				slidebtn.html('메<br>뉴<br>열<br>기<br>>');
		} else if (height==0) {
			$('#board_left').css({ "transition": "500ms", "width": "210px" });
			boardRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
			slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
			slidebtn.html('메<br>뉴<br>접<br>기<br><');
		} else {
			$('#board_left').css({ "transition": "500ms", "width": "0px" });
				boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
				slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#b6b6a8", "color": "#fff" });
				slidebtn.html('메<br>뉴<br>열<br>기<br>>');
		}
	});  */

	function slide() {
		var width = $('#board_left').width();

		if (width == 0) {
			// 서브메뉴가 열린상태
			$('#board_left').css({ "transition": "500ms", "width": "210px" });
			boardRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
			slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
			slidebtn.html('메<br>뉴<br>접<br>기<br><');
		} else {
			// 서브메뉴가 접힌상태
			$('#board_left').css({ "transition": "500ms", "width": "0px" });
			boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
			slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#b6b6a8", "color": "#fff" });
			slidebtn.html('메<br>뉴<br>열<br>기<br>>');
		}
	}
	
</script>

<%@ include file="/views/common/footer.jsp" %>