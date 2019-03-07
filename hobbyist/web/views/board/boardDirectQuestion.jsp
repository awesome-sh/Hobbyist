<%@page import="com.hobbyist.board.model.vo.BoardDQ"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	List<BoardDQ> list = (List<BoardDQ>)request.getAttribute("list");
	int cPage = (int)(request.getAttribute("cPage"));
	int numPerPage = (int)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
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
		</div>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			접<br>
			기<br>
		</div>
		
		<div id="board_right">
			<h3>COMMUNITY</h3><p>메인 > 커뮤니티 > 1:1문의</p>
			<div class="board_DQ_top">
				<h2>1:1 문의</h2>
		<form action="<%=request.getContextPath()%>/board/boardEndDirectQuestion" method="post" enctype="multipart/form-data">
		
			<table id="tbl_write">
				<tr>
					<th style="width:200px">제목</th>
					<td style="width:650px"><input type="text" name="title" id="title" required/></td>
				</tr>
				<tr>
					<th>작성자</th>
					<td><input type="text" name="writer"
					value='<%=logginMember.getMemberNickname()%>' style="width: 80px;" readonly/></td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td>
						<input type="file" name="up_file"/>
					</td>
				</tr>
				<tr>
					<th>내용</th>
					<td><textarea name="content" id="editor1"></textarea></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:right">
						<button class="inp" type="submit" onclick="return validate();">문의하기</button>
					</td>
				</tr>
			</table>
		</form>	
			</div>
			
			<div class="board_DQ_middle">
				<h2>나의 문의 리스트</h2>
				<div id="tbl_list">
			    	<a class="list_table">
			            <div class="list">NO</div>
			            <div class="list">제목</div>
			            <div class="list">작성자</div>
			            <div class="list">작성일</div>
			            <div class="list">첨부파일</div>
			            <div class="list">처리상태</div>
				    </a>
					<%for(BoardDQ b : list) { %>
						<%if(logginMember.getMemberNickname().equals(b.getBoardDQWriter()) || logginMember.getMemberNickname().equals("Hobbyist")) {%>
						<a class="list_table" href='<%=request.getContextPath()%>/board/boardDirectQuestionView?boardDQNo=<%=b.getBoardDQNo()%>'>
							<div class="list"><%=b.getBoardDQNo()%></div>
							<div class="list"><%=b.getBoardDQTitle()%></div>
							<div class="list"><%=b.getBoardDQWriter()%></div>
							<div class="list"><%=b.getBoardDQDate()%></div>
							<div class="list"><%if(b.getBoardDQReNameFileName()!=null) {%>
							<img src="<%=request.getContextPath()%>/images/file.png" width="16px"/>
							<%}%></div>
							<div class="list"><%=b.getBoardDQProcess().equals("N")?"처리중":"답변완료"%></div>
						</a>
					    <%}
				     }%>
				</div>
			</div>
			<div class="board_DQ_bottom">
				<%=pageBar%>
			</div>
		</div>
		
<script>

	// Replace the <textarea id="editor1"> with a CKEditor
	// instance, using default configuration.
	CKEDITOR.replace( 'editor1' );
	CKEDITOR.add

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
	
	function validate() {
		for(var instanceName in CKEDITOR.instances){
		    CKEDITOR.instances[instanceName].updateElement();
		}
		var title = $('[name=title]').val();
		var content = CKEDITOR.instances.editor1.getData();
		if(title.trim().length == 0) {
			alert("제목은 공란으로 작성할 수 없습니다.");
			$('[name=title]').focus();
			return false;
		} else if(content.trim().length == 0) {
			alert("내용은 공란으로 작성할 수 없습니다.");
			CKEDITOR.instances.editor1.focus();
			return false;
		} else if(title.length > 16) {
			alert("제목을 50 bytes를 초과하였습니다.");
			return false;
		} else if(content.length > 2000) {
			alert("내용을 3000 bytes를 초과하였습니다.");
			return false;
		}
		return true;
	}

</script>
	</div>
		
</section>

<%@ include file="/views/common/footer.jsp" %>