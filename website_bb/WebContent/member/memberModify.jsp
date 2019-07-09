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
<div class = "container">
<h1>MODIFY PAGE</h1>
	<form action = "memberModify.member" method = "POST">
		<div class="form-group">
		 <label for="id">ID:</label>${memberDTO.id }
		 </div>
		 
		 <div class="form-group">
		 <label for="email">Email:</label><input type = "email" class = "form-control" name= "email" value = ${memberDTO.email }>
		 </div>
		 <div class="form-group">
		 <label for="gender">gender:</label><select name = "gender">
		<option value = "">==성별==</option>
		<option value="female">여성</option>
		<option value = "male">남성</option>
		<option value = "none of these">보기에 없음</option>
		</select>
		 </div>
		 <div class="form-group">
		<label for="name">name:</label><input type = "text" class = "form-control" name= "name" value = ${memberDTO.name }>
		 </div>
		<button type="submit" class="btn btn-default">Update</button>
	</form>
</div>


</body>
</html>