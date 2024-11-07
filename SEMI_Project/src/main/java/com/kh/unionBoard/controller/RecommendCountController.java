package com.kh.unionBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.unionBoard.model.service.UnionBoardService;

/**
 * Servlet implementation class RecommendCountController
 */
@WebServlet("/recomCountUp.un")
public class RecommendCountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecommendCountController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnionBoardService service = new UnionBoardService();
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		// 이 게시글에 추천을 했는지 확인
		int checkDupl = service.checkDupRecom(userNo, boardNo);
		
		// 추천을 하지 않았다면
		if(checkDupl == 0) {
			// 추천수 +1
			service.insertRecomCount(userNo, boardNo);
		}
		// 추천을 했었다면
		else {
			// 추천수 -1
			service.deleteRecomCount(userNo, boardNo);
		}
		
		int count = service.selectRecomCount(boardNo);
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(count);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
