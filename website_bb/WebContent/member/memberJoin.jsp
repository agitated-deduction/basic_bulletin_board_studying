<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@include file = "../header.jsp" %>
<h1>JOIN PAGE</h1>
	<form action = "memberJoin.member" method = "POST">
		ID <input type = "text" name = "id"><br>
		Password <input type = "password" name = "password"><br>
		Email <input type = "email" name = "email"><br>
		gender<select name = "gender">
		<option value = "">==성별==</option>
		<option value="female">여성</option>
		<option value = "male">남성</option>
		<option value = "none of these">보기에 없음</option>
		</select><br>
		name <input type = "text" name = "name"><br>
		<input type = "submit" value = "SUBMIT">
	</form>

</body>
</html>