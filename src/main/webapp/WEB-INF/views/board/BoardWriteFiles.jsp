<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

	<h2>BoardWriteFiles.jsp</h2>
	<form name="boardWriteFile" id="boardWriteFile" 
	action="${pageContext.request.contextPath}/boards/boardWriteFile.do" method="post" 
	enctype="multipart/form-data">
		작성자 : <input type="text" name="boardWriter"
			id="boardWriter" value="${sessionScope.loginId}" readonly maxlength="50">
		제목 : <input type="text" name="boardSubject" id="boardSubject"
			autofocus maxlength="50"> 
		비밀번호 : <input type="password" name="boardPassword" id="boardPassword"> 
		<br>
		내용 : <textarea name="boardContents" id="boardContents" rows="10" cols="100"></textarea>
		파일 : <input type="file" name="boardFile" row="10">
		<input type="submit" value="업로드">
	</form>
</body>
</html>