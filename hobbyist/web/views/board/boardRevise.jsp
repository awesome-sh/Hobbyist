<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	Board b = (Board)request.getAttribute("b");
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
		<b>Class Board </b>|&nbsp;&nbsp;메인 > 커뮤니티
		<div class="board_top"></div>
		<div class="board_middle">
			<h2>자유게시판 등록</h2>
	<form action="<%=request.getContextPath()%>/board/boardReviseEnd?boardNo=<%=b.getBoardNo()%>&fname=<%=b.getBoardReNamedFileName()%>" method="post" enctype="multipart/form-data">
	
		<table id="tbl_write">
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" id="title" value="<%=b.getBoardTitle()%>" required/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"
				value='<%=logginMember.getMemberNickname()%>' style="width: 80px;" value="<%=b.getBoardWriter()%>" readonly/></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td>
					<input type="file" name="up_file" value="<%=b.getBoardReNamedFileName()%>"/>
				</td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" id="editor1"><%=b.getBoardContent()%></textarea></td>
			</tr>
			<tr>
				<td colspan="2">
					<input class="inp" type="submit" value="변경" onclick="return validate();"/>
					<input class="inp" type="button" value="취소" onclick="cancel()"/>
				</td>
			</tr>
		</table>
	</form>	
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
			
			var title = $('[name=title]').val();
			var content = $('#editor1').value;
			if(title.trim().length == 0) {
				alert("제목은 공란으로 작성할 수 없습니다.");
				$('[name=title]').focus();
				return false;
			}
			else if(content.trim().length == 0) {
				alert("내용은 공란으로 작성할 수 없습니다.");
				$('#editor1').focus();
				return false;
			}
			return true;
		}
		
		function cancel() {
			if(confirm('취소하시겠습니끼?')) {
				location.href = "<%=request.getContextPath()%>/board/boardView?boardNo=<%=b.getBoardNo()%>";
			}
		}

	</script>
</div>
	
</section>

<%@ include file="/views/common/footer.jsp" %>