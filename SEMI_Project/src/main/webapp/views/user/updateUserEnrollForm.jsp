<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
h3 {
	color: orange;
}

label {
	color: white;
}
</style>

</head>
<body>

	<%@ include file="/views/common/menuBar.jsp"%>

	<div class="outer">
		<br> <br>
		<h3 align="center">USERINFO UPDATE</h3>
		<br> <br>
		<div class="update-area" align="center">
			<form action="/semi/updateUserInfoPage.us" class="update-form" method="post">
				<input type="hidden" name="userId" value="${user.userId}">
				<table class="update">
					<tr>
						<th><label for="userId">ID : </label></th>
						<td><input id="userId" name="outputId" value="${user.userId}" disabled></td>
					</tr>
					<tr>
						<th><label for="userName">NAME : </label></th>
						<td><input type="text" id="userName" name="userName" value="${user.userName}"></td>
					</tr>
					<tr>
						<th><label for="nickname">NICKNAME : </label></th>
						<td><input type="text" id="nickname" name="nickname" value="${user.nickname}"></td>
						<td><input type="button" onclick="checkNickname();" class="btn btn-outline-warning btn-sm" value="중복확인"></td>
					</tr>
					<tr>
						<th></th>
						<td>
							<input type=radio id="male" name="gender" value="MALE">
							<label for="male">MALE</label>
							<input type="radio" id="female" name="gender" value="FEMALE">
							<label for="female">FEMALE</label>
							<input type="radio" id="none" name="gender" value="NONE">
							<label for="none">NONE</label>
						</td>
					</tr>
					<tr>
						<th><label for="phone">PHONE : </label></th>
						<td><input type="text" id="phone" name="phone" value="${user.phone}"></td>
					</tr>
					<tr>
						<th><label for="email">EMAIL : </label></th>
						<td><input type="text" id="email" name="email" value="${user.email}"></td>
					</tr>
					<tr>
						<th><label for="address">ADDRESS : </label></th>
						<td><input type="text" id="address" name="address" value="${user.address}"></td>
					</tr>
				</table>
				
				<br><br>
				
				<div class="btn-area">
					<button id="userInfoUpdate" type="submit" class="btn btn-outline-warning">회원 정보 수정</button>
				</div>
				
			</form>
		</div>
	</div>
	
	<script>
		function checkNickname(){
			
			$.ajax({
				url : "/semi/checkNickname.us",
				data : {
					nickname : $("#nickname").val()
				},
				success : function(msg){
					if(msg == "NNNNN"){
						alert("이미 사용중인 넥네임 입니다.");
						$("#userInfoUpdate").attr("disabled",true);
					}
					else{
						alert("사용가능한 넥네임 입니다.");
						$("#userInfoUpdate").attr("disabled",false);
					}
				},
				error : function(){
					alert("요청 오류");
				}
			});
		};
	
		$(function(){
			
			var gender = "${user.gender}";
			
			if($("#male").val() == gender){
				$("#male").attr("checked",true);
			}
			else if($("#female").val() == gender){
				$("#female").attr("checked",true);
			}
			else{
				$("#none").attr("checked",true);
			}
			
		});
	</script>

<br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>