<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

<h2>boardPageMain jsp</h2>

<c:forEach var="result" items="${boardList}">
	글번호 : ${result.boardNum} 
	작성자 : ${result.boardWriter} 
	<a href="${pageContext.request.contextPath}/boards/boardContents.do?boardNum=${result.boardNum}&page=${pagedto.page}">글제목 :
		${result.boardSubject}</a><!-- &page=${paging.page} -->
	<br>
	<br>
</c:forEach>
	
	<!-- 페이징 처리 -->
	<c:if test="${pagedto.page<=1}">
		[이전]&nbsp;
	</c:if>
	<c:if test="${pagedto.page>1}">
		<a href="${pageContext.request.contextPath}/boards/boardPageMain.do?page=${pagedto.page-1}">[이전]</a>&nbsp;
	</c:if>
	<c:forEach begin="${pagedto.startPage}" end="${pagedto.endPage}" var="i" step="1">
		<c:choose>
			<c:when test="${i eq pagedto.page}">
				${i}
			</c:when>
			<c:otherwise>
				<a href="${pageContext.request.contextPath}/boards/boardPageMain.do?page=${i}">${i}</a>
			</c:otherwise>
		</c:choose>
	</c:forEach>
	
	<c:if test="${pagedto.page>=pagedto.maxPage}">
		[다음]
	</c:if>
	<c:if test="${pagedto.page<pagedto.maxPage}">
		<a href="${pageContext.request.contextPath}/boards/boardPageMain.do?page=${pagedto.page+1}">[다음]</a>
	</c:if>
	
	<br>
	<a href="${pageContext.request.contextPath}/boards/boardWriteFile.do">글쓰기</a>
</body>
</html>