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
<title>My info</title>
</head>
<body>
<%@include file = "../header.jsp" %>
	<div class="container">
  <h2>INFO</h2>          
  
  <table class="table table-bordered">
  <colgroup>
<col width="10px" />
<col width="100px" />
</colgroup>
      <tr>
      <th>ID</th>
      <td>${memberDTO.id }</td>
      </tr>
      <tr>
        <th>email</th>
        <td>${memberDTO.email }</td>
      </tr>
      <tr>
        <th>name</th>
        <td>${memberDTO.name }</td>
      </tr>
      <tr>
        <th>gender</th>
        <td>${memberDTO.gender }</td>
      </tr>
  </table>


	<a href = "./memberModify.member"class="btn btn-default" role="button">회원 정보 수정</a> 
	<a href = "./memberWithdrawal.member"class="btn btn-danger" role="button">회원 탈퇴</a>
	</div>
</body>
</html>