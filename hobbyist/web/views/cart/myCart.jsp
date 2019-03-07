<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.mycart.model.vo.MyCart, com.hobbyist.shop.model.vo.Shop" %>

<%	
	List<MyCart> list = (List)request.getAttribute("MyCart");
	List<Shop> shop = (List)request.getAttribute("Shop");
%>

<%@ include file="/views/common/header.jsp" %>


<section id="cart">
	<div class="cart_content">
		<div class="mycart_banner">
		<h3>취미바구니 ( MyCart )</h3>
		</div>
		<ul class="mycart_submenu">
		<li id="btn1" onclick="fn_classShopBtn()">클래스샵</li>
		<li id="btn2" onclick="fn_onedayClassBtn()">원데이클래스</li>
		</ul>
		<br>
		<table id="myCart_Ajax">
		<% 
			if(!list.isEmpty()) {
				%>
				<tr>
				<th style="width: 80px;">선택</th>
				<th style="width: 100px;">카테고리</th>
				<th style="width: 120px;"></th>
				<th style="width: 390px;">클래스</th>
				<th style="width: 170px;">옵션</th>
				<th style="width: 100px;">가격</th>
			</tr>
				<% 
				double cnt = 0.1;
				double cnt2 = 0;
				if(cnt2>=0.3) {
					cnt2 = 0;
				}
				
				for(MyCart mc : list) {
					for(Shop s : shop) {
						if(mc.getMyCartClass()==s.getShopNo()) {
		%>
			<tr class="wow fadeInUp fast" data-wow-delay="<%=cnt2+=cnt %>s">
				<td>
					<input type="checkbox" name="checkbox" value="<%= mc.getMyCartNo() %>"/>
					<input type="hidden" name="classNo" value="<%= s.getShopNo() %>"/>
				</td>
				<td><%= s.getShopCate() %></td>
				<td><img src="<%= request.getContextPath() %>/upload/shop/images/<%= s.getShopImage1()%>" width="110px"></td>
				<td style="text-align: left;  padding-left: 15px;"><h3><a href="<%=request.getContextPath() %>/shop/shopView?no=<%= s.getShopNo() %>"><%= s.getShopName() %></a></h3><%= s.getShopInfo() %></td>
				<td>
						<input type="hidden" id="classOption" name="classOption" value="<%= mc.getMyCartClassOption() %>"><%= mc.getMyCartClassOption() %>
				</td>
				<td class="price"><%= s.getShopPrice() %></td>
			</tr>
		<% }}}} else { %>
		<tr>
			<td colspan="6" style="width: 970px;"><h3>장바구니에 담긴 상품이 없습니다</h3></td>
		</tr>
		<% } %>
			<tr>
			<td colspan="3" id="priceResult" class="result"></td>
				<td colspan="3" class="result" style="text-align: right;">
					<button type="button" onclick="fn_delete('classshop')">선택삭제</button>
					<button type="button" onclick='fn_order()'>선택주문</button>
				</td>
			</tr>
		</table>
		
		<form name="orderFrm" action="<%= request.getContextPath() %>/order/order" method="POST">
		<input type="hidden" id="memberVal" name="memberVal">		
		<input type="hidden" id="cartNoVal" name="cartNoVal">
		<input type="hidden" id="selectNoVal" name="selectNoVal">
		<input type="hidden" id="classOptionVal" name="classOptionVal">
		</form>
		
		<form name="orderFrm2" action="<%= request.getContextPath() %>/order/orderOneday" method="POST">
		<input type="hidden" id="memberVal2" name="memberVal2">
		<input type="hidden" id="cartNoVal2" name="cartNoVal2">	
		<input type="hidden" id="selectNoVal2" name="selectNoVal2">
		<input type="hidden" id="classOptionVal2" name="classOptionVal2">
		</form>
		
		<script>
		
			$('#btn1').addClass("addCartSelected");
		
			function fn_classShopBtn() {
				$.ajax({
					url : '<%= request.getContextPath() %>/myCartAjaxList',
					data : 'member=<%= logginMember.getMemberEmail() %>&cartCate=classshop',
					type: 'POST',
					dataType : 'text',
					success: function (data) {
						$('#myCart_Ajax').empty();
						$('#myCart_Ajax').html(data);
						$('#btn1').addClass("addCartSelected");
						$('#btn2').removeClass("addCartSelected");
						fn_priceUpdate();
					}
				});
			}
			
			function fn_onedayClassBtn() {
				$.ajax({
					url : '<%= request.getContextPath() %>/myCartOnedayAjaxList',
					data : 'member=<%= logginMember.getMemberEmail() %>&cartCate=oneday',
					type: 'POST',
					dataType : 'text',
					success: function (data) {
						$('#myCart_Ajax').empty();
						$('#myCart_Ajax').html(data);
						$('#btn1').removeClass("addCartSelected");
						$('#btn2').addClass("addCartSelected");
						fn_priceUpdate();
					}
				});
			}
		
			
			function fn_priceUpdate() {
				$('[name=checkbox]').on('click', function() {
						var indexNo = $('#myCart_Ajax input[type=checkbox]').index(this);
						var price_temp = $('.price');
						var price = 0;
						var checkedbox = $('[name=checkbox]');
						$.each(checkedbox, function(index) {
							if(checkedbox[index].checked==true) {
								price += parseInt(price_temp[index].innerText);
							}
						})
						$('#priceResult').empty();
						$('#priceResult').append("예상결제금액 : " + price + " 원");
				});
			}
			
			$('[name=checkbox]').on('click', function() {
				var indexNo = $('#myCart_Ajax input[type=checkbox]').index(this);
				var price_temp = $('.price');
				var price = 0;
				var checkedbox = $('[name=checkbox]');
				$.each(checkedbox, function(index) {
					if(checkedbox[index].checked==true) {
						price += parseInt(price_temp[index].innerText);
					}
				})
				$('#priceResult').empty();
				$('#priceResult').append("예상결제금액 : " + price + " 원");
		});
			
			function fn_delete(cartCate) {
				var deleteNo = '';
				var checkbox = $('[name=checkbox]');
				$.each(checkbox, function(index) {
					if(checkbox[index].checked) {
						deleteNo += checkbox[index].value +',';
					}
				})
				$.ajax({
					url : '<%= request.getContextPath() %>/myCartDelete?member=<%=logginMember.getMemberEmail() %>&deleteNo=' + deleteNo + '&cartCate=' + cartCate,
					success: function(data) {
						$('#myCart_Ajax').empty();
						$('#myCart_Ajax').html(data);
						fn_priceUpdate();
					}
				});
			}
			
			var check_count = 0;
			
			function fn_order() {
				var checkbox = $('[name=checkbox]');
				$.each(checkbox, function(index) {
					if(checkbox[index].checked==true) {
						check_count++;
					} 
				});
				
				if(check_count>0) {
				var selectNo = '';
				var classOption = '';
				var cartNo = '';
				var checkbox = $('[name=checkbox]');
				var classNo = $('[name=classNo]');
				var classOptions = $('[name=classOption]');
				$.each(checkbox, function(index) {
						if(checkbox[index].checked) {
							cartNo += checkbox[index].value + ',';
							selectNo += classNo[index].value +',';
							classOption += classOptions[index].value + ',';
						}
				});
				$('#cartNoVal').val(cartNo);
				$('#memberVal').val('<%= logginMember.getMemberEmail() %>');
				$('#selectNoVal').val(selectNo);
				$('#classOptionVal').val(classOption);
				
				orderFrm.submit();
				} else {
					swal('선택한 상품이 없습니다');
				}
			}
			
			var check_count2 = 0;
			
			function fn_orderOneday() {
				var checkbox = $('[name=checkbox]');
				$.each(checkbox, function(index) {
					if(checkbox[index].checked==true) {
						check_count2++;
					} 
				});
				
				if(check_count2>0) {
				var selectNo = '';
				var classOption = '';
				var cartNo = '';
				var checkbox = $('[name=checkbox]');
				var classNo = $('[name=classNo]');
				var classOptions = $('[name=classOption]');
				$.each(checkbox, function(index) {
						if(checkbox[index].checked) {
							cartNo += checkbox[index].value + ',';
							selectNo += classNo[index].value +',';
							classOption += classOptions[index].value + ',';
						}
				});
				
				$('#cartNoVal2').val(cartNo);
				$('#memberVal2').val('<%= logginMember.getMemberEmail() %>');
				$('#selectNoVal2').val(selectNo);
				$('#classOptionVal2').val(classOption);
				
				orderFrm2.submit();
				} else {
					swal('선택한 상품이 없습니다');
				}
			}
		</script>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>