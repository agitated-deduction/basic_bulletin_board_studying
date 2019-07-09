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
<title>Insert title here</title>
</head>
<body>
<%@include file = "../header.jsp" %>
<div class="container">
<table class="table table-bordered">

      <tr>
      <th colspan = "1">title</th> 
      <td colspan = "5">${dto.getTitle() }</td>
      <tr>
      <tr>
      <th>writer</th> 
      <td>${dto.getWriter() }</td>
      <th>date</th>
      <td>${dto.getReg_date() }</td>
      <th>hit</th>
      <td>${dto.getHit() }</td>
      </tr>
      <tr>
      <td colspan = "6" height = "200">
	${dto.getContents() }
	</td>
	</tr>
	</table>
	<!-- 1. board== freeboard 2. board== notice && session id == admin 두가지 경우만 버튼 보이도록 설정할 것!-->
	<a href = "./boardUpdate.${board }?num=${dto.getNum()}"class="btn btn-default" role="button">수정</a> 
	<a href = "./boardDelete.${board }?num=${dto.getNum()}"class="btn btn-danger" role="button">삭제</a>
	</div>
</body>
</html>