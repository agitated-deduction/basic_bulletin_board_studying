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
	
	<table>
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
	<c:if test = "${startPage !=1}">
	<a href = "boardList.${board }?curPage=${startPage-1 }&search=${saerch }&searchBy=${searchBy }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPage}" end= "${endPage}">
	|	<a href = "boardList.${board}?curPage=${i}&search=${saerch }&searchBy=${searchBy }">${i}</a>
	</c:forEach>
	|
	<c:if test = "${endPage!=totalPage}">
	<a href = "boardList.${board }?curPage=${endPage+1 }&search=${saerch }&searchBy=${searchBy }">[다음]</a>
	</c:if>
	<br>
	
	<!-- 1. board== freeboard 2. board== notice && session id == admin 두가지 경우만 버튼 보이도록 설정할 것!-->
	<a href = "./boardWrite.${board}">WRITE</a>
</body>
</html>