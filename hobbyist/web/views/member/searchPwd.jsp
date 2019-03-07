<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hobbyist.member.model.vo.Member"%>
<!DOCTYPE html>
<html>
<head>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">
<title>비밀번호 찾기</title>
<%-- <%
	String randomPwd = (String)request.getAttribute("randomPwd");
	Member result = (Member)request.getAttribute("result");
%> --%>
 <link rel="stylesheet" href="<%= request.getContextPath() %>/css/emailCheck.css">
</head>
<body>
<div id="searchPw">
	<form action="<%=request.getContextPath()%>/searchPwd.do" method="post">
		<div>
			<img src="<%= request.getContextPath() %>/images/logo.jpg" width="140px">
			<h3>비밀번호 찾기</h3>
			<div>
				이메일<br><input type="text" id="memberEmail" name="memberEmail" placeholder="이메일을 입력해주세요."/><br>
				<select name="memberEmailaddress" id="memberEmailaddress">
					<option>선택해주세요.</option>
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
					<option>@yahoo.co.kr</option>
					<option>@empal.com</optsion>
					<option>@dreamwiz.com</option>
					<option>@orgio.net</option>
					<option>@korea.com</option>
					<option>@nate.com</option>
				</select>
				<div>
					<button type="button" id="checkId" name="checkId"onclick="fn_checkId()">아이디 확인</button><button type="button" id="cancel"  onclick="self.close()">닫기</button> 
				</div>
			</div>
		</div>
	</form>
	
	<form name="pwdCodeFrm" method="post">
		<input type="hidden" id="pwdCode" name="pwdCode"/>
	</form>
	</div>
	
	<script>
	function fn_checkId(){
		var memberEmail = $('#memberEmail').val();
		console.log(memberEmail);
		var memberEmailaddress = $('#memberEmailaddress').val();
		console.log(memberEmailaddress);
		
		
		var text1 = "";
		var text2 = "";
		var result = "";
		var alphabet = "abcdefghijklmnopqrstuvwxyz";
		var num = "0123456789";
		     for( var i=0; i < 4; i++ )
		     {      
		         text1 += alphabet.charAt(Math.floor(Math.random() * alphabet.length));
		         text2 += num.charAt(Math.floor(Math.random() * num.length));
		     }
		   	result = text1+text2+"!!";
		   	console.log(result);
		
		$.ajax({
			url : '<%= request.getContextPath() %>/searchPwd.do',
			data : 'memberEmail=' + memberEmail + '&memberEmailaddress=' + memberEmailaddress +'&pwdCode=' +result,
			dataType : 'json',
			success : function(data) {
				alert(data.msg);
				self.close();
			}
		});
	}

	</script>
	

</body>
</html>