package com.kh.user.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class UpdatePwdController
 */
@WebServlet("/updatePwd.us")
public class UpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdController() {
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
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String newPwd = request.getParameter("newPwd");
		
		HashMap<String, String> map = new HashMap<>();
		map.put("userId", userId);
		map.put("userPwd", userPwd);
		map.put("newPwd", newPwd);
		
		int result = new UserService().updatePwd(map);
		
		HttpSession session = request.getSession();
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "비밀번호가 변경되었습니다.";
		}
		else {
			alertMsg = "비밀번호를 다시 확인해주세요.";
		}
		
		session.setAttribute("alertMsg", alertMsg);
		request.getRequestDispatcher("/mypage.us").forward(request, response);
	}

}
