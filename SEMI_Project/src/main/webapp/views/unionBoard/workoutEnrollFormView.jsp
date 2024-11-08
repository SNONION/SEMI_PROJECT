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
		<form action="/semi/insertWorkout.un" method="post">
			<input type="hidden" name="userNo" value="${userNo}">
			<div class="detail-area">
				<div class="container">
					<table class="table table-dark table-striped">
						<tr align="center">
							<th width="700px">
								<div class="input-group mb-3">
									<input type="text" name="workoutTitle" id="workoutTitle" class="form-control" placeholder="운동 제목">
								</div>
							</th>
							<th width="150px">${nickname}</th>
							<th width="150px">${date}</th>
						</tr>
						<tr>
							<th colspan="4"><textarea name="workoutContent"
									id="workoutContent" cols="125" rows="15" placeholder="오늘 진행한 운동을 적어주세요" style="resize:none;" required></textarea> <br>
							</th>
						</tr>
						<tr align="right">
							<th colspan="4">
								<button type="submit"
									class="btn btn-outline-warning btn-sm">작성하기</button>
								<button type="reset" class="btn btn-outline-danger btn-sm">전부 지우기</button>
							</th>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>
	
	<script>
		function backPage() {
			
			$("<form>", {
				method : "post",
				action : "/semi/mypage.us"
			}).appendTo($("body")).submit();
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