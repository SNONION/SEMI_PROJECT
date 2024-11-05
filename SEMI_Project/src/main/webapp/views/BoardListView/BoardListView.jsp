<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

   <style>
   
     
     *{
     
     color:orange;
     
     }
     
    
   
   
   
   </style>





</head>
<body>


  
  
  <%@ include file="/views/common/menuBar.jsp" %>



	<div class="outer">


		<br>



		<h2 align="center">게시판</h2>

		<br> <br> <br>

		<!-- 글 작성 버튼은 로그인했을때 회원일 경우  보일수 있도록 처리 비회원은 게시판 이용불가 -->



		<div align="center"></div>



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

					<td>20</td>
					<td>운동</td>
					<td>테니스입니다</td>
					<td>김재영</td>
					<td>40</td>
					<td>2024-10-30</td>
					<td>관리자</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>공통</td>
					<td>탁구입니다</td>
					<td>박민호</td>
					<td>40</td>
					<td>2024-10-29</td>
					<td>버즈</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>운동</td>
					<td>경마입니다</td>
					<td>김다민</td>
					<td>40</td>
					<td>2024-10-28</td>
					<td>관리자</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>운동</td>
					<td>야구입니다</td>
					<td>이규한</td>
					<td>40</td>
					<td>2024-10-27</td>
					<td>관리자</td>


				</tr>
			<tbody>

				<tr>

					<td>30</td>
					<td>운동</td>
					<td>농구입니다</td>
					<td>이민수</td>
					<td>40</td>
					<td>2024-10-27</td>
					<td>세종대왕</td>


				</tr>
			<tbody>

				<tr>

					<td>50</td>
					<td>공통</td>
					<td>스포츠음료입니다</td>
					<td>이다영</td>
					<td>40</td>
					<td>2024-10-27</td>
					<td>관리자</td>


				</tr>
		</table>

	</div>


	<script>
		$(function() {

			$(".list-area>tbody>tr").click(function() {

				var bno = $(this).children().first().text();

				location.href = "/UnionBoard.do?bno=" + bno;

			});

		});
	</script>


   




		

		

	</div>

	<br>
	<br>
	</div>

</body>
</html>