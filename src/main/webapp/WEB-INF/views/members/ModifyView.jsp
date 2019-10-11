<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

	<h2>modifyView.jsp</h2>
	<form name="memberModify" 
		action="${pageContext.request.contextPath}/members/memberModify.do" method="POST">
		아이디 : <input type="text" name="id" readonly value="${sessionScope.loginId}"> <br>
		이름 :  <input type="text" name="name" value="${modifyView.name}"> <br>
		비밀번호 :  <input type="password" readonly name="password" value="${modifyView.password}"> <br>
		성별 : <input type="text" name="gender" value="${modifyView.gender}"><br>
		나이 : <input type="text" name="age" value="${modifyView.age}"><br>
		이메일 : <input type="text" name="email" value="${modifyView.email}"><br>
		<input type="submit" value="수정완료">
	</form>
</body>
</html>