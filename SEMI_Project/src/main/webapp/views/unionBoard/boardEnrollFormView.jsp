<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
			<button type="button" onclick="backPage();"
				class="btn btn-outline-secondary">뒤로가기</button>
		</div>
		<br> <br>
		<form action="/semi/insertBoard.un" method="post" enctype="multipart/form-data">
			<input type="hidden" name="userNo" value="${userNo}">
			<div class="detail-area">
				<div class="container">
					<table class="table table-dark table-striped">
						<tr align="center">
							<th width="700px">
								<div class="input-group mb-3">
									<div class="input-group-prepend">
										<select name="categoryNo">
											<c:forEach var="c" items="${cList}">
												<c:choose>
													<c:when test="${c.categoryNo eq 10}">
														<!-- 공지 작성 권한 -->
														<c:if test="${userNo == 1}">
															<option value="${c.categoryNo}">${c.categoryName}</option>
														</c:if>
													</c:when>
													<c:otherwise>
														<option value="${c.categoryNo}">${c.categoryName}</option>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</select>
									</div>
									<input type="text" name="boardTitle" id="boardTitle" class="form-control">
								</div>
							</th>
							<th width="150px">${nickname}</th>
							<th width="150px">${date}</th>
						</tr>
						<tr>
							<td><input type="file" name="file0" id="file0"></td>
							<td>
								<img id="preview-area1">
								<img id="preview-area2">
								<img id="preview-area3">
							</td>
							<td>
								<div id="imgSaveArea">
									
								</div>
							</td>
						</tr>
						<tr>
							<th colspan="4"><textarea name="boardContent"
									id="boardContent" cols="125" rows="5" placeholder="내용을 입력해주세요." required></textarea> <br>
							</th>
						</tr>
						<tr align="right">
							<th colspan="4">
								<button type="submit"
									class="btn btn-outline-warning btn-sm">작성하기</button>
								<button type="reset" class="btn btn-outline-danger btn-sm">취소</button>
							</th>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>

	<script>
		function backPage() {
			location.href = "/semi/unionBoardListView.un?currentPage=1";
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
	
	<div align="center">
	
	
	
	</div>
	
	
	
	
	
	
</body>
</html>