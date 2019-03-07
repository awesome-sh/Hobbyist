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
			<b>Award</b>|&nbsp;&nbsp;메인 > 하비스트 어워드 > 게시물 작성
			<div class="award_top"></div>
			<div class="award_middle">
				<table id="tbl-awardFormEnd">
					<h2>어워드 게시판</h2>

					<tr>
						<th>제목</th>
						<td><%=a.getAwardName()%></td>
					</tr>
					<tr>
						<th>작성자</th>
						<td><%=a.getAwardWriter()%></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><%=a.getAwardContent()%></td>
					</tr>
					<tr>
						<th>첨부파일</th>
						<td>
							<%if (a.getAwardOriginalFilename() != null) {%> 
							<a href="javascript:fn_fileDown('<%=a.getAwardOriginalFilename()%>','<%=a.getAwardRenamedFilename()%>')">
								<img alt="첨부파일" src="<%=request.getContextPath()%>/images/file.png" width="16px"><%=a.getAwardOriginalFilename()%>
							</a>		
						<%}%>
						</td>
					</tr>

					
				</table>

			</div>
		</div>
	</div>
	
	
</section>




<%@ include file="/views/common/footer.jsp"%>