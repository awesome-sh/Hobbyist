<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	int boardNo = (int)request.getAttribute("boardNo");
	String loginUser = (String)request.getAttribute("loginUser");
	String reportedUser = (String)request.getAttribute("reportedUser");
	String reportedContent = (String)request.getAttribute("reportedContent");
	String boardMainCategory = (String)request.getAttribute("boardMainCategory");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>R E P O R T</title>
<style>

	body, html {
		margin: 10;
		padding: 0;
	}
	body {
		background-image: url("<%=request.getContextPath()%>/images/reportbg.png");
	}
	
	#board_report_section {
		width: 95%;
		height: 330px;
		background-color: rgba(255, 255, 255, 0.2);
		margin-left: 2.5%;
		border-radius: 12px;
	}

	h2 {
		font-size: 21px;
		padding-bottom: 8px;
		margin-left: 8px;
		margin-right: 8px;
		border-bottom: 2px solid rgba(255, 255, 255, 0.4);
		color: #FFFFF5;
		text-align: center;
	}
	
	select {
		height: 35px;
		width: 90%;
		margin-left: 5%;
		outline: none;
		border: none;
		border-radius: 3px;
		color: rgb(159, 159, 159);
	}
	
	select:hover {
		border: 1px solid black;
		background-color: #FCFFFF;
	}
	
	textarea {
		outline: none;
		border-radius: 8px;
		width: 90%;
		margin-top: 8px;
		margin-left: 4.5%;
		padding-top: 8px;
		padding-left: 8px;
		background-color: rgba(255, 255, 255, 0.3);
		color: white;
	}
	
	.content {
		margin-top: 12px;
		margin-right: 5px;
		margin-left: 4.5%;
		padding: 8px;
		border-radius: 6px;
		border: 1px solid rgba(255, 255, 255, 0.4);
		display: inline-block;
		background-color: rgba(0, 0, 0, 0.2);
		color: rgba(255, 255, 255, 0.7);
	}
	
	.reported {
		color: rgba(255, 0, 0, 0.8);
	}
	
	button {
		width: 120px;
		height: 40px;
		border-radius: 6px;
		outline: none;
		margin-top: 5px;
		margin-left: 150px;
		border: 1px solid #cacaca;
		background: none;
		transition: 500ms;
		font-size: 14px;
	}
	
	button:hover {
		border: 1px solid #252525;
	    background-color: rgba(30, 30, 30, 0.7);
	    color: #fff;
	    transition: 500ms;
	}
	
	button:active {
		margin-top: 6px;
	}

</style>

</head>
<body>
	<div id="board_report_section">
		<h2>신고하기</h2>
		<form name="boardReportFrm" action="<%=request.getContextPath()%>/board/boardReportEnd">
			<select name="reportCategory">
				<option class="option">욕설 / 비방 / 음란글 작성</option>
				<option class="option">상업적 목적의 글 작성</option>
				<option class="option">광고 / 스팸</option>
				<option class="option">반복적으로 글을 도배하는 행위</option>
				<option class="option">개인정보 노출</option>
				<option class="option">기타</option>
			</select>
			
			<div class="content">신고자 : <%=loginUser%></div>
			<div class="content reported">신고대상 : <%=reportedUser%></div><div></div>
			
			<div class="content">신고 내용</div>
			<textarea name="reportContent" rows="4" style="resize: none;" placeholder="허위사실 신고시 본인이 제재가 될 수 있습니다."></textarea>
	
			<input type="hidden" name="boardNo" value="<%=boardNo%>"/>
			<input type="hidden" name="loginUser" value="<%=loginUser%>"/>
			<input type="hidden" name="reportedUser" value="<%=reportedUser%>"/>
			<input type="hidden" name="reportedContent" value="<%=reportedContent%>"/>
			<input type="hidden" name="boardMainCategory" value="<%=boardMainCategory%>"/>
			
			<button type="submit" onclick="return report()">신고접수</button>
		</form>
	</div>
	
	<script>
	
		function report() {
			if(confirm('이대로 접수 하시겠습니까?')) {
				$('[name = boardReportFrm]').submit();
				self.close();
			} else {
				return false;
			}
		}
	
	</script>
	
</body>
</html>






