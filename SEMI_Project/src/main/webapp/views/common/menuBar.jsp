<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

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
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css" integrity="sha384-lZN37f5QGtY3VHgisS14W3ExzMWZxybE1SJSEsQp9S+oqd12jhcu+A56Ebc1zFSJ" crossorigin="anonymous">

<style>
body {
	background-color: black;
}

.outer {
	width: 1000px;
	margin: auto;
}

h3 {
	color: orange;
}

.img-area img {
	width: 100%;
	height: 100%;
}

a {
	color: orange;
	text-decoration: none;
	text-align: center;
}

a:hover {
	cursor: pointer;
	color: orange;
	text-decoration: none;
	background-color: rgb(48, 48, 48);
}

.menubar {
	width: 100%;
	position: relative;
	color: orange;
}

.backbtn-area{
	position: absolute;
	margin: auto;
	left: 0px;
}

.backbtn-area:hover{
	cursor: pointer;
}

.dropdown-area{
	position: absolute;
	margin: auto;
	right: 0px;
}

.logout-area{
	position: absolute;
	margin: auto;
	right: 100px;
}

.logout-area a{
	font-size: 12px;
}

i{
	color: orange;
}

#AD-area{
	margin-top: 80px;
	border: 1px solid white;
    border-radius: 5px;
    width: 100%;
    height: 200px;
}

.dropdown-item{
	color: orange;
}

</style>


</head>
<body>

	<div class="outer">
		<!-- 메뉴바 DIV -->
		<br>
		<div class="menubar">
			<div class="backbtn-area">
				<h1 onclick="backToMain();" id="home">HELL-C</h1>
			</div>
			<div class="dropdown-area">
				<div class="dropdown">
					<button type="button" class="btn btn-outline-warning dropdown-toggle"
						data-toggle="dropdown">MENU</button>
					<div class="dropdown-menu" style="background: black; border: 1px solid orange;">
						<a class="dropdown-item" onclick="shopPage();" id="home">SHOP</a>
						<a class="dropdown-item" onclick="myPage();" id="mypage">MYPAGE</a>
						<a class="dropdown-item" onclick="unionBoardPage();" id="board">BOARD</a>
						<a class="dropdown-item" href="/semi/LogoutUserInfo.us">LOGOUT</a>
					</div>
				</div>
			</div>
		</div>
		<div class="AD-area" id="AD-area">
			<!-- 광고리스트를 가져와 랜덤으로 출력할 예정 (새로고침이나 페이지 이동시 변경됨) -->
			<img src="/semi/resources/shopImgFile/ad-test.gif" width="100%" height = "100%">
		</div>
	</div>
	
	<!-- 메뉴바 영역 (로그인, 회원가입 스크립트 작성 영역) -->
	<script>
		var msg = "<%=alertMsg%>";

		if (msg != "null") {
			alert(msg);
		<%session.removeAttribute("alertMsg");%>
		}
		
		function backToMain(){
			location.href="/semi/unionBoardListView.un?currentPage=1";
		}
		
		function unionBoardPage(){
			
			location.href="/semi/unionBoardListView.un?currentPage=1";
		};
		
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