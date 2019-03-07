<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Oneday, com.hobbyist.oneday.model.vo.Cate" %>

<%	
	List<Oneday> list = (List)request.getAttribute("List");
	List<Cate> category = (List)request.getAttribute("Category");
	int cPage = (Integer)request.getAttribute("cPage");
	int numPerPage = (Integer)request.getAttribute("numPerPage");
	String pageBar = (String)request.getAttribute("pageBar");
	String keyword = (String)request.getAttribute("keyword");
	String sort = (String)request.getAttribute("sort");
%>

<%@ include file="/views/common/header.jsp" %>


<section id="oneday">
	<div class="oneday_content">
		<div id="oneday_left">
			<div id="oneday_sub">
				<h3>oneday Menu</h3>
				<ul>
					<li> · 전체보기</li>
					<% 
						if(!category.isEmpty()) { 
						for(Cate c : category) {
					%>
					<li onclick="location.href='<%=request.getContextPath()%>/oneday/onedayCateList?cate=<%=c.getCateTitle()%>'"> · <%= c.getCateTitle() %></li>
					<% }} %>
				</ul>
			</div>
		</div><BR><bR>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			열<br>
			기<br>
		</div> 
			<div id="oneday_right">
				<h3>ONEDAY CLASS </h3>
				<p>메인 > 원데이클래스</p>
				<div class="oneday_top">
					<form id="searchFrm" name="searchFrm" action="<%= request.getContextPath() %>/oneday/onedayList" method="POST">
						<div class="sort">
							<input type="hidden" id="sort" name="sort" value="<%= sort!=null? sort : "" %>"/>
							<div id="sort1" class="sortBtn" name="descEnroll" onclick="fn_sortBtn(this)" >최근등록순</div>
							<div id="sort2" class="sortBtn" name="ascPrice" onclick="fn_sortBtn(this)" >낮은가격순</div>
							<div id="sort3" class="sortBtn" name="descPrice" onclick="fn_sortBtn(this)" >높은가격순</div>
						</div>
					
						<div class="searchForm">
							상품명&nbsp;&nbsp;&nbsp;<input type="search" autocomplete="off" placeholder="Search..." name="keyword" id="keyword" value="<%= !keyword.equals("")? keyword : "" %>">
							<button type="submit"><img src="<%= request.getContextPath() %>/images/search.png" width="70%"></button>
							<div id="ajaxSearch"></div>
						</div>
					</form>
					
					<script>
						var keyword = $('#keyword');
						var url = window.location.href.split('?');
						var cateData_temp = url[1].split('=');
						var cateData = decodeURIComponent(cateData_temp[1]);

						function fn_sortBtn(e) {
							if(e.innerText=='최근등록순') {
								$('#sort').val('');
								$('#sort').val('descEnroll');
								$.ajax({
									url: '<%= request.getContextPath() %>/oneday/onedayCateListAjax',
									type: 'POST',
									dataType: 'text',
									data: {"cate":cateData, "sort":"descEnroll","keyword":keyword.val()},
									success: function(data) {
										var onedayAjax = $('#oneday-ajax');
										onedayAjax.empty();
										onedayAjax.html(data);
										$('#sort1').addClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').removeClass('selected');
									}});
							} else if (e.innerText=='낮은가격순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/oneday/onedayCateListAjax',
									type: 'POST',
									dataType: 'text',
									data:  {"cate":cateData, "sort":"ascPrice","keyword":keyword.val()},
									success: function(data) {
										var onedayAjax = $('#oneday-ajax');
										onedayAjax.empty();
										onedayAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').addClass('selected');
										$('#sort3').removeClass('selected');
									}});
							} else if (e.innerText=='높은가격순') {
								$.ajax({
									url: '<%= request.getContextPath() %>/oneday/onedayCateListAjax',
									type: 'POST',
									dataType: 'text',
									data: {"cate":cateData, "sort":"descPrice","keyword":keyword.val()},
									success: function(data) {
										var onedayAjax = $('#oneday-ajax');
										onedayAjax.empty();
										onedayAjax.html(data);
										$('#sort1').removeClass('selected');
										$('#sort2').removeClass('selected');
										$('#sort3').addClass('selected');
									}});
							}}
						
						keyword.keyup(function() {
							$.ajax({
								url: '<%= request.getContextPath() %>/oneday/onedaySearch?keyword=' + keyword.val(),
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
				<div class="oneday_middle" id="oneday-ajax">
					<% 
					double cnt = 0.1;
					double cnt2 = 0;
					if(cnt2>=0.3) {
						cnt2 = 0;
					}
					if(!list.isEmpty()) {
					for(Oneday oneday : list) { %>
					<div class="item wow fadeInUp" data-wow-delay="<%=cnt2+=cnt %>s">
						<ul>
						<li><%= oneday.getOnedayCate() %></li>
						<li>
							<a href="<%= request.getContextPath() %>/oneday/onedayView?no=<%= oneday.getOnedayNo()%>">
							<img src="<%=request.getContextPath()%>/upload/oneday/images/<%= oneday.getOnedayImage1() %>" width="250px"></a></li>
						<li class="title">
							<a href="<%= request.getContextPath() %>/oneday/onedayView?no=<%= oneday.getOnedayNo()%>">
							<%= oneday.getOnedayName() %></a>
							<p><%= oneday.getOnedayInfo()%></p>
						</li>
						<li>
								가격 : <%= oneday.getOnedayPrice() %>원<br><br>
								예약현황 <%= oneday.getOnedayCurrentPeople() %>/<%= oneday.getOnedayPeople() %> (명)<br>
								<b><%= oneday.getOnedayReservationStatus().equals("Y") ?  "[ 예약 가능 ]" : "[ 예약 불가 ]" %></b><br>
								<button onclick="location.href='<%= request.getContextPath() %>/oneday/onedayView?no=<%= oneday.getOnedayNo() %>'">예약하기</button></li>
						</ul>
					</div>
					<% }} else { %>
					<center><h3>목록이 없습니다.</h3></center>
					<% } %>
					<div class="oneday_bottom">
						<%= pageBar %>
					</div>
				</div>
		</div>
		<script>
			var onedayRight = $('#oneday_right');
			var slidebtn = $('#slideButton');
			var width = $('#oneday_left').width();
			var item = $('.item');

			function slide() {
				var width = $('#oneday_left').width();
					if (width == 0) {
						// 서브메뉴가 열린상태
						$('#oneday_left').css({"z-index":"999", "transition": "500ms", "width": "210px","border-right":"1px solid #c2c2c2"});
						slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
						slidebtn.html('메<br>뉴<br>접<br>기<br>');
					} else {
						// 서브메뉴가 접힌상태
						$('#oneday_left').css({ "transition": "500ms", "width": "0px", "border":"none"});
						onedayRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
						slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#fff", "color": "#333" });
						slidebtn.html('메<br>뉴<br>열<br>기<br>');
					}
			}
			


		</script>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>