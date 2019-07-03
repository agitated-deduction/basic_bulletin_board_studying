
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
	<h1>${board} UPDATE FORM</h1>

	<form action = "boardUpdate.${board}" method = "POST">
		<input type = "hidden" name = "num" value = "${dto.num }">
		title  <input type = "text" name = "title" value = "${dto.title }"><br>
		writer  <input type = "text" name = "writer"value = "${dto.writer }"><br>
		<br>contents<br>
		<textarea cols = "100" rows = "20" name = "contents">${dto.contents }</textarea><br>
		<button>Update</button>	
	</form>
</body>
</html>