<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="/views/common/header.jsp" %>

<%
	Member result = (Member)request.getAttribute("result");

%>
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

		<div class="myPage_MemberInfo_right">
			<div id="myPage_main">
			<Br>
				<h2>내 정보 수정하기</h2>
			</div>
			<br><Br>
			<form action="<%=request.getContextPath()%>/memberUpdate.do" method="post" enctype="multipart/form-data" name="updateMemberFrm">
				<table>
					<tr>
						<th style="width:150px;">프로필사진</th>
						<td style="width:400px;">
							<img src="<%= request.getContextPath() %>/upload/member/<%= result.getMemberRenamedImage() %>" width="150px" style="border-radius: 40px;"><br>
							<% if(result.getMemberOriginalImage()!=null){ %>
								<input type="file" class="inputImage" name="memberOriginalImage" value="<%=result.getMemberOriginalImage()%>"/>
								<span id="fname"><%=result.getMemberOriginalImage() %></span>
								<input type="hidden" name="old_file" value="<%=result.getMemberOriginalImage()%>"/>
								<input type="hidden" name="old_rename" value="<%= result.getMemberRenamedImage() %>"/>
							<%} else {%>
									<input type="file" name="memberOriginalImage"/>
							<%} %>
						</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td><%=logginMember.getMemberEmail()%><input type="hidden" id="memberEmail" name="memberEmail" value="<%=logginMember.getMemberEmail()%>" /></td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td><input style="width:140px;" type="text" id="memberNickname" name="memberNickname" value="<%=logginMember.getMemberNickname()%>"/>
						<!-- <button style="width:80px;" type="button" id="memberNicknameBtn" name="memberNicknameBtn">중복확인</button> --></td>
					</tr>
					<tr>
						<th>휴대폰번호</th>
						<td><input type="text" id="memberPhone" name="memberPhone" value="<%=logginMember.getMemberPhone()%>" /></td>
					</tr>
					<tr>
						<th>생년월일</th>
						<td><%=logginMember.getMemberBirthday()%><input type="hidden" id="memberBirthday" name="memberBirthday" value="<%=logginMember.getMemberBirthday()%>" /></td>
					</tr>
					<tr>
						<td colspan="2" style="text-align: center;">
						<br><Br>
							<button type="submit" id="submit" name="submit" onclick="return fn_memberUpdate()">수정</button>
							<button type="reset" id="cancel" name="cancel">취소</button>
							<button type="button" id="deleteMember" name="deleteMember"onclick="fn_delete()">회원탈퇴</button>
						</td>
					</tr>
				</table>
			</form>
			
			<form action="<%=request.getContextPath()%>/memberDelete.do" name="deleteFrm" method="post">
				<input type="hidden" id="deleteId" name="deleteId" value="<%=logginMember.getMemberEmail()%>" />
			</form>
		</div>
	</div>
</section>
<script>
	/* function fn_updateMember(){
		var memberBirthday = $('#memberBirthday').val();
		if(memberBirthday.trim().length()<7){
			alert('생년월일은 6글자입니다. 예)940308');
			return false;
		}
		return true;
	} */
</script>

<script>
	function fn_memberUpdate(){
		var flag=confirm('수정하시겠습니까?');
		if(flag){			
			return true;
		} else{
			return false;
		}
		
	}

</script>
<script>
	//회원 사진 보여주기
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $('#image_section').attr('src', e.target.result)
                 .width(100)
                 .height(100);
            }
            reader.readAsDataURL(input.files[0]);
        };
    }; 
    
    $('#memberOriginalImage').change(function(){
    	readURL(this);
    });
</script>

<script>
	function fn_delete(){
		var flag=confirm('삭제하시겠습니까?');
		if(flag){
			deleteFrm.submit();
			alert('삭제되었습니다.');
		} else{
			return;			
		}
	}
</script>
<%@ include file="/views/common/footer.jsp" %>