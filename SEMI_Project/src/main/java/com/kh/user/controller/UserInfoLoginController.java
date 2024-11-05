package com.kh.user.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.LoginCount;
import com.kh.user.model.vo.UserInfo;

/**
 * Servlet implementation class UserInfoLoginController
 */
@WebServlet("/loginUserInfo.us")
public class UserInfoLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoLoginController() {
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
		String userPwd = request.getParameter("userPwd");
		
		UserInfo loginUser = new UserService().loginUser(userId,userPwd);
		
		HttpSession session = request.getSession();
		
		if(loginUser != null) {
			LoginCount lc = new UserService().LoginCountInfo(loginUser.getUserNo());
			
			// 편의를 위해 날짜 형식변경
			String sqlDate = new SimpleDateFormat("yyyyMMdd").format(lc.getLoginDate()); 
			String javaDate = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
			
			// lc에 저장된 날짜와 date로 자바에서 꺼내온 날짜가 일치할때 => 로그인 횟수 그대로
			// lc에 저장된 날짜와 date로 자바에서 꺼내온 날짜가 1일 차이 날때 => 로그인 횟수 / 연속 로그인 횟수 1씩 증가
			// lc에 저장된 날짜와 date로 자바에서 꺼내온 날짜가 1보다 많이 차이날때 => 로그인 횟수는 증가 / 연속 로그인 횟수는 1로 초기화
			
			// 위에서 변경된 형식을 int형으로 변경하여 계산
			int date = Integer.parseInt(javaDate) - Integer.parseInt(sqlDate);
			
			if(date == 1) {
				// 출력할 필요가 없으니 따로 변수에 담을 필요가 없다.
				new UserService().updateAllLoginCount(loginUser.getUserNo());
			}
			else if(date > 1){
				new UserService().updateOnlyLoginCount(loginUser.getUserNo());
			}
			
			session.setAttribute("loginUser", loginUser);
			session.setAttribute("alertMsg", "환영합니다.");
			
		}else {
			session.setAttribute("alertMsg", "일치하는 회원 정보가 없습니다.");
		}

		response.sendRedirect(request.getContextPath());
	}

}
