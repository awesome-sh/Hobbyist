<%@page import="com.hobbyist.member.model.vo.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	Member logginMember = (Member)session.getAttribute("logginMember");
%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>H O B B Y I S T</title>
    <!-- ------- 페이지 별 CSS 추가 -------- -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/loginStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/emailCheck.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/writerEnrollStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/orderStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/noticeStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/awardStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/boardStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/myCartStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/myClassStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/onedayStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/studyStyle.css">

    <!-- ------------- 관리자페이지 CSS -------------- -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/adminStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_memberStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_noticeDelListStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_noticePreListStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_shopStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_shopViewStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_shopWriteStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_shopPinStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_onedayStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_onedayViewStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_onedayWriteStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_writerEnrollStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_communityStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/admin_noticeDelListStyle.css">
    
	<!-- ------------- 마이페이지 CSS -------------- -->
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/myPageStyle.css">
	<link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage_communityStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/mypage_writerEnrollStyle.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/myPage_writerEnrollModifyStyle.css">

    <!-- ------ Wow 애니메이션 ------ -->
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/animate.css">

    <!-- ----------- JQUERY 추가 ----------- -->
    <script src="<%= request.getContextPath() %>/js/jquery-3.3.1.min.js"></script>
    <!-- ----------- Wow.js 추가 ----------- -->
    <script src="<%= request.getContextPath() %>/js/wow.min.js"></script>
    <!-- WOW 애니메이션 효과 모든 페이지 적용하기 위해 불러옴 -->
    <script>
        new WOW().init();
    </script>
    <!-- -------------- 타이핑 JS 추가 -------------- -->
    <script src="<%= request.getContextPath() %>/js/typed.min.js"></script>

    <!-- TEXTAREA EDITOR ( CKEDITOR VER 4 ) -->
    <script src="//cdn.ckeditor.com/4.11.2/standard/ckeditor.js"></script>

    <!-- alert 라이브러리 -->
    <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>

    <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>

</head>

<body>

    <!-- 결제 api 추가 -->
    <!-- 서브메뉴 마우스오버시 배경 블랙으로 변경하기 위한 숨겨놓은 DIV -->
    <div class="blur"></div>

    <header>
        <div id="header_line"></div>
        <script>
            $(window).scroll(function () {
                var target = $('#header_line');
                var scrollTo = $(window).scrollTop(),
                    docHeight = $(document).height(),
                    windowHeight = $(window).height();
                scrollPercent = (scrollTo / (docHeight - windowHeight)) * 100;
                target.css("width", scrollPercent + "%");
            })
        </script>
        <div id="header-top">
            <div class="left-top">
                <a href="<%= request.getContextPath() %>"><img alt="하비스트"
                        src="<%= request.getContextPath() %>/images/logo.jpg"></a>
            </div>
            <div class="top">
                <ul>
                    <% if(logginMember==null) {%>
                    <li onclick="fn_loginPageView()">로그인/회원가입
                    </li>
                    <% } else { %>
                    <span><a href="#"><img
                                src="<%= request.getContextPath() %>/upload/member/<%= logginMember.getMemberRenamedImage() %>"
                                width="15px" height="15px">
                            <%= logginMember.getMemberNickname() %></a> 님 환영합니다</span>
                    <% if (logginMember.getMemberEmail().equals("admin")) {%>
                    <li id="admin" onclick="location.href='<%= request.getContextPath() %>/views/admin/admin.jsp'">관리자
                    </li>&nbsp;&nbsp;&nbsp;
                    <% } %>
                    <li onclick="location.href='<%= request.getContextPath() %>/mypage/myPage'">마이페이지</li>
                    <li onclick="location.href='<%= request.getContextPath() %>/member/LogoutMember'">로그아웃</li>
                    <% } %>
                </ul>
            </div>
        </div>
        <div id="header-bottom">
            <div class="bottom">
                <ul>
                    <li style="width: 160px;">
                        <div class="menuline"></div><img src="<%=request.getContextPath()%>/images/allmenu.png"
                            width="10px">&nbsp;&nbsp;전체보기
                    </li>
                    <li style="width: 130px;"
                        onclick="location.href='<%= request.getContextPath()%>/notice/noticeList'">
                        <div class="menuline"></div>공지사항
                    </li>
                    <li style="width: 130px;" onclick="location.href='<%= request.getContextPath()%>/shop/shopList'">
                        <div class="menuline"></div>클래스샵
                    </li>
                    <li style="width: 130px;" onclick="location.href='<%=request.getContextPath()%>/oneday/onedayList'">
                        <div class="menuline"></div>원데이클래스
                    </li>
                    <li style="width: 130px;" onclick="location.href='<%= request.getContextPath()%>/award/awardList'">
                        <div class="menuline"></div>어워드
                    </li>
                    <li style="width: 130px;" onclick="location.href='<%=request.getContextPath()%>/board/boardList'">
                        <div class="menuline"></div>커뮤니티
                    </li>
                    <li style="width: 66px;"><img src="<%= request.getContextPath() %>/images/myclass.png"
                            id="icon_myclass" width="23px" onclick="fn_myclass()" style="cursor:pointer;"></li>
                    <li style="width: 66px;">
                        <img src="<%= request.getContextPath() %>/images/cart.png" id="icon_cart" width="23px"
                            onclick="fn_cart()" style="cursor:pointer;"></li>

                </ul>
            </div>

            <!-- ------------ 전체메뉴보기 서브메뉴 ----------- -->
            <div class="sub" id="menuAll">
                <div class="sub_content">
                    <ul>
                    <li class="all_li">
                  		<ul>
                        	<li onclick="fn_loginPageView()">로그인</li>
                        	<li></li>
                        	<li onclick="fn_myclass()">내클래스</li>
                        	<li onclick="fn_cart()">취미바구니</li>
                        </ul>
                    </li>
                    <li  class="all_li">
                        <ul>
                        	<li onclick="location.href='<%= request.getContextPath()%>/notice/noticeList'">공지사항/이벤트</li>
                        </ul>
                    </li>
                    <li class="all_li">
                        <ul>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopList'">전체보기</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=가죽공예'">가죽공예</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=위빙'">위빙</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=수채화'">수채화</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=마크라메'">마크라메</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=뜨개질'">뜨개질</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/shop/shopCateList?cate=프랑스자수'">프랑스자수</li>
                        </ul>
                    </li>
                    <li class="all_li">
                       	<ul>
                        	<li onclick="location.href='<%=request.getContextPath() %>/oneday/onedayList'">전체보기</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/oneday/onedayCateList?cate=서울'">서울지역</li>
                        	<li onclick="location.href='<%=request.getContextPath() %>/oneday/onedayCateList?cate=경기'">경기지역</li>
                        </ul>
                    </li>
                    <li class="all_li">
                        <ul>
                        	<li onclick="location.href='<%=request.getContextPath() %>/award/awardList'">어워드</li>
                        </ul>
                    </li>
                    <li class="all_li">
                        <ul>
                        	<li onclick="location.href='<%=request.getContextPath()%>/board/boardList'">자유게시판</li>
                        	<li>리뷰/후기</li>
                        	<li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'">자주묻는질문</li>
                        	<li onclick="directQuestion()" style="cursor: pointer;"> · 1:1 문의</li>
							<script>
								function directQuestion() {
									<%if(logginMember != null) {%>
									location.href='<%=request.getContextPath()%>/board/boardDirectQuestion?NickName=<%=logginMember.getMemberNickname()%>';
									<% } else { %>
                                            alert('로그인 후 이용가능합니다.');
									<% } %>
								}
                                </script>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
            <div class="sub" id="notice">
                <div class="sub_content">
                    <div class="sub_left">
                        <h3>N O T I C E & E V E N T</h3>
                        <p>공지사항 및 이벤트</p>
                        <br>
                        <div id="writerEnrollAlert">
                        </div>
                        <script>
                        	// 작가 모집 공고 AJAX
	                        $.ajax({
	                            url: '<%= request.getContextPath() %>/notice/writerEnrollAlertAjax.do',
	                            success: function (data) {
                                    $('#writerEnrollAlert').empty();
	                                $('#writerEnrollAlert').html(data);
	                            }
	                        });
                        </script>
                        <div id="line"></div>
                    </div>
                    <div class="sub_center">
                        <h3>L A T E S T - N O T I C E</h3>
                        <ul id="newLatestNotice">
                        </ul>
                        <script>
                            // 공지사항 최근 글 AJAX
                            $.ajax({
                                url: '<%= request.getContextPath() %>/notice/newLatestNoticeAjax.do',
                                success: function (data) {
                                    $('#newLatestNotice').html(data);
                                }
                            });
                        </script>
                    </div>
                    <div class="sub_right">
                        <h3>S U B - M E N U</h3>
                        <ul>
                            <li onclick="location.href='<%=request.getContextPath()%>/notice/noticeList'"> · 공지사항</li>
                            <li onclick="location.href='<%=request.getContextPath()%>/notice/noticeList'"> · 이벤트</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="sub" id="classshop">
                <div class="sub_content">
                    <div class="sub_left">
                        <h3>C L A S S - S H O P</h3>
                        <p>마음에 드는 취미클래스를 찾아보세요</p>
                        <p><img alt="CLASS SHOP" src="<%= request.getContextPath() %>/images/classshop.png" width="25%">
                        </p>
                    </div>
                    <div class="sub_center">
                        <h3>N E W - C L A S S</h3>
                        <ul id="newLatestClass">
                        </ul>
                        <script>
                            // 클래스샵 최근 상품 AJAX
                            $.ajax({
                                url: '<%= request.getContextPath() %>/shop/shopLatestClass',
                                success: function (data) {
                                    $('#newLatestClass').html(data);
                                }
                            });
                        </script>
                    </div>
                    <div class="sub_right">
                        <h3>B E S T - C L A S S</h3>
                        <ul id="bestClass">
                        </ul>
                        <script>
                            // 클래스샵 베스트 상품 AJAX
                            $.ajax({
                                url: '<%= request.getContextPath() %>/shop/shopBestClass',
                                success: function (data) {
                                    $('#bestClass').html(data);
                                }
                            });
                        </script>
                    </div>
                </div>
            </div>
            <div class="sub" id="oneday">
                <div class="sub_content">
                    <div class="sub_left">
                        <h3>O N E D A Y - C L A S S</h3>
                        <p>오프라인 취미클래스 <br>하비스트 원데이클래스</p>
                    </div>
                    <div class="sub_center">
                        <h3>하비스트 원데이클래스</h3>
                        <p>원데이클래스는 하비스트 작가를 초빙해 오프라인으로 진행되는 취미클래스입니다. 예약제로 진행되며 자세한 사항은 각 해당 원데이클래스 정보를 확인하세요</p>
                    </div>
                    <div class="sub_right">
                        <h3>S U B - M E N U</h3>
                        <ul>
                            <li onclick="location.href='<%=request.getContextPath()%>/oneday/onedayList'"> · 원데이클래스</li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="sub" id="award">
                <div class="sub_content">
                    <div class="sub_left">
                        <h3>A W A R D</h3>
                        <p>하비스트 취미클래스를 통해 완성작품을 공유해보세요.
                            매달 추천수가 많은 작품들을 선정해 포인트를 적립해드립니다</p>
                    </div>
                    <div class="sub_center">
                    <br><Br><Br>
                    <img src="<%= request.getContextPath() %>/upload/award/images/award39.jpg" width="110px" style="cursor:pointer" onclick="location.href='<%=request.getContextPath() %>/award/awardList'">
                    <img src="<%= request.getContextPath() %>/upload/award/images/award38.jpg" width="110px" style="cursor:pointer" onclick="location.href='<%=request.getContextPath() %>/award/awardList'">
                    <img src="<%= request.getContextPath() %>/upload/award/images/award37.jpg" width="110px" style="cursor:pointer" onclick="location.href='<%=request.getContextPath() %>/award/awardList'">
                    </div>
                </div>
            </div>
            <div class="sub" id="community">
                <div class="sub_content">
                    <div class="sub_left">
                        <h3>C O M M U N I T Y</h3>
                        <p>커뮤니티</p>
                        <center><img src="<%=request.getContextPath()%>/images/community.png" width="90px"></center>
                    </div>
                    <div class="sub_center">
                        <h3>L A T E S T - C O M M N I T Y</h3>
                        <ul id="board_latest">
                            <script>

                                $.ajax({
                                    url: "<%=request.getContextPath()%>/board/boardLatest",
                                    success: function (data) {
                                        $('#board_latest').html(data);
                                    }
                                });

                            </script>
                        </ul>
                    </div>
                    <div class="sub_right">
                        <h3>S U B - M E N U</h3>
                        <ul>
                            <li onclick="location.href='<%=request.getContextPath()%>/board/boardList'"
                                style="cursor: pointer;"> · 자유게시판</li>
                            <li style="cursor: pointer;"> · 리뷰/후기</li>
                            <li onclick="location.href='<%=request.getContextPath()%>/board/boardFAQ'"
                                style="cursor: pointer;"> · FAQ 자주 묻는 질문</li>
                            <li onclick="directQuestion()" style="cursor: pointer;"> · 1:1 문의</li>
                            <script>
                                function directQuestion() {
									<%if (logginMember != null) {%>
                                        location.href='<%=request.getContextPath()%>/board/boardDirectQuestion?NickName=<%=logginMember.getMemberNickname()%>';
									<%} else {%>
                                        alert('로그인 후 이용가능합니다.');
									<%}%>
								}
                            </script>
                        </ul>
                    </div>
                </div>
            </div>
            <script>
                var subMenu = $('.bottom ul>li');

                var menuAll = $('#menuAll');
                var notice = $('#notice');
                var classshop = $('#classshop');
                var oneday = $('#oneday');
                var award = $('#award');
                var community = $('#community');

                var blur = $('.blur');

                // 전체보기
                subMenu.eq(0).mouseover(function () {
                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).addClass("active");
                    subMenu.eq(1).removeClass("active");
                    subMenu.eq(2).removeClass("active");
                    subMenu.eq(3).removeClass("active");
                    subMenu.eq(4).removeClass("active");
                    subMenu.eq(5).removeClass("active");
                    menuAll.css({ "transition": "400ms", "height": "250px" });
                    notice.css({ "transition": "400ms", "height": "0px" });
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    oneday.css({ "transition": "400ms", "height": "0px" });
                    award.css({ "transition": "400ms", "height": "0px" });
                    community.css({ "transition": "400ms", "height": "0px" });
                })

                menuAll.mouseleave(function () {
                    subMenu.eq(1).removeClass("active");
                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    blur.eq(0).css("display", "none");
                })

                // 공지사항 서브메뉴 이벤트
                subMenu.eq(1).mouseover(function () {
                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).removeClass("active");

                    subMenu.eq(1).addClass("active");
                    subMenu.eq(2).removeClass("active");
                    subMenu.eq(3).removeClass("active");
                    subMenu.eq(4).removeClass("active");
                    subMenu.eq(5).removeClass("active");
                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    notice.css({ "transition": "400ms", "height": "200px" });
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    oneday.css({ "transition": "400ms", "height": "0px" });
                    award.css({ "transition": "400ms", "height": "0px" });
                    community.css({ "transition": "400ms", "height": "0px" });
                })

                notice.mouseleave(function () {
                    subMenu.eq(1).removeClass("active");
                    notice.css({ "transition": "400ms", "height": "0px" });
                    blur.eq(0).css("display", "none");
                })

                // 클래스샵 서브메뉴 이벤트
                subMenu.eq(2).mouseover(function () {

                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).removeClass("active");
                    subMenu.eq(1).removeClass("active");
                    subMenu.eq(2).addClass("active");
                    subMenu.eq(3).removeClass("active");
                    subMenu.eq(4).removeClass("active");
                    subMenu.eq(5).removeClass("active");

                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    notice.css({ "transition": "400ms", "height": "0px" });
                    classshop.css({ "transition": "400ms", "height": "270px" });
                    oneday.css({ "transition": "400ms", "height": "0px" });
                    award.css({ "transition": "400ms", "height": "0px" });
                    community.css({ "transition": "400ms", "height": "0px" });
                })

                classshop.mouseleave(function () {
                    subMenu.eq(2).removeClass("active");
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    blur.eq(0).css("display", "none");
                })

                // 원데이클래스 서브메뉴 이벤트
                subMenu.eq(3).mouseover(function () {
                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).removeClass("active");

                    subMenu.eq(1).removeClass("active");
                    subMenu.eq(2).removeClass("active");
                    subMenu.eq(3).addClass("active");
                    subMenu.eq(4).removeClass("active");
                    subMenu.eq(5).removeClass("active");
                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    notice.css({ "transition": "400ms", "height": "0px" });
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    oneday.css({ "transition": "400ms", "height": "160px" });
                    award.css({ "transition": "400ms", "height": "0px" });
                    community.css({ "transition": "400ms", "height": "0px" });
                })

                oneday.mouseleave(function () {
                    blur.eq(0).css("display", "none");
                    subMenu.eq(3).removeClass("active");
                    oneday.css({ "transition": "400ms", "height": "0px" });
                })

                // 어워드 서브메뉴 이벤트
                subMenu.eq(4).mouseover(function () {

                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).removeClass("active");

                    subMenu.eq(1).removeClass("active");
                    subMenu.eq(2).removeClass("active");
                    subMenu.eq(3).removeClass("active");
                    subMenu.eq(4).addClass("active");
                    subMenu.eq(5).removeClass("active");

                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    notice.css({ "transition": "400ms", "height": "0px" });
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    oneday.css({ "transition": "400ms", "height": "0px" });
                    award.css({ "transition": "400ms", "height": "210px" });
                    community.css({ "transition": "400ms", "height": "0px" });
                })

                award.mouseleave(function () {
                    blur.eq(0).css("display", "none");
                    subMenu.eq(4).removeClass("active");
                    award.css({ "transition": "400ms", "height": "0px" });
                })

                // 커뮤니티 서브메뉴 이벤트
                subMenu.eq(5).mouseover(function () {
                    blur.eq(0).css("display", "block");
                    subMenu.eq(0).removeClass("active");
                    subMenu.eq(1).removeClass("active");
                    subMenu.eq(2).removeClass("active");
                    subMenu.eq(3).removeClass("active");
                    subMenu.eq(4).removeClass("active");
                    subMenu.eq(5).addClass("active");

                    menuAll.css({ "transition": "400ms", "height": "0px" });
                    notice.css({ "transition": "400ms", "height": "0px" });
                    classshop.css({ "transition": "400ms", "height": "0px" });
                    oneday.css({ "transition": "400ms", "height": "0px" });
                    award.css({ "transition": "400ms", "height": "0px" });
                    community.css({ "transition": "400ms", "height": "205px" });

                })

                community.mouseleave(function () {
                    blur.eq(0).css("display", "none");
                    subMenu.eq(5).removeClass("active");
                    community.css({ "transition": "400ms", "height": "0px" });
                })




            </script>
        </div>
        <%-- <div id="header-middle">
            <div class="middle">
                <div class="logo">
                    
                </div>
                <div class="search">
                    <input type="search" placeholder="Search..." /><input type="button" value="검색" />
                </div>
                <div class="info">
                    <!-- dddd -->
                </div>
                <script>
                    $.ajax({
                        url: '<%= request.getContextPath() %>/myCartCount?member=<%= logginMember!=null ? logginMember.getMemberEmail() : "" %>',
                        success: function (data) {
                            if (data != 0) {
                                $('#cartCount').html(data);
                            } else {
                                $('#cartCount').css("display", "none");
                            }
                        }
                    });

                    // 내클래스, 장바구니 마우스오버 시 서브
                    $('#icon_myclass').mouseover(function () {
                        $('#icon_sub').html('내 클래스').css({ "display": "inline-block", "opacity": "1" });
                    })
                    $('#icon_myclass').mouseleave(function () {
                        $('#icon_sub').css("display", "none");
                    })

                    $('#icon_cart').mouseover(function () {
                        $('#icon_sub').html('취미바구니').css({ "display": "inline-block", "opacity": "1" });
                    })
                    $('#icon_cart').mouseleave(function () {
                        $('#icon_sub').css("display", "none");
                    })

                    // 내클래스
                    function fn_myclass() {
                        if (<%= logginMember != null %>) {
                            location.href =
                                '<%= request.getContextPath() %>/myClass?member=<%= logginMember!=null? logginMember.getMemberEmail() : "" %>';
                        } else {
                            swal("[내 클래스] 로그인 후 이용해주세요");
                            return;
                        }
                    }

                    // 장바구니
                    function fn_cart() {
                        if (<%= logginMember != null %>) {
                            location.href =
                                '<%= request.getContextPath() %>/myCart?member=<%= logginMember!=null? logginMember.getMemberEmail() : "" %>';
                        } else {
                            swal("[장바구니] 로그인 후 이용해주세요");
                            return;
                        }
                    }
                </script>
        </div>
        </div> --%>
        <script>
            // 내클래스
            function fn_myclass() {
                if (<%= logginMember != null %>) {
                    location.href = '<%= request.getContextPath() %>/myClass?member=<%= logginMember!=null? logginMember.getMemberEmail() : "" %>';
                } else {
                    swal("[내 클래스] 로그인 후 이용해주세요");
                    return;
                }
            }

            // 장바구니
            function fn_cart() {
                if (<%= logginMember != null %>) {
                    location.href = '<%= request.getContextPath() %>/myCart?member=<%= logginMember!=null? logginMember.getMemberEmail() : "" %>';
                } else {
                    swal("[장바구니] 로그인 후 이용해주세요");
                    return;
                }
            }
        </script>
    </header>

    <%@ include file="/views/member/loginPage.jsp" %>

    <script>
        function fn_loginPageView() {
            $("#loginSection").css("display", "flex");
        }

    </script>
    <%-- 
    <!-- 로그인 페이지 연결 -->
    <%
	// 로그인 화면창 분기 처리
	boolean loginFlag = false;
	if(request.getAttribute("loginFlag")!=null) {
		loginFlag = (boolean)request.getAttribute("loginFlag");
	}
	%>
    <% if(loginFlag==true) { %>
    <% }; %>
    --%>