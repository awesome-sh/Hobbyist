<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ page import="com.hobbyist.member.model.vo.Member" %>
<%@ include file="/views/common/header.jsp" %>
<%
	List<Member> list = (List)request.getAttribute("list");
	
	String pageBar = (String)request.getAttribute("pageBar");
	int cPage = (int)request.getAttribute("cPage");
	int numPerPage = (int)request.getAttribute("numPerPage");
	int totalContent = (Integer)request.getAttribute("totalContent");
%>

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
				<li onclick="location.href='<%= request.getContextPath() %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li onclick="location.href='#'">MEMBER MANAGEMENT | 회원관리</li>
				<li onclick="location.href='#'">관리자페이지 > 회원관리</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div class="adminShop_right">
			<div id="adminShop_main">
				<table>
					<tr>
						<td colspan="11"><h3>회원 목록</h3>
					</tr>
					
					<tr>
						<td colspan="11" style="text-align:center;">총 ( <%=totalContent %> ) 건의 회원 목록</td>
					</tr>
				
				
					<tr>
						<th style="width:50px;">No</th>
						<th style="width:120px;">이름</th>
						<th style="width:250px;">이메일</th>
						<th style="width:200px;">닉네임</th>
						<th style="width:200px;">전화번호</th>
						<th style="width:100px;">생년월일</th>
						<th style="width:315px;">주소</th>
						<th style="width:90px;">가입일</th>
						<th style="width:300px;">메모</th>
						<th style="width:50px;">삭제</th>
					</tr>
					<%if(!list.isEmpty()) {
						for(Member m : list) {
					%>
						<tr>
							<td><%=m.getMemberNo()%></td>
							<td><a href="<%=request.getContextPath()%>/adminMemberUpdateView.do?memberEmail=<%=m.getMemberEmail()%>"><%=m.getMemberName()%></a></td>
							<td><%=m.getMemberEmail()%></td>
							<td><%=m.getMemberNickname()%></td>
							<td><%=m.getMemberPhone()%></td>
							<td><%=m.getMemberBirthday()%></td>
							<td><%=m.getMemberAddress()%></td> 
							<td><%=m.getMemberEnrolldate()%></td>
							<td><%=m.getMemberMemo()%></td>
							<td><input type="button" id="deleteMember" value="X" onclick="fn_deleteMember('<%=m.getMemberEmail()%>')"/></td>
						</tr>
					<%}
					} else { %>
						<tr>
							<td colspan="11" align="center">검색결과가 없습니다.</td>
						</tr>
					<%} %>
					<tr>
						<td colspan="11" class="pageBar"><%=pageBar %></td>
					</tr>
				</table>
			</div>
				<form action="<%=request.getContextPath() %>/adminMemberDelete.do" method="post" name="adminMemberListFrm">
					<input type="hidden" id="memberEmailAdmin" name="memberEmailAdmin"/>
				</form>
		</div>
	</div>
</section>
<script>
	function fn_deleteMember(email){
		if(confirm('삭제하시겠습니까?')) {
			$('#memberEmailAdmin').val(email);
			adminMemberListFrm.submit();
		} else{
			return;
		}
	}
	
</script>
<%@ include file="/views/common/footer.jsp" %>