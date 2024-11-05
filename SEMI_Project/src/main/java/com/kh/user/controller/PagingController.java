package com.kh.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.common.model.vo.PageInfo;
import com.kh.request.model.vo.Request;
import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.WorkoutList;

/**
 * Servlet implementation class PagingController
 */
@WebServlet("/paging.us")
public class PagingController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PagingController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserService service = new UserService();
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		
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
		
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		rListCount = service.countRequestList(userNo);
		rpageLimit = 5;
		rListLimit = 5;
		rmaxPage = (int) Math.ceil((double)rListCount / rListLimit);
		rstartPage = (currentPage - 1) / rpageLimit * rpageLimit + 1;
		rendPage = (rstartPage + rpageLimit) - 1;
		
		wListCount = service.countWorkoutList(userNo);
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
		
		ArrayList<WorkoutList> wList = service.selectWorkoutList(userNo, p1);
		ArrayList<Request> rList = service.selectRequest(userNo, p2);
		
		response.setContentType("json/application;charset=UTF-8");
		Gson gson = new Gson();
		
		if(request.getParameter("type").equals("workout")) {			
			gson.toJson(wList, response.getWriter());
		}
		else if(request.getParameter("type").equals("request")) {
			gson.toJson(rList, response.getWriter());
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
