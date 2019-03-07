<%@page import="com.hobbyist.board.model.vo.BoardComment"%>
<%@page import="java.util.List"%>
<%@page import="com.hobbyist.board.model.vo.Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>

<%
	Board b = (Board)request.getAttribute("b");
	List<BoardComment> comments = (List)request.getAttribute("bc");
%>

<section id="boardView">
	<div class="boardView_content">
		<div id="boardView_left">
			<div id="boardView_sub">
				<h3>Board Menu</h3>
				<ul>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardList'" style="cursor: pointer;"> · 자유게시판</li>
					<li style="cursor: pointer;"> · 리뷰/후기</li>
					<li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'" style="cursor: pointer;"> · FAQ 자주 묻는 질문</li>
					<li onclick="directQuestion()"> · 1:1문의</li>
				</ul>
			</div>
		</div>
		<div id="slideButton" onclick="slide()">
			메<br>
			뉴<br>
			접<br>
			기<br>
		</div>
		
		<div id="boardView_right">
			<div class="right_top">
			<h3>COMMUNITY</h3><p>메인 > 커뮤니티 > 자유게시판</p>		
			</div>
			<div class="boardView_top">
				<%=b.getBoardTitle()%>
			</div>
			<div class="boardView_middle">
				<div id="info_head">
						<div id="left">
							작성자 : <%=b.getBoardWriter()%>
						</div>
						<div id="right">
							작성일 : <%=b.getBoardDate()%>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
							조회수 : <%=b.getBoardReadCount()%>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
							<img src="<%=request.getContextPath()%>/images/board_like.png" width="12px"/>
							<%=b.getBoardLike()%>
						</div>
				</div>
				<br>
				<div id="view_content">
				<% if(b.getBoardOriginalFileName()!=null) { %>
					<img src="<%=request.getContextPath()%>/upload/board/<%=b.getBoardReNamedFileName()%>" width="500px" />
				<% } %>
					<%=b.getBoardContent()%>
				</div>
				<br/>
				
				<div id="view_bottom">
					<div id="like_btn" onclick="clickLike()">
						<img id="like_img" src="<%=request.getContextPath()%>/images/board_like.png" width="20px">추천
					</div>
					<%-- <div id="report">
						<%if(logginMember != null) { %>
						<a href="javascript:report();"><img src="<%=request.getContextPath()%>/images/report.png">신고</a>
						<%}%>
					</div> --%>
				</div>
				
				
				
				<div id="view_page">
					<div id="left">
						<a href="<%=request.getContextPath()%>/board/boardList"><img src="<%=request.getContextPath()%>/images/view_list.png"></a>
						<a href="<%=request.getContextPath()%>/board/boardMove?boardNo=<%=b.getBoardNo()%>&flag=1"><img src="<%=request.getContextPath()%>/images/prev.png"></a>
						<a href="<%=request.getContextPath()%>/board/boardMove?boardNo=<%=b.getBoardNo()%>&flag=0"><img src="<%=request.getContextPath()%>/images/next.png"></a>
					</div>
					<div id="right">
						<%if(logginMember != null && (logginMember.getMemberNickname().equals(b.getBoardWriter()) || logginMember.getMemberNickname().equals("Hobbyist"))) {%>
							<button onclick="location.href='<%=request.getContextPath()%>/board/boardUpdate?boardNo=<%=b.getBoardNo()%>'">수정</button>
							<button onclick="location.href='<%=request.getContextPath()%>/board/boardDelete?boardNo=<%=b.getBoardNo()%>'">삭제</button>
						<% } %>
					</div>
				</div>
				
		
				<div id="chat_head">
					<form name="commentFrm" action="<%=request.getContextPath()%>/board/boardCommentInsert" method="post">
						<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>"/>
						<input type="hidden" name="boardCommentWriter" value="<%=logginMember!=null?logginMember.getMemberNickname():""%>"/>
						<input type="hidden" name="boardCommentLevel" value="1"/>
						<input type="hidden" name="boardRef" value="<%=b.getBoardNo()%>"/>
						<input type="hidden" name="boardCommentRef" value="0"/>
						<%if(logginMember != null) {%>
						<textarea name="chat_content" id="chat_content" placeholder='댓글을 입력하세요.' style="resize: none;"></textarea>
						<% } else { %>
						<textarea name="chat_content" id="chat_content" placeholder='로그인 후 이용가능합니다.' style="resize: none;"></textarea>
						<% } %>
						<input class="chat_write" type="submit" value="등록"/>
					</form>
				</div>
				
				<div id="commentTable">
					<table id="tbl-comment">
					<%if(comments != null) { 
						for(BoardComment bc : comments) {
								int height = 0;
								if(bc.getBoardCommentContent().length() <= 150) height = 50;
								else if(bc.getBoardCommentContent().length() <= 250) height = 110;
								else if(bc.getBoardCommentContent().length() <= 350) height = 150;
								else if(bc.getBoardCommentContent().length() <= 450) height = 190;
								else if(bc.getBoardCommentContent().length() <= 600) height = 220;
							if(bc.getBoardCommentLevel() == 1) {%>
						<tr class="level1">
							<td style="width: 160px; text-align: left; padding-left: 15px;"><%=bc.getBoardCommentWriter()%></td>
							<td style="width: 370px; text-align: left; padding-left: 25px;"><%=bc.getBoardCommentContent()%></td>
							<td style="width: 120px;"><%=bc.getBoardCommentDate()%></td>
							<td style="width: 190px; text-align: right;">
								<button class="btn-reply" value="<%=bc.getBoardCommentNo()%>">댓글</button>
								<%if(logginMember != null && (bc.getBoardCommentWriter().equals(logginMember.getMemberNickname()) || logginMember.getMemberNickname().equals("admin"))) { %>
								<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
								<%}%>
								<%if(logginMember != null) { %>
								<button class="btn-report" onclick="commentReport('<%=bc.getBoardCommentWriter()%>', '<%=bc.getBoardCommentContent()%>', '<%=b.getBoardNo()%>')">신고</button>
								<%} %>
							</td>
						</tr>
						
					 	<% } else { %>
					 			<tr class="level2">
									<td><img src="<%=request.getContextPath() %>/images/board_reply.png" width="20px"/>&nbsp;&nbsp;<%=bc.getBoardCommentWriter()%></td>
									<td style="text-align: left; padding-left: 45px;"><%=bc.getBoardCommentContent()%></td>
									<td><%=bc.getBoardCommentDate()%></td>
									<td><%if(logginMember != null && (bc.getBoardCommentWriter().equals(logginMember.getMemberNickname()) || logginMember.getMemberNickname().equals("admin"))) { %>
											<button class="btn-delete" value="<%=bc.getBoardCommentNo()%>">삭제</button>
											<%}%>
											<%if(logginMember != null) { %>
											<button class="btn-report" onclick="commentReport('<%=bc.getBoardCommentWriter()%>', '<%=bc.getBoardCommentContent()%>', '<%=b.getBoardNo()%>')">신고</button>
											<%}%>
									</td>
								</tr>
					  	 <% }
						}
					} %>
					</table>
				</div>
			</div>
		</div>
	</div>

</section>

<script>

	var boardRight = $('#boardView_right');
	var slidebtn = $('#slideButton');
	var width = $('#boardView_left').width();

	function slide() {
		var width = $('#boardView_left').width();

		if (width == 0) {
			// 서브메뉴가 열린상태
			$('#boardView_left').css({"z-index":"999", "transition": "500ms", "width": "210px","border-right":"1px solid #c2c2c2"});
			//shopRight.css({ "transition": "500ms", "width": "735px", "margin-left": "260px" });
			slidebtn.css({ "transition": "800ms", "margin-left": "215px", "background-color": "#fff", "color": "#252525" });
			slidebtn.html('메<br>뉴<br>접<br>기<br><');
		} else {
			// 서브메뉴가 접힌상태
			$('#boardView_left').css({ "transition": "500ms", "width": "0px", "border":"none"});
			boardRight.css({ "transition": "500ms", "width": "945px", "margin-left": "45px" });
			slidebtn.css({ "transition": "800ms", "margin-left": "0px", "background-color": "#fff", "color": "#333" });
			slidebtn.html('메<br>뉴<br>열<br>기<br>>');
		}
	}
	
	$('#like_btn').mouseenter(function() {
		$('#like_img').attr("src", "<%=request.getContextPath()%>/images/board_liked.png");
	});
	$('#like_btn').mouseleave(function(){
		$('#like_img').attr("src", "<%=request.getContextPath()%>/images/board_like.png");
	});
	
	function clickLike() {
		
		<%if(logginMember!=null && logginMember.getMemberEmail() != null) {
			if(!logginMember.getMemberNickname().equals(b.getBoardWriter())) {	%>
				location.href='<%=request.getContextPath()%>/board/boardLike?boardNo=<%=b.getBoardNo()%>&userId=<%=logginMember.getMemberEmail()%>';
			<% } else { %>
				alert('자신의 글에는 추천하실 수 없습니다');
			<% }
		} else { %>
			alert('로그인 후 이용가능합니다.');	
		<% } %>
	}
	

	
	function fn_loginAlert() {
		alert("로그인 후 이용할 수 있습니다.");
	}
	
	$(function(){
		
		 $("textarea[name=chat_content]").focus(function(e) {
			if(<%=logginMember==null%>) {
				fn_loginAlert();
				$("textarea[name=chat_content]").blur();
			}
		});
		
		$("[name=commentFrm]").submit(function(e) {
			if(<%=logginMember==null%>) {
				fn_loginAlert();
				e.preventDefault();
				return;
			}
			var area = $("textarea[name=chat_content]").val().trim().length;
			var areaMax = $("textarea[name=chat_content]").val().length;
			
			if(area == 0) {
				alert("내용을 입력하세요");
				e.preventDefault();
				return;
			}
			if(area > 600) {
				alert("댓글을 600 bytes 이하로 입력해주시기 바랍니다.");
				e.preventDefault();
				return;
			}
		});
		
		$("[name=inp]").submit(function(e) {
			if(<%=logginMember==null%>) {
				fn_loginAlert();
				e.preventDefault();
				return;
			}
			
		});
	});

	$(function(){
		$(".btn-delete").on('click',function(){
			if(confirm("정말로 삭제하시겠습니까?")) { location.href="<%=request.getContextPath()%>/board/boardCommentDelete?boardNo=<%=b.getBoardNo()%>&delNo=" + $(this).val(); }
		});
		
		$("textarea[name=boardCommentContent]").focus(function(){
			if(<%=logginMember==null%>)
			{
				fn_loginAlert();
				
			}
		});
		
	});

	$(".btn-reply").on("click", function() {
		<%if(logginMember != null) {%>
			var tr = $("<tr></tr>");
			var html = "<td colspan='4'>";
			html += "<form action='<%=request.getContextPath()%>/board/boardCommentInsert' method='post'>";
			html += "<input type='hidden' name='boardRef' value='<%=b.getBoardNo()%>'/>";
			html += "<input type='hidden' name='boardCommentWriter' value='<%=logginMember.getMemberNickname()%>'/>";
			html += "<input type='hidden' name='boardCommentLevel' value='2'/>";
			html += "<input type='hidden' name='boardCommentRef' value='"+$(this).val()+"'/>";
			html += "<textarea placeholder='댓글을 입력하세요.' cols='70' rows='4' name='chat_content' style='resize:none; outline:none; padding: 8px; margin-left: 120px;'></textarea>";
			html += "<button id='insert2' type='submit' class='btn-insert2'>등록</button>";
			html += "</form></td>";
			tr.html(html);
			
			tr.insertAfter($(this).parent().parent()).children('td');
			$("textarea[name=chat_content]").focus();
			$(this).off('click');
				
			tr.find("form").submit(function(e) {
				if(<%=logginMember == null%>) {
					fn_loginAlert();
					e.preventDefault();
					return;
				}
				var content = $(this).children('textarea').val().trim().length;
				var contentMax = $(this).children('textarea').val().length;
				if(content == 0) {
					alert("내용이 비었습니다.");
					e.preventDefault();
					return;
				}
				if(contentMax >= 600) {
					alert("댓글을 600 bytes 이하로 입력해주시기 바랍니다.");
					e.preventDefault();
					return;
				}
			});
			
		<% } else { %>
				fn_loginAlert();
		<% } %>
	});
	
	function directQuestion() {
		<%if(logginMember != null) {%>
		location.href='<%=request.getContextPath()%>/board/boardDirectQuestion?NickName=<%=logginMember.getMemberNickname()%>';
		<%} else {%>
			alert('로그인 후 이용가능합니다.');
		<%}%>
	}
	<%if(logginMember != null) { %>
	function report(){
		
		var content = $('#view_content').html();
		text = content.replace(/<(\/)?([a-zA-Z]*)(\s[a-zA-Z]*=[^>]*)?(\s)*(\/)?>/ig, "");
		
		var url = "<%=request.getContextPath()%>/board/boardReport?loginUser=<%=logginMember.getMemberNickname()%>&reportedUser=<%=b.getBoardWriter()%>&reportedContent=" + text + "&boardMainCategory=board&boardNo=<%=b.getBoardNo()%>";
		var popupX = 287; /* 1024/2-450/2 */
		var popupY = 210;  /* 780/2-360/2 */
		var option = "left=" + popupX + ", top=" + popupY + ", width=450, height=360, resizable=no, scrollbars=no, status=no;";

		window.open(url,"REPORT",option);
	}

	function commentReport(commentWriter, commentContent, boardReportNo) {
		var reportedUser = commentWriter;
		var reportedContent = commentContent;
		var boardNo = boardReportNo;
		
		var url = "<%=request.getContextPath()%>/board/boardReport?loginUser=<%=logginMember.getMemberNickname()%>&reportedUser=" + reportedUser + "&reportedContent=" + reportedContent + "&boardMainCategory=comment&boardNo=" + boardNo;
		var popupX = 287; /* 1024/2-450/2 */
		var popupY = 210;  /* 780/2-360/2 */
		var option = "left=" + popupX + ", top=" + popupY + ", width=450, height=360, resizable=no, scrollbars=no, status=no;";

		window.open(url,"REPORT",option);
	}
	<%}%>
	
</script>





<%@ include file="/views/common/footer.jsp" %>