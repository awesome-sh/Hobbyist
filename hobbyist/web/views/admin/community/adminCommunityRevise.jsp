<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Board b = (Board)request.getAttribute("b");
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

		<div class="admin_right">
			<div id="admin_main">
			<br><br>
				<form action="<%=request.getContextPath()%>/admin/community/adminCommunityReviseEnd?boardNo=<%=b.getBoardNo()%>&fname=<%=b.getBoardReNamedFileName()%>" method="post" enctype="multipart/form-data">
					<table id="tbl_revise">
						<tr>
							<th style="width:200px;">제목</th>
							<td style="width:600px; text-align:left"><input type="text" name="title" id="title" value="<%=b.getBoardTitle()%>" required/></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td style="width:600px; text-align:left"><input type="text" name="writer"
							value='<%=logginMember.getMemberNickname()%>' style="width: 80px;" value="<%=b.getBoardWriter()%>" readonly/></td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td style="width:600px; text-align:left">
								<input type="file" name="up_file" value="<%=b.getBoardReNamedFileName()%>"/>
							</td>
						</tr>
						<tr>
							<th>내용</th>
							<td style="width:600px; text-align:left"><textarea name="content" id="editor1"><%=b.getBoardContent()%></textarea></td>
						</tr>
						<tr>
							<td class="tableBottom" colspan="2">
								<input class="inp" type="submit" value="변경" onclick="return validate();"/>
								<input class="inp" type="button" value="취소" onclick="cancel()"/>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
</section>

<script>

CKEDITOR.replace( 'editor1' );
CKEDITOR.add

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
			location.href = "<%=request.getContextPath()%>/admin/community/adminCommunityList";
		}
	}

</script>

<%@ include file="/views/common/footer.jsp" %>