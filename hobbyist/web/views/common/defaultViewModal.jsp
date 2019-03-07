<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/css/defaultViewModal.css">
<script src="<%= request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
</head>
<body>
<section>

	<!-- 임시버튼 -->
	<button onclick="fn_defaultViewAjax()">모달창 띠우기</button>
	
	
	<!-- 모달창 소스------------------------------------------------------------ -->
	<div id="defaultViewModal" class="defaultViewModal">
		<div class="defaultViewModalBox">
			<button type="button" class="defaultViewClose">X</button>
			<div class="defaultViewHeader">
				<div>
				</div>
			</div>
			<div class="defaultViewBody">
				
			</div>
			<div class="defaultViewfooter">
				<div>
					<button type="button" class="button1">수정하기</button>
					<button type="button" class="button1">삭제하기</button>
				</div>
			</div>
		</div>
	</div>
	<script>
		function fn_defaultViewAjax(index) {
			$(".defaultViewModal").css("display", "flex");
			$(".defaultViewBody").animate({ scrollTop: 0 }, 0);
		}
	
		$(function () {
			$(".defaultViewClose").click(function () {
				$(".defaultViewModal").css("display", "none");
			})
		})
	</script>
	<!-- ------------------------------------------------------------------- -->
	
	
</section>
</body>
</html>