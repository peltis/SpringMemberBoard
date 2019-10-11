<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>

<script>
	$(document).ready(function(){
		
	});
	function memberList(){
		$.ajax({
			type : "get" , //GET, POST
			url : "${pageContext.request.contextPath}/members/memberListAjax.do", //컨트롤러 주소값
			dataType : "json" ,
			success : function(data) { //통신이 성공했을때 처리구문, 매개변수는 리턴되어서 오는 값이 저장됌
				console.log(d ata);
				var output ="";
				for(var i in data){
					output += "아이디 : "+"<a href='memberView?id="+
	                    data[i].id+"'>"+data[i].id+"</a><br>";
				}
				$("#memberListArea").html(output);	
			},
			error : function() {
				alert("ajax 함수 통신 실패");
			}
		});
	}
</script>
	<h2>LoginMain.jsp</h2>
	<br><br>
	${sessionScope.loginId}님 환영합니다. <br>
	로그인회원정보 <br>
	아이디 : ${loginMember.id}<br>
	비밀번호 : ${loginMember.password}<br>
	이름 : ${loginMember.name}<br>
	성별 : ${loginMember.gender}<br>
	나이 : ${loginMember.age}<br>
	이메일 : ${loginMember.email}<br>
	
	<c:if test="${sessionScope.loginId eq 'admin'}">
		<button onclick="memberList();">회원목록보기(ajax)</button>
	</c:if>
	
	<div id="memberListArea"></div>
</body>
</html>