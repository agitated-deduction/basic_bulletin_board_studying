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
	<h1>member INFO</h1>
	ID : ${memberDTO.id }<br>
	email : ${memberDTO.email }<br>
	name : ${memberDTO.name }<br>
	gender : ${memberDTO.gender }<br>
	<a href = "./memberModify.member">회원 정보 수정</a><br>
	<a href = "./memberWithdrawal.member">회원 탈퇴</a>
</body>
</html>