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
    border-radius: 20px;
    width: 100%;
    height: 200px;
}

.dropdown-item{
	color: orange;
}
/* 이미지 미리보기 창 스타일 */
.preview-box {
    position: absolute;
    display: none;
    border: 1px solid #ccc;
    padding: 5px;
    background: #fff;
    z-index: 10;
    box-shadow: 0px 0px 10px rgba(0,0,0,0.3);
}
.preview-box img {
    width: 150px;  /* 미리보기 이미지 크기 */
    height: auto;
}

#home1{
	color: rgb(211, 205, 205);
	font-size: 50px;
	font-family: 'Viking-Normal', serif;
	font-weight: 900;
	text-shadow: 0px 0px 4px gold
               , 0px -5px 4px chocolate
               , 0px -15px 14px orange
               , 0px -22px 19px red;
}

#adimg{
	border-radius: 20px;
}
</style>

</head>
<body>

	<div class="outer">
		<!-- 메뉴바 DIV -->
		<br>
		<div class="menubar">
			<div class="backbtn-area">
				<span onclick="backToMain();" id="home1">HELL-</span><span onclick="backToMain();" id="home1">C</span>
			</div>
			
			<script>
				$(function(){
					$.ajax({
						url : "/semi/questList.un",
						success : function(qList){
							
							$("#questList-area tr").remove();
							
							for(var q of qList){
								var tr = $("<tr align='center'>");
								tr.append($("<td>").text(q.questNo));
								tr.append($("<td>").text(q.questTitle));
								tr.append($("<td>").text(q.reward));
								
								$("#questList-area").append(tr);
							}
							
						},
						error : function(){
							console.log("요청 오류");
						}
					});
				})
			</script>
			
			<div class="dropdown-area" style="margin-right: 70px;">
					<button type="button" class="btn btn-outline-warning" 
									data-toggle="modal" data-target="#questModal">Quest</button>
			</div>

			<!-- 퀘스트 모달창 -->
			<div class="modal" id="questModal">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">

						<!-- 퀘스트 헤더 -->
						<div class="modal-header">
							<h4 class="modal-title">Quest List</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- 퀘스트 리스트 출력 구역 -->
						<div class="modal-body">
							<table class="table table-striped">
								<thead>
									<tr>
										<th width="120px">No.</th>
										<th width="400px">퀘스트명</th>
										<th width="120px">보상</th>
									</tr>
								</thead>
								<tbody id="questList-area">
									
								</tbody>
							</table>
						</div>

						<!-- 퀘스트 footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>

					</div>
				</div>
			</div>

			<div class="dropdown-area">
				<div class="dropdown">
					<button type="button" class="btn btn-outline-warning dropdown-toggle"
						data-toggle="dropdown"><i class="fas fa-bars"></i></button>
					<div class="dropdown-menu" style="background: black; border: 1px solid orange;">
						<c:choose>
							<c:when test="${empty loginUser}">
								<a class="dropdown-item" href="/semi/views/common/loginPage.jsp">LOGIN PAGE</a>
							</c:when>
							<c:otherwise>
								<a class="dropdown-item" onclick="shopPage();" id="home">SHOP</a>
								<a class="dropdown-item" onclick="myPage();" id="mypage">MYPAGE</a>
								<a class="dropdown-item" onclick="unionBoardPage();" id="board">BOARD</a>
								<a class="dropdown-item" href="/semi/LogoutUserInfo.us">LOGOUT</a>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
		<div class="AD-area" id="AD-area">
			<!-- 광고리스트를 가져와 랜덤으로 출력할 예정 (새로고침이나 페이지 이동시 변경됨) -->
			<img src="/semi/resources/shopImgFile/sam-sulek.gif" width="100%" height = "100%" id="adimg">
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
			location.href="/semi/views/common/mainPage.jsp";
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
			}).append($("<input>", {
				type : "hidden",
				name : "userId",
				value : "${loginUser.userId}"
			})).appendTo($("body")).submit();

		};
	</script>

</body>
</html>