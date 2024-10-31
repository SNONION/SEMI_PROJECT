package com.kh.unionBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.unionBoard.model.service.UnionBoardService;

/**
 * Servlet implementation class UnionListController
 */
@WebServlet("/UnionBoard.do")
public class UnionListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnionListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//페이징 처리 (사용자에게 보여줄 게시글 정보들)
		
		//준비물
		
		int listCount; //총 게시글 숫자
		int currentPage;
		int pageLimit;
		int unionBoardLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		
		listCount = new UnionBoardService().listCount();
		
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		
		
		pageLimit = 10;
		unionBoardLimit = 10;
		
		
		
		
		maxPage = (int)Math.ceil((double)listCount/unionBoardLimit);
		
		
		
		startPage = (currentPage-1)/pageLimit * pageLimit + 1;
		
		
		
		endPage = startPage+pageLimit-1;
		
		
		
		
		if(maxPage<endPage) {
			endPage = maxPage;
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
