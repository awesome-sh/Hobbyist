<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String msg = (String)request.getAttribute("msg");
   		String loc = (String)request.getAttribute("loc");
   		int no = (Integer)request.getAttribute("classNo");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
		var confirm = confirm('<%= msg %>');
		if (confirm) {
			location.href="<%=request.getContextPath()%><%=loc%>";	
		} else {
			location.href="<%=request.getContextPath()%>/shop/shopView?no=<%= no %>";	
		}
		
	</script>
</body>
</html>