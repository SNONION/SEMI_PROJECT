package com.kh.shop.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;

/**
 * Servlet implementation class ProductPurchaseController
 */
@WebServlet("/purchase.sh")
public class ProductPurchaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPurchaseController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		 	int boardNo = Integer.parseInt(request.getParameter("boardNo")) ;
		 	int quantity = Integer.parseInt(request.getParameter("quantity")); 
		 	       
	        // Service를 사용하여 주문 처리
	       
	        Product order = new Product();
	        order.setBoardNo(boardNo);
	        order.setQuantity(quantity);
	        
	        ShopService shopService = new ShopService();
	        boolean isSuccess = shopService.purchaseProduct(order);

	        // 결과에 따라 응답 처리
	        if (isSuccess) {
	            request.setAttribute("message", "구매가 완료되었습니다!");
	            request.setAttribute("order", order);
	        } else {
	            request.setAttribute("message", "구매 처리 중 오류가 발생했습니다. 다시 시도해 주세요.");
	        }

	        // 구매 페이지로 재포워딩
	        request.getRequestDispatcher("/views/common/productPurchaseView.jsp").forward(request, response);
	    }
	
		
		
		
	}


