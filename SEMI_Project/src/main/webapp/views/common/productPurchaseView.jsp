<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ page import="com.kh.shop.model.vo.Product" %>
<%@ page import="com.kh.shop.model.service.ShopService" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>상품 구매</title>

    <!-- 스타일 추가 -->
    <style>
        /* 모달창 스타일 */
        .modal {
            display: none; /* 기본적으로 모달은 보이지 않게 설정 */
            position: fixed;
            z-index: 1;
            left: 0;
            top: 0;
            width: 100%;
            height: 100%;
            overflow: auto;
            background-color: rgba(0, 0, 0, 0.4);
        }

        .modal-content {
            background-color: #fefefe;
            margin: 15% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
        }

        /* 닫기 버튼 */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
</head>
<body>

<%@ include file="/views/common/menuBar.jsp" %>

<h2>상품 구매</h2>

<!-- 예시로 상품 정보를 표시하는 부분 (현재 bno를 이용해 상품 정보 가져오기) -->
<% 
    String bno = request.getParameter("bno"); 
    // bno를 사용하여 DB에서 상품 정보를 가져오는 로직 추가
    // 예시로, 아래와 같이 product 객체를 가져온 후 사용
%>

<!-- 상품 정보 표시 -->
<div>
    <h3>상품명: ${product.proName}</h3>
    <p>가격: ${product.price}</p>
    <img src="semi/uploadfiles/${product.boardNo}.jpg" alt="${product.proName}" style="width: 100px;">
</div>

<!-- 상품 구매 버튼 (모달 창 띄우기) -->
<button id="buyButton">상품 구매하기</button>

<!-- 모달 창 구조 -->
<div id="myModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span> <!-- 모달 닫기 버튼 -->
        
        <h3>상품명: ${product.proName}</h3>
        <p>가격: ${product.price}</p>
        <img src="semi/uploadfiles/${product.boardNo}.jpg" alt="${product.proName}" style="width: 100px;">
        
        <form action="/update.sh" method="post">
            <input type="hidden" name="boardNo" value="${product.boardNo}">
            <label for="quantity">수량:</label>
            <input type="number" name="quantity" id="quantity" min="1" required>
            <button type="submit">구매하기</button>
        </form>
    </div>
</div>

<!-- JavaScript: 모달 창 제어 -->
<script>
    // 모달과 관련된 변수들
    var modal = document.getElementById("myModal");
    var btn = document.getElementById("buyButton");
    var span = document.getElementsByClassName("close")[0];

    // 버튼 클릭 시 모달 띄우기
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // 모달 닫기 버튼 클릭 시 모달 닫기
    span.onclick = function() {
        modal.style.display = "none";
    }

    // 모달 밖을 클릭하면 모달을 닫도록 설정
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
</script>

<br><br><br><br><br><br><br>
</body>
</html>
