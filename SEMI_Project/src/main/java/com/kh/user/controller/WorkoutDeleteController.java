package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.WorkoutList;

/**
 * Servlet implementation class WorkoutDeleteController
 */
@WebServlet("/deleteWorkout.us")
public class WorkoutDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WorkoutDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String workoutTitle = request.getParameter("workoutTitle");
		String workoutContent = request.getParameter("workoutContent");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
		WorkoutList workout = new WorkoutList(userNo, workoutTitle, workoutContent);
		
		int result = new UserService().deleteWorkout(workout);
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "운동기록이 삭제되었습니다.";
		}
		else {
			alertMsg = "요청 오류";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(alertMsg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
