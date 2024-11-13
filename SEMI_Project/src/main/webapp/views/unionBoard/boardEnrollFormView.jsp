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
						<tr style="display:none;" id="file-area">
							<td colspan="3">
								<input type="file" name="file0" id="file0" onchange="loadImg(this,0);">
								<input type="file" name="file1" id="file1" onchange="loadImg(this,1);">
								<input type="file" name="file2" id="file2" onchange="loadImg(this,2);">
								<input type="file" name="file3" id="file3" onchange="loadImg(this,3);">
							</td>
						</tr>
						<tr id="plusImg-btnarea">
							<td colspan="3">
								<button type="button" id="imgPlusBtn" onclick="plusImgArea();" class="btn btn-outline-secondary"> 이미지추가 </button>
							</td>
						</tr>
						<tr id="img-area">
							
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
							</th>
						</tr>
					</table>
				</div>
			</div>
		</form>
	</div>

	<script>
		// 버튼을 눌르면 이미지 부분을 클릭하면 파일을 넣을 수 있도록 설정
		function plusImgArea(){
			
			var td = $("<td>");
			
			td.append($("<img id='contentImg0' width='100' height='100' style='margin-left:20px'>"))
			td.append($("<img id='contentImg1' width='100' height='100' style='margin-left:20px'>"))
			td.append($("<img id='contentImg2' width='100' height='100' style='margin-left:20px'>"))
			td.append($("<img id='contentImg3' width='100' height='100' style='margin-left:20px'>"))
			
			$("#img-area").append(td);
			
			$("#contentImg0").click(function(){
				$("#file0").click();
			});
			$("#contentImg1").click(function(){
				$("#file1").click();
			});
			$("#contentImg2").click(function(){
				$("#file2").click();
			});
			$("#contentImg3").click(function(){
				$("#file3").click();
			});
			
			$("#file-area").hide();
			$("#imgPlusBtn").attr("disabled", true);
			
		};
		
		function deleteImgArea(){
		};
	
		function backPage() {
			location.href = "/semi/unionBoardListView.un?currentPage=1";
		};
		
		function loadImg(inputFiles, i){
			
			// 파일이 있을 경우
			if(inputFiles.files.length == 1){
				
				// 파일의 정보를 읽어올 수 있는 객체 new FileReader()를 생성한다.
				var reader = new FileReader();
				
				// readAsDataURL : 해당 파일의 url 정보를 불러온다.
				reader.readAsDataURL(inputFiles.files[0]);
				
				// 위에서 불러온 해당 파일의 url 정보가 result에 저장되고 다시 이벤트 매개변수 e에 저장된다.
				// onload 메소드가 끝나는 시점에 reader가 해당 파일의 url정보를 불러온다.
				reader.onload = function(e){
					
					switch(i){
					
					case 0 :
						$("#contentImg0").attr("src",e.target.result);
						break;
					case 1 :
						$("#contentImg1").attr("src",e.target.result);
						break;
					case 2 :
						$("#contentImg2").attr("src",e.target.result);
						break;
					case 3 :
						$("#contentImg3").attr("src",e.target.result);
						break;
					}
				};
			}
			else{
				switch(i){
				
				case 0 :
					$("#contentImg0").attr("src",null);
					break;
				case 1 :
					$("#contentImg1").attr("src",null);
					break;
				case 2 :
					$("#contentImg2").attr("src",null);
					break;
				case 3 :
					$("#contentImg3").attr("src",null);
					break;
				}
			}
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