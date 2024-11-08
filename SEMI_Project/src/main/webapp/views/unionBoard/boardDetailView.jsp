<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
h5 {
	color: white;
}
</style>

</head>
<body>

	<%@ include file="/views/common/menuBar.jsp"%>

	<div class="outer">

		<br> <br>
		<div class="title-area" align="center">
			<h3>BOARD DETAIL</h3>
		</div>
		<br>
		<div class="btn-area" align="center">
			<button type="button" onclick="backPage();" class="btn btn-outline-secondary">뒤로가기</button>
		</div>
		<br><br>
		<div class="detail-area">
			<div class="container">
				<table class="table table-dark table-striped">
					<tr align="center">
						<th width="120px">${ub.categoryName}</th>
						<th width="600px">${ub.boardTitle}</th>
						<th width="150px">${ub.boardWriter}</th>
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
							${ub.boardContent} <br> <br> <br></th>
					</tr>
					<tr align="right">
						<th colspan="4">
							<c:choose>
								<c:when test="${ub.boardWriter eq nickname}">
									<button type="button" onclick="recomCount();" class="btn btn-outline-danger btn-sm">
										추천 <span id="countUp">${ub.recommend}</span>
									</button> 
									<button type="button" onclick="" class="btn btn-outline-warning btn-sm">수정하기</button>
									<button type="button" onclick="" class="btn btn-outline-danger btn-sm">삭제하기</button>
								</c:when>
								<c:otherwise>
									<button type="button" onclick="" class="btn btn-outline-danger btn-sm">
										추천 <span id="countUp">${ub.recommend}</span>
									</button>
								</c:otherwise>
							</c:choose>
						</th>
					</tr>
				</table>
			</div>
		</div>
		
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
									id="replyContent" cols="110" placeholder="내용을 입력해주세요." style="resize:none;" required></textarea>
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

		<div id="reply-outputArea" class="container" align="center">
			<table class="table table-dark">
				<thead>
					<tr align="center">
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
									<td>${reply.nickname}</td>
									<td>${reply.replyContent}</td>
									<td>${reply.replyDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>

	</div>

	<script>
		function replyUpdate(){
			
			$.ajax({
				url : "/semi/replyUpdate.un",
				method : "post",
				data : {
					boardNo : "${ub.boardNo}"
				},
				success : function(replyList){
					
					$("#replyInput-area tr").remove();
					
					for(var re of replyList){
						var tr = $("<tr>");
						tr.append($("<td>").text(re.nickname));
						tr.append($("<td>").text(re.replyContent));
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