<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Oneday"%>
    
<% 
	Oneday oneday = (Oneday)request.getAttribute("Oneday");
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
				<li onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'">
				<img src="<%= request.getContextPath() %>/images/back.png" width="18px"></li>
				<li>CLASS SHOP | 클래스샵</li>
				<li>관리자페이지 > 클래스샵 관리 > 클래스샵 등록</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->


		<!-- 관리자 -> 클래스샵 - > 등록 -->
		<div class="adminOnedayView_right">
			<div id="adminOnedayView_main">
			
		<form action="<%= request.getContextPath() %>/admin/adminOnedayUpdate" method="POST" enctype="multipart/form-data">
			<input type="hidden" name="oneday_no" value="<%= oneday.getOnedayNo() %>"/>
			<h2><%= oneday.getOnedayName() %></h2>
			<table>
				<tr>
					<td colspan="2"><a href="<%= request.getContextPath() %>/admin/adminOnedayList">목록으로</a></td>
				</tr>
				<tr>
					<th style="width: 180px;">카테고리</th>
					<td id="cate"  style="width: 620px; text-align: left;">
						<%= oneday.getOnedayCate() %>
						<input type="hidden" name="oneday_cate" value="<%=  oneday.getOnedayCate() %>"/>
					</td>
				<tr>
					<th>클래스명</th>
					<td style="text-align: left;"><input type="text" name="oneday_name" value="<%= oneday.getOnedayName() %>" autofocus style="width: 450px;""></td>
				</tr>
				<tr>
					<th>한줄소개</th>
					<td style="text-align: left;"><input type="text" name="oneday_info" value="<%= oneday.getOnedayInfo() %>" style="width: 450px;"></td>
				</tr>
				<tr>
					<th>작가</th>
					<td  style="text-align: left;"  id="searchWriterTd"><input type="text" id="oneday_writer" name="oneday_writer" value="<%= oneday.getOnedayWriter() %>" autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button></td>
				</tr>
				<tr>
					<th>클래스 장소 (주소)</th>
					<td style="text-align: left;"><input type="text" name="oneday_address" value="<%= oneday.getOnedayAddress() %>" style="width: 450px;"></td>
				</tr>
				<tr>
					<th>가격</th>
					<td  style="text-align: left;"><input type="number" name="oneday_price" value="<%= oneday.getOnedayPrice() %>"></td>
				</tr>
				<tr>
					<th>모집정원</th>
					<td  style="text-align: left;"><input type="number" name="oneday_people" value="<%= oneday.getOnedayPeople() %>"></td>
				</tr>
				<tr>
					<th>상품상세설명</th>
					<td><textarea name="oneday_content" id="editor1"><%= oneday.getOnedayContent() %></textarea></td>
					<script>
							// Replace the <textarea id="editor1"> with a CKEditor
							// instance, using default configuration.
							CKEDITOR.replace( 'editor1' );
							CKEDITOR.add
					</script>
				</tr>
				
				<tr>
					<th>클래스 선택옵션 1</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option1" value="<%= oneday.getOnedayOption1() %>"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 2</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option2" value="<%= oneday.getOnedayOption2() %>"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 3</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option3" value="<%= oneday.getOnedayOption3() %>"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 4</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option4" value="<%= oneday.getOnedayOption4() %>"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 5</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option5" value="<%= oneday.getOnedayOption5() %>"/></td>
				</tr>
				<tr>
					<th>이미지1</th>
					<td  style="text-align: left;">
						<% if(oneday.getOnedayImage1()!=null) { %>
							<input type="hidden" name="old_file" value="<%= oneday.getOnedayImage1() %>"/>
							<input type="file" name="up_file" value="<%= oneday.getOnedayImage1() %>"/>
							<span id="fname"><%= oneday.getOnedayImage1() %></span>
							<% } else { %>
							<input type="file" name="oneday_image1"/>
						<% } %>
					</td>
				</tr>
				<tr>
					<th>이미지2</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image2" value="<%= oneday.getOnedayImage2() %>"></td>
				</tr>
				<tr>
					<th>이미지3</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image3" value="<%= oneday.getOnedayImage3() %>"></td>
				</tr>
				<tr>
					<th>이미지4</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image4" value="<%= oneday.getOnedayImage4() %>"></td>
				</tr>
				<tr>
					<th>이미지5</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image5" value="<%= oneday.getOnedayImage5() %>"></td>
				</tr>
				<tr>
					<th>예약</th>
					<td>
						<textarea name="oneday_policy" id="editor2">
							<h3>예약안내</h3>
							* 전국 모든 지역에 배송이 가능합니다.<br>
							* 택배사는 CJ대한통운 입니다.<br>
							* 기본 배송비는 무료입니다.<br>
							* 도서산간지역의 경우 추가 배송비 결제 요청이 있을 수 있습니다.<br>
							* 배송기간은 정해진 배송시작일로부터 1~5일이 소요됩니다.
						</textarea>
						<script>
								// Replace the <textarea id="editor1"> with a CKEditor
								// instance, using default configuration.
								CKEDITOR.replace( 'editor2' );
								CKEDITOR.add
						</script>
					</td>
				</tr>
				
			</table>
			
			<button type="submit">원데이클래스 등록</button>
			</form>
			</div>
			
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>