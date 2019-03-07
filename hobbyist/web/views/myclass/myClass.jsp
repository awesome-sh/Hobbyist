<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.myclass.model.vo.MyClass, com.hobbyist.shop.model.vo.Shop" %>

<%
	List<MyClass> myclass = (List)request.getAttribute("MyClass");
	List<Shop> shop = (List)request.getAttribute("Shop");
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

<section id="myclass">
	<div class="myclass_content">
	<br>
		<table>
			<tr>
				<th style="width: 200px; "><p class="wow fadeInUp" data-wow-delay="0.1">내 클래스</p></th>
				<td style="width: 700px; text-align: left; padding-left: 20px; " class="wow fadeInUp" data-wow-delay="0.2"><h3 class="wow fadeInUp" data-wow-delay="0.3">클래스 PIN CODE 입력</h3>클래스를 등록하시려면 클래스키트와 함께 동봉된 하비스트 클래스 PIN CODE를 입력해주세요<br>* PIN CODE 등록 시, 교환 및 환불 불가능 (키트 불량은 교환가능합니다)</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: right"><input type="text" id="pin" name="class_pin" placeholder="PIN CODE (숫자)"/><button style="width: 110px; height: 36px; margin:0; background-color:#575b69;border: 2px solid #575b69; color: #fff;" onclick="fn_pincheck()">PIN CODE 등록</button></td>
				
			</tr>
			<tr>
				<th colspan="2" style="text-align: left; padding-left:30px;"><p class="wow fadeInUp">등록된 내 클래스 목록</p></th>
			</tr>
			<tr>
				<td style="text-align:center;" colspan="2">
			<%
					for(MyClass mc : myclass) {
						for(Shop s : shop) {
							if(mc.getMyClassClass()==s.getShopNo()) {
			%>
				<div class="myclass_lecture wow fadeInUp" onclick="location.href='<%=request.getContextPath() %>/myClassView?no=<%= s.getShopNo() %>'" style="cursor:pointer;">
					<ul>
							<li><%= s.getShopCate() %>&nbsp;클래스</li>
							<li><img src="<%= request.getContextPath() %>/upload/shop/images/<%= s.getShopImage1() %>" width="100%"></li>
							<li><%= s.getShopName() %></li>
							<li>남은기한 : 무제한</li>
							<li><div class="progress"><%= mc.getMyClassProgress().equals("N") ? "클래스를 시작해보세요" : "이미 수강한 클래스 입니다" %></div></li>
							<li><button onclick="location.href='<%=request.getContextPath() %>/myClassView?no=<%= s.getShopNo() %>';">강좌보기</button></li>
					</ul>
				</div>
			<% 
						}
					}
				}
			%>
			</td>
			</tr>
		</table>
		<script>
			function fn_pincheck() {
				var pin = $('#pin');
				if(pin.val().trim().length==0) {
					swal('PIN CODE를 입력해주세요')
				} else {
					if(confirm('클래스를 등록하시겠습니까? \n *클래스 등록 시, 취소 불가')) {
						$.ajax({
							url : '<%= request.getContextPath() %>/myClassInsert',
							data : 'pin=' + pin.val() + '&member=<%=logginMember.getMemberEmail()%>',
							type: 'POST',
							dataType : 'text',
							success: function(data) {
								alert(data);
								location.href="<%=request.getContextPath() %>/myClass?member=<%= logginMember.getMemberEmail() %>";
							}
						});
					} else {
						swal('PIN CODE 등록 취소!');
					}
				}
			}
			
			
			var pro = $('.progress2');
			pro.eq(1).css("background-color","#e7d04c");
		</script>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>