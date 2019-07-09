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

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">

	<a class= "navbar-brand" href="<%=request.getContextPath()%>/index.jsp">my first web site</a>
	</div>
	<div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
      <li class="active"><a href = "<%=request.getContextPath()%>/index.jsp">HOME</a>
	<li><a href="<%=request.getContextPath()%>/board/boardList.notice">Notice</a></li>
	<li><a href="<%=request.getContextPath()%>/board/boardList.freeboard">Free Board</a></li>
	<!--  <a href="<%=request.getContextPath()%>/board/boardList.qna">QnA</a>-->
	<c:if test = "${empty memberDTO}">
		<ul class="nav navbar-nav navbar-right">
		<li><a href="<%=request.getContextPath()%>/member/memberJoin.member">
		<span class="glyphicon glyphicon-user"></span>Join</a></li>
		<li><a href="<%=request.getContextPath()%>/member/memberLogin.member">
		<span class="glyphicon glyphicon-log-in"></span>Login</a></li>
		</ul>
	</c:if>
	<c:if test = "${not empty memberDTO}">
	<ul class="nav navbar-nav navbar-right">
		<li><a href="<%=request.getContextPath()%>/member/memberMyPage.member">
		<span class="glyphicon glyphicon-user"></span>My Page</a></li>
		<li><a href="<%=request.getContextPath()%>/member/memberLogout.member">
		<span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
	</ul>
	</c:if>
	</ul>
	</div>
	</div>
	</nav>
</body>
</html>