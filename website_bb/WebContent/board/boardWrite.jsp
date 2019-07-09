
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
<div class = "container">
	<h1>${board} WRITE FORM</h1>

			<form action = "boardWrite.${board}" method = "POST">
			<div class = "form-group">
					<label for ="title">title</label> <input type = "text" class = "form-control" name = "title">
			</div>
			<div class = "form-group">
			
				<label for = "writer">writer</label>  <input type = "text" class = "form-control" name = "writer">
			</div>
			<div class = "form-group">
				<label for = "contents">contents</label>
				<textarea class="form-control" rows="10" id="contents" name = "contents"></textarea>
			</div>
				<button type="submit" class="btn btn-default">Write</button>
			</form>
	
		</div>
</body>
</html>