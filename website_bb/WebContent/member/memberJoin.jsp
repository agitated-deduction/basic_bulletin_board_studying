<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>join page</title>
</head>
<body>
<%@include file = "../header.jsp" %>
<div class = "container">
<h1>JOIN PAGE</h1>
	<form action = "memberJoin.member" method = "POST">
		<div class="form-group">
		 <label for="id">ID:</label><input type = "text" class = "form-control" name= "id">
		 </div>
		 <div class="form-group">
		 <label for="password">password:</label><input type = "password" class = "form-control" name= "password">
		 </div>
		 <div class="form-group">
		 <label for="email">Email:</label><input type = "email" class = "form-control" name= "email">
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
		<label for="name">name:</label><input type = "text" class = "form-control" name= "name">
		 </div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
</body>
</html>