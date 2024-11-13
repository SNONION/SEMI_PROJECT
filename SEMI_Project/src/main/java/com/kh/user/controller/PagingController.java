package com.kh.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.request.model.vo.Request;
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.unionBoard.model.vo.UnionBoard;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.UserInfo;
import com.kh.user.model.vo.UserTier;
import com.kh.user.model.vo.WorkoutList;

/**
 * Servlet implementation class PagingController
 */
@WebServlet("/paging.us")
public class PagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = new UserService();
		UnionBoardService uService = new UnionBoardService();
		HttpSession session = request.getSession();
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		String type = request.getParameter("type");
		int userNo = loginUser.getUserNo();
		
		int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재 페이지 정보 (몇번 페이지인지)
		
		response.setContentType("json/application;charset=UTF-8");
		Gson gson = new Gson();
		
		if(type.equals("announcement")) {
			
			int listCount; // 총 게시글 갯수를 담아줄 변수
			int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int listLimit; // 한 페이지에 보여질 게시글 개수
			int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int startPage; // 페이지 하단에 보여질 페이징바 시작 수
			int endPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
			listCount = new UnionBoardService().selectAnnounceCount();
			pageLimit = 10;
			listLimit = 15;
			maxPage = (int)Math.ceil(((double)listCount/listLimit));
			startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			endPage = (startPage + pageLimit) - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo p = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
			ArrayList<UnionBoard> bList = new UnionBoardService().announceBoardList(p);
			
			for(UnionBoard u : bList) {
				
				// 작성자 이미지 가져오는 과정
				UserTier tier = new UnionBoardService().selectTierImg(u.getBoardWriter());
				u.setTierPath(tier.getTierPath());
				u.setTierName(tier.getTierOriginFileName());
			}
			
			gson.toJson(bList, response.getWriter());
		}
		
		if(type.equals("popularBoard")) {
			
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
			ArrayList<UnionBoard> pList = new UnionBoardService().selectPopularBoardList(p);
			
			for(UnionBoard u : pList) {
				
				// 작성자 이미지 가져오는 과정
				UserTier tier = uService.selectTierImg(u.getBoardWriter());
				
				u.setTierPath(tier.getTierPath());
				u.setTierName(tier.getTierOriginFileName());
			}
			
			gson.toJson(pList, response.getWriter());
		}
		
		if(type.equals("reply")) {
			
			int boardNo = Integer.parseInt(request.getParameter("boardNo"));
			
			int blistCount; // 총 게시글 갯수를 담아줄 변수
			int bpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int blistLimit; // 한 페이지에 보여질 게시글 개수
			int bmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int bstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int bendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			blistCount = uService.selectReplyCount(boardNo);
			bpageLimit = 5;
			blistLimit = 15;
			bmaxPage = (int)Math.ceil(((double)blistCount/blistLimit));
			bstartPage = (currentPage - 1) / bpageLimit * bpageLimit + 1;
			bendPage = (bstartPage + bpageLimit) - 1;
			
			if(bmaxPage < bendPage) {
				bendPage = bmaxPage;
			}

			PageInfo p3 = new PageInfo(blistCount, currentPage, bpageLimit, blistLimit, bmaxPage, bstartPage, bendPage); // 게시판 페이지용
			ArrayList<Reply> replyList = uService.selectReply(boardNo, p3);
			
			gson.toJson(replyList,response.getWriter());
		}
		
		if(type.equals("keywordBoard")) {
			
			String keyword = request.getParameter("keyword");
			
			int listCount; // 총 게시글 갯수를 담아줄 변수
			int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int listLimit; // 한 페이지에 보여질 게시글 개수
			int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int startPage; // 페이지 하단에 보여질 페이징바 시작 수
			int endPage; // 페이지 하단에 보여질 페이징바 끝 수
			int cateNo = 0;
			
			if(request.getParameter("cateNo") != null) {
				cateNo = Integer.parseInt(request.getParameter("cateNo"));
				listCount = new UnionBoardService().selectCateKeyBoardCount(keyword, cateNo);
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
				bList = new UnionBoardService().selectCateTitleKeyBoard(keyword, cateNo, p);	
			}
			else {
				bList = new UnionBoardService().selectTitleKeyBoard(keyword, p);	
			}
			
			for(UnionBoard u : bList) {
				int recommend = uService.selectRecomCount(u.getBoardNo());
				int replyCount = uService.selectReplyCount(u.getBoardNo());
				u.setReplyCount(replyCount);
				u.setRecommend(recommend);
				
				// 작성자 이미지 가져오는 과정
				UserTier tier = uService.selectTierImg(u.getBoardWriter());
				u.setTierPath(tier.getTierPath());
				u.setTierName(tier.getTierOriginFileName());
			}
			
			gson.toJson(bList, response.getWriter());
		}
		
		if(type.equals("workout")) {
			
			int wListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
			int wpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int wListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
			int wmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int wstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int wendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			wListCount = service.countWorkoutList(userNo);
			wpageLimit = 5;
			wListLimit = 5;
			wmaxPage = (int) Math.ceil((double)wListCount / wListLimit);
			wstartPage = (currentPage - 1) / wpageLimit * wpageLimit + 1;
			wendPage = (wstartPage + wpageLimit) - 1;
			
			if(wmaxPage < wendPage) {
				wendPage = wmaxPage;
			}
			
			PageInfo p1 = new PageInfo(wListCount, currentPage, wpageLimit, wListLimit, wmaxPage, wstartPage, wendPage); // 운동기록용
			ArrayList<WorkoutList> wList = service.selectWorkoutList(userNo, p1);
			
			gson.toJson(wList, response.getWriter());
		}
		if(type.equals("request")) {
			
			int rListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
			int rpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int rListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
			int rmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int rstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int rendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			rListCount = service.countRequestList(userNo);
			rpageLimit = 5;
			rListLimit = 5;
			rmaxPage = (int) Math.ceil((double)rListCount / rListLimit);
			rstartPage = (currentPage - 1) / rpageLimit * rpageLimit + 1;
			rendPage = (rstartPage + rpageLimit) - 1;
			
			if(rmaxPage < rendPage) {
				rendPage = rmaxPage;
			}
			
			PageInfo p2 = new PageInfo(rListCount, currentPage, rpageLimit, rListLimit, rmaxPage, rstartPage, rendPage); // 문의글용
			ArrayList<Request> rList = service.selectRequest(userNo, p2);
			
			gson.toJson(rList, response.getWriter());
		}
		if(type.equals("unionBoard")){
			
			int blistCount; // 총 게시글 갯수를 담아줄 변수
			int bpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int blistLimit; // 한 페이지에 보여질 게시글 개수
			int bmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int bstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int bendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			blistCount = uService.selectListCount();
			bpageLimit = 10;
			blistLimit = 15;
			bmaxPage = (int)Math.ceil(((double)blistCount/blistLimit));
			bstartPage = (currentPage - 1) / bpageLimit * bpageLimit + 1;
			bendPage = (bstartPage + bpageLimit) - 1;
			
			if(bmaxPage < bendPage) {
				bendPage = bmaxPage;
			}

			PageInfo p3 = new PageInfo(blistCount, currentPage, bpageLimit, blistLimit, bmaxPage, bstartPage, bendPage); // 게시판 페이지용
			ArrayList<UnionBoard> bList = new UnionBoardService().selectBoardList(p3);
			
			for(UnionBoard u : bList) {
				int recommend = uService.selectRecomCount(u.getBoardNo());
				int replyCount = uService.selectReplyCount(u.getBoardNo());
				u.setReplyCount(replyCount);
				u.setRecommend(recommend);
					
				// 작성자 이미지 가져오는 과정
				UserTier tier = uService.selectTierImg(u.getBoardWriter());
				u.setTierPath(tier.getTierPath());
				u.setTierName(tier.getTierOriginFileName());
			
			}
			
			gson.toJson(bList,response.getWriter());
		}
		if(type.equals("cateBoard")) {
			
			int listCount = 0; // 총 게시글 갯수를 담아줄 변수
			int pageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int listLimit; // 한 페이지에 보여질 게시글 개수
			int maxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int startPage; // 페이지 하단에 보여질 페이징바 시작 수
			int endPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			int cateNo = 0;
			
			if(Integer.parseInt(request.getParameter("cateNo")) != 0) {
				cateNo = Integer.parseInt(request.getParameter("cateNo"));
				
				listCount = uService.cateBoardCount(cateNo);
			}
			
			pageLimit = 10;
			listLimit = 15;
			maxPage = (int)Math.ceil(((double)listCount/listLimit));
			startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
			endPage = (startPage + pageLimit) - 1;
			
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			
			PageInfo p4 = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
			ArrayList<UnionBoard> list = uService.selectCateBoardList(cateNo, p4);
			
			for(UnionBoard l : list) {
				int recommend = uService.selectRecomCount(l.getBoardNo());
				int replyCount = uService.selectReplyCount(l.getBoardNo());
				l.setReplyCount(replyCount);
				l.setRecommend(recommend);
				
				// 작성자 이미지 가져오는 과정
				UserTier tier = uService.selectTierImg(l.getBoardWriter());
				l.setTierPath(tier.getTierPath());
				l.setTierName(tier.getTierOriginFileName());
			}
			
			gson.toJson(list,response.getWriter());
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
