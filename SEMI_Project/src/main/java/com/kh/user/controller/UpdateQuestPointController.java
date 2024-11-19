package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class UpdateQuestPointController
 */
@WebServlet("/updatePoint.us")
public class UpdateQuestPointController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateQuestPointController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int reward = Integer.parseInt(request.getParameter("reward"));
		int questNo = Integer.parseInt(request.getParameter("questNo"));
		
		int result = new UserService().updateUserPoint(userNo, reward);
		
		String msg = "";
		
		if(result > 0) {
			int questStatus = new UserService().updateQuestStatusN(questNo);
			if(questStatus > 0) {
				msg = "NNNNY";
			}
			else {
				msg = "NNNNN";
			}
		}
		else {
			msg = "NNNNN";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(msg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
