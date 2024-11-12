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
import com.kh.user.model.vo.UserTier;

/**
 * Servlet implementation class PopularBoardController
 */
@WebServlet("/popularBoardListView.un")
public class PopularBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<UnionBoard> pList = null;	
		int currentPage; // 현재 페이지 정보 (몇번 페이지인지)
		int listCount; // 총 게시글 갯수를 담아줄 변수
		int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
		int listLimit; // 한 페이지에 보여질 게시글 개수
		int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바 끝 수
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		listCount = new UnionBoardService().popularBoardListCount();
		pageLimit = 10;
		listLimit = 15;
		maxPage = (int)Math.ceil(((double)listCount/listLimit));
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = (startPage + pageLimit) - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
		
		pList = new UnionBoardService().selectPopularBoardList(p);
		
		for(UnionBoard u : pList) {
			// 작성자 이미지 가져오는 과정
			UserTier tier = new UnionBoardService().selectTierImg(u.getBoardWriter());
			u.setTierPath(tier.getTierPath());
			u.setTierName(tier.getTierOriginFileName());
		}
		
		request.setAttribute("p", p);
		request.setAttribute("pList", pList);
		request.getRequestDispatcher("/views/unionBoard/popularBoardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
