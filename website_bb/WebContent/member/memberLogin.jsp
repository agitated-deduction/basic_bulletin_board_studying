<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix ="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
<title>login page</title>
</head>
<body>
<%@include file = "../header.jsp" %>
<div class="container">
	<h1>LOGIN PAGE</h1>
	<form action ="memberLogin.member" method = "POST">
	<div class="form-group">
		 <label for="id">ID:</label><input type = "text" class = "form-control" name= "id" value = "${cookieID }">
		 </div>
		 <div class="form-group">
		 <label for="password">password:</label><input type = "password" class = "form-control" name= "password">
		 </div>
		<div class = "checkbox">
		<c:choose>
			<c:when test = "${cookieID eq null}">
				<input type = "checkbox" name = "remember" >remember me <br>
			</c:when>
		
			<c:otherwise>
				<label></label><input type = "checkbox" name = "remember" checked="checked">remember me 
		
			</c:otherwise>
		</c:choose>
		</div>
		<button type="submit" class="btn btn-default">Submit</button>
	</form>
</div>
</body>
</html>