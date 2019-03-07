<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.shop.model.vo.Study" %>

<%
	Study study = (Study)request.getAttribute("Study");
%>

<%@ include file="/views/common/header.jsp" %>

<!-- 로그인 안된 상태로 왔을때 접근 막기 -->
<script>
	if(<%= logginMember!=null %>) {
		
	} else {
		alert('회원만 접근 가능합니다');
		location.href=history.back(-1);
	}
</script>

<section id="study">
	<div class="study_content">
		<div class="study_top" id="study_top">
			<ul>
				<li onclick="location.href='<%= request.getContextPath() %>/myClass?member=<%= logginMember != null ? logginMember.getMemberEmail() : "" %>'"><img src="<%= request.getContextPath() %>/images/back.png" width="16px">&nbsp;&nbsp;
				돌아가기</li>
				<li>&nbsp;&nbsp;&nbsp;<img src="<%= request.getContextPath() %>/images/progress.png" width="13px">My Class</li>
				<li>내 클래스 > 내 클래스 강좌</li>
			</ul>
		</div>
		<br>
		<div class="study_center">
				<table>
					<tr>
						<td></td>
						<td style="height: 80px; text-align:left; padding-left: 25px;"><h3><%= study.getStudyTitle() %></h3>
						<%= study.getStudySubTitle() %></td>
					</tr>
					<tr>
						<td style="width: 200px;">
						 <br>작가정보
						<br><%= study.getStudyWriter() %></td>
						<td style="width: 790px;"><%= study.getStudyVideo() %></td>
					</tr>
					<tr>
						<td></td>
						<td><%= study.getStudyContent() %></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
					</tr>
				</table>
		</div>
	</div>
	<script>
			// 강좌 페이지 상단 숨기기 및 보이기
            $(window).scroll(function(){
                var target = $('#study_top');
				var scrollTo = $(window).scrollTop();
				if(scrollTo>=70) {
					$('#study_top').css("height","0px");
				} else {
					$('#study_top').css({"height":"50px","border":"none"});
				}
                });
			
			// 다 봤어요 버튼 클릭 시, 수강현황 업데이트
			function fn_progress(no) {
				$.ajax({
					url : '<%= request.getContextPath() %>/myClassUpdate?no=' + no + '&member=<%= logginMember!=null ? logginMember.getMemberEmail() : "" %>',
					success: function(data) {
						
					}
				})
			}
        </script>
</section>

<%@ include file="/views/common/footer.jsp" %>