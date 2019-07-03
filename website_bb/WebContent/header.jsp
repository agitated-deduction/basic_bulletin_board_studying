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
	<a href="<%=request.getContextPath()%>/index.jsp">HOME</a>
	<a href="<%=request.getContextPath()%>/board/boardList.notice">Notice</a>
	<a href="<%=request.getContextPath()%>/board/boardList.freeboard">Free Board</a>
	<a href="<%=request.getContextPath()%>/board/boardList.qna">QnA</a>
	<c:if test = "${empty memberDTO}">
		<a href="<%=request.getContextPath()%>/member/memberJoin.member">Join</a>
		<a href="<%=request.getContextPath()%>/member/memberLogin.member">Login</a>
	</c:if>
	<c:if test = "${not empty memberDTO}">
		<a href="<%=request.getContextPath()%>/member/memberMyPage.member">My Page</a>
		<a href="<%=request.getContextPath()%>/member/memberLogout.member">Logout</a>
	</c:if>
</body>
</html>