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
	<h1>${board } selected contents</h1>
	<h3>title : ${dto.getTitle() }</h3>
	writer : ${dto.getWriter() }<br/>
	date : ${dto.getReg_date() }<br/>
	hit : ${dto.getHit() }<br/>
	Contents<br/>
	${dto.getContents() }
	<br/><br/>
	<!-- 1. board== freeboard 2. board== notice && session id == admin 두가지 경우만 버튼 보이도록 설정할 것!-->
	<a href = "./boardUpdate.${board }?num=${dto.getNum()}">UPDATE</a>
	<br/>
	<a href = "./boardDelete.${board }?num=${dto.getNum()}">DELETE</a>
</body>
</html>