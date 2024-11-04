package com.kh.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;


/**
 * Servlet implementation class ProductDetailView
 */
@WebServlet("/detail.sh")
public class ProductDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int proNo = Integer.parseInt(request.getParameter("pno"));
		
		ShopService service = new ShopService();
		
		//일반 게시판에서 조회수 증가 작성한 메소드 이용
		int result = new ShopService().increaseCount(proNo);
		
		if(result>0) {
			//리스트 정보 조회
			Product p = service.selectProduct(proNo);
			
			//첨부파일 정보
			ArrayList<Product> pList = service.selectProductList(proNo);
			
			request.setAttribute("p", p);
			request.setAttribute("pList", pList);
			
			request.getRequestDispatcher("/views/common/productDetailView.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("errorMsg", "사진 게시글 조회 실패");
			
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
