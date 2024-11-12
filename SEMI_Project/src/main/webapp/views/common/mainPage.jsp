<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.container{
	align: center;
}

.middle-boardArea{
	align: center;
	margin: auto;
}

.left-board{
	width: 49%;
	margin: auto;
	display: inline-block;
}

.right-board{
	width: 49%;
	margin: auto;
	display: inline-block;
}

a{
    text-decoration: none;
	color: orange;
	font-size: 10px;
}

#go:hover{
	color: red;
	background-color: black;
}

#board-area tr:hover{
	cursor: pointer;
	background-color: gray;
}

#announcement-area tr:hover{
	cursor: pointer;
	background-color: gray;
}

#popular-area tr:hover{
	cursor: pointer;
	background-color: gray;
}

</style>

</head>
<body>
	
	<%@ include file="/views/common/menuBar.jsp"%>
	
	<div class="outer">
		<input type="hidden" name="userNo" value="${loginUser.userNo}">
		<div class="top-board">
			<div class="container">
				<br>
				<br>
				<a href="/semi/announcementPage.un?currentPage=1" id="go">공지사항 바로가기 >>></a>
				<table class="table table-dark table-striped">
					<thead>
						<tr align="center">
							<th>No.</th>
							<th>카테고리</th>
							<th width="400px">제목</th>
							<th>작성자</th>
							<th>작성일</th>
							<th width="80px">조회수</th>
							<th width="80px">추천</th>
						</tr>
					</thead>
					<tbody id="announcement-area">

					</tbody>
				</table>
			</div>
		</div>
		
		<script>
			$(function(){
				
				$.ajax({
					url : "/semi/simpleBoard.un",
					data : {
						type : "announcement",
						currentPage : 1
					},
					success : function(list){
						
						$("#announcement-area tr").remove();
						
						for(var board of list){
							
							var tr = $("<tr align='center'>");
							var td = $("<td>");
							
							if (board.recommend >= 2) {
							    td.append('<i class="fas fa-star"></i>&nbsp;');
							}
							
							tr.append($("<td>").text(board.boardNo));
							tr.append($("<td>").text(board.categoryName));
							td.append(board.boardTitle + " [" + board.replyCount + "]");
							tr.append(td);
							tr.append($("<td>").text(board.boardWriter));
							tr.append($("<td>").text(board.createDate));
							tr.append($("<td>").text(board.count));
							tr.append($("<td>").text(board.recommend));
							
							$("#announcement-area").append(tr);
						}
					},
					error : function(){
						
					}
				});
			});
		</script>
		
		<div class="middle-boardArea">
			<div class="left-board">
				<div class="container">
					<br>
					<br>
					<a href="/semi/unionBoardListView.un?currentPage=1" id="go">실시간 게시글 바로가기 >>></a>
					<table class="table table-dark table-striped">
						<thead>
							<tr align="center">
								<th>No.</th>
								<th width="400px">제목</th>
								<th width="80px">조회수</th>
							</tr>
						</thead>
						<tbody id="board-area">
	
						</tbody>
					</table>
				</div>
			</div>
			
			<script>
				$(function(){
					
					$.ajax({
						url : "/semi/simpleBoard.un",
						data : {
							type : "unionBoard",
							currentPage : 1
						},
						success : function(list){
							
							$("#board-area tr").remove();
							
							for(var b of list){
								var tr = $("<tr align='center'>");
								
								tr.append($("<td>").text(b.boardNo));
								tr.append($("<td>").text(b.boardTitle));
								tr.append($("<td>").text(b.count));
								
								$("#board-area").append(tr);
							}
						},
						error : function(){
							
						}
					});
				});
			</script>
	
			<div class="right-board">
				<div class="container">
					<br>
					<br>
					<a href="/semi/popularBoardListView.un?currentPage=1" id="go">실시간 인기글 바로가기 >>></a>
					<table class="table table-dark table-striped">
						<thead>
							<tr align="center">
								<th>No.</th>
								<th width="400px">제목</th>
								<th width="80px">조회수</th>
							</tr>
						</thead>
						<tbody id="popular-area">
							
						</tbody>
					</table>
				</div>
			</div>
			
			<script>
				$(function(){
					
					$.ajax({
						url : "/semi/simpleBoard.un",
						data : {
							type : "popularBoard",
							currentPage : 1
						},
						success : function(list){
							
							$("#popular-area tr").remove();
							
							for(var b of list){
								var tr = $("<tr align='center'>");
								
								tr.append($("<td>").text(b.boardNo));
								tr.append($("<td>").text(b.boardTitle));
								tr.append($("<td>").text(b.count));
								
								
								$("#popular-area").append(tr);
							}
						},
						error : function(){
							
						}
					});
				});
			</script>
		</div>
	</div>
	
	<script>
		$("#announcement-area").on("click","tr",function(){
			var boardNo = $(this).children().first().text();
			
			if(boardNo != '게시물이 없습니다.'){
				var userNo = $("input[name=userNo]").val();
				
				location.href="/semi/boardDetailView.un?boardNo=" + boardNo + "&userNo=" + userNo;
			}
		});
		
		$("#board-area").on("click","tr",function(){
			var boardNo = $(this).children().first().text();
			
			if(boardNo != '게시물이 없습니다.'){
				var userNo = $("input[name=userNo]").val();
				
				location.href="/semi/boardDetailView.un?boardNo=" + boardNo + "&userNo=" + userNo;
			}
		});
		
		$("#popular-area").on("click","tr",function(){
			var boardNo = $(this).children().first().text();
			
			if(boardNo != '게시물이 없습니다.'){
				var userNo = $("input[name=userNo]").val();
				
				location.href="/semi/boardDetailView.un?boardNo=" + boardNo + "&userNo=" + userNo;
			}
		});
	</script>

<br><br><br><br><br><br>
</body>
</html>