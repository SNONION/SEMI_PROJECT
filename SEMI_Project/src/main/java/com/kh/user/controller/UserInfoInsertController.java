package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.UserInfo;

/**
 * Servlet implementation class UserInfoInsertController
 */
@WebServlet("/enrollUser.us")
public class UserInfoInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoInsertController() {
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
		
		// UserInfo 테이블에 위에서 받아온 회원정보를 삽입하는 기능 insertUserInfo();
		
		String inputId = request.getParameter("inputId");
		String inputPwd = request.getParameter("inputPwd");
		String userName = request.getParameter("userName");
		String nickname = request.getParameter("nickname");
		String gender = request.getParameter("gender");
		String phoneFront = request.getParameter("phoneFront");
		String phoneMiddle = request.getParameter("phoneMiddle");
		String phoneLast = request.getParameter("phoneLast");
		String phone = phoneFront + "-" + phoneMiddle + "-" + phoneLast;
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		UserInfo user = new UserInfo();
		user.setUserId(inputId);
		user.setUserPwd(inputPwd);
	    user.setUserName(userName);
	    user.setNickname(nickname);
	    user.setGender(gender);
	    user.setPhone(phone);
	    user.setEmail(email);
	    user.setAddress(address);
	
		UserService userService = new UserService();
		
		 boolean isRegistered = userService.insertUserInfo(user);

		    if (isRegistered) {
		       
		        response.sendRedirect("/mainpage.jsp");
		    } else {
		        
		        request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
		        request.getRequestDispatcher("signup.jsp").forward(request, response);
		    }
		
		
		
		
		// 회원정보 insert 성공시 if문 통과
		// if문 안에서 TB_MYITEMS에 회원번호(USER_NO)를 들고가서 TB_LOGINCOUNT에 insert 구문 INSERT INTO TB_LOGINCOUNT(USER_NO) VALUES(?)
		
		// 둘다 성공시 메인페이지로 복귀
	}

}
