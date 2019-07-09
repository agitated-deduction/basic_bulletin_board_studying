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
<title>Insert title here</title>
</head>
<body>
<%@include file = "../header.jsp" %>
	<h1>${board} LIST</h1>
	<br>
	
	<form action = "./boardList.${board }">
		<select name="searchBy">
    		<option value="">검색기준</option>
    		<option value="title">제목</option>
    		<option value="writer">작성자</option>
    		<option value="contents">내용</option>
		</select>
		<input type = "text" name = "search" value = "">
		<input type = "submit" value = "검색">
	</form>
	<p align="right"><a href = "./boardWrite.${board}"class="btn btn-default" role="button" >글 쓰기</a></p> 
	
	<table class = "table table-hover">
	<thead>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		</thead>
		<tbody>
		<c:forEach var = "dto" items = "${dtoList}">
			<tr>
				<td>${dto.getNum()}</td>
				<td><a href = "boardSelectOne.${board }?num=${dto.getNum()}">${dto.getTitle()}</a></td>
				<td>${dto.getWriter()}</td>
				<td>${dto.getReg_date()}</td>
				<td>${dto.getHit()}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<br>
	<div class= "container">
	<ul class = "pagination">
	<c:if test = "${startPage !=1}">
	<li><a href = "boardList.${board }?curPage=${startPage-1 }&search=${saerch }&searchBy=${searchBy }">이전</a></li>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end= "${endPage}">
	<li><a href = "boardList.${board}?curPage=${i}&search=${saerch }&searchBy=${searchBy }">${i}</a></li>
	</c:forEach>

	<c:if test = "${endPage!=totalPage}">
	<li><a href = "boardList.${board }?curPage=${endPage+1 }&search=${saerch }&searchBy=${searchBy }">다음</a></li>
	</c:if>
	</ul>
	<br>
	</div>
	<!-- 1. board== freeboard 2. board== notice && session id == admin 두가지 경우만 버튼 보이도록 설정할 것!-->
	
</body>
</html>