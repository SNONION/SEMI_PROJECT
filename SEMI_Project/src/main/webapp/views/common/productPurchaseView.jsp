<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.kh.shop.model.service.ShopService" %>
<%@ page import="com.kh.shop.model.vo.Product" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>구매 완료</title>
</head>
<body>

<%
    // 요청 파라미터로부터 boardNo와 quantity를 가져옵니다.
    String boardNoStr = request.getParameter("boardNo");
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    
    // boardNo를 int로 변환
    int boardNo = Integer.parseInt(boardNoStr);

    // Service를 사용하여 주문 처리
    ShopService shopService = new ShopService();

    // Product 객체 생성 및 속성 설정
    Product order = new Product();
    order.setBoardNo(boardNo);
    order.setQuantity(quantity);

    // 주문 처리
    boolean isSuccess = shopService.purchaseProduct(order);

    if (isSuccess) {
%>
        <h2>구매가 완료되었습니다!</h2>
<%
    } else {
%>
        <h2>구매 처리 중 오류가 발생했습니다. 다시 시도해 주세요.</h2>
<%
    }
%>

<a href="${contextPath}/list.sh">상품 목록으로 돌아가기</a>

</body>
</html>
