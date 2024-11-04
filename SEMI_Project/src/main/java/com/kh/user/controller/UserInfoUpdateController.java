package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.UserInfo;

/**
 * Servlet implementation class UserInfoUpdateController
 */
@WebServlet("/updateUserInfoPage.us")
public class UserInfoUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 기능 추가 후 session.getParameter로 받아오기
		String userId = request.getParameter("userId");
		UserInfo user = new UserService().selectMyPage(userId);
		
		request.setAttribute("user", user); 
		request.getRequestDispatcher("/views/user/updateUserEnrollForm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		UserInfo user = new UserInfo(userId, userName, nickname, gender, phone, email, address);
		int checkNickname = new UserService().checkNickname(nickname);
		HttpSession session = request.getSession();
		
		if(checkNickname == 0) {
			
			int result = new UserService().updateUserInfo(user);
			
			if(result > 0) {
				session.setAttribute("alertMsg", "회원정보가 수정되었습니다.");
			}
			else {
				session.setAttribute("alertMsg", "요청 오류");
			}
	
		}
		else {
			session.setAttribute("alertMsg", "닉네임 중복을 확인해주세요.");
		}
		
		request.getRequestDispatcher("/mypage.us").forward(request, response);
	}

}
