package com.kh.unionBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.UnionBoard;

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
		
		//준비물들
		
		int wListCount; //총 게시글 숫자들
		int currentPage;
		int pageLimit;
		int wListLimit;
		
		int maxPage;
		int startPage;
		int endPage;
		
		
		wListCount = new UnionBoardService().listCount();
		
		//현재 페이지 정보
		
		 currentPage = Integer.parseInt(request.getParameter("currentPage"));
		 
		
		
		System.out.println(Integer.parseInt(request.getParameter("currentPage")));
		
		
		
		
		
		pageLimit = 10;
		wListLimit = 10;
		
		
		
		
		maxPage = (int)Math.ceil((double)wListCount/wListLimit);
		
		
		
		startPage = (currentPage-1)/pageLimit * pageLimit + 1;
		
		
		
		endPage = startPage+pageLimit-1;
		
		
		
		if(maxPage<endPage) {
			endPage = maxPage;
		}
		
		
		
		
		PageInfo pi = new PageInfo(wListCount,currentPage,pageLimit,wListLimit,maxPage,startPage,endPage);
		
		
		ArrayList<UnionBoard> list = new UnionBoardService().selectList(pi);
		
		
		
		request.setAttribute("list",list);
		request.setAttribute("pi", pi);
		
		
		request.getRequestDispatcher("/views/BoardListView/BoardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
