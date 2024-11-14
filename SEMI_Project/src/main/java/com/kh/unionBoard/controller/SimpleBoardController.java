package com.kh.unionBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.UnionBoard;

/**
 * Servlet implementation class SimpleBoardController
 */
@WebServlet("/simpleBoard.un")
public class SimpleBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleBoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int listCount; // 총 게시글 갯수를 담아줄 변수
		int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
		int listLimit; // 한 페이지에 보여질 게시글 개수
		int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바 끝 수

		pageLimit = 5;
		listLimit = 5;
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = (startPage + pageLimit) - 1;
		
		String type = request.getParameter("type");
		
		if(type.equals("unionBoard")) {
		
			listCount = new UnionBoardService().selectListCount();
			maxPage = (int)Math.ceil(((double)listCount/listLimit));
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
			ArrayList<UnionBoard> bList = new UnionBoardService().simpleBoardList(p);
			
			response.setContentType("json/application;charset=UTF-8");
			Gson gson = new Gson();
			gson.toJson(bList, response.getWriter());
		}
		
		if(type.equals("popularBoard")) {
			
			listCount = new UnionBoardService().popularBoardListCount();
			maxPage = (int)Math.ceil(((double)listCount/listLimit));
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
			ArrayList<UnionBoard> pList = new UnionBoardService().selectPopularBoardList(p);
			
			response.setContentType("json/application;charset=UTF-8");
			Gson gson = new Gson();
			gson.toJson(pList, response.getWriter());
		}
		
		if(type.equals("announcement")) {
			
			listCount = new UnionBoardService().selectAnnounceCount();
			maxPage = (int)Math.ceil(((double)listCount/listLimit));
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
			ArrayList<UnionBoard> aList = new UnionBoardService().announceBoardList(p);
			
			response.setContentType("json/application;charset=UTF-8");
			Gson gson = new Gson();
			gson.toJson(aList, response.getWriter());
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
