package com.kh.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;

/**
 * Servlet implementation class ProductUpdateController
 */
@WebServlet("/update.sh")
public class ProductUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
				int boardNo = Integer.parseInt(request.getParameter("bno"));
				//수정페이지로 보내야하는 데이터
				//게시글 정보,카테고리 목록, 첨부파일 정보
				
				ShopService service = new ShopService();
				
				Product p = service.selectProduct(boardNo);
				
				ShopMediaFile smf = service.selectShopMediaFile(boardNo);
				
				//ArrayList<Category> cList = service.selectCategory();
				
				request.setAttribute("p", p);
				request.setAttribute("smf", smf);
				//request.setAttribute("cList", cList);
				
				request.getRequestDispatcher("/views/common/productListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
