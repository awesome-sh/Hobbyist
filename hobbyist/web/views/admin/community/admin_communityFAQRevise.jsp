<%@page import="com.hobbyist.board.model.vo.BoardFAQ"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
    
<%
	BoardFAQ b = (BoardFAQ)request.getAttribute("b");
%>

<%@ include file="/views/common/header.jsp" %>

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
				<li onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'"><img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>Administrator</li>
				<li>오늘방문자 : 4205　　　어제방문자 : 8243　　　전체방문자 : 52483094</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->

		<div id="admin_right" class="admin_FAQright">
			<form action="<%=request.getContextPath()%>/admin/community/adminCommunityFAQReviseEnd?boardFAQNo=<%=b.getBoardFAQNo()%>" method="post">
				<div class="FAQView_top">
					<input type='text' name='title' value=<%=b.getBoardFAQTitle()%>>
				</div>
				<div class="FAQView_middle">
					<div id="info_head">
					
						카테고리 : <select name="category">
							<option value="changeAndCancel" <%="changeAndCancel".equals(b.getBoardFAQCategory())?"selected":""%>>변경/취소</option>
							<option value="depositAndDelivery" <%="depositAndDelivery".equals(b.getBoardFAQCategory())?"selected":""%>>입금/배송</option>
							<option value="exchangeAndReturn" <%="exchangeAndReturn".equals(b.getBoardFAQCategory())?"selected":""%>>교환/반품</option>
							<option value="therInquiries" <%="therInquiries".equals(b.getBoardFAQCategory())?"selected":""%>>기타문의</option>
							<option value="saleAndBenefits" <%="saleAndBenefits".equals(b.getBoardFAQCategory())?"selected":""%>>할인/혜택</option>
							<option value="memberAndBenefits" <%="memberAndBenefits".equals(b.getBoardFAQCategory())?"selected":""%>>회원/혜택</option>
							<option value="orderAndLookup" <%="orderAndLookup".equals(b.getBoardFAQCategory())?"selected":""%>>주문/조회</option>
							<option value="paymentInquiry" <%="paymentInquiry".equals(b.getBoardFAQCategory())?"selected":""%>>결제문의</option>
							<option value="soldOutAndWearing" <%="soldOutAndWearing".equals(b.getBoardFAQCategory())?"selected":""%>>품절/입고</option>
							<option value="contactUs" <%="contactUs".equals(b.getBoardFAQCategory())?"selected":""%>>상품문의</option>
						</select>
						작성일 : <%=b.getBoardFAQDate()%>
					</div>
					<div id="FAQview_content">
						<textarea name="content" id="editor1"><%=b.getBoardFAQContent()%></textarea>
					</div>
					
					<div id="FAQview_bottom">
						<button type="button" onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQView?boardFAQNo=<%=b.getBoardFAQNo()%>'">취소</button>
						<button type="submit">완료</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</section>

<script>

	CKEDITOR.replace( 'editor1' );
	CKEDITOR.add

	function validate() {
		for(var instanceName in CKEDITOR.instances){
		    CKEDITOR.instances[instanceName].updateElement();
		}
		var title = $('[name=title]').val();
		var content = CKEDITOR.instances.editor1.getData();
		if(title.trim().length == 0) {
			alert("제목은 공란으로 작성할 수 없습니다.");
			$('[name=title]').focus();
			return false;
		} else if(content.trim().length == 0) {
			alert("내용은 공란으로 작성할 수 없습니다.");
			CKEDITOR.instances.editor1.focus();
			return false;
		} else if(title.length > 16) {
			alert("제목을 50 bytes를 초과하였습니다.");
			return false;
		} else if(content.length > 2000) {
			alert("내용을 3000 bytes를 초과하였습니다.");
			return false;
		}
		return true;
	}
	
</script>



<%@ include file="/views/common/footer.jsp" %>