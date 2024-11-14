package com.kh.unionBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.UnionBoard;

/**
 * Servlet implementation class WorkoutInsertController
 */
@WebServlet("/insertWorkout.un")
public class WorkoutInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutInsertController() {
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
		
		HttpSession session = request.getSession();
		
		String userNo = request.getParameter("userNo");
		String workoutTitle = request.getParameter("workoutTitle");
		String workoutContent = request.getParameter("workoutContent");
		
		UnionBoard ub = new UnionBoard();
		ub.setBoardWriter(userNo);
		ub.setBoardTitle(workoutTitle);
		ub.setBoardContent(workoutContent);
		
		int result = new UnionBoardService().insertWorkout(ub);
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "정상적으로 기록되었습니다.";
		}
		else {
			alertMsg = "요청 오류";
		}
		
		session.setAttribute("alertMsg", alertMsg);
		request.getRequestDispatcher("/mypage.us").forward(request, response);
	}

}
