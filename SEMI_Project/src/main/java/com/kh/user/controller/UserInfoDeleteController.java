package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class UserInfoDeleteController
 */
@WebServlet("/deleteUser.us")
public class UserInfoDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoDeleteController() {
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
		
		String userId = request.getParameter("userId");
		
		int result = new UserService().deleteUser(userId);
		
		HttpSession session = request.getSession();
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "회원 탈퇴가 처리되었습니다.";
		}
		else {
			alertMsg = "요청 오류";
		}
		
		session.setAttribute("alertMsg", alertMsg);
		request.getRequestDispatcher("/").forward(request, response);
		
	}

}
