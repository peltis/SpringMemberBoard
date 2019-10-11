<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<%@ include file="../header.jsp"%>


<script>
	function deleteBtn(boardNum){
		$.ajax({
			type : "get",
			url : "${pageContext.request.contextPath}/boards/boardDeleteAjax.do",
			data : {"boardNumber" : boardNum },
			dataType : "text",
			success : function(result){
				if(result == "OK"){
					alert("삭제성공");
					location.href="boardMain";
				}
				else {
					alert("삭제실패");
				}
			},
			error : function(){
				alert("통신 실패");
			}
		});
	}
	
	function commentWriteBtnSec(){
		var commentContents = document.getElementById("commentContents").value;
		var commentWriter = document.getElementById("commentWriter").value;
		var commentBoardNumber = '${boardDetail.boardNum}';
		
		$.ajax({
			type : "post",
			url : "comment/commentWrite",
			data : {"commentContents" : commentContents,
				"commentWriter" : commentWriter,
				"commentBoardNumber" : commentBoardNumber },
			dataType : "json",
			success : function(result){
				console.log("댓글 등록 성공");
				var output = "<table board='1'>";
				for(var i in result){
					output += "<tr>";
					output += "<td>"+result[i].commentWriter+"</td>"
					output += "<td>"+result[i].commentContents+"</td>"
					output += "</tr>";
				}
				output += "</table>";
				$("#commentList").html(output);
				$("#commentContents").val("");
				$("#commentWriter").val("");
			},
			error : function(){
				alert("통신 실패");
			}
		});
	}
	
	$(document).ready(function(){
		$("#commentWriteBtn").click(function(){
			var commentContents = $("#commentContents").val();
			var commentWriter = $("#commentWriter").val();
			var commentBoardNumber = '${boardDetail.boardNum}';
			$.ajax({
				type : "post",
				url : "${pageContext.request.contextPath}/comment/commentWrite",
				data : {
					"commentContents" : commentContents,
					"commentWriter" : commentWriter,
					"commentBoardNumber" : commentBoardNumber
					},
				dataType : "json",
				success : function(result){
					console.log("댓글 등록 성공");
					var output = "<table border='1' style='margin: 0 auto;'>";
					for(var i in result){
						output += "<tr>";
						output += "<td>"+result[i].commentWriter+"</td>"
						output += "<td>"+result[i].commentContents+"</td>"
						output += "</tr>";
					}
					output += "</table>";
					$("#commentList").html(output);
					$("#commentContents").val("");
					$("#commentWriter").val("");
				},
				error : function(){
					alert("통신 실패");
				}
			});
		});
	})
</script>

	<h2>boardDatail jsp</h2>
	<div style="board:1px solid #d9d9d9">
		작성자 : ${boardDetail.boardWriter} <br>
		글제목 : ${boardDetail.boardSubject} <br>
		글비밀번호 : ${boardDetail.boardPassword}  <br> 
		글내용 : ${boardDetail.boardContents} <br> 
		작성일자 : ${boardDetail.boardDate}  <br> 
		조회수 : ${boardDetail.boardHit} <br> 
		파일 : ${boardDetail.boardFileName}<br> 
		<img src="${pageContext.request.contextPath}/resources/uploadFile/${boardDetail.boardFileName}" alt="boardFileName">
		<br> <br> 
	</div>
	
	<h2>댓글</h2>
	<div id="commentArea">
		작성자 : <input type="text" id="commentWriter" value=${sessionScope.loginId} /> <br>
		내용 : <textarea rows="5" cols="30" id="commentContents"></textarea><br>
		<button id="commentWriteBtn">완료</button>
	</div>
	<h2>댓글목록</h2>
	<div id="commentList">
		<table border="1" style="margin: 0 auto;">
			<c:forEach items="${commentList}" var="cList">
				<tr>
					<td>${cList.commentWriter}</td>
					<td>${cList.commentContents}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<br>
	<a href="${pageContext.request.contextPath}/boards/boardModifyView.do?boardNum=${boardDetail.boardNum}">수정</a>
	<a href="javascript:deleteBtn(${boardDetail.boardNum});">삭제</a>
	<a href="${pageContext.request.contextPath}/boards/boardPageMain.do?page=${page}">목록으로</a>
</body>
</html>