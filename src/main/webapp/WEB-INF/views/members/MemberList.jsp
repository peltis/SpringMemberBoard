<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

<h2>MemberList jsp</h2>
	<c:forEach var="result" items="${memberList}">
	아이디 : <a href="${pageContext.request.contextPath}/members/memberDetail.do?id=${result.id}"> 
	${result.id}</a> <br>
	</c:forEach>
</body>
</html>