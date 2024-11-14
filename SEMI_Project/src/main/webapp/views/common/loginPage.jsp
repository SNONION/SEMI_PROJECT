<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%
    String contextPath = request.getContextPath();
    String alertMsg = (String) session.getAttribute("alertMsg");
%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>

<meta name ="google-signin-client_id" content="535387066748-f46vteepvqbbihan729cuit3umrdp7eo.apps.googleusercontent.com">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.7.0/css/all.css"
	integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ"
	crossorigin="anonymous">
	
<!-- 구글 api 사용을 위한 스크립트 -->
<script src="https://apis.google.com/js/platform.js?onload=init" async defer></script>
	
<style>
body {
	background-color: black;
}

.outer {
	width: 1300px;
	height: 750px;
	margin: auto;
	position: relative;
}

.logo-area {
	color: wheat;
	position: absolute;
	margin: auto;
	right: 0px;
	left: 50px;
	top: 20px;
	bottom: 0px;
}

.img-area {
	width: 100%;
	height: 100%;
	position: absolute;
}

.util-area {
	background-color: black;
	box-shadow: 0px 0px 15px 5px white;
	border-radius: 20px;
	width: 20%;
	height: 40%;
	position: absolute;
	margin: auto;
	right: 0px;
	left: 600px;
	top: 0px;
	bottom: 0px;
}

.login-form {
	width: 60%;
	height: 60%;
	position: absolute;
	margin: auto;
	right: 0px;
	left: 0px;
	top: 10px;
	bottom: 0px;
}

.btn-group {
	width: 100%;
}

#userId:hover {
	border: 3px solid orange;
	cursor: pointer;
}

#userPwd:hover {
	border: 3px solid orange;
	cursor: pointer;
}

.googlelogin-form{
	border: 1px solid black;
	width: 50px;
	height: 50px;
	border-radius: 10px;
	background-color: white;
}
</style>

</head>
<body>

	<div class="outer">
		<div class="logo-area">
			<h1></h1>
		</div>
		<div class="img-area">
			<div class="util-area" align="center">
				<form action="/semi/loginUserInfo.us" method="post"
					class="login-form">
					<div class="input-group mb-3">
						<input type="text" name="userId" id="userId" class="form-control"
							placeholder="HELL-C ID">
					</div>
					<div class="input-group mb-3">
						<input type="text" name="userPwd" id="userPwd"
							class="form-control" placeholder="Password">
					</div>
					<div class="btn-group">
						<button type="submit" class="btn btn-outline-warning btn-sm"
							style="color: orange; border: 1px solid orange;">LOGIN</button>
						<button type="button" class="btn btn-outline-warning btn-sm"
							style="color: orange; border: 1px solid orange;"
							data-toggle="modal" data-target="#signInModal">SGIN IN</button>
					</div>
					<br><br>
					<div class="googlelogin-form" id="GgCustomLogin">
						<a href="javascript:void(0)"> 
							<span id="icon-area">
								<img src="https://www.ghec.ca/images/google-g-logo.png" width="50px" height="50px">
							</span>
						</a>
					</div>
				</form>
			</div>
		</div>
		<img src="/semi/resources/backgroundImg/main_img.png" alt=""
			width="100%" height="100%">
	</div>

	<!-- 회원가입 모달창 -->
	<div class="modal" id="signInModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- 머리말 구역 -->
				<div class="modal-header">
					<h4 class="modal-title">회원가입</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<!-- 회원가입 form생성 영역 -->
				<form action="/semi/enrollUser.us" method="post">
					<div class="modal-body">
						<fieldset>
							<table>
								<tr>
									<th width="150px"><label for="userId">아이디</label></th>
									<td><input type="text" name="inputId" id="inputId"
										required></td>
									<td><button type="button" id="idCheck"
											onclick="idCheckBtn();"
											class="btn btn-outline-warning btn-sm">중복확인</button></td>
								</tr>
								<tr>
									<th></th>
									<td>
										<div id="checkIdBox" style="font-size: 8px;">6~15자로 특수기호
											없이 입력</div>
									</td>
								</tr>
								<tr>
									<th><label for="userPwd">비밀번호</label></th>
									<td><input type="text" name="inputPwd" id="inputPwd"
										required></td>
								</tr>
								<tr>
									<th><label for="userPwd">비밀번호 확인</label></th>
									<td><input type="text" name="doubleCheckPwd"
										id="doubleCheckPwd" required></td>
								</tr>
								<tr>
									<th></th>
									<td><div id="checkPwdnameBox" style="font-size: 8px;">4~15자까지
											가능(첫글자 특수기호 가능)</div>
									<td>
								</tr>
								<tr>
									<th><label for="userName">이름</label></th>
									<td><input type="text" name="userName" id="userName"
										required></td>
								</tr>
								<tr>
									<th><label for="nickname">닉네임</label></th>
									<td><input type="text" name="nickname" id="nickname"
										required></td>
									<td><button type="button" id="nicknameCheck"
											onclick="nicknameCheckBtn();"
											class="btn btn-outline-warning btn-sm">중복확인</button></td>
								</tr>
								<tr>
									<th></th>
									<td><div id="checkNicknameBox" style="font-size: 8px;">1~15자까지
											가능(특수기호 불가)</div>
									<td>
								</tr>
								<tr>
									<th>성별</th>
									<td><input type="radio" name="gender" id="male"
										value="MALE"> <label for="male">MALE</label> <input
										type="radio" name="gender" id="female" value="FEMALE">
										<label for="female">FEMAL</label> <input type="radio"
										name="gender" id="other" value="OTHER" checked> <label
										for="other">OTHER</label></td>
								</tr>
								<tr>
									<th><label for="phone">핸드폰 번호</label></th>
									<td><select name="phoneFront" id="phoneFront">
											<option value="010" selected>010</option>
											<option value="010">011</option>
											<option value="010">001</option>
									</select> - <input type="text" pattern="[0-9]*" name="phoneMiddle"
										id="phoneMiddle" size="3" maxlength="4" required> - <input
										type="text" pattern="[0-9]*" name="phoneLast" id="phoneLast"
										size="3" maxlength="4" required></td>
								</tr>
								<tr>
									<th><label for="email">이메일</label></th>
									<td><input type="text" name="email" id="email"></td>
								</tr>
								<tr>
									<th><label for="address">주소</label></th>
									<td><input type="text" name="address" id="address"></td>
								</tr>
							</table>
							<br>
							<div id="alertBox" style="font-size: 8px; color: red;"
								align="center">* 이메일과 주소는 필수 입력사항이 아닙니다.</div>
						</fieldset>
					</div>

					<!-- 버튼 구역 -->
					<div class="modal-footer">
						<button id="enroll" type="submit" onclick="return signIn();"
							class="btn btn-danger" disabled>가입</button>
						<button type="button" class="btn btn-light" data-dismiss="modal"
							style="border: 1px solid black;">취소</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script>
		//처음 실행하는 함수
		function init() {
		
		gapi.load('auth2', function() {
				gapi.auth2.init();
				options = new gapi.auth2.SigninOptionsBuilder();
				options.setPrompt('select_account');
				// 추가는 Oauth 승인 권한 추가 후 띄어쓰기 기준으로 추가
				options.setScope('email profile openid https://www.googleapis.com/auth/user.birthday.read');
				// 인스턴스의 함수 호출 - element에 로그인 기능 추가
				// GgCustomLogin은 li태그안에 있는 ID, 위에 설정한 options와 아래 성공,실패시 실행하는 함수들
				gapi.auth2.getAuthInstance().attachClickHandler('GgCustomLogin', options, onSignIn, onSignInFailure);
			})
		}

		function onSignIn(googleUser) {
			var access_token = googleUser.getAuthResponse().access_token
			$.ajax({
				// people api를 이용하여 프로필 및 생년월일에 대한 선택동의후 가져온다.
				url : 'https://people.googleapis.com/v1/people/me',
				data : {
					// key에 자신의 API 키를 넣습니다.
					personFields : 'birthdays',
					key : 'AIzaSyAbL_m9noTSe7nscGyZYXUJ5e_r4z-CDBM',
					'access_token' : access_token
				},
				method : 'GET'
			}).done(function(e) {
				//프로필을 가져온다.
				var profile = googleUser.getBasicProfile();
				console.log(profile)
			}).fail(function(e) {
				console.log(e);
			})
		}
		function onSignInFailure(t) {
			console.log(t);
		}

		 function signIn(){
		     var inputId = $("#inputId").val();
		     var userPwd = $("#inputPwd").val();
		     var doubleCheckPwd = $("#doubleCheckPwd").val();
		     var userName = $("#userName").val();
		     var nickname = $("#nickname").val();
		     var gender = $("input[name=gender]:checked").val();
		     var phoneFront = $("#phoneFront").val();
		     var phoneMiddle = $("#phoneMiddle").val();
		     var phoneLast = $("#phoneLast").val();
		     var phone = phoneFront + "-" + phoneMiddle + "-" + phoneLast;
		     var email = $("#email").val();
		     var address = $("#address").val();
		     
		     var pwRegExp = /^[0-9a-zA-Z!@#$%^&*]{4,15}$/;
		     
		     if(pwRegExp.test(userPwd)){
		         if(userPwd == doubleCheckPwd){
		             return true;
		         }
		         else{
		             $("#checkPwdnameBox").text("비밀번호가 일치하지 않습니다.");
		             return false;
		         }
		     }
		     else{
		         $("#checkPwdnameBox").text("형식 -> 8~15자까지 가능(첫글자 특수기호 가능)");
		         return false;
		     }
		
		 };
		
		 function idCheckBtn(){
		     var inputId = $("#inputId").val();
		     var idRegExp = /^[0-9a-zA-Z]{6,15}$/;
		     
		     if(idRegExp.test(inputId)){
		         if(inputId != ""){
		             $.ajax({
		                 url : "/semi/checkIdDup.us",
		                 data : {
		                     userId : $("#inputId").val(),
		                 },	
		                 success : function(imsg){
		                     if(imsg == 'NNNNN'){
		                         $("#checkIdBox").text("이미 사용중인 아이디입니다.");
		                         $("#enroll").attr("disabled", true);
		                     }
		                     else{
		                         $("#checkIdBox").text("사용 가능한 아이디입니다.");
		                     }
		                 },
		                 error : function(){
		                     alert("요청 실패");
		                 }
		             });
		         }
		         else{
		             $("#checkIdBox").text("아이디를 입력해주세요.");
		         }
		     }
		     else{
		         $("#checkIdBox").text("형식 -> 8~15자로 특수기호 없이 입력");
		     }
		 };
		 
		 function nicknameCheckBtn(){
		     var nickname = $("#nickname").val();
		     var nickRegExp = /^[0-9a-zA-Z가-힣]{1,15}$/;
		     
		     if(nickRegExp.test(nickname)){
		         if(nickname != ""){
		             $.ajax({
		                 url : "/semi/checkIdDup.us",
		                 data : {
		                     nickname : $("#nickname").val()
		                 },	
		                 success : function(nmsg){
		                     if(nmsg == 'NNNNN'){
		                         $("#checkNicknameBox").text("이미 사용중인 닉네임입니다.");
		                         $("#enroll").attr("disabled", true);
		                     }
		                     else{
		                         if($("#checkIdBox").text() == "사용 가능한 아이디입니다."){
		                             $("#checkNicknameBox").text("사용 가능한 닉네임입니다.");
		                             $("#enroll").attr("disabled", false);
		                         }
		                         else{
		                             $("#checkNicknameBox").text("아이디 먼저 중복확인 해주세요.");
		                         }
		                     }
		                 },
		                 error : function(){
		                     alert("요청 실패");
		                 }
		             });
		         }
		         else{
		             $("#checkNicknameBox").text("닉네임을 입력해주세요.");
		         }
		     }
		     else{
		         $("#checkNicknameBox").text("형식 -> 1~15자까지 가능(특수기호 불가)");
		     }
		 };
		
		 var msg = "<%=alertMsg%>";
		
		 if (msg != "null") {
		     alert(msg);
		     <%session.removeAttribute("alertMsg");%>
		 }
		</script>
</body>
</html>