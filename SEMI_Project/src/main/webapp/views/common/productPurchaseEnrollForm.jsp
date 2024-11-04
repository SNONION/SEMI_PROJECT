<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 구매</title>
</head>
<body>

<%@ include file="/views/common/menuBar.jsp" %>

<% 
    String bno = request.getParameter("bno"); 
    // bno를 사용하여 DB에서 상품 정보를 가져오는 로직 추가
    // 예를 들어, 상품 객체를 가져온 후 아래에 표시
%>

<h2>상품 구매</h2>

<%-- 예시로 상품 정보를 표시하는 부분 --%>
<div>
    <h3>상품명: ${product.proName}</h3>
    <p>가격: ${product.price}</p>
    <img src="${contextPath}/uploadfiles/${product.boardNo}.jpg" alt="${product.proName}">
</div>

<form action="${contextPath}/update.sh" method="post">
    <input type="hidden" name="boardNo" value="${product.boardNo}">
    <label for="quantity">수량:</label>
    <input type="number" name="quantity" id="quantity" min="1" required>
    <button type="submit">구매하기</button>
</form>

</body>
</html>