<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<footer>
    <div id="footer_content">
        <div class="footer_top">
            <div class="footer_top_left">
                <h3>HOBBYIST</h3>
                새로운 취미 하비스트<br>
                Find a new Hobby with Hobbyist<br><br>
                사업자등록번호 : 851-87-00622<br>
                서울 강남 제2014-01호<br>
                서울특별시 강남구 테헤란로14길 6 남도빌딩 2F<Br>
            </div>
            <div class="footer_top_center">
                <h3>SITE MENU</h3>
                    <ul>
                        <li onclick="location.href='<%= request.getContextPath()%>/notice/noticeList'">공지사항 </li>
                        <li onclick="location.href='<%= request.getContextPath()%>/shop/shopList'">클래스샵</li>
                        <li onclick="location.href='<%=request.getContextPath()%>/oneday/onedayList'">원데이클래스</li>
                        <li onclick="location.href='<%= request.getContextPath()%>/award/awardList'">어워드</li>
                        <li onclick="location.href='<%=request.getContextPath()%>/board/boardList'">커뮤니티</li>
                    </ul>
            </div>
            <div class="footer_top_right">
                <h3>하비스트 정기구독</h3>
                <p>하비스트의 새로운 클래스와 이벤트 등 다양한 소식을 받아보세요!</p>
                <input type="email"  placeholder="Your Email..."/><input id="subsc" type="button" value="구독"/>
            </div>
            <script>
            	$('#subsc').on('click', function() {
            		alert('구독신청이 완료되었습니다.');
            	});
            </script>
        </div>
        <div class="footer_bottom">
            @ 2019 Hobbyist all rights Reserved.
        </div>
    </div>
</footer>
</body>

</html>