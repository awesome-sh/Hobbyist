<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%
	Member result = (Member)request.getAttribute("result");
%> 

<section id="admin">
	<div class="admin_content">
		<div class="admin_top" id="admin_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">MEMBER MANAGEMENT | 회원관리</li>
				<li onclick="location.href='#'">관리자페이지 > 회원관리</li>
			</ul>
			
		</div><br>
		
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		
		<div class="adminShopWrite_right">
			<div id="adminShopWrite_main">
				<form action='<%=request.getContextPath()%>/adminMemberUpdate.do' method="post" name="memberAdminUpdateFrm">
					<table>
						<tr>
							<th colspan="2"><h3>회원수정</h3></th>
						</tr>
						
						<tr>
							<th style="width:200px">회원번호</th>
							<td style="width:600px; text-align:left"><%=result.getMemberNo() %></td>
						</tr>
						
						<tr>
							<th>회원이름</th>
							<td style="text-align:left"><%=result.getMemberName() %></td>

						</tr>
						
						<tr>
							<th>회원이메일</th>
							<td style="text-align:left"><%=result.getMemberEmail() %></td>
							<td><input type="hidden" name="memberEmail" id="memberEmail" value="<%=result.getMemberEmail()%>"/></td>
						</tr>
						
						<tr>
							<th>닉네임</th>
							<td style="text-align:left"><input type="text" id="memberNickname" name="memberNickname" value="<%=result.getMemberNickname()%>"/></td>
						</tr>
						
						<tr>
							<th>전화번호</th>
							<td style="text-align:left"><input type="text" id="memberPhone" name="memberPhone" value="<%=result.getMemberPhone() %>" placeholder="-없이 입력해주세요."/></td>
						</tr>
						
						<tr>
							<th>생년월일</th>
								<td style="text-align:left"><%=result.getMemberBirthday() %></td>
						</tr>
						
						<tr>
							<th>회원등급</th>
							<td style="text-align:left"><input type="number" id="memberGrade" name="memberGrade" value=<%=result.getMemberGrade() %>/></td>
						</tr>
						
						<tr>
							<th>주소</th>
							<td style="text-align:left"><input type="text" id="memberAddress" name="memberAddress" value="<%=result.getMemberAddress()%>"/></td>
						</tr>
						
						<tr>
							<th>가입일</th>
							<td style="text-align:left"><%=result.getMemberEnrolldate()%></td>
						</tr>
						
						<tr>
							<th>메모</th>
							<td style="text-align:left"><input type="text" id="memberMemo" name="memberMemo" value="<%=result.getMemberMemo()%>"/></td>
						</tr>
					</table>
					<input type="submit" id="submit" value="저장하기" onclick="return fn_submit()"/>
					<input type="reset" id="cancel" value="취소"/>
				</form>
			</div>
		</div>
	</div>
</section>
<script>
	function fn_submit(){
		if(confirm('수정하시겠습니까?')){
			memberAdminUpdateFrm.submit();
			return true;
		} else {
			return false;
		}
	}
	
</script>
<%@ include file="/views/common/footer.jsp" %>