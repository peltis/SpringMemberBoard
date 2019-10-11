<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>BoardWrite jsp</h2>
	<form name="boardWrite" id="boardWrite" action="boardWrite" method="post">
		작성자 : <input type="text" name="boardWriter"
			id="boardWriter" value="${sessionScope.loginId}" readonly maxlength="50">
		제목 : <input type="text" name="boardSubject" id="boardSubject"
			autofocus maxlength="50"> 
		비밀번호 : <input type="password" name="boardPassword" id="boardPassword"> 
		<br>내용 : <textarea name="boardContents" id="boardContents" rows="10" cols="100"></textarea>
		<input type="submit" value="업로드">
	</form>
</body>
</html>