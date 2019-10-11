<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

<h2>MemberDetail jsp</h2>
	멤버 상세조회 아이디 : ${memberDetail.id}
	<br> 이름 : ${memberDetail.name}
	<br> 비밀번호 : ${memberDetail.password}
	<br> 성별 : ${memberDetail.gender}
	<br> 나이 : ${memberDetail.age}
	<br> 이메일 : ${memberDetail.email}
	<br>


	<a href="memberModifyView.do?id=${memberDetail.id}">수정하기</a>

	
	<div id="memberListArea"></div>
</body>
</html>