<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>BoardMain jsp</h2>
	<c:forEach var="result" items="${boardList}">
		글번호 : ${result.boardNum} 
		작성자 : ${result.boardWriter} 
		<a href="boardContents?boardNum=${result.boardNum}&page=${pagedto.page}">글제목 : ${result.boardSubject}</a>
		<!-- 글내용 : ${result.boardContents} <br>
		글비밀번호 : ${result.boardPassword}  -->
		<br><br>
	</c:forEach>
	
	
	
	<a href="boardWrite">글쓰기</a>
	<a href="boardWriteFile">글쓰기(file)</a>
	<c:if test="${not empty sessionScope.loginId}">
		<a href="goLoginMain">로그인 메인가기</a>
	</c:if>
	<c:if test="${empty sessionScope.loginId}">
		<a href="LoginForm">로그인 하러가기</a>
	</c:if>
</body>
</html>