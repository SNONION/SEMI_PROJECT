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
 * Servlet implementation class ProductDetailListController
 */
@WebServlet("/detailList.sh")
public class ProductDetailListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductDetailListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		ShopService service = new ShopService();
		
		//일반 게시판에서 조회수 증가 작성한 메소드 이용
		int result = service.increaseCount(boardNo);
		
		if(result>0) {
			//게시글 정보 조회
			Product p = service.selectProduct(boardNo);
			
			//첨부파일 정보
			ShopMediaFile smf = service.selectShopMediaFile(boardNo);
			
			request.setAttribute("p", p);
			request.setAttribute("smf", smf);
			
			request.getRequestDispatcher("/views/common/photoDetailView.jsp").forward(request, response);
			
		}else {
			
			request.setAttribute("errorMsg", "게시글 상세조회 실패");
			
			request.getRequestDispatcher("/views/common/errorPage.jsp").forward(request, response);
	}

	}	
		
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
