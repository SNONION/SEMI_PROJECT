package com.kh.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.common.model.vo.PageInfo;
import com.kh.request.model.vo.Request;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.MyItems;
import com.kh.user.model.vo.UserInfo;
import com.kh.user.model.vo.WorkoutList;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/mypage.us")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyPageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserService service = new UserService();
		
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
		
		if(loginUser != null) {
			// 회원 정보를 가져옴
			UserInfo user = service.selectMyPage(loginUser.getUserId());
			
			int loginCount = service.selectLoginCount(user.getUserNo());
			
			int currentPage; // 현재 페이지 정보 (몇번 페이지인지)
			
			int rListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
			int rpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int rListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
			int rmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int rstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int rendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			int wListCount; // 운동기록 총 게시글 갯수를 담아줄 변수
			int wpageLimit; // 페이지 하단에 보여질 페이징바 최대 갯수
			int wListLimit; // 운동기록에서 한 페이지에 보여질 게시글 개수
			int wmaxPage; // 가장 마지막 페이지는 몇번째 페이지 인지 (총 페이지 갯수)
			int wstartPage; // 페이지 하단에 보여질 페이징바 시작 수
			int wendPage; // 페이지 하단에 보여질 페이징바 끝 수
			
			currentPage = 1;
			
			rListCount = service.countRequestList(user.getUserNo());
			rpageLimit = 5;
			rListLimit = 5;
			rmaxPage = (int) Math.ceil((double)rListCount / rListLimit);
			rstartPage = (currentPage - 1) / rpageLimit * rpageLimit + 1;
			rendPage = (rstartPage + rpageLimit) - 1;
			
			wListCount = service.countWorkoutList(user.getUserNo());
			wpageLimit = 5;
			wListLimit = 5;
			wmaxPage = (int) Math.ceil((double)wListCount / wListLimit);
			wstartPage = (currentPage - 1) / wpageLimit * wpageLimit + 1;
			wendPage = (wstartPage + wpageLimit) - 1;
			
			if(wmaxPage < wendPage) {
				wendPage = wmaxPage;
			}
			
			if(rmaxPage < rendPage) {
				rendPage = rmaxPage;
			}
			
			PageInfo p1 = new PageInfo(wListCount, currentPage, wpageLimit, wListLimit, wmaxPage, wstartPage, wendPage); // 운동기록용
			PageInfo p2 = new PageInfo(rListCount, currentPage, rpageLimit, rListLimit, rmaxPage, rstartPage, rendPage); // 문의글용
			
			// 운동 기록을 가져옴
			ArrayList<WorkoutList> wList = service.selectWorkoutList(user.getUserNo(), p1);
			
			for(WorkoutList wl : wList) {
				String str = wl.getWorkoutContent().replace("\n","<br>");
				wl.setWorkoutContent(str);
			}
	
			// 내가 작성한 문의글 내역을 가져옴
			ArrayList<Request> rList = service.selectRequest(user.getUserNo(), p2);
	
			// 내가 구매한 아이템 목록 가져옴
			ArrayList<MyItems> iList = service.selectMyItems(user.getUserNo());
			
			request.setAttribute("loginCount", loginCount);
			request.setAttribute("p1", p1);
			request.setAttribute("p2", p2);
			request.setAttribute("user", user);
			request.setAttribute("wList", wList);
			request.setAttribute("iList", iList);
			request.setAttribute("rList", rList);
			request.getRequestDispatcher("/views/user/mypage.jsp").forward(request, response);
		}
		else {
			session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스 입니다.");
			response.sendRedirect(request.getContextPath());
		}
	}

}
