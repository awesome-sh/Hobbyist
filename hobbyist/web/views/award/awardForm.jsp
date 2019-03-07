<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*, com.hobbyist.award.model.vo.Award"%>
<%@ include file="/views/common/header.jsp"%>
<%
	Award a = (Award) request.getAttribute("award");
%>
<section id="award">
	<div class="award_content">
		<div id=award_right>
			<h3>AWARD</h3><p>자신의 취미를 자랑해보세요~ 하비스트 어워드!</p>
			<div class="award_top"><h3>어워드 글쓰기</h3></div>
			<div class="award_middle">
				<table id="tbl-awardForm">
					<form action="<%=request.getContextPath()%>/award/awardFormEnd" method="post" enctype="multipart/form-data">
						<tr>
							<th style="width:200px">제목</th>
							<td style="width:700px;  text-align:left"><input type="text" name="title" id="title" style="width:450px;" required placeholder="제목을 입력해주세요"></td>
						</tr>
						<tr>
							<th>작성자</th>
							<td  style=" text-align:left; "><input type="text"  style="width:450px;" name="writer"
								value="<%=logginMember != null ? logginMember.getMemberEmail() : ""%>"
								readOnly></td>
						</tr>
						<tr>
                     <th>첨부파일</th>
                     <td  style="text-align:left"> 
                        <!-- <input type="file" name="up_file"> -->
                        
                        <div class="inputImageBox">
                              <div class="inputImageback">
                                 <div class="inputImage">
                                    <img src="<%= request.getContextPath() %>/images/uploadIcon.png" width="100px" height="100px" onclick="up_file()"/>
                                    <div class="imageTitle"></div>
                                 </div>
                                 <input style="width:450px;" type="file" name="up_file" class="inputImageFile" id="img_editor" required/>
                              </div>
                              </div>
                              
                              <script>
                               function up_file(){
                                 var image=$('[name=img_editor]').val();
                                 if(image.trim().length==0){
                                    alert("이미지를 등록하세요");
                                    return false;
                                 }
                              return true;
                              } 
                              </script>
                        
                     </td>      
                  </tr>
						<tr>
							<th>내용</th>
							<td  style="text-align:left"><textarea id="noticeContent_editor" rows="5" cols="50" name="content">　　</textarea></td>
						</tr>

						<tr>
							<td colspan="2">
							<button type="submit" onclick="return fn_validate();">어워드 등록</button>
							</td>
						</tr>
				</table>
				</form>
			</div>
		</div>


	</div>
</section>
<script>
	function fn_validate() {
		var content = $('[name=noticeContent_editor]').val();
		if (content.trim().length == 0) {
			alert("내용을 입력하세요");
			return false;
		}
		return true;
	}
	
	
	 $(function () {
         $("input[name='up_file']").change(function (event) {
            $(".inputImage img").attr("src", URL.createObjectURL(event.target.files[0]));
            $(".inputImage img").css({"width":"300px","height":"200px"});
            var fileName = $("input[name='up_file']").val();
            $(".imageTitle").text("파일명 : " + fileName.substring(fileName.lastIndexOf('\\') + 1));
         })
      })
	
	 //textarea css 가져오기
    CKEDITOR.replace('noticeContent_editor');
    CKEDITOR.add
</script>


<%@ include file="/views/common/footer.jsp"%>