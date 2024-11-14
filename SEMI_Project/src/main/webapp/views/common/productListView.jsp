<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.kh.common.model.vo.PageInfo" %>
<%@ page import="com.kh.shop.model.vo.ShopMediaFile" %>    
    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>상품등록</title>

	<style>
		
		.list-area td{
			color:white;
			text-align: center;
		}
		
		.list-area th{
			color:white;
			text-align: center;
		}
	
	.modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0, 0, 0, 0.4);
        /* Flexbox 중앙 배치 */
        display: flex;
        align-items: center;
        justify-content: center;
    }
	/* Modal Content */
	.modal-content {
        background-color: #FEFEFE;
        padding: 20px;
        border: 1px solid #888;
        width: 90%; /* 기본 너비 */
        max-width: 600px; /* 최대 너비 */
        max-height: 90vh; /* 최대 높이를 화면의 90%로 설정 */
        overflow-y: auto; /* 내용이 넘칠 경우 스크롤 */
    }
	
	/* Modal Header */
	.modal-header {
	    display: flex;
	    justify-content: space-between;
	    align-items: center;
	    border-bottom: 1px solid #ddd;
	    padding-bottom: 10px;
	}
	
	.modal-header h2 {
	    margin: 0;
	}
	
	.close {
	    color: #aaa;
	    font-size: 28px;
	    font-weight: bold;
	    cursor: pointer;
	}
	
	.close:hover,
	.close:focus {
	    color: black;
	    text-decoration: none;
	    cursor: pointer;
	}
	
	/* Modal Body */
	.modal-body {
	    padding: 10px 0;
	}
	
	.modal-image {
	    max-width: 100%;
	    height: auto;
	}
	
	
	/* 버튼 스타일 */
    #buyButton {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 16px;
        color: white;
        background-color: #28a745;
        border: none;
        cursor: pointer;
        border-radius: 5px;
    }

    #buyButton:hover {
        background-color: #218838;
    }
		
	
	
	</style>


</head>
<body>
	
	<%@ include file="/views/common/menuBar.jsp" %>
	
	
	<div class="outer">
	<br> <br>
	
	<%--글작성 버튼은 로그인한 회원일 경우 보일 수 있도록 조건처리 --%>
	
	<c:if test="${not empty loginUser && loginUser.userId == 'admin01'}">
		<div align="center">
			<a href="#" class="btn btn-outline-warning" id="openModalBtn">상품 게시</a>		<!-- insert/update구분 -->
		</div>	
	</c:if>

		<!-- The Modal -->
		<div class="modal" id="productModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">상품 정보 입력</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<!-- 상품 입력 폼 -->
						<form id="productForm" enctype="multipart/form-data">
							<div class="form-group">
								<label for="proNo">상품 코드</label> 
								<input type="text" id="proNo" class="form-control" required>
							</div>

							<div class="form-group">
								<label for="shopFileNo">파일 번호</label> 
								<input type="text" id="shopFileNo" class="form-control" required>
							</div>

							<div class="form-group">
								<label for="proName">상품명</label> 
								<input type="text" id="proName" class="form-control" required>
							</div>

							<div class="form-group">
								<label for="proMenual">상품 설명</label>
								<textarea id="proMenual" class="form-control" required></textarea>
							</div>

							<div class="form-group">
								<label for="price">상품 가격</label> <input type="number" id="price"
									class="form-control" required>
							</div>

							<div class="form-group">
								<label for="status">상태</label> <select id="status"
									class="form-control">
									<option value="Y">활성</option>
									<option value="N">비활성</option>
								</select>
							</div>
							
							<div class="form-group">
                            <label for="file1">상품 이미지</label> <!-- 수정된 부분 -->
                            <input type="file" id="file1" name="file1" class="form-control" required> <!-- 수정된 부분 -->
                        	</div>

							<button type="submit" class="btn btn-success">상품 등록</button>
						</form>
					</div>
				</div>
			</div>
		</div>
		

	<script>
	
	$(document).ready(function() {
	    // 모달 열기
	    $("#openModalBtn").click(function() {
	        $("#productModal").fadeIn();  // 모달 열기
	    });

	    // 모달 닫기
	    $(".close").click(function() {
	        $("#productModal").fadeOut();  // 모달 닫기
	    });

	    // 모달 외부 클릭 시 닫기
	    $(window).click(function(event) {
	        if ($(event.target).is("#productModal")) {
	            $("#productModal").fadeOut();  // 모달 닫기
	        }
	    });

	    // 상품 등록 폼 제출
	    $("#productForm").submit(function(event) {
	        event.preventDefault();  // 폼 제출 기본 동작 방지
	        
	       
	      	var formData = new FormData();	        
	       
	        formData.append('proNo', document.getElementById('proNo').value);	      
	        formData.append('shopFileNo', document.getElementById('shopFileNo').value);		     
	        formData.append('proName', document.getElementById('proName').value);		      
	        formData.append('proMenual', document.getElementById('proMenual').value);		      
	        formData.append('price', document.getElementById('price').value);		      
	        formData.append('status', document.getElementById('status').value);		     
	        formData.append('file1', document.getElementById('file1').value);	        	        
	        // 폼 데이터 가져오기
	       
	         $.ajax({
                url: '/insert.sh',  // 서버의 상품 등록 처리 서블릿 URL
                type: 'POST',
                data: formData,  // FormData 객체 전송

                // FormData 객체를 사용할 때는 processData와 contentType을 false로 설정해야 함
                processData: false,  // **수정된 부분**
                contentType: false,  // **수정된 부분**	            	
	            
	            success: function(response) {
	                alert("상품이 성공적으로 등록되었습니다!");
	                $("#productModal").fadeOut();  // 모달 닫기
	                $("#productForm")[0].reset();  // 폼 초기화
	            },
	            error: function(xhr, status, error) {
	                alert("상품 등록에 실패했습니다. 다시 시도해주세요.");
	            }
	        });
	    });
	});
	
	
	</script>
	
	
	
	<br>
	
	<table class='list-area' align='center' border='1'>
		<thead>
			<tr>
				<th width='150'>상품번호</th>			
				<th width='200'>상품명</th>
				<th width='400'>상품설명</th>
				<th width='200'>가격</th>		
				
			</tr>


		</thead>

		<tbody>
				
		<c:choose>
			<c:when test="${empty pList }"> <!-- list가 비어있다면 -->
				<tr>
					<td colspan="6">등록된 상품이 없습니다</td>
				</tr>				
			
			</c:when>
			<c:otherwise>			
				
				<c:forEach var="p" items="${pList}">
					<tr>
						<td>${p.proNo }</td>
						<td>${p.proName }</td>
						<td>${p.proMenual }</td>
						<td>${p.price }</td>										
					</tr>
				
				</c:forEach>
			</c:otherwise>
		</c:choose>				
		</tbody>		
	</table>
	
	
	
		<!-- 모달 -->
		
	<!-- The Modal -->
		<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">상품 상세</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">

						<h3 id="modalItemTitle">상품명</h3>
						<p id="modalItemDescription">상품 설명</p>
						<img id="modalItemImage"
							src="${smf.filePath}${smf.changeName}"
							alt="상품 이미지" class="modal-image"> <br>
						<!-- 구매 버튼 추가 -->
						<button id="buyButton" class="btn btn-success">구매하기</button>
					</div>
				</div>
			</div>



		<script>
			$(function() {
				// 테이블의 행을 클릭하면 모달을 열고, 상품 정보를 설정
				$(".list-area > tbody > tr").click(function(){
						var proNo = $(this).children().first().text(); // 상품 번호 가져오기
						var itemTitle = $(this).children().eq(1).text(); // 상품 제목 (두 번째 칼럼)
						var itemDescription = $(this).children().eq(2).text(); // 상품 설명 (세 번째 칼럼)
						var itemImage = $(this).children().eq(3).find('img').attr('src'); // 상품 이미지 (네 번째 칼럼의 이미지)
						// 모달에 내용 채우기
						$("#modalItemTitle").text(itemTitle);
						$("#modalItemDescription").text(itemDescription);
						$("#modalItemImage").attr("src",itemImage);
						// 구매 버튼 클릭 시 AJAX 요청을 통해 purchase.sh 서블릿 실행
						$("#buyButton").off("click").on("click",function(){
						var proNo = $("#modalItemTitle").text(); // 예시: 상품 번호를 모달 제목에서 가져오기 (적절한 값으로 수정)
														
						$.ajax({
							url : '/purchase.sh', // 서블릿 URL
							type : 'POST', // POST 방식
							data : {proNo : proNo}, // 서버로 보낼 데이터 (상품 번호)
							success : function(response) {
							// 서버에서 응답이 성공적으로 왔을 때의 처리
							// 예를 들어, 응답을 받아서 페이지 전환이나 특정 UI 처리 등을 할 수 있음
							alert("구매가 완료되었습니다!"); // 알림 표시
							location.href = "location.href='list.sh?currentPage=${pi.currentPage+1}'"
							//리스트 페이지로 리다이렉션
							},
							error : function() {
							// AJAX 요청 실패 시 처리
							alert("구매 실패했습니다.");
							}
							});
							});

						// 모달 열기
						$("#myModal").fadeIn();
						});
	
						// 모달 닫기
						$(".close").click(function() {
							$("#myModal").fadeOut();
						});
	
						// 모달 외부 클릭 시 모달 닫기
						$(window).click(function(event) {
							if ($(event.target).is("#myModal")) {
								$("#myModal").fadeOut();
							}
						});
					});
		</script>



			<br> <br>

			<div align="center" class="paging-area">

				<c:if test="${pi.currentPage != 1 }">
					<button
						onclick="location.href='list.sh?currentPage=${pi.currentPage-1}'">이전</button>
				</c:if>

				<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">
					<c:choose>
						<c:when test="${i !=pi.currentPage }">
							<button onclick="location.href='list.sh?currentPage=${i}'">${i}</button>
						</c:when>
						<c:otherwise>
							<!-- 현재 페이지 버튼은 비활성화 -->
							<button disabled>${i}</button>
						</c:otherwise>
					</c:choose>

				</c:forEach>

				<c:if test="${pi.currentPage != pi.maxPage }">
					<button
						onclick="location.href='list.sh?currentPage=${pi.currentPage+1}'">다음</button>
				</c:if>

			</div>

		</div>
</div>
	
<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>