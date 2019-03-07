<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>

<section id="myPage">
	<div class="myPage_content">
		<div class="myPage_top" id="myPage_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Member</li>
				<li>마이 페이지에 오신 걸 환영합니다.</li>
			</ul>
			
		</div><br>
		
		<!-- 마이페이지메뉴는 중복되기때문에 myPage_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/mypage/myPage_menu.jsp" %> 
		<!-- 마이페이지 메뉴 인클루드 끝 -->

		<div class="myPage_MemberPwd_right">
			<div id="myPage_main">
				<h2>비밀번호 변경하기</h2><br><Br>
			</div>
		<form action="<%=request.getContextPath()%>/changePwd.do" name="changePwdFrm" method="post">
			<table>
				<tr>
					<th style="width: 150px;">아이디</th>
					<td style="width: 300px;"><%=logginMember.getMemberEmail()%><input type="hidden" id="memberEmail" name="memberEmail" value="<%=logginMember.getMemberEmail()%>"/></td>
				</tr>
				<tr>
					<th>기존비밀번호</th>
					<td>
					<input type="hidden" id="memberEmail" name="memberEmail" value="<%=logginMember.getMemberEmail()%>"/>
					<input type="password" id="memberPassword" name="memberPassword" /></td>
				</tr>
				<tr>
					<th>변경할 비밀번호</th>
					<td><input type="password" id="memberPassword_new" name="memberPassword_new"/></td>
				</tr>
				<tr>
					<th>비밀번호 확인</th>
					<td> <input type="password" id="memberPassword_newCheck" name="memberPassword_newCheck"/></td>
				</tr>
				<tr>
					<th colspan="2">
						<button type="submit" id="submit" name="submit" onclick="fn_submit()">비밀번호 변경</button>
						<button type="reset" id="reset" name="cancel">취소</button>
					</th>
				</tr>
			</table>
		</form>
		
		<%-- <form action="<%=request.getContextPath() %>/changePwd.do" name="changePwdFrm" method="post"> --%>

		</div>
	</div>
</section>
<%@ include file="/views/common/footer.jsp" %>