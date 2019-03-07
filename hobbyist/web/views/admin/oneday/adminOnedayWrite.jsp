<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.oneday.model.vo.Cate"%>
    
<% 
	List<Cate> list = (List)request.getAttribute("Cate");
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
				<li onclick="location.href='#'">ONEDAY CLASS | 원데이클래스</li>
				<li onclick="location.href='#'">관리자페이지 > 원데이클래스 관리 > 원데이클래스 등록</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->


		<!-- 관리자 -> 원데이클래스 - > 등록 -->
		<div class="adminOnedayWrite_right">
			<div id="adminOnedayWrite_main">
			
		<form action="<%= request.getContextPath() %>/oneday/onedayWriteEnd" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
							<td colspan="8" style="text-align:center;"><h3>원데이클래스 등록</h3></td>
				</tr>
				<tr>
					<th style="width: 180px;">지역</th>
					<td id="cate"  style="width: 620px; text-align: left; padding-left: 10px;">
						<select name="oneday_cate">
						<%  
							if(!list.isEmpty()) {
						for(Cate c : list) { %>
							<option value="<%= c.getCateTitle()%>"><%= c.getCateTitle()%></option>
							<% } } %>
						</select>
						<button type="button" onclick="newCate()">신규 카테고리 생성</button>
						<script>
							function newCate() {
								$('#cate').append("<form action='<%= request.getContextPath() %>/oneday/onedayCateInsert' method='POST'><input type='text' name='newCate' placeholder='지역명'/><input type='submit' value='생성'/></form>");
							}
						</script>
					</td>
				<tr>
					<th>원데이클래스명</th>
					<td style="text-align: left;"><input type="text" name="oneday_name" placeholder="클래스 이름을 작성해주세요" autofocus style="width: 450px;""></td>
				</tr>
				<tr>
					<th>기본설명</th>
					<td style="text-align: left;"><input type="text" name="oneday_info" placeholder="클래스에 대한 간략한 소개를 작성해주세요" style="width: 450px;"></td>
				</tr>
				<tr>
					<th>클래스 장소 (주소)</th>
					<td style="text-align: left;"><input type="text" name="oneday_address" placeholder="주소를 입력해주세요" style="width: 450px;"></td>
				</tr>
				<tr>
					<th>작가</th>
					<td  style="text-align: left;"  id="searchWriterTd"><input type="text" id="oneday_writer" name="oneday_writer" placeholder="작가 (닉네임)" autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button></td>
					<script>
						function fn_writerSearch() {
							var searchWriterTd = $('#searchWriterTd');
							$.ajax({
								url : '<%=request.getContextPath()%>/oneday/onedayWriterSearch',
								data : 'oneday_writer=' + $('#oneday_writer').val(),
								success: function (data) {
									searchWriterTd.html(data);
								}
							});
						}
						
						function fn_close() {
							var searchWriterTd = $('#searchWriterTd');
							searchWriterTd.html('<input type="text" id="oneday_writer" name="oneday_writer" placeholder="작가 (닉네임)" autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button>');
						}

						function fn_searched(e) {
							var searchWriterTd = $('#searchWriterTd');
							searchWriterTd.html('<input type="text" id="oneday_writer" name="oneday_writer" placeholder="작가 (닉네임)" value=' + e.innerText + ' autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button>');
						}
					</script>
				</tr>
				<tr>
					<th>가격</th>
					<td  style="text-align: left;"><input type="number" name="oneday_price"></td>
				</tr>
				<tr>
					<th>모집정원</th>
					<td  style="text-align: left;"><input type="number" name="oneday_people"></td>
				</tr>

				<tr>
					<th>상품상세설명</th>
					<td><textarea name="oneday_content" id="editor1"></textarea></td>
					<script>
							// Replace the <textarea id="editor1"> with a CKEditor
							// instance, using default configuration.
							CKEDITOR.replace( 'editor1' );
							CKEDITOR.add
					</script>
				</tr>
				<tr>
					<th>클래스 선택옵션 1</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option1"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 2</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option2"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 3</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option3"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 4</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option4"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 5</th>
					<td  style="text-align: left;"><input type="text" name="oneday_option5"/></td>
				</tr>
				<tr>
					<th>이미지1</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image1"></td>
				</tr>
				<tr>
					<th>이미지2</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image2"></td>
				</tr>
				<tr>
					<th>이미지3</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image3"></td>
				</tr>
				<tr>
					<th>이미지4</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image4"></td>
				</tr>
				<tr>
					<th>이미지5</th>
					<td  style="text-align: left;"><input type="file" name="oneday_image5"></td>
				</tr>
				<tr>
					<th>원데이클래스 이용안내</th>
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
			
			<button type="submit">클래스 등록</button>
			</form>
			</div>
			
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>