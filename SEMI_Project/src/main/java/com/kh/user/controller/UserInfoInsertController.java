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
		
		HttpSession session = request.getSession();
		
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
	    
	    // 입력받은 회원 번호 테이블에 insert
	    // 입력하면서 회원 포인트 1포인트 지급 (첫 회원가입 보너스)
	    int UserInsert = new UserService().insertUserInfo(user);
	    
	    // 입력된 회원 정보에서 회원번호 추출
	    int userNo = new UserService().selectUserNo(inputId);
	    
	    // 전달할 메시지를 담아두는 문자열
	    String alertMsg = "";
	    
	    if(UserInsert > 0) {
	    	// insert가 성공했을 경우 loginCount에 회원정보 생성
	    	int insertLoginInfo = new UserService().insertLoginInfo(userNo);
	    	
	    	if(insertLoginInfo > 0) {
	    		// 로그인 횟수 , 연속 로그인 전부 1씩 증가
	    		new UserService().updateAllLoginCount(userNo);
	    		
	    		alertMsg = nickname + "님 가입을 축하드립니다. (1포인트 지급)";
	    	}
	    }
	    else {
	    	alertMsg = "회원가입이 정상적으로 처리되지 않았습니다.";
	    }
	    
	    session.setAttribute("alertMsg", alertMsg);
	    response.sendRedirect(request.getContextPath());
	}

}
