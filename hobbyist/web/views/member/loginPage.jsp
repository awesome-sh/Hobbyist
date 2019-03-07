<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.mail.Message"%>
<%@page import="javax.mail.Address"%>
<%@page import="javax.mail.internet.InternetAddress"%>
<%@page import="javax.mail.internet.MimeMessage"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.Authenticator"%>
<%@page import="java.util.Properties"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.hobbyist.member.model.dao.MemberDao" %>
<%@page import="com.hobbyist.member.model.vo.Member" %>

<%
	Cookie[] cookies = request.getCookies();
	String saveEmail = null;
	if(cookies != null) {
		for(Cookie c : cookies) {
			System.out.println("c : "+ c.getName() +" "+ c.getValue());
			String key = c.getName();
			String value = c.getValue();
			if(key.equals("saveEmail")) {
				saveEmail = value;
				break;
			}
		}
	}
%>


<div id="loginSection">
	<div class="container">
		<button type="button" class="loginPageClose" onclick='fn_loginPageClose()'>X</button>
		<div class="message signup">
			<div class="btn-wrapper">
				<button  id="signup" style="width: 210px;">로그인</button><Br>
				<button  id="login" style="width: 210px;">회원가입</button>
				<input type="button" id="searchId" class="loginInputCss searchIdButton" value="아이디찾기" onclick="searchId()"/>
				<input type="button" id="searchPwd" class="loginInputCss searchPwdButton" value="비밀번호 찾기" onclick="searchPwd()"/>
			</div>
		</div>
		
		<form action="<%= request.getContextPath() %>/member/loginMember" id="loginMember" method="post"
			onsubmit="return fn_loginMember();" autocomplete="off">
			<div class="form form--memberLogin">
				<div class="form--heading"></div>
				<input type="text" class="loginInputCss" name="loginMemberEmail" placeholder="Email" id="loginMemberEmail" value="<%= saveEmail!=null?saveEmail:"" %>" autocomplete="off">
				<input type="password" class="loginInputCss" name="memberPassword" placeholder="Password" autocomplete="new-password">
				<div class="checkboxDiv">
					<input type="checkbox" class="loginInputCss" name="saveEmail" id="saveEmail" <%= saveEmail!=null?"checked":"" %> />
					<label for="saveEmail">EMAIL 저장</label>
				</div>
				<button style="width: 230px;" type="submit">L O G I N</button>
			</div>
		</form>

		<form action="<%= request.getContextPath() %>/member/memberEnroll" id="memberEnroll" method="post" name="memberEnrollFrm"
				onsubmit="return fn_memberEnroll();" autocomplete="off" enctype="multipart/form-data">
			<div class="enroll signup" id="memberEnrollSignup">
			<br>
				<img src="<%= request.getContextPath() %>/images/joinlogo.png" width="120px">
				<input type="text" class="loginInputCss" name="memberEmail" placeholder="Email" id="memberEmail"><Br>
				<select name="memberEmailaddress" id="memberEmailaddress">
					<option>선택해주세요.</option>
					<option>@naver.com</option>
					<option>@daum.net</option>
					<option>@gmail.com</option>
					<option>@yahoo.co.kr</option>
					<option>@empal.com</option>
					<option>@dreamwiz.com</option>
					<option>@orgio.net</option>
					<option>@korea.com</option>
					<option>@nate.com</option>
				</select>
				<div>
					<button type="button"  id="comfirmIdButton">아이디체크</button>
					<button type="button"  id="emailCheckButton" onclick="fn_emailSend()">이메일인증</button>
				</div>
				<div id="send"></div>
				<input type="hidden" id="confirmIdButtonHidden" value="2"/>
				<input type="password" class="loginInputCss" name="memberPassword" id="memberPassword" placeholder="Password" autocomplete="new-password">
				<input type="password" class="loginInputCss" name="confirmPassword" id="confirmPassword" placeholder="Confirm password"><br>
				<button style="width: 230px; " type="button" id="signupStart"  onclick="return fn_nextJoin()">다음</button>
			</div>
		
			<div class="endForm">
				<div class="memberImageBox">
					<div class="memberImage">
						<div class="inputBackground" id="image_section">
							<div>
								<img class="fileUploadIcon" width="100px" height="100px"/>
								<input type="file" class="profileImgBox" name="memberOriginalImage" id="memberOriginalImage"/>
							</div>
						</div>
						<img alt="camaraIcon" class="image_section" src="<%= request.getContextPath() %>/images/camaraIcon30.png"/>
					</div>
				</div>
<!-- 
				<div class="memberImage">
					<div class="inputBackground">
						<input type="file" class="inputImage" name="memberOriginalImage" id="memberOriginalImage"/>
					</div>
				</div> -->

				<input style="width: 140px;" type="text" class="inputText" name="memberNickname" placeholder="NickName" id="memberNickname"/>
				<input type="button" class="loginInputCss comfirmNickNameButton" value="중복확인" id="comfirmNickNameButton">
				<input type="hidden" id="comfirmNickNameButtonHidden" value="2"/>
				<input type="text" class="loginInputCss inputText" name="memberName" id="memberName" placeholder="Name" />
				<input type="number" class="loginInputCss inputText" name="memberBirthday" id="memberBirthday" placeholder="Birthday ex)940308"/>
				<input type="text" class="loginInputCss inputText" name="memberPhone" id="memberPhone" placeholder="Phone"/>
				<div id="selectBtn">
					<button type="button" id="beforeBtn" class="beforeBtn">이전</button>
					<button type="submit" class="nextBtn"  onclick="return fn_submit()">가입</button>
				</div>
			</div>
		</form>
		
		<%!
		public int getRandom(){
			int random=0;
			random=(int)Math.floor((Math.random()*(99999-10000+1)))+10000;
			return random;
		}
		%>   
		
		<form name="emailSendFrm" method="post">
			<input type="hidden" name="emailSend"/>
			<input type="hidden" id="numberCode" name="numberCode" value="<%=getRandom()%>" readonly/>
		</form>
	</div>
</div>#3a4763

		
<script>
	/* 기능 구현 */
	function fn_loginMember() {
		return true;
	}
	function fn_memberEnroll() {
		return true;
	}

	/* 애니메이션 기능 */
	$("#signup").click(function () {
		$(".message").css("transform", "translateX(100%)");
		$(".enroll").css("transform", "translateX(0)");
		
		if ($(".message").hasClass("login")) {
			$(".message").removeClass("login");
		}
		$(".message").addClass("signup");
	});

	$("#login").click(function () {
		$(".message").css("transform", "translateX(0)");
		$(".enroll").css("transform", "translateX(0)");
		if ($(".message").hasClass("login")) {
			$(".message").removeClass("signup");
		}
		$(".message").addClass("login");
	});

	/* $("#signupStart").click(function () {
		$(".enroll").css("transform", "translateX(-200%)");
	}); */

	$("#beforeBtn").click(function () {
		$(".enroll").css("transform", "translateX(0)");
	});

	$("#nextBtn").click(function () {
		$(".enroll").css({"transform":"translateX(0)"});
	});
	
</script>

<script>
	/* 로그인 창 닫기 분기 처리 */
	<%-- 
	$(function () {
		var closeFlag = true;
		$("#loginSection .container").click(function () {
			closeFlag = false;
		})
		$("#loginSection .container").mouseleave(function () {
			closeFlag = true;
		})
		$("#loginSection").on("click", function () {
			if (closeFlag) {
				location.href = "<%= request.getContextPath()%>/member/loginPage";
			}
		})
	});
	 --%>
	function fn_loginPageClose() {
		$("#loginSection").css("display","none");
	}
</script>

<!-- 회원가입 창 띠우기 -->
<%
	//회원가입이 실패했을때 변수값을 받아서 처리
	boolean signupWindow = false;
	if(request.getAttribute("signupWindow")!=null) {
		signupWindow = (boolean)request.getAttribute("signupWindow");
	}
%>
<% if(signupWindow) { %>
<script>
	$(".message").css("transform", "translateX(0)");
	$(".enroll").css("transform", "translateX(0)");
	if ($(".message").hasClass("login")) {
		$(".message").removeClass("signup");
	}
	$(".message").addClass("login");
</script>
<% } %>

<script>
	//다음버튼 눌렀을때 
	function fn_nextJoin(){
		
		if($('#memberEmail').val().trim().length==0){
			alert("아이디를 입력해주세요");
			$('#numberEmail').focus();
			return false;
		}
		
		//비밀번호
		if($('#memberPassword').val().trim().length==0){
			alert("비밀번호를 입력해주세요");
			$('#memberPassword').focus();
			return false;
		} 
			
		if($('#confirmPassword').val().trim().length==0){
			alert("비밀번호를 한 번더 입력해주세요");
			$('#confirmPassword').focus();
			return false;
		}

		//이메일 주소 선택
		if($('#memberEmailaddress option:selected').val()=='선택해주세요.'){
			alert("이메일주소를 선택해주세요.");
			return false;
		}
		
		//아이디 중복확인 누르게 하기
		if($('#confirmIdButtonHidden').val()==2||$('#confirmIdButtonHidden').val()==1){
			alert('아이디 중복체크 버튼을 눌려주세요.');
			return false;
		} 
		
		 //이메일 인증버튼 누르기 하기
		if($('#code_check').val()==null || $('#code_check').val().trim().length==0 || $('#emailCheckButton').val()!='인증완료'){
			alert('이메일 인증버튼을 눌러주세요');
			return false;
		} 
		
		$(".enroll").css("transform", "translateX(-100%)"); 
		console.log($('#code_check').val());
		return true;
	} 
	
	/* //비밀번호 일치하는지 확인하기
	//비밀번호 정규화
	$(function(){
		$('#confirmPassword').blur(function(){
			var memberPassword = $('#memberPassword').val();
			var confirmPassword = $('#confirmPassword').val();
			var pwFlag = /(?=.*\d{1,50})(?=.*[~`!@#$%\^&*()-+=]{1,50})(?=.*[a-zA-Z]{2,50}).{8,50}$/; //비밀번호 정규화 숫자와 영문자 조합으로 8~15글자 
			
			if(!pwFlag.test($('#memberPassword').val())){
				alert('비밀번호의 규칙을 지켜주세요. \n ● 적어도 하나 이상의 영문 소문자  \n ● 적어도 하나 이상의 숫자 \n ● 적어도 하나 이상의 특수문자 \n ● 길이 8~15글자 ');
				$('#memberPassword').val('');
				$('#confirmPassword').val('');
				$('#memberPassword').focus();
			} else if(memberPassword.trim()!=confirmPassword.trim()) {
				alert('패스워드가 틀렸습니다.');
				$('#memberPassword').focus();
				$('#memberPassword').val('');
				$('#confirmPassword').val('');
			} 
		});
	}); */
	
	//아이디 정규화
	/* $(function(){
		$('#memberEmail').blur(function(){
			//아이디 정규화하기
			var idFlag = /^[a-z](?=.{0,28})[\w]{6,15}$/; //_제외한 특수문자 안되고,영문+숫자
			
			if(!idFlag.test($('#memberEmail').val())){
				alert("아이디는 7~15자 내로 입력해주세요.\n('_'제외한 특수문자 사용불가, 숫자 필수아님, 한글입력불가)");
				$('#memberEmail').val('');
			}
		});
	}); 
	
	//생년월일 6글자 이상 입력하는 경우 or 6글자 미만인 경우 경고문 처리
	$(function(){
		$('#memberBirthday').blur(function(){
			var memberBirthday = $('#memberBirthday').val();
			if(memberBirthday.trim().length>6 || memberBirthday.trim().length<6){
				alert('생년월일을 확인해주세요. 생년월일은 6글자로 입력해주세요 \n예시 ) 1994년 3월 8일생인 경우 -> 940308');
				$('#memberBirthday').val('');
			}
		});
	});
	
	/* $(function(){
		$('#memberName').blur(function(){
			var nameFlag = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
			if(!nameFlag.test($('#memberName').val())|| $('#memberName').val().trim().length==0){
				alert('이름은 한글 또는 영어로 입력해주세요. 띄어쓰기는 사용 할 수 없습니다.');
				$('#memberName').val('');
				
			};
		});
	}); */
	
</script>

<script>
	//아이디 중복확인하기
	$('#comfirmIdButton').click(function(){
		var memberEmail = $('#memberEmail').val();
		var memberEmailaddress = $('#memberEmailaddress').val();
		var finalEmail = memberEmail+memberEmailaddress;
		console.log(memberEmail);
		$.ajax({
			url:"<%=request.getContextPath()%>/emailCheck.do?memberEmail="+memberEmail+"&memberEmailaddress="+memberEmailaddress,
			type:"get",
			dataType:"text",
			success:function(result){
				if(memberEmail.trim().length=0){
					alert('아이디를 입력해주세요. 아이디는 6~12글자 사이만 가능합니다.');
				} 
				else if(memberEmailaddress=='선택해주세요.'){
					alert('이메일 주소를 선택해주세요.');
				}
				else if(result>0){
					alert("사용할 수 없는 아이디 입니다. 다시 입력해주세요.");
					$('#confirmIdButtonHidden').val(result);
					$('#memberEmail').val('');
				} else {
					alert("사용할 수 있는 아이디 입니다.");
					$('#confirmIdButtonHidden').val(result);
					$('#comfirmIdButton').val('중복확인함');
					$('#comfirmIdButton').css('background-color','#D3D3D3');
				}
			}
		});
	});
</script>

<script>
	//닉네임 중복 확인하기 
	$('#comfirmNickNameButton').click(function(){
		var memberNickname = $('#memberNickname').val();
		$.ajax({
			url:"<%=request.getContextPath()%>/nickname.do?memberNickname="+memberNickname,
			data:memberNickname=memberNickname,
			type:"post",
			dataType:"text",
			success:function(result){
				console.log(result);
				if(memberNickname.trim().length<=0 || memberNickname.trim().length>9){
					alert('닉네임은 최소 2글자에서 최대 8글자까지 입력해주세요.');
					$('#memberNickname').val('');
					$('#memberNickname').focus();
				} else if(result>0) { //기존의 닉네임이 있는 경우
					alert('사용할 수 없는 닉네임입니다. 다시 입력해주세요.');
					$('#comfirmNickNameButtonHidden').val(result);
					emailCheckButton
				} else {
					alert('사용할 수 있는 닉네임입니다.');
					$('#comfirmNickNameButtonHidden').val(result);
					$('#comfirmNickNameButton').val('중복확인함');
					$('#comfirmNickNameButton').css('background-color','#D3D3D3');
				}
			}
		});
	});

</script>
	
<script>
	//가입하기 버튼 눌렀을 때 조건문
	function fn_submit(){
		if($('#memberNickname').val().trim().length==0){
			alert('닉네임을 입력해주세요.');
			$('#memberNickname').focus();
			return false;
		}
		
		if($('#memberName').val().trim().length==0){
			alert('이름을 입력해주세요.');
			$('#memberName').focus();
			return false;
		}
		
		if($('#memberBirthday').val().trim().length==0){
			alert('생년월일을 입력해주세요. 예시)940308');
			$('#memberBirthday').focus();
			return false;
		} 
		
		if($('#memberBirthday').val().trim().length<6 || $('#memberBirthday').val().trim().length>6){
			alert('생년월일을 6자리로 입력해주세요. 예시)940308');
			$('#memberBirthday').focus();
			$('#memberBirthday').val('');
			return false;
		} 
		
		if($('#memberPhone').val().trim().length==0){
			alert("핸드폰 번호를 입력해주세요.('-없이 입력')");
			return false;
		}
		
		if($('#memberPhone').val().trim().length<11 || $('#memberPhone').val().trim().length>11){
			alert("핸드폰번호를 확인해주세요.('-없이 입력')");
			return false;
			
		}
		//닉네임 중복확인 누르게 하기
		if($('#comfirmNickNameButtonHidden').val()==2||$('#comfirmNickNameButtonHidden').val()==1){
			alert('아이디 중복체크 버튼을 눌려주세요.');
			return false;
		} 
		return true;
	}
</script>

<script>
	//회원 사진 보여주기
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function(e) {
                $('.fileUploadIcon').attr('src', e.target.result)
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
	//메일인증 새창열기
	function fn_emailSend() {
		if($('#memberEmail').val().trim().length==0){
			alert("아이디를 입력해주세요");
			$('#numberEmail').focus();
			return false;
		} else if($('#memberEmailaddress option:selected').val()=='선택해주세요.'){
			alert("이메일주소를 선택해주세요.");
			return false;
		} else if($('#confirmIdButtonHidden').val()==2||$('#confirmIdButtonHidden').val()==1){
			alert('아이디 중복체크 버튼을 눌려주세요.');
			return false;
		} else {
			var flag = confirm("인증 메일을 보내시겠습니까?");
			if(flag==true){
				var memberEmail=$('#memberEmail').val();
				var memberEmailaddress=$('#memberEmailaddress').val();
				var numberCode=$('#numberCode').val();
				console.log(memberEmail);
				console.log(memberEmailaddress);
				console.log(numberCode);
				var url='<%=request.getContextPath()%>/emailSend.do?memberEmail='+memberEmail+'&memberEmailaddress='+memberEmailaddress+'&code_check='+numberCode;
				var title = "_blank";
				var option ="left=500px, top=100px, width=400px, height=400px, menubar=no, status=no, scrollbars=no";
				var popup = window.open(url, title, option);
				
				var inputTag = '<input type="hidden" name="code_check" id="code_check" value="<%=getRandom() %>" readonly="readonly"/>';
				$('#send').html(inputTag);
			} else {
				var inputTag = '<input type="hidden" name="code_check" id="code_check" value="" readonly="readonly"/>';
				$('#send').html(inputTag);
			}
		} 	
	}
	
	//아이디찾기 새창열기
	function searchId(){
		var url='<%=request.getContextPath()%>/views/member/searchId.jsp';
		var title = "_blank";
		var option ="left=500px, top=100px, width=400px, height=400px, menubar=no, status=no, scrollbars=no";
		var popup = window.open(url, title, option);
		
		var numberCode=$('#numberCode').val();
		
		var inputTag = '<input type="hidden" name="code_check" id="code_check" value="<%=getRandom() %>" readonly="readonly"/>';
		$('#send').html(inputTag);
		console.log(numberCode);
		
	}
	
	//비밀번호 찾기 새창열기
	function searchPwd(){
		var url='<%=request.getContextPath()%>/views/member/searchPwd.jsp';
		var title = "_blank";
		var option ="left=500px, top=100px, width=400px, height=400px, menubar=no, status=no, scrollbars=no";
		var popup = window.open(url, title, option);
	}
</script>


