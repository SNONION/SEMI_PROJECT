<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>




	<div class="outer">


		<br>



		<h2 align="center">게시판</h2>

		<br> <br> <br>

		<!-- 글 작성 버튼은 로그인했을때 회원일 경우  보일수 있도록 처리 비회원은 게시판 이용불가 -->



		<div align="center">


		</div>



		<table class='list-area' align='center' border='1'>

			<thead>
				<tr>

					<th width='60'>게시판 번호</th>
					<th width='70'>카테고리</th>
					<th width='200'>게시물 제목</th>
					<th width='100'>게시판 작성자</th>
					<th width='50'>조회수</th>
					<th width='70'>작성일</th>
					<th width='100'>추천자</th>

				</tr>
			</thead>


			<tbody>

				<tr>

					<td>10</td>
					<td>공통</td>
					<td>운동입니다</td>
					<td>김다온</td>
					<td>40</td>
					<td>2024-11-01</td>
					<td>관리자</td>


				</tr>

				<tr>

					<td>5</td>
					<td>운동</td>
					<td>헬스입니다</td>
					<td>이가영</td>
					<td>30</td>
					<td>2024-11-01</td>
					<td>사이키</td>

				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>10</td>
					<td>운동</td>
					<td>수영</td>
					<td>박다민</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>관리자</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠입니다</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>헬스장입니다</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-30</td>
					<td>버즈</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>스포츠</td>
					<td>박민수</td>
					<td>40</td>
					<td>2024-10-31</td>
					<td>세종대왕</td>


				</tr>
		</table>

	</div>
	
	
	  <script>
	  
	  
	    $ (function(){
	    	
	    	$ (".list-area>tbody>tr").click(function(){
	    		
	    		
	    		var bno = $(this).children().first().text();
	    		
	    		
	    		location.href="/UnionBoard.do?bno="+bno;
	    		
	    	});
	    	
	    });
	    	
	  </script>
	  
	  
	   <div align="center" class="paging-area">
			
			 
			 
			    <c:if test="${pi.currnentPage != 1}">
			 	<button onclick="location.href='UnionBoard.do?currentPage=${pi.currentPage-1}'">이전</button>
			
						</c:if>
						<button disabled>${i}</button>
						
				<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">	
			
			 	<button onclick="location.href='UnionBoard.do?currentPage=${pi.currentPage+1}'">다음</button>
			 	</c:forEach>
			 	
			 	<c:choose>
					<c:when test="${i != pi.currentPage }">
						<button onclick="location.href='list.bo?currentPage=${i}'">${i}</button>
					</c:when>
					<c:otherwise>
					
					</c:otherwise>
				
				     <button disabled>${i}</button>
					
					</c:choose>
					
					
				<c:if test="${pi.currentPage != pi.maxPage }">
				
			 	</c:if>
			 
		</div>
		
		<br><br>
	 </div>
	
</body>
</html>