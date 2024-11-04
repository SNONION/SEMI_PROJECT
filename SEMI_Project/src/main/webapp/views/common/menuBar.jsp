<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

.outer{
	width: 100%;
}

.img-area img{
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

				<!-- 로그인 구역 -->
				<div class="login-area" align="center">
					<form action="" method="post" class="login-form">
						<table>
							<tr>
								<th><label for="userId">ID</label></th>
								<td><input type="text" name="userId" id="userId" value="ID"></td>
							</tr>
							<tr>
								<th><label for="userPwd">PWD</label></th>
								<td><input type="text" name="userPwd" id="userPwd"
									value="PW"></td>
							</tr>
						</table>

						<!-- 로그인 버튼 구역 -->
						<div align="center">
							<button onclick="">login</button>
						</div>
					</form>
				</div>
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
				<div class="modal-body"></div>

				<!-- 버튼 구역 -->
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">가입</button>
					<button type="button" class="btn btn-light" data-dismiss="modal"
						style="border: 1px solid black;">취소</button>
				</div>

			</div>
		</div>
	</div>
	
	<div class="outer">
		<!-- 메뉴바 DIV -->
		<div class="menubar">
			<table class="menu-table">
				<tr>
					<th><a href="" id="home">HOME</a></th>
					<th><a onclick="myPage();" id="mypage">MYPAGE</a></th>
				<th><a href="/semi/UnionBoard.do?currentPage=1">BOARD</a></th>
					<th><a data-toggle="modal" data-target="#loginModal">LOGIN</a></th>
					<th><a data-toggle="modal" data-target="#signInModal">SIGNIN</a></th>
				</tr>
			</table>
		</div>

		<!-- 배경 이미지 영역 -->
		<div class="img-area">
			<img src="/semi/resources/backgroundImg/Main_Background_Img.png" alt="">
		</div>
	</div>
	
	<!-- 메뉴바 영역 (로그인, 회원가입 스크립트 작성 영역 -->
	<script>
		function myPage(){
			
			$.ajax({
				url : "myPage.us",
				data : {
					userId : "${loginUser.userId}"
				},
				success : function(result){
					
				},
				error : function(){
					console.log("에러")
				}
			});
		};
	</script>
	
	
	
	
	
	
	
	
	
	

</body>
</html>