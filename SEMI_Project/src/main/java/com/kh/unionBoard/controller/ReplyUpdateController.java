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
import com.kh.unionBoard.model.vo.Reply;

/**
 * Servlet implementation class ReplyUpdateController
 */
@WebServlet("/replyUpdate.un")
public class ReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnionBoardService service = new UnionBoardService();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int currentPage; // 현재 페이지 정보 (몇번 페이지인지)
		
		int rListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
		int rpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
		int rListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
		int rmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
		int rstartPage; // 페이지 하단에 보여질 페이징바 시작 수
		int rendPage; // 페이지 하단에 보여질 페이징바 끝 수
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		rListCount = service.selectReplyCount(boardNo);
		rpageLimit = 5;
		rListLimit = 15;
		rmaxPage = (int) Math.ceil((double)rListCount / rListLimit);
		rstartPage = (currentPage - 1) / rpageLimit * rpageLimit + 1;
		rendPage = (rstartPage + rpageLimit) - 1;
		
		if(rmaxPage < rendPage) {
			rendPage = rmaxPage;
		}
		
		PageInfo p2 = new PageInfo(rListCount, currentPage, rpageLimit, rListLimit, rmaxPage, rstartPage, rendPage);
		
		ArrayList<Reply> replyList = service.selectReply(boardNo, p2);
		
		response.setContentType("json/application;charset=UTF-8");
		Gson gson = new Gson();
		gson.toJson(replyList, response.getWriter());
	}

}
