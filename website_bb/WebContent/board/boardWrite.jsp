
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file = "../header.jsp" %>
	<h1>${board} WRITE FORM</h1>

			<form action = "boardWrite.${board}" method = "POST">
				title  <input type = "text" name = "title"><br>
				writer  <input type = "text" name = "writer"><br>
				<br>contents<br>
				<textarea cols = "100" rows = "20" name = "contents"></textarea><br>
				<button>Write</button>	
			</form>
	
		
</body>
</html>