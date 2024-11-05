<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

	<style>
		
		.list-area td{
			color:white;
			text-align: center;
		}
		
		.list-area th{
			color:white;
			text-align: center;
		}
	
	
	</style>


</head>
<body>
	
	<%@ include file="/views/common/menuBar.jsp" %>
	
	
	<div class="outer">
	 <br>
	 <h2 align="center">상품 게시판</h2>
	<br> <br>
	
	<%--글작성 버튼은 로그인한 회원일 경우 보일 수 있도록 조건처리 --%>
	
	<c:if test="${not empty loginUser}">
		<div align="center">
			<a href="${contextPath }/detailList.sh" class="btn btn-info">상품 게시</a>		
		</div>	
	</c:if>
	
	<br>
	
	<table class='list-area' align='center' border='1'>
		<thead>
			<tr>
				<th width='100'>상품번호</th>			
				<th width='150'>상품명</th>
				<th width='200'>상품설명</th>
				<th width='100'>가격</th>		
				
			</tr>
		
<<<<<<< HEAD
		<c:if test="${loginUser != null && loginUser.userId == 'admin01'}">
=======
		</thead>
>>>>>>> refs/remotes/origin/dsfgdfgdfg
		
		<tbody>
				
		<c:choose>
			<c:when test="${empty pList }"> <!-- list가 비어있다면 -->
				<tr>
					<td colspan="6">등록된 상품이 없습니다</td>
				</tr>				
			
			</c:when>
			<c:otherwise>			
				
				<c:forEach var="p" items="${pList}">
					<tr>
						<td>${p.proNo }</td>
						<td>${p.proName }</td>
						<td>${p.proMenual }</td>
						<td>${p.price }</td>										
					</tr>
				
				</c:forEach>
			</c:otherwise>
		</c:choose>				
		</tbody>		
	</table>
	
		<script>
			$(function(){
				$(".list-area>tbody>tr").click(function(){
					
					var bno = $(this).children().first().text();
					
					location.href="/detailList.sh?bno="+bno;
					
				});
			});
		
		</script>	
	
		<br> <br>
		
		<div align="center" class="paging-area">
	
			
			<c:if test="${pi.currentPage != 1 }">
				<button onclick="location.href='list.sh?currentPage=${pi.currentPage-1}'">이전</button>
			</c:if>		
			
			<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">
				<c:choose>
					<c:when test="${i !=pi.currentPage }">
						<button onclick="location.href='list.sh?currentPage=${i}'">${i}</button>					
					</c:when>
					<c:otherwise>
					<!-- 현재 페이지 버튼은 비활성화 -->
						<button disabled >${i}</button>
					</c:otherwise>				
				</c:choose>
							
			</c:forEach>
			
			<c:if test="${pi.currentPage != pi.maxPage }">
				<button onclick="location.href='list.sh?currentPage=${pi.currentPage+1}'">다음</button>
			</c:if>
		
		</div>
		
		</div>
		
		<c:if test="${not empty loginUser}">
	    <button onclick="location.href='purchase.sh?boardNo=${p.boardNo}'">상품 구매하기</button>
		</c:if>
			
		



</body>
</html>