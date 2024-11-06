<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
	#enroll-area input, #enroll-area textarea{
		width : 100%;
		box-sizing : border-box;	
	}

</style>


</head>
<body>
	<%@ include file="/views/common/menuBar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">상품 작성</h2>
		<br>
		
		<form action="${contextPath}/insert.sh" method="post" id="enroll-form" enctype="multipart/form-data">
			<!-- 작성자 정보 요청시 전달 같이 하기 -->
			<input type="hidden" name="userNo" value="${loginUser.userNo }">
			<table align="center">
			
				<tr>
					<th width="100">상품명</th>
					<td colspan="3"> <input type="text" name="title" required> </td>
				</tr>
				
				<tr>
					<th>상품설명</th>
					<td colspan='3'>
					<textarea name="content" rows="10" style='resize:none' required></textarea>
					</td>
				</tr>
				
				<tr>	
					<th>상품이미지</th>				
					<td colspan="3" align="center" width="250" height="170">
						<img id="titleImg">
					</td>
				</tr>	
									
			</table>
			<br> <br>
			
			<div id="file-area">
				<input type="file" id="file1" name="file1" onchange="loadImg(this,1);" required>
							
			</div>	
			
			<div align="center" id="enroll-content">
				<button type="submit">등록하기</button>
				<button type="button" onclick="history.back()">뒤로가기</button>
			</div>
			
		</form>
			
			<script>
			
				$(function(){
					//이미지 영역 클릭했을 때 input file 태그가 동작하도록 처리해보기
					$("#titleImg").click(function(){
						//대표이미지 영역이 클릭되었을 때 input file1 태그 동작시키기
						$("#file1").click();
						
					});
												
					//input file 태그들 숨기기
					$("#file-area").hide();
					
				});
				
					//확인 요망				
				function loadImg(inputFile) {				
				    if (inputFile.files.length == 1) { // 파일이 등록됐을 때
				        var reader = new FileReader();
				        var file = inputFile.files[0];

				        // 이미지 파일인지 확인
				        if (file.type.startsWith('image/')) {
				            reader.readAsDataURL(file);
				            reader.onload = function(e) {
				                $("#titleImg").attr("src", e.target.result);
				            };
				        } else {
				            alert("이미지 파일을 선택해 주세요.");
				        }
				    } else {
				        $("#titleImg").attr("src", ""); // 파일이 없을 때 이미지 초기화
				    }
				}
			
			</script>
			
			
			
		
		
		<br> <br>
		
	
	
	
	</div>


</body>
</html>