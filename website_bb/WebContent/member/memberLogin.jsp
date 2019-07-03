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
	<h1>LOGIN PAGE</h1>
	<form action ="memberLogin.member" method = "POST">
		ID <input type = "text" name= "id" value = "${cookieID }"><br>
		password <input type = "password" name = "password"><br>
		<c:choose>
			<c:when test = "${cookieID eq null}">
				<input type = "checkbox" name = "remember" >remember me <br>
			</c:when>
		
			<c:otherwise>
				<input type = "checkbox" name = "remember" checked="checked">remember me <br>
		
			</c:otherwise>
		</c:choose>
		<input type ="submit" value = "login">
	</form>

</body>
</html>