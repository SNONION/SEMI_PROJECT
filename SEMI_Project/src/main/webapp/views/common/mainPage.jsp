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
	background-color: orange;
}

#announcement-area tr:hover{
	cursor: pointer;
	background-color: orange;
}

#popular-area tr:hover{
	cursor: pointer;
	background-color: orange;
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
							
							var writerTd = $("<td>").text(board.boardWriter);
							var imgTag = $("<img width='20px' height='20px' style='margin-left: 5px; margin-bottom: 5px;'>").attr("src","/semi" + board.tierPath + board.tierName);
							writerTd.append(imgTag);
							tr.append(writerTd);
							
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
		
		$(function() {
		    // 미리보기 박스 생성
		    const previewBox = $('<div class="preview-box"></div>').appendTo('body');

		    // 마우스를 이미지가 있는 행에 올렸을 때 이벤트 처리
		    $("#announcement-area").on("mouseenter", "tr", function() {
		        var boardNo = $(this).children().first().text();

		        // 이전 이미지 초기화
		        previewBox.empty();

		        // Ajax 요청으로 이미지 가져오기
		        $.ajax({
		            url : "/semi/imgPreview.un",
		            data : {
		                boardNo : boardNo
		            },
		            success : function(file) {
		                // 파일이 있을 경우 미리보기 이미지 생성
		                if (file != null) {
		                    var img = $("<img>").attr("src", "/semi" + file.filePath + file.changeFileName);
		                    previewBox.append(img).show();
		                }
		            },
		            error : function() {
		                console.log("호출 오류");
		            }
		        });
		    }).on("mouseleave", "tr", function() {
		        // 마우스가 tr에서 벗어날 때 미리보기 창 숨기기
		        previewBox.empty();
		        previewBox.hide();
		    }).on("mousemove", "tr", function(event) {
		        // 마우스 이동에 따라 미리보기 창 위치 조정
		        previewBox.css({
		            top: event.pageY + 10 + "px", // 마우스 위치 기준으로 아래 10px
		            left: event.pageX + 10 + "px" // 마우스 위치 기준으로 오른쪽 10px
		        });
		    });
		    
		    $("#popular-area").on("mouseenter", "tr", function() {
		        var boardNo = $(this).children().first().text();

		        // 이전 이미지 초기화
		        previewBox.empty();

		        // Ajax 요청으로 이미지 가져오기
		        $.ajax({
		            url : "/semi/imgPreview.un",
		            data : {
		                boardNo : boardNo
		            },
		            success : function(file) {
		                // 파일이 있을 경우 미리보기 이미지 생성
		                if (file != null) {
		                    var img = $("<img>").attr("src", "/semi" + file.filePath + file.changeFileName);
		                    previewBox.append(img).show();
		                }
		            },
		            error : function() {
		                console.log("호출 오류");
		            }
		        });
		    }).on("mouseleave", "tr", function() {
		        // 마우스가 tr에서 벗어날 때 미리보기 창 숨기기
		        previewBox.empty();
		        previewBox.hide();
		    }).on("mousemove", "tr", function(event) {
		        // 마우스 이동에 따라 미리보기 창 위치 조정
		        previewBox.css({
		            top: event.pageY + 10 + "px", // 마우스 위치 기준으로 아래 10px
		            left: event.pageX + 10 + "px" // 마우스 위치 기준으로 오른쪽 10px
		        });
		    });
		    
		    $("#board-area").on("mouseenter", "tr", function() {
		        var boardNo = $(this).children().first().text();

		        // 이전 이미지 초기화
		        previewBox.empty();

		        // Ajax 요청으로 이미지 가져오기
		        $.ajax({
		            url : "/semi/imgPreview.un",
		            data : {
		                boardNo : boardNo
		            },
		            success : function(file) {
		                // 파일이 있을 경우 미리보기 이미지 생성
		                if (file != null) {
		                    var img = $("<img>").attr("src", "/semi" + file.filePath + file.changeFileName);
		                    previewBox.append(img).show();
		                }
		            },
		            error : function() {
		                console.log("호출 오류");
		            }
		        });
		    }).on("mouseleave", "tr", function() {
		        // 마우스가 tr에서 벗어날 때 미리보기 창 숨기기
		        previewBox.empty();
		        previewBox.hide();
		    }).on("mousemove", "tr", function(event) {
		        // 마우스 이동에 따라 미리보기 창 위치 조정
		        previewBox.css({
		            top: event.pageY + 10 + "px", // 마우스 위치 기준으로 아래 10px
		            left: event.pageX + 10 + "px" // 마우스 위치 기준으로 오른쪽 10px
		        });
		    });
		});
	</script>

<br><br><br><br><br><br>
</body>
</html>