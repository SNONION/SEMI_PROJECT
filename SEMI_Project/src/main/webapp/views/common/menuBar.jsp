<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String contextPath = request.getContextPath();
String alertMsg = (String) session.getAttribute("alertMsg");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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


<style>
body {
	background-color: black;
}

.outer {
	width: 1000px;
	margin: auto;
}

.img-area img {
	width: 100%;
}

a {
	color: orange;
	text-decoration: none;
	height: 100%;
	width: 100%;
	display: block;
	line-height: 45px;
	text-align: center;
}

a:hover {
	cursor: pointer;
	background-color: rgb(48, 48, 48);
}

.menubar {
	color: orange;
}

.menu-table {
	border: 1px solid black;
	width: 100%;
	height: 50px;
}

.login-area {
	height: 400px;
}
</style>


</head>
<body>

	<!-- 로그인 모달창 -->
	<div class="modal" id="loginModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<!-- 로그인 머리말 구역 -->
				<div class="modal-header">
					<h4 class="modal-title">로그인</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<form action="/semi/loginUserInfo.us" method="post"
					class="login-form">
					<!-- 로그인 구역 -->
					<div class="login-form" align="center">
						<table>
							<tr>
								<th><label for="userId"></label></th>
								<td><input type="text" name="userId" id="userId" placeholder="헬씨ID(아이디)"></td>
							</tr>
							<tr>
								<th><label for="userPwd"></label></th>
								<td><input type="text" name="userPwd" id="userPwd"
									placeholder="비밀번호"></td>
							</tr>
						</table>
					</div>

					<!-- 로그인 버튼 구역 -->
					<div id="login-footer" class="modal-footer">
						<button type="submit" class="btn btn-danger">login</button>
					</div>
				</form>
			</div>
		</div>
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
	</script>

	<div class="outer">
		<!-- 메뉴바 DIV -->
		<div class="menubar">
			<table class="menu-table">
				<tr>
					<th><a href="/semi" id="home">HOME</a></th>
					<th><a onclick="shopPage();" id="home">SHOP</a></th>
					<th><a onclick="myPage();" id="mypage">MYPAGE</a></th>
					<th><a href="" id="board">BOARD</a></th>

					<c:choose>
						<c:when test="${loginUser == null}">
							<th><a data-toggle="modal" data-target="#loginModal">LOGIN</a></th>
							<th><a data-toggle="modal" data-target="#signInModal">SIGNIN</a></th>
						</c:when>
						<c:otherwise>
							<th><a href="/semi/LogoutUserInfo.us">LOGOUT</a></th>
						</c:otherwise>
					</c:choose>
				</tr>
			</table>
		</div>

		<!-- 배경 이미지 영역 -->
		<div class="img-area">
			<img src="/semi/resources/backgroundImg/Main_Background_Img.png"
				alt="">
		</div>
	</div>

	<!-- 메뉴바 영역 (로그인, 회원가입 스크립트 작성 영역) -->
	<script>
		var msg = "<%=alertMsg%>";

		if (msg != "null") {
			alert(msg);
	<%session.removeAttribute("alertMsg");%>
		}
		
		function shopPage(){
			
			$("<form>", {
				method : "post",
				action : "/semi/list.sh?currentPage=1"
			}).appendTo($("body")).submit();
		};

		function myPage() {

			$("<form>", {
				method : "post",
				action : "/semi/mypage.us"
			}).appendTo($("body")).submit();

		};
	</script>

</body>
</html>