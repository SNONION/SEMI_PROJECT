<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>

	<style>
	
	.modal {
    display: none; 
    position: fixed;
    z-index: 1; 
    left: 0;
    top: 0;
    width: 100%; 
    height: 100%; 
    overflow: auto; 
    background-color: rgb(0,0,0); 
    background-color: rgba(0,0,0,0.4); 
    padding-top: 60px;
}

/* Modal Content */
.modal-content {
    background-color: #fefefe;
    margin: 5% auto;
    padding: 20px;
    border: 1px solid #888;
    width: 80%;
    max-width: 600px;
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
		
	
	</style>

</head>
<body>

<%@ include file="/views/common/menuBar.jsp" %>

<form action="${contextPath}/detailList.sh" method="get" id="detail-form" enctype="multipart/form-data">

    <!-- 모달을 여는 버튼 -->
    <button type="button" id="openModalBtn" class="btn btn-primary">
        상품 상세 보기
    </button>

    <!-- 모달 -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <!-- 모달 헤더 -->
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>상품 상세</h2>
            </div>
            
            <!-- 모달 내용 -->
            <div class="modal-body">
                <h3>Item Title</h3>
                <p>상품에 대한 설명이 여기에 들어갑니다.</p>
                <img src="https://via.placeholder.com/300" alt="상품 이미지" class="modal-image">
            </div>
        </div>
    </div>

</form>

<!-- JavaScript 코드 -->
<script>
    // 모달을 가져옵니다.
    var modal = document.getElementById("myModal");

    // 모달을 여는 버튼을 가져옵니다.
    var btn = document.getElementById("openModalBtn");

    // 모달을 닫는 X 버튼을 가져옵니다.
    var span = document.getElementsByClassName("close")[0];

    // 버튼 클릭 시 모달을 열도록 설정
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // X 버튼 클릭 시 모달을 닫도록 설정
    span.onclick = function() {
        modal.style.display = "none";
    }

    // 모달 외부 클릭 시 모달을 닫도록 설정
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>


</body>
</html>