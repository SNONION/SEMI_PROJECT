<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
#board-area tr:hover{
	cursor: pointer;
	background-color: orange;
}

#recommedClick:hover{
	cursor: pointer;
}
</style>

</head>
<body>
	<%@ include file="/views/common/menuBar.jsp"%>

	<div class="outer">
		<div class="container" align="center">
			<br> <br>
			<c:choose>
				<c:when test="${empty bList}">
					<h3>${cateName} - "${keyword}" 관련된 내용이 없습니다.</h3>
				</c:when>
				<c:otherwise>
					<h3>${cateName} - "${keyword}" 관련 검색 내용입니다.</h3>
				</c:otherwise>
			</c:choose>
			<br>
		</div>
		<br> <br>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item">
					<button type="button" onclick="goToUnion();" class="btn btn-dark">통합</button>
				</li>
				<li>
					<button type="button" onclick="goToAnnounce();" class="btn btn-dark">공지</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goTopopul();" class="btn btn-dark">인기</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goToFree();" class="btn btn-dark">자유</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goToEat();" class="btn btn-dark">식단</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goToChall();" class="btn btn-dark">챌린지</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goToExp();" class="btn btn-dark">전문가</button>
				</li>
				<li class="nav-item">
					<button type="button" onclick="goToReq();" class="btn btn-dark">문의</button>
				</li>
			</ul>
			<form class="form-inline" action="/semi/searchKeyword.un?currentPage=1" method="post">
				
				<input class="form-control mr-sm-2" type="text" name="keyword" placeholder="Search">
				<button class="btn btn-success" type="submit">Search</button>
			</form>
			<div class="writeBtn-area" align="center">
				<input type="hidden" name=userNo value="${loginUser.userNo}">
				<button type="button" onclick="writeBoard();"
					class="btn btn-outline-warning" style="margin-left:20px;">글작성</button>
			</div>
		</nav>

		<script>
			function writeBoard(){
				var userNo = $("input[name=userNo]").val();
				location.href="/semi/boardEnrollForm.un?userNo=" + userNo;
			};
			
			function goToAnnounce(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=announce"
			};
		
			function goTopopul(){
				
				location.href="/semi/popularBoardListView.un?currentPage=1&type=popular"
			};
			
			function goToUnion(){
				location.href="/semi/unionBoardListView.un?currentPage=1";
			};
			
			function goToFree(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=free";
			};
			
			function goToEat(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=eat";
			};
			
			function goToChall(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=challange";
			};
			
			function goToExp(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=expert";
			};
			
			function goToReq(){
				location.href="/semi/categoeyListView.un?currentPage=1&type=request";
			};
		</script>

		<div align="center">
			<table class="table table-dark table-striped">
				<thead>
					<tr>
						<th>No.</th>
						<th>카테고리</th>
						<th width="400px">제목</th>
						<th>작성자</th>
						<th>작성일</th>
						<th width="80px">조회수</th>
						<th width="80px">추천</th>
					</tr>
				</thead>
				<tbody id="board-area">
					<c:choose>
						<c:when test="${empty bList}">
							<tr align="center">
								<td colspan="7">게시물이 없습니다.</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="b" items="${bList}">
								<tr>
									<td>${b.boardNo}</td>
									<td>${b.categoryName}</td>
									<td>
										<c:if test="${b.recommend >= 2}">
											<i class="fas fa-star"></i>&nbsp;
										</c:if>
										${b.boardTitle}&nbsp;[${b.replyCount}]
									</td>
									<td>
										${b.boardWriter}
										<img src="/semi${b.tierPath}${b.tierName}"
												width='20px' height='20px' style='margin-left: 5px; margin-bottom: 5px;'>
									</td>
									<td>${b.createDate}</td>
									<td>${b.count}</td>
									<td>${b.recommend}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<div class="pageBtn-area" align="center">
			<c:if test="${p.currentPage > 5}">
				<a href="/semi/searchKeyword.un?currentPage=${p.currentPage - 1}" class="btn btn-outline-secondary"><</a>
			</c:if>
			<c:forEach var="i" begin="${p.startPage}" end="${p.endPage}">
				<button type="button" class="btn btn-outline-secondary">${i}</button>
			</c:forEach>
			<c:if test="${p.currentPage != p.maxPage}">
				<a href="/semi/searchKeyword.un?currentPage=${p.currentPage + 1}" class="btn btn-outline-secondary">></a>
			</c:if>
		</div>
	</div>

	<script>
		$("#board-area").on("click","tr",function(){
			var boardNo = $(this).children().first().text();
			
			if(boardNo != '게시물이 없습니다.'){
				var userNo = $("input[name=userNo]").val();
				
				location.href="/semi/boardDetailView.un?boardNo=" + boardNo + "&userNo=" + userNo;
			}
		});
	
		$(".pageBtn-area button").click(function(){
			
			$.ajax({
				url : "/semi/paging.us",
				data : {
					type : "keywordBoard",
					keyword : "${keyword}",
					cateNo : "${cateNo}",
					currentPage : $(this).text()
				},
				success : function(bList){
					
					$("#board-area tr").remove();
					
					for(var board of bList){
						
						var tr = $("<tr>");
						var td = $("<td>");
						
						if (board.recommend >= 2) {
						    td.append('<i class="fas fa-star"></i>&nbsp;');
						}
						
						tr.append($("<td>").text(board.boardNo));
						tr.append($("<td>").text(board.categoryName));
						td.append(board.boardTitle+ " [" + board.replyCount + "]");
						tr.append(td);
						
						var writerTd = $("<td>").text(board.boardWriter);
						var imgTag = $("<img width='20px' height='20px' style='margin-left: 5px; margin-bottom: 5px;'>").attr("src","/semi" + board.tierPath + board.tierName);
						writerTd.append(imgTag);
						tr.append(writerTd);
						
						tr.append($("<td>").text(board.createDate));
						tr.append($("<td>").text(board.count));
						tr.append($("<td>").text(board.recommend));
						
						$("#board-area").append(tr);
					}
				},
				error : function(){
					alert("요청 오류");
				}
			});
		});
		
		$(function() {
		    // 미리보기 박스 생성
		    const previewBox = $('<div class="preview-box"></div>').appendTo('body');

		    // 마우스를 이미지가 있는 행에 올렸을 때 이벤트 처리
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



	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</body>
</html>