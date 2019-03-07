<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String result = (String)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/emailCheck.css">
<meta charset="UTF-8">
<title>아이디찾기</title>
</head>

<body>
	<div id="searchId">
	<form action="<%=request.getContextPath()%>/searchId.do" method="post">
		<div>
			<img src="<%= request.getContextPath() %>/images/logo.jpg" width="140px">
			<h3>아이디 찾기</h3>
			<div>
				이름<br><input type="text" id="memberName" name="memberName" placeholder="이름을 입력해주세요."/><br>
				휴대폰번호<br><input type="text" id="memberPhone" name="memberPhone" placeholder="핸드폰번호를 입력해주세요."/>
			<div><br>
				<button type="submit" id="submit" onclick="fn_confirm">확인</button>
				<button type="button" id="cancel" onclick="self.close()">취소</button> 
			</div>
			<div id="confirm"></div>
			</div>
		</div>
	</form>
	</div>
	
	<script>
		function fn_confirm(){
			var memberName = $('#memberName').val();
			var memberEmail = $('#memberPhone').val();
			console.log(memberName+memberPhone);
			$.ajax({
				url:'<%=request.getContextPath()%>/searchId.do',
				data:'memberName='+memberName+'&memberPhone='+memberPhone,
				type:'post',
				dataType:'json',
				success:function(data){
					alert(data.msg);
					self.close();
				}
			}); 
			
		}
	</script>

	
	
</body>
</html>