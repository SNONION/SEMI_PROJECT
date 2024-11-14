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
        background-color: #fefefe;
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
			<a class="btn btn-info" id="openModalBtn" data-toggle="modal" data-target="#productModal">상품 게시</a>		<!-- insert/update구분 -->
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
						<form action="/semi/itemInsert.sh" method="post" id="productForm" enctype="multipart/form-data">

							<div class="form-group">
								<label for="proName">상품명</label> 
								<input type="text" id="proName" name="proName" class="form-control" required>
							</div>

							<div class="form-group">
								<label for="proMenual">상품 설명</label>
								<textarea id="proMenual" name="proMenual" class="form-control" required></textarea>
							</div>

							<div class="form-group">
								<label for="price">상품 가격</label> <input type="number" name="price" id="price"
									class="form-control" required>
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
	});
	   
	
	</script>
	
	
	
	<br>

		<table class='list-area' align='center' border='1'>
			<tbody>

				<c:choose>
					<c:when test="${empty pList }">
						<!-- list가 비어있다면 -->
						<tr>
							<td colspan="6">등록된 상품이 없습니다</td>
						</tr>

					</c:when>
					<c:otherwise>

						<c:forEach var="p" items="${pList}">
							<tr>
								<td style="display:none;">${p.proNo}</td>
								<td style="display:none;">${p.proName}</td>
								<td style="display:none;">${p.proMenual}</td>
								<td style="display:none;">${p.price}</td>
								
								<td>
									<div class="card" style="width: 400px;">
										<img class="card-img-top" src="/semi${p.proPath}${p.proImgName}"
											alt="Card image">
										<div class="card-body">
											<h4 class="card-title" style="color:black;">${p.proName}</h4>
											<p class="card-text" style="color:black;">${p.proMenual}</p>
											<h4 class="card-text" style="color:black;">${p.price} POINT</h4>
										</div>
									</div>
									<br>
								</td>
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
						
						<span style="display:none;" id="modalItemNo">제품번호</span>
						<h3 id="modalItemTitle">상품명</h3>
						<p id="modalItemDescription">상품 설명</p>
						<p id="modalItemPoint">포인트</p>
						<br>
						<!-- 구매 버튼 추가 -->
						<button onclick="purchProduct();" class="btn btn-success">구매하기</button>
						<button onclick="productDelete();" class="btn btn-success">삭제하기</button>
					</div>
				</div>
			</div>



		<script>
			function productDelete(){
				var proNo = $("#modalItemNo").text();
				var proPrice = $("#modalItemPoint").text();
				
				if(confirm("정말로 삭제하시겠습니까?")){
					
					$.ajax({
						
						url : "/semi/purchProduct.sh",
						method : "post",
						data : {
							proNo : proNo,
							proPrice : proPrice
						},
						success : function(msg){

							if(msg == 'NNNNN'){
								console.log("처리 실패");
							}
							else{
								alert("삭제되었습니다.");
								window.location.reload();
							}
						},
						error : function(){
							console.log("요청 오류");
						}
					});
				}
				
			};
		
			$(function() {
				// 테이블의 행을 클릭하면 모달을 열고, 상품 정보를 설정
				$(".list-area > tbody > tr").click(function(){
						var proNo = $(this).children().first().text(); // 상품 번호 가져오기
						var proName = $(this).children().first().next().text();
						var proMenual = $(this).children().first().next().next().text();
						var proPrice = $(this).children().first().next().next().next().text();
						
						$("#modalItemNo").text(proNo);
						$("#modalItemTitle").text(proName);
						$("#modalItemDescription").text(proMenual);
						$("#modalItemPoint").text(proPrice);
						
						// 모달 열기
						$("#myModal").fadeIn();
	
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
			});
			
			function purchProduct(){
				var proNo = $("#modalItemNo").text();
				var proPrice = $("#modalItemPoint").text();
				
				$.ajax({
					
					url : "/semi/purchProduct.sh",
					data : {
						proNo : proNo,
						proPrice : proPrice
					},
					success : function(msg){

						if(msg == 'NNNNN'){
							alert("당신은 거지입니다.");
							$("#myModal").fadeOut();
						}
						else{
							alert("구매되었습니다.");
							$("#myModal").fadeOut();
						}
					},
					error : function(){
						console.log("요청 오류");
					}
				});
			};
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