<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Spring Project</title>
<link href="https://fonts.googleapis.com/css?family=Nanum+Gothic:400,700,800&amp;subset=korean" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

<script>
	function goJoinForm() {
		location.href = "JoinForm";
	}
	console.log('${pageContext.request.contextPath}');
</script>

</head>
<body>
	<c:if test="${not empty sessionScope.loginId}">
		<div style="position:absolute; top:10px; right:10px">
			${sessionScope.loginId}님 로그인 중
		</div>
	</c:if>
	<ul id="gnb">
		<li><a href="${pageContext.request.contextPath}/members/JoinForm.do">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/members/LoginForm.do">로그인</a></li>
		<li><a href="${pageContext.request.contextPath}/index"><img src="${pageContext.request.contextPath}/resources/img/top_logo.png"></a></li>
		<li><a href="${pageContext.request.contextPath}/boards/boardPageMain.do?page=1">게시판</a></li>
		<li><a href="${pageContext.request.contextPath}/members/MemberList.do">회원정보</a></li>
	</ul>
	