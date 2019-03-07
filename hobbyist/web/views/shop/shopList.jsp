<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.shop.model.vo.Shop, com.hobbyist.shop.model.vo.Cate" %>

<%	
	List<Shop> list = (List)request.getAttribute("List");
	List<Cate> category = (List)request.getAttribute("Category");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
%>

<%@ include file="/views/common/header.jsp" %>


<section id="shop">
	<div class="shop_content">
		<div id="shop_left">
			<div id="shop_sub">
				<h3>Shop Menu</h3>
				<ul>
					<li onclick="location.href='<%=request.getContextPath() %>/shop/shopList';"> · 전체보기</li>
					<% for(Cate cate : category) { %>
						<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=<%= cate.getCateTitle()  %>';"> · <%= cate.getCateTitle()  %></li>
					<% } %>
				</ul>
			</div>
		</div>
		<br>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			열<br>
			기
			</div> 
			<div id="shop_right">
				<h3>CLASS SHOP </h3>
				<p>메인 > 클래스샵</p>
				<div class="shop_top">
					<form id="searchFrm" name="searchFrm" action="<%= request.getContextPath() %>/shop/shopList" method="POST">
					<div class="sort">
						<input type="hidden" id="sort" name="sort" value="<%= sort!=null? sort : "" %>"/>
						<div id="sort1" class="sortBtn" name="descEnroll" value="descEnroll" onclick="fn_sortBtn(this)" >최근등록순</div>
						<div id="sort2" class="sortBtn" name="ascPrice" value="ascPrice" onclick="fn_sortBtn(this)" >낮은가격순</div>
						<div id="sort3" class="sortBtn" name="descPrice" value="descPrice" onclick="fn_sortBtn(this)" >높은가격순</div>
					</div>
					
					<div class="searchForm">
						상품명&nbsp;&nbsp;&nbsp;<input type="search" autocomplete="off" placeholder="Search..." name="keyword" id="keyword" value="<%= !keyword.equals("")? keyword : "" %>">
						<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png" width="70%"></button>
						<div id="ajaxSearch"></div>
					</div>
					</form>
					<script>
					
						var keyword = $('#keyword');
					
						function fn_sortBtn(e) {
							if(e.innerText=='최근등록순') {
								$('#sort').val('');
								$('#sort').val('descEnroll');
								$.ajax({
									url: '<%= request.getContextPath() %>/shop/shopListAjax',
									type: 'POST',
									dataType: 'text',
									data: {"sort":"descEnroll","keyword":keyword.val()},
									success: function(data) {
										var shopAjax = $('#shop-ajax');
										shopAjax.empty();
										shopAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
									}});
							} else if (e.innerText=='낮은가격순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/shop/shopListAjax',
									type: 'POST',
									dataType: 'text',
									data:  {"sort":"ascPrice","keyword":keyword.val()},
									success: function(data) {
										var shopAjax = $('#shop-ajax');
										shopAjax.empty();
										shopAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').addClass('selected');
										$('#sort3').removeClass('selected');
									}});
							} else if (e.innerText=='높은가격순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/shop/shopListAjax',
									type: 'POST',
									dataType: 'text',
									data: {"sort":"descPrice","keyword":keyword.val()},
									success: function(data) {
										var shopAjax = $('#shop-ajax');
										shopAjax.empty();
										shopAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').addClass('selected');
									}});
							}}
						
						keyword.keyup(function() {
							$.ajax({
								url: '<%= request.getContextPath() %>/shop/shopSearch?keyword=' + keyword.val(),
								success: function(data) {
									var result = JSON.parse(data);
									var ajaxSearch = $('#ajaxSearch').css("display","block");
									ajaxSearch.html('');
									ajaxSearch.append(
										"<h3>추천검색어</h3>"
										);
									$.each(result, function(index, item) {
										ajaxSearch.append("<div style='cursor: pointer; padding-top: 6px; height:28px; border-bottom: 1px solid #eee;' onclick='clickSearch(this)'>" + result[index] + "</div>");
									})
								}})
						});

						function clickSearch(e) {
							keyword.val(e.innerText);
							searchFrm.submit();
						}

						var ajaxSearch = $('#ajaxSearch');
						ajaxSearch.mouseleave(function() {
							ajaxSearch.css("display","none");
						});
					</script>

				</div>
				<div class="shop_middle" id="shop-ajax">
					<% 
					double cnt = 0.1;
					double cnt2 = 0;
					if(cnt2>=0.3) {
						cnt2 = 0;
					}
					if(!list.isEmpty()) {
					for(Shop s : list) { %>
					<div class="item wow fadeInUp" data-wow-delay="<%=cnt2+=cnt %>s">
						<p><%= s.getShopCate() %></p>
						<div id="img">
						<a href="<%= request.getContextPath() %>/shop/shopView?no=<%= s.getShopNo()%>"><img src="<%=request.getContextPath()%>/upload/shop/images/<%= s.getShopImage1() %>"
							 width="100%"></a>
							 </div>
							 <br>
						<ul>
							<li class="title"><a href="<%= request.getContextPath() %>/shop/shopView?no=<%= s.getShopNo()%>">
									<%= s.getShopName() %></a></li>
									<li><p><%= s.getShopInfo() %></p></li>
									<li><span id="review_line"><%= s.getReviewCount() != 0 ? s.getReviewCount() +" 개의 클래스 리뷰" : "등록된 리뷰가 없습니다" %></span></li>
							<li>가격
								<%= s.getShopPrice() %>원</li>
						</ul>
						<button onclick="location.href='<%= request.getContextPath() %>/shop/shopView?no=<%= s.getShopNo()%>'">VIEW</button>
					</div>
					<% }}%>
					
					<div class="shop_bottom">
					<br><br>
						<%= pageBar %>
					</div>
				</div>
				
		</div>
		<script>
			function fn_ListAjax(pageNo) {
				var text = $('.selected')[0].innerText;
				
				if(text=='최근등록순') {
					$.ajax({
						url: '<%= request.getContextPath() %>/shop/shopListAjax',
						data : 'sort=descEnroll&cPage=' + pageNo,
						success: function (data) {
							var shopAjax = $('#shop-ajax');
							shopAjax.empty();
							shopAjax.html(data);
						}
					});
				} else if (text=='낮은가격순') {
					$.ajax({
						url: '<%= request.getContextPath() %>/shop/shopListAjax',
						data : 'sort=ascPrice&cPage=' + pageNo,
						success: function (data) {
							var shopAjax = $('#shop-ajax');
							shopAjax.empty();
							shopAjax.html(data);
						}
					});
				} else {
					$.ajax({
						url: '<%= request.getContextPath() %>/shop/shopListAjax',
						data : 'sort=descPrice&cPage=' + pageNo,
						success: function (data) {
							var shopAjax = $('#shop-ajax');
							shopAjax.empty();
							shopAjax.html(data);
						}
					});
				}
				
			}
		
		
			var shopRight = $('#shop_right');
			var slidebtn = $('#slideButton');
			var width = $('#shop_left').width();
			var item = $('.item');
			

			function slide() {
				var width = $('#shop_left').width();

				if (width == 0) {
					// 서브메뉴가 열린상태
					$('#shop_left').css({"z-index":"999", "transition": "500ms", "width": "210px","border-right":"1px solid #c2c2c2"});
					//shopRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
					slidebtn.html('메<br>뉴<br>접<br>기<br>');
				} else {
					// 서브메뉴가 접힌상태
					$('#shop_left').css({ "transition": "500ms", "width": "0px", "border":"none"});
					shopRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
					slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#fff", "color": "#333" });
					slidebtn.html('메<br>뉴<br>열<br>기<br>');
				}
			}
			

		</script>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>