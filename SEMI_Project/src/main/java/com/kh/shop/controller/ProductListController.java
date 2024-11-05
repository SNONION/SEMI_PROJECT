package com.kh.shop.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;


/**
 * Servlet implementation class ProductListController
 */
@WebServlet("/list.sh") //listView.pr
public class ProductListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
				int listCount; //총 게시글 개수
				int currentPage; //현재 페이지
				int pageLimit; // 페이지 하단에 보여질 페이징바 최대 가수
				int boardLimit; //한 페이지에 보여질 게시글 개수				
				int maxPage; // 가장 마지막 페이지는 몇번 페이지인지(총 페이지개수)
				int startPage; //페이지 하단에 보여질 페이징바 시작 수
				int endPage; //페이지 하단에 보여질 페이징바 끝 수

				
				//총 게시글 개수 - db에서 게시글 개수 세어오기
				listCount = new ShopService().listCount(); //
				
				//현재 페이지 정보
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				
				//페이지 하단에 보여질 페이징 바 개수
				pageLimit = 10;
				boardLimit = 10;						
				
				maxPage = (int) Math.ceil((double)listCount/boardLimit);					
				
				startPage =(currentPage -1)/pageLimit * pageLimit +1;								
				
				endPage = startPage+pageLimit-1; //시작페이지로부터 몇개를 보여줄지에 따라 처리됨
								
				
				if(maxPage<endPage) {
					endPage = maxPage; //maxPage에 담긴 값을 endPage에 대입하기
				}
				
				//페이징바에 필요한 데이터 7개를 담아줄 수 있는 VO객체 정의하기
				PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit, maxPage, startPage, endPage);
								
				
				//상품 목록 조회
				ArrayList<Product> list = new ShopService().selectProduct(pi);
				
				//조회된 게시글 목록과 페이징바 정보객체를 위임시 전달하기
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
				
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
