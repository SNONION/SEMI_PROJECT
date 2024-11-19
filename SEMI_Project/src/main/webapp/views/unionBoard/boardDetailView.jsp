<%@page import="com.kh.unionBoard.model.vo.UnionBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>

h5 {
	color: blue;
	
	
}
.fixed-td {
    word-wrap: break-word;  /* 긴 단어도 자동 줄바꿈 */
}

#board-area tr:hover{
	cursor: pointer;
	background-color: orange;
}
</style>

</head>
<body>

	<%@ include file="/views/common/menuBar.jsp"%>

	<div class="outer">

		<br>
		<div class="title-area" align="center">
		</div>
		<div class="btn-area" align="center">
			<button type="button" onclick="backPage();" class="btn btn-outline-secondary">뒤로가기</button>
		</div>
		<br>
		<div class="detail-area">
			<div class="container">
				<table class="table table-dark table-striped">
					<tr align="center">
						<th width="100px">${ub.categoryName}</th>
						<th width="600px">${ub.boardTitle}</th>
						<th width="150px">${ub.boardWriter}
							<img src="/semi${ub.tierPath}${ub.tierName}"
												width='20px' height='20px' style='margin-left: 5px; margin-bottom: 5px;'>
						</th>
						<td width="120px">${ub.createDate}</td>
					</tr>
					<c:choose>
						<c:when test="${empty mediaList}">
							<tr align="center">
								<td>${noFile}</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="media" items="${mediaList}">
								<tr align="center">
									<td colspan="4"><img
										src="/semi${media.filePath}${media.changeFileName}"
										width='50%' height='50%' style='margin-left: 20px'></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
					<tr>
						<th colspan="4"><br> <br> <br>
							<%UnionBoard ub = (UnionBoard)request.getAttribute("ub");%>
							<%String content = ub.getBoardContent().replace("\n","<br>");%>
							<%=content%>
							 <br> <br> <br></th>
					</tr>
					<tr align="right">
						<th colspan="4">
							<button type="button" onclick="recomCount();" class="btn btn-outline-danger btn-sm">
										추천 <span id="countUp">${ub.recommend}</span>
							</button>
							<button type="button" onclick="reportBoard();" class="btn btn-outline-danger btn-sm">신고하기</button>
							<c:choose>
								<c:when test="${ub.boardWriter eq nickname}">
									<button type="button" onclick="updateBoard();" class="btn btn-outline-warning btn-sm">수정하기</button>
									<button type="button" onclick="deleteBoard();" class="btn btn-outline-danger btn-sm">삭제하기</button>
								</c:when>
								<c:when test="${loginUser.userId == 'admin01'}">
									<button type="button" onclick="deleteBoard();" class="btn btn-outline-danger btn-sm">삭제하기</button>
								</c:when>
							</c:choose>
						</th>
					</tr>
				</table>
			</div>
		</div>
		
		<script>
			function reportBoard(){
				
				alert("게시판이 신고되었습니다.");
			};
		
			function updateBoard(){
				
				location.href="/semi/updateBoard.un?boardNo=${boardNo}";
			};
			
			function deleteBoard(){
				
				if(confirm("게시글을 삭제하시겠습니까?")){
					location.href="/semi/deleteBoard.un?boardNo=${boardNo}";
				}
			};
		</script>
		
		<div id="reply-inputArea" class="container" align="center">
			<table class="table table-dark">
				<thead>
					<tr>
						<th width="600px">댓글 작성</th>
						<th width="100px">${nickname}</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th>
							<textarea name="replyContent"
									id="replyContent" cols="110" placeholder="내용을 입력해주세요." style="resize:none;" maxlength="100" required></textarea>
						</th>
						<th>
							<button type="button" onclick="replyWrite();" class="btn btn-outline-secondary">작성</button>
						</th>
					</tr>
				</tbody>
			</table>
		</div>
		
		<script>
			function replyWrite(){
				var replyContent = $("#replyContent").val();
				
				$.ajax({
					url : "/semi/replyInsert.un",
					method : "post",
					data : {
						boardNo : "${ub.boardNo}",
						userNo : "${userNo}",
						replyContent : replyContent
					},
					success : function(msg){
						if(msg == "NNNNN"){
							console.log("처리 오류");
						}
						else{
							console.log("정상 처리");
							replyUpdate();
						}
					},
					error : function(){
						alert("요청 오류");
					}
				});
			};
		</script>

		<div id="reply-outputArea" class="container" align="center" style="text-overflow: ellipsis;">
			<table class="table table-dark" style="table-layout: fixed;">
				<thead>
					<tr align="center">
						<th width="120px">No.</th>
						<th width="120px">작성자</th>
						<th width="600px">내용</th>
						<th width="120px">작성일</th>
					</tr>
				</thead>
				<tbody id="replyInput-area">
					<c:choose>
						<c:when test="${empty replyList}">
							<tr align="center">
								<td colspan="3"><br><br>현재 작성된 댓글이 없습니다.<br><br></td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="reply" items="${replyList}">
								<tr>
									<td>${reply.replyNo}</td>
									<td id="checkMyNick">${reply.nickname}</td>
									<td class="fixed-td">${reply.replyContent}</td>
									<td>${reply.replyDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		
		<div class="replyPageBtn-area" align="center">
			<c:forEach var="i" begin="${p2.startPage}" end="${p2.endPage}">
				<button type="button" class="btn btn-outline-secondary">${i}</button>
			</c:forEach>
		</div>
		
	</div>

	<script>
		$("#replyInput-area").on("click","tr",function(){
			var replyNo = $(this).children().first().text();
			var nickname = $(this).children().first().next().text();
			
			if(replyNo != "현재 작성된 댓글이 없습니다."){
				if("${loginNickname}" == nickname || "${loginNickname}" == '관리자'){
					if(confirm("이 댓글을 삭제하시겠습니까?")){
						$.ajax({
							url : "/semi/deleteReply.un",
							data : {
								replyNo : replyNo
							},
							success : function(msg){
								if(msg == 'NNNNN'){
									console("처리 오류");
								}
								else{
									alert("삭제되었습니다.");
								}
								replyUpdate();
							},
							error : function(){
								alert("요청 오류");
							}
						})
					}
				}
			}
		});
	
		$(".replyPageBtn-area button").click(function(){
			$.ajax({
				url : "/semi/paging.us",
				data : {
					type : "reply",
					boardNo : "${boardNo}",
					currentPage : $(this).text()
				},
				success : function(replyList){
					
					$("#replyInput-area tr").remove();
					
					for(var re of replyList){
						
						var tr = $("<tr>");
						tr.append($("<td id='checkMyNick'>").text(re.replyNo));
						tr.append($("<td id='checkMyNick'>").text(re.nickname));
						tr.append($("<td class='fixed-td'>").html(re.replyContent.replace(/\n/g, "<br>")));
						tr.append($("<td>").text(re.replyDate));
						$("#replyInput-area").append(tr);
					}
					
				},
				error : function(){
					console.log("N")
				}
			});
		});

		function replyUpdate(){
			
			$.ajax({
				url : "/semi/replyUpdate.un",
				method : "post",
				data : {
					boardNo : "${ub.boardNo}",
					currentPage : "${p2.currentPage}"
				},
				success : function(replyList){
					
					$("#replyInput-area tr").remove();
					
					for(var re of replyList){
						var tr = $("<tr>");
						tr.append($("<td>").text(re.replyNo));
						tr.append($("<td>").text(re.nickname));
						tr.append($("<td class='fixed-td'>").html(re.replyContent.replace(/\n/g, "<br>")));
						tr.append($("<td>").text(re.replyDate));
						
						tr.appendTo($("#replyInput-area"));
					}
					$("#replyContent").val("");
				},
				error : function(){
					alert("요청 오류");
				}
			});
		};
	
		function backPage() {
			location.href = "/semi/unionBoardListView.un?currentPage=1";
		};

		function recomCount() {

			$.ajax({
				url : "/semi/recomCountUp.un",
				data : {
					userNo : "${userNo}",
					boardNo : "${ub.boardNo}"
				},
				success : function(count) {
					$("#countUp").text(count);
				},
				error : function() {
					alert("요청 오류");
				}
			});
		};
		
	</script>
	
	<div class="outer">
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
					
				</tbody>
			</table>
		</div>
	</div>

	<script>
		$(function(){
			
			$.ajax({
				url : "/semi/detailBoardList.un",
				data : {
					currentPage : 1
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
						td.append(board.boardTitle + " [" + board.replyCount + "]");
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
					type : "unionBoard",
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
						td.append(board.boardTitle + " [" + board.replyCount + "]");
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
	</script>
	

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
</body>
</html>