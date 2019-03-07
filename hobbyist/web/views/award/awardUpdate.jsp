<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.award.model.vo.Award"%>
<%@ include file="/views/common/header.jsp"%>
<%
	Award a = (Award) request.getAttribute("award");
%>

<section id="award">
	<div class="award_content">
		<div id=award_right>
			<b>Award</b>|&nbsp;&nbsp;메인 > 하비스트 어워드 > 게시물 수정
			<div class="award_top"></div>
			<div class="award_middle">
				
				<table id="tbl-awardForm">
					<h2>어워드 게시판</h2>
					<form action="<%=request.getContextPath()%>/award/updateEnd" method="post" enctype="multipart/form-data">

						<tr>
							<th>제목</th>
							<td><input type="text" name="title"
								value="<%=a.getAwardName()%>"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td><input type="text" name="writer"
								value="<%=logginMember != null ? logginMember.getMemberEmail() : ""%>"
								readOnly></td>
						</tr>
						<tr>
							<th>내용</th>
							<td>
							<textarea cols="50" rows="5" name="content"><%=a.getAwardContent()%></textarea>
							</td>
						</tr>
						<tr>
							<th>첨부파일</th>
							<td>
								<% if (a.getAwardOriginalFilename() != null) {%> 
								<input type="file" name="up_file" value="<%=a.getAwardOriginalFilename()%>">
								<input type="hidden" name="old_file" value="<%=a.getAwardOriginalFilename()%>">
								
								<a href="javascript:fn_fileDown('<%=a.getAwardOriginalFilename()%>','<%=a.getAwardRenamedFilename()%>')">
									
									<span id="fname">
										<img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" width="16px">
										<%=a.getAwardOriginalFilename()%></span>
							</a> <%}else{ %>
							<input type="file" name="up_file">
							<%} %>
							</td>
						</tr>
						<tr>
							<td colspan="2"><input type="hidden" name="awardNo" value="<%=a.getAwardNo()%>"> 
							<input type="submit" value="수정완료" onclick="return validate()"></td>
						</tr>
				</form>
				</table>
			</div>
			</div>
			</div>

			<script>
				function validate() {
					var content = $('[name=content]').val();
					if (content.trim().length == 0) {
						alert("내용을 입력하세요");
						return false;
					}
					return true;
				}

				$(function() {
					$('[type=file]').change(function() {
						if ($(this).val() == "") {
							$('#fname').show();
						} else {
							$('#fname').hide();
						}
					})
				})
			</script>
</section>
<%@ include file="/views/common/footer.jsp"%>