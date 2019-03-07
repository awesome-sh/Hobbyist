<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8"%>

<%@ include file="/views/common/header.jsp" %>

    <section id="index">
        <div class="intro_content">
            <img class="wow fadeInUp slower" data-wow-delay="0.7s" alt="하비스트" src="<%= request.getContextPath()  %>/images/logo-white.png"><br>
            <div class="wow fadeInUp slow" id="intro-text"></div>
            <script>
            var typed = new Typed('#intro-text', {
            	  strings: ["하비스트와 함께 새로운 취미를 찾아보세요 <br> Find a new Hobby, with Hobbyist"],
            	  typeSpeed: 40
            	});
            </script>
        </div>
    </section>
    
    <script>
  	//메인 헤더 숨기기

  	$(window).scroll(function () {
	    if ($(window).scrollTop() != 0) {
	       $("#header-middle").css("transition", "500ms")
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

<%@ include file="/views/common/footer.jsp" %>

</body>
</html>