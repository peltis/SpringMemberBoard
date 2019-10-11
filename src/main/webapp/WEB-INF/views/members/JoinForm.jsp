<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp"%>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script>
	function checkValue() {	
		var re = /^[a-zA-Z0-9]{4,12}$/;
		 
		if (document.memberJoin.password.value != document.memberJoin.passwordConfirm.value) {
			alert("비밀번호를 동일하게 입력하세요.");	
			return false;
		}
		
		if( !re.test(document.memberJoin.password.value) ) {
			alert("패스워드는 4~12자의 영문 대소문자와 숫자로만 입력하세요.");
			return false;
		}
	}
	
	function idOverlap(){
		var idCheck = document.getElementById("id").value;
		var confirmId = document.getElementById("confirmId");
		
		/* ajax : Asynchronous Javascript and XML(비동기(앞이 아니라 백그라운드에서 처리되는 것) 처리를 위한 자바스크립트)
		ajax은 JSON 형식으로 데이터를 전송(JSON(Javascript Object Notation) : 자바스크립트 객체 표기법)
		*/
		
		$.ajax({
			type : "post" , //GET, POST
			url : "idOverlap", //컨트롤러 주소값
			data : {"id" : idCheck }, //주소로 가지고 갈 데이터
			dataType : "text" ,
			success : function(data) { //통신이 성공했을때 처리구문, 매개변수는 리턴되어서 오는 값이 저장됌
				if(data=="OK"){
					confirmId.style.color = "#ccc";
					confirmId.innerHTML = "사용 가능한 아이디입니다.";
				} else {
					confirmId.style.color = "#ff0000";
					confirmId.innerHTML = "사용 중인 아이디입니다.";
				}
			},
			error : function() {
				alert("idOverlap 함수 통신 실패");
			}
		});
	}
</script>

</head>

<body>
	<h2>JoinForm.jsp</h2>
	<div id="join_form" class="container">
		<form name="memberJoin" id="memberJoin" action="memberJoin" class="form_base"
			method="POST" onsubmit="return checkValue();">
			<ul>
				<li><span>아이디</span> <input type="text" name="id" id="id"
					onkeyup="idOverlap();" placeholder="아이디를 입력하세요" autofocus
					maxlength="50"> <br>
				<li id="confirmId"></li>
				<li><span>비밀번호</span> <input type="password" name="password"
					id="password" placeholder="비밀번호를 입력하세요" autofocus maxlength="50">
				</li>
				<li><span>비밀번호</span> <input type="password"
					name="passwordConfirm" id="passwordConfirm"
					placeholder="비밀번호 확인" autofocus maxlength="50"></li>
				<li><span>이름</span> <input type="text" name="name" id="name"
					placeholder="이름를 입력하세요" autofocus maxlength="20"></li>
				<li><span>나이</span> <input type="text" name="age" id="age"
					placeholder="i나이를 입력하세요" autofocus maxlength="20"></li>
				<li><span>성별</span> <span class="gender_index"
					style="width: 200px;"> <input type="radio" name="gender"
						value="남자">남자 <input type="radio" name="gender" value="여자">여자
				</span></li>
				<li><span>이메일</span> <input type="email" name="email"
					id="email" placeholder="이메일을 입력하세요" autofocus maxlength="50">
				</li>
				<li><input type="submit" value="회원가입완료"></li>
			</ul>
		</form>
	</div>
</body>
</html>