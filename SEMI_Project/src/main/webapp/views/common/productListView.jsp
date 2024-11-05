<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	.list-area{
		width:760px;
		margin:auto;
	}
	
	.thumbnail{
		border : 1px solid white;
		width : 200px;
		display : inline-block;
		margin : 14px;
	}
	
	.thumbnail:hover{
		cursor : pointer;
		opacity : 0.7; 
	}


</style>

</head>
<body>
	
	<%@ include file="/views/common/menuBar.jsp" %>
	
	<div class="outer">
		<h2 align="center">상품 게시판</h2>
		<br>
		
		<c:if test="${loginUser != null && loginUser.userId == 'admin01'}">
		
			<div align="center">
				<button class="btn btn-info" onclick="location.href='${contextPath}/insert.sh'">상품 게시</button>
			</div>		
		
		</c:if>

			 
		<script>
			$(function(){
				
				$(".thumbnail").click(function(){
					
					var bno = $(this).children().first().val();
					
					location.href='${contextPath}/list.sh?bno='+bno; //경로 확인요망
				
				});
				
			});
		
		</script>			
	
		<div class="list-area">
		
			<c:forEach items="${list}" var="p">
			
			<div class="thumbnail" align="center">
				<input type="hidden" value="${p.boardNo }"> <!-- 글번호 숨겨넣기 -->
			
				<img src="${contextPath}/uploadfiles/${p.boardNo}.jpg" 
				width="200px" height="150px" alt="${p.proName}">
				<!-- 이미지파일 경로 설정(uploadfiles)필요 -->
				<p>
					NO.${p.boardNo} ${p.proName} <br>					
				</p>
			</div>
			
			</c:forEach>
			
		</div>
	
	</div>

</body>
</html>