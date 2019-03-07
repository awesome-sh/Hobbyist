<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*, com.hobbyist.shop.model.vo.Cate"%>
    
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
				<li>CLASS SHOP | 클래스샵</li>
				<li>관리자페이지 > 클래스샵 관리 > 클래스샵 등록</li>
			</ul>
			
		</div><br>
		
		<!-- 관리자메뉴는 중복되기때문에 admin_menu.jsp 파일로 인클루드 시켜 가져옴 -->
		<%@ include file="/views/admin/admin_menu.jsp" %> 
		<!-- 관리자 메뉴 인클루드 끝 -->


		<!-- 관리자 -> 클래스샵 - > 등록 -->
		<div class="adminShopWrite_right">
			<div id="adminShopWrite_main">
			
		<form action="<%= request.getContextPath() %>/shop/shopWriteEnd" method="POST" enctype="multipart/form-data">
			<table>
				<tr>
					<th colspan="2"><h3>클래스 등록</h3></th>
				</tr>
				<tr>
					<th style="width: 180px;">카테고리</th>
					<td id="cate"  style="width: 620px; text-align: left; padding-left: 10px;">
						<select name="class_cate">
						<%  
							if(!list.isEmpty()) {
						for(Cate c : list) { %>
							<option value="<%= c.getCateTitle()%>"><%= c.getCateTitle()%></option>
							<% } } %>
						</select>
						<button type="button" onclick="newCate()">신규 카테고리 생성</button>
						<script>
							function newCate() {
								$('#cate').append("<form action='<%= request.getContextPath() %>/shop/shopCateInsert' method='POST'><input type='text' name='newCate' placeholder='카테고리 명'/><input type='submit' value='생성'/></form>");
							}
						</script>
					</td>
				<tr>
					<th>클래스명</th>
					<td style="text-align: left;"><input type="text" name="class_name" placeholder="클래스 이름을 작성해주세요" autofocus style="width: 450px;"></td>
				</tr>
				<tr>
					<th>기본설명</th>
					<td style="text-align: left;"><input type="text" name="class_info" placeholder="클래스에 대한 간략한 소개를 작성해주세요" style="width: 450px;"></td>
				</tr>
				<tr>
					<th>작가</th>
					<td  style="text-align: left;"  id="searchWriterTd"><input type="text" id="class_writer" name="class_writer" placeholder="작가 (닉네임)" autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button></td>
					<script>
						function fn_writerSearch() {
							var searchWriterTd = $('#searchWriterTd');
							$.ajax({
								url : '<%=request.getContextPath()%>/shop/shopWriterSearch',
								data : 'class_writer=' + $('#class_writer').val(),
								success: function (data) {
									searchWriterTd.html(data);
								}
							});
						}
						
						function fn_close() {
							var searchWriterTd = $('#searchWriterTd');
							searchWriterTd.html('<input type="text" id="class_writer" name="class_writer" placeholder="작가 (닉네임)" autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button>');
						}

						function fn_searched(e) {
							var searchWriterTd = $('#searchWriterTd');
							searchWriterTd.html('<input type="text" id="class_writer" name="class_writer" placeholder="작가 (닉네임)" value=' + e.innerText + ' autocomplete="off"><button type="button" onclick="fn_writerSearch()">찾기</button>');
						}
					</script>
				</tr>
				<tr>
					<th>상품상세설명</th>
					<td><textarea name="class_content" id="editor1"></textarea></td>
					<script>
							// Replace the <textarea id="editor1"> with a CKEditor
							// instance, using default configuration.
							CKEDITOR.replace( 'editor1' );
							CKEDITOR.add
					</script>
				</tr>
				<tr>
					<th>가격</th>
					<td  style="text-align: left;"><input type="number" name="class_price"></td>
				</tr>
				<tr>
					<th>적립포인트</th>
					<td  style="text-align: left;"><input type="number" name="class_point"></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 1</th>
					<td  style="text-align: left;"><input type="text" name="class_option1"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 2</th>
					<td  style="text-align: left;"><input type="text" name="class_option2"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 3</th>
					<td  style="text-align: left;"><input type="text" name="class_option3"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 4</th>
					<td  style="text-align: left;"><input type="text" name="class_option4"/></td>
				</tr>
				<tr>
					<th>클래스 선택옵션 5</th>
					<td  style="text-align: left;"><input type="text" name="class_option5"/></td>
				</tr>
				<tr>
					<th>이미지1</th>
					<td  style="text-align: left;"><input type="file" name="class_image1"></td>
				</tr>
				<tr>
					<th>이미지2</th>
					<td  style="text-align: left;"><input type="file" name="class_image2"></td>
				</tr>
				<tr>
					<th>이미지3</th>
					<td  style="text-align: left;"><input type="file" name="class_image3"></td>
				</tr>
				<tr>
					<th>이미지4</th>
					<td  style="text-align: left;"><input type="file" name="class_image4"></td>
				</tr>
				<tr>
					<th>이미지5</th>
					<td  style="text-align: left;"><input type="file" name="class_image5"></td>
				</tr>
				<tr>
					<th>배송정책</th>
					<td>
						<textarea name="class_policy1" id="editor2">
							<h3>배송정책</h3>
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
				<tr>
					<th>교환정책</th>
					<td>
						<textarea name="class_policy2" id="editor3">
							<h3>교환정책</h3>
							* 상품 수령일로부터 7일 이내 교환 신청 가능하며, 7일이 지나면 불가능합니다.<br>
							* 단순 변심 교환의 경우 왕복배송비를 지불하셔야하며, 제품 및 포장이 재판매 가능한 상태여야 합니다.(포장 훼손 시 교환 불가)<br>
							* 상품 불량 등 회사의 귀책 사유로 인한 교환의 경우는 배송비를 지불하지 않으셔도 됩니다.<br>
							* 담당자 확인 후 아래 주소지로 상품을 보내주시면, 상품 회수 후 교환 상품을 발송해드립니다.<br>
							* [교환 주소지] : (12246) 경기도 남양주시 양정로319번길 15-18 두손컴퍼니<br><br>
						</textarea>
						<script>
								// Replace the <textarea id="editor1"> with a CKEditor
								// instance, using default configuration.
								CKEDITOR.replace( 'editor3' );
								CKEDITOR.add
						</script>
					</td>
				</tr>
				<tr>
					<th>환불정책</th>
					<td>
						<textarea name="class_policy3" id="editor4">
							<h3>환불정책</h3>
							* 상품 수령일로부터 7일 이내 반품 신청 가능하며, 7일이 지나면 불가능합니다.<br>
							* 단순 변심 반품의 경우 왕복배송비를 차감한 금액이 환불되며, 제품 및 포장이 재판매 가능한 상태여야 합니다.(포장 훼손 시 반품 불가)<br>
							* 상품 불량 등 회사의 귀책 사유로 인한 반품의 경우는 배송비를 포함한 전액이 환불됩니다.<br>
							* 담당자 확인 후 아래 주소지로 상품을 보내주시면, 상품 회수 후 환불 진행됩니다.<br>
							* [반품 주소지] : (12246) 경기도 남양주시 양정로319번길 15-18 두손컴퍼니
						</textarea>
						<script>
								// Replace the <textarea id="editor1"> with a CKEditor
								// instance, using default configuration.
								CKEDITOR.replace( 'editor4' );
								CKEDITOR.add
						</script>
					</td>
				</tr>
			</table>
			<h3>강좌등록</h3>
			<table>
				<tr>
					<th style="width: 180px;">강좌 한줄소개</th>
					<td style="text-align: left; width: 620px;"><input type="text" name="study_subtitle" style="width: 450px;"/></td>
				</tr>
				<tr>
					<th>강좌 영상</th>
					<td style="text-align: left;"><input type="text" name="study_video" style="width: 450px;" placeholder="스트리밍 사이트 내 태그 예) iframe, embed..."/></td>
				</tr>
				<tr>
					<th>강좌내용</th>
					<td><textarea name="study_content" id="editor6"></textarea></td>
					<script>
							// Replace the <textarea id="editor1"> with a CKEditor
							// instance, using default configuration.
							CKEDITOR.replace( 'editor6' );
							CKEDITOR.add
					</script>
				</tr>
			</table>
			<button type="submit">클래스 등록</button>
			</form>
			</div>
			
		</div>
	</div>
</section>

<%@ include file="/views/common/footer.jsp" %>