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
 * Servlet implementation class BoardKeySearchController
 */
@WebServlet("/searchKeyword.un")
public class BoardKeySearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardKeySearchController() {
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
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		String keyword = request.getParameter("keyword");
		int cateNo = 0;
		
		int listCount; // 총 게시글 갯수를 담아줄 변수
		int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
		int listLimit; // 한 페이지에 보여질 게시글 개수
		int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바 끝 수
		
		if(request.getParameter("categoryNo") != null) {
			cateNo = Integer.parseInt(request.getParameter("categoryNo"));
			
			if(cateNo != 999) {
				listCount = new UnionBoardService().selectCateKeyBoardCount(keyword, cateNo);
			}
			else {
				listCount = new UnionBoardService().selectPopularKeyBoardCount(keyword);
			}
		}
		else {
			listCount = new UnionBoardService().selectKeyBoardCount(keyword);
		}
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		pageLimit = 10;
		listLimit = 15;
		maxPage = (int)Math.ceil(((double)listCount/listLimit));
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = (startPage + pageLimit) - 1;
		
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
		ArrayList<UnionBoard> bList = null;
		
		if(cateNo != 0) {
			
			String cateName = "";
			
			if(cateNo != 999) {
				bList = new UnionBoardService().selectCateTitleKeyBoard(keyword, cateNo, p);	
				
				switch(cateNo) {		
					case 20 :
						cateName = "자유게시판";
						break;
					case 30 :
						cateName = "식단게시판";
						break;
					case 40 :
						cateName = "챌린지게시판";
						break;
					case 50 :
						cateName = "전문가게시판";
						break;
					case 60 :
						cateName = "문의게시판";
						break;
				}
			}
			else {
				cateName = "인기게시판";
				bList = new UnionBoardService().selectPopularTitleKeyBoard(keyword, p);
			}
			
			request.setAttribute("cateName", cateName);
		}
		else {
			bList = new UnionBoardService().selectTitleKeyBoard(keyword, p);
			request.setAttribute("cateName", "통합게시판");
		}
		
		for(UnionBoard u : bList) {
			int recommend = new UnionBoardService().selectRecomCount(u.getBoardNo());
			int replyCount = new UnionBoardService().selectReplyCount(u.getBoardNo());
			u.setReplyCount(replyCount);
			u.setRecommend(recommend);
		}
	
		request.setAttribute("cateNo", cateNo);
		request.setAttribute("keyword", keyword);
		request.setAttribute("p", p);
		request.setAttribute("bList", bList);
		request.getRequestDispatcher("/views/unionBoard/keywordBoardListView.jsp").forward(request, response);
		
	}

}
