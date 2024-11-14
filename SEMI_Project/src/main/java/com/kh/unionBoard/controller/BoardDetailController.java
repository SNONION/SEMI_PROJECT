package com.kh.unionBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.PageInfo;
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.unionBoard.model.vo.UnionBoard;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.UserInfo;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/boardDetailView.un")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnionBoardService service = new UnionBoardService();
		HttpSession session = request.getSession();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		UnionBoard ub = service.selectDetailBoard(boardNo);
		int result = service.updateClickCount(boardNo);
		String nickname = new UserService().selectNickname(userNo);
		
		int recommend = new UnionBoardService().selectRecomCount(ub.getBoardNo());
		ub.setRecommend(recommend);
		
		// 현재 로그인한 유저
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		String loginNickname = new UserService().selectNickname(loginUser.getUserNo());
		
		int currentPage; // 현재 페이지 정보 (몇번 페이지인지)
		
		int rListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
		int rpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
		int rListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
		int rmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
		int rstartPage; // 페이지 하단에 보여질 페이징바 시작 수
		int rendPage; // 페이지 하단에 보여질 페이징바 끝 수
		
		currentPage = 1;
		
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
		
		// 게시판 정보가 있는 경우s
		if(ub != null && result > 0) {
			
			request.setAttribute("loginNickname", loginNickname);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("p2", p2);
			request.setAttribute("replyList", replyList);
			request.setAttribute("userNo", userNo);
			request.setAttribute("nickname", nickname);
			request.setAttribute("ub", ub);
			ArrayList<MediaFile> mediaList = service.selectMediaFile(boardNo);
			
			// 게시판 정보가 있지만 사진은 있는 경우
			if(mediaList != null) {
				request.setAttribute("mediaList", mediaList);
			}
			// 게시판 정보와 사진이 둘다 없는 경우
			else {
				request.setAttribute("noFile", "저장된 파일이 없습니다.");
			}
		}
		else {
			session.setAttribute("alertMsg", "요청 오류");
		}
		request.getRequestDispatcher("/views/unionBoard/boardDetailView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
