<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>

	<h2>BoardModifyView jsp</h2>
	<form name="boardModify" id="boardModify" 
		action="${pageContext.request.contextPath}/boards/boardModify.do" method="post">
		<input type="hidden" name="boardNum" id="boardNum" value="${boardDetail.boardNum}">
		작성자 : <input type="text" name="boardWriter" id="boardWriter" value="${sessionScope.loginId}" readonly>
		제목 : <input type="text" name="boardSubject" id="boardSubject" value="${boardDetail.boardSubject}" > 
		비밀번호 : <input type="password" name="boardPassword" id="boardPassword" value="${boardDetail.boardPassword}"> 
		<br>내용 : <textarea name="boardContents" id="boardContents" rows="10" cols="100">${boardDetail.boardContents}</textarea>
		<input type="submit" value="수정완료">
	</form>
</body>
</html>