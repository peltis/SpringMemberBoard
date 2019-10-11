<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

	<h2>LoginForm jsp</h2>
	<form name="memberLogin" action="${pageContext.request.contextPath}/members/memberLogin" method="post">
		<ul>
			<li><span>아이디 </span> <input type="text" name="id" id="id"
				autofocus placeholder="input your ID" maxlength="20"></li>
			<li><span>비밀번호</span> <input type="password" name="password" id="password"
				autofocus placeholder="input your PW" maxlength="20"></li>
			<li><input type="submit" value="로그인"></li>
			<li><input type="button" onclick="goJoinForm();" value="회원가입"></li>
		</ul>
	</form>
</body>
</html>
