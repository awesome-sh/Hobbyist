<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hobbyist.member.model.vo.Member" %>
<%
	Member member = (Member)request.getAttribute("member");
	String numberCode = (String)request.getAttribute("numberCode");
	String finalEmail = (String)request.getAttribute("finalEmail");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입 > 이메일인증</title>
<script src="http://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/emailCheck.css">
</head>
<body>
	<div class="emailSendCheck" id="emailSendCheck">
		<form action="<%=request.getContextPath()%>/emailSend.do" method="post" name="emailSendFrm" id="emailSendForm">
			<div>
				<div>
					<img src="<%= request.getContextPath() %>/images/logo.jpg" width="140px">
					<h3>인증메일이 전송되었습니다!<br>이메일을 확인해주세요.</h3>
				</div>
				<div class="emailSendImg"><img src="<%=request.getContextPath()%>/images/mail.png" width="60px"></div>
				
				<div>
					<input type="text" class="EmailInputText" value="<%=finalEmail %>" readonly/><br>
					<input type="hidden" name="code_check" id="code_check" value="<%=numberCode%>"/>					
					<input type="text" class="EmailInputText" id="inputEmailcheck" placeholder="인증번호를 입력해주세요.">
					<input type="hidden" id="inputEmailcheckHidden" name="inputEmailcheckHidden" value="인증하기" onclick="codesuc()"/>
				</div>
				
				<div id="confirm"></div>
				
				<div>
					<button type="button" id="emailBeforeBtn" class="emailSendCheckButton" onclick="fn_cancel()" >취소</button>
					<button type="button" id="emailAfterBtn" class="emailSendCheckButton" onclick="fn_comfirm()">확인</button>
				</div>
			</div>
		</form>
	</div>
</body>

<script>
	function fn_comfirm(){
		var v1 = $('#code_check').val(); //인증 코드 고유의 값
		var v2 = $('#inputEmailcheck').val(); //사용자가 입력한 인증코드 값
		
		if(v1==v2){
			alert('인증성공!');
			opener.memberEnrollFrm.emailCheckButton.value="인증완료"
			$('#emailCheckButton',opener.document).css('background-color','#D3D3D3');
			self.close();
		} else {
			$('#confirm').text('잘못된 인증번호입니다.');
			$('#confirm').css('color','red');
		}
	}
	
	function fn_cancel(){
		var v1 = $('#code_check').val(''); //인증 코드 고유의 값
		var v2 = $('#inputEmailcheck').val(''); //사용자가 입력한 인증코드 값
		self.close();
	}
</script>

<script>
	function codesuc(){
		window.close();
	}
	/* setTimeout(function(){		
		$('#sendEmailBtn').attr('disable','false');
	},3000); */
</script>
</html>