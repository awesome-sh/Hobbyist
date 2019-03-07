<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>

<div class="admin_left">
   <ul>
      <li onclick="location.href='<%=request.getContextPath()%>/adminMemberList.do'">회원관리</li>
      <li>작가관리</li>
      <ul class="sub">
         <li onclick="location.href='<%= request.getContextPath()%>/admin/adminWriterEnroll'">작가 신청 관리</li>
      </ul>
      <li>공지 관리</li>
      <ul class="sub">
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminNoticePreList'">예약공지 목록</li>
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminNoticeDelList'">삭제공지 목록</li>
      </ul>
      <li>클래스샵 관리</li>
      <ul class="sub">
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminShopList'">클래스 목록</li>
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminShopWrite'">클래스 등록</li>
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminPinList'">PIN CODE 관리</li>
      </ul>
      <li>원데이클래스 관리</li>
      <ul class="sub">
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminOnedayList'">원데이클래스 목록</li>
         <li onclick="location.href='<%= request.getContextPath() %>/admin/adminOnedayWrite'">원데이클래스 등록</li>
      </ul>
      <li>커뮤니티 관리</li>
      <ul class="sub">
	      <li onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityList'">자유게시판 목록</li>
		  <li onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityFAQList'">FAQ 자주 묻는 질문 목록</li>
		  <li onclick="location.href='<%=request.getContextPath()%>/admin/community/adminCommunityDQList'">1:1 문의 목록</li>
	  </ul>
      <li>주문관리</li>
      <ul class="sub">
	      <li onclick="location.href='<%= request.getContextPath() %>/admin/adminOrderList'">주문관리 목록</li>
		  <li onclick="location.href='<%=request.getContextPath()%>/admin/adminOrderExList'">환불처리 목록</li>
	  </ul>
   </ul>
</div>

<script>
$(function () {
    var adminLeftSelectValue = "";
    $(".admin_left>ul>li").click(function () {
       if($(this).html() == adminLeftSelectValue) {
          adminLeftSelectValue = $(this).html();
          $(this).next("ul").slideToggle(500);
       } else {
          adminLeftSelectValue = $(this).html();
          $(".admin_left>ul>li").next("ul").slideUp(500);
          $(this).next("ul").slideToggle(500);
       }
    })
 })

 $(window).scroll(function () {
    var target = $('#admin_top');
    var scrollTo = $(window).scrollTop();
    if (scrollTo != 0) {
       $('#admin_top').css("top", "97px");  
    } else {
       $('#admin_top').css("top", "153px");
    }
 });

 //메인 헤더 숨기기
 $(window).scroll(function () {
    if ($(window).scrollTop() != 0) {
       $("#header-middle").css("transition", "500ms");
       $("#header-middle").css("height", "0px");
       $("#header-middle .middle").css("display", "none");
    } else {
       $("#header-middle").css("height", "100px");
       setTimeout(function () {
          $("#header-middle .middle").css("display", "inline-block");
       }, 500)
    }
 });
</script>