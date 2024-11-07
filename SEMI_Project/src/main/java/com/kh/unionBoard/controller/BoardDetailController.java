package com.kh.unionBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.unionBoard.model.vo.UnionBoard;
import com.kh.user.model.service.UserService;

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
		ArrayList<Reply> replyList = service.selectReply(boardNo);
		
		// 게시판 정보가 있는 경우
		if(ub != null && result > 0) {
			
			request.setAttribute("replyList", replyList);
			request.setAttribute("userNo", userNo);
			request.setAttribute("nickname", nickname);
			request.setAttribute("ub", ub);
			MediaFile file = new UnionBoardService().selectImgFile(boardNo);
			
			// 게시판 정보가 있지만 사진은 있는 경우
			if(file != null) {
				request.setAttribute("file", file);
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
