package com.kh.unionBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.unionBoard.model.service.UnionBoardService;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/deleteBoard.un")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		int result = new UnionBoardService().deleteBoard(boardNo);
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "게시글이 삭제되었습니다.";
		}
		else {
			alertMsg = "처리 오류";
		}
		
		session.setAttribute("alertMsg", alertMsg);
		request.getRequestDispatcher("/unionBoardListView.un?currentPage=1").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
