package com.kh.request.model.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.request.model.vo.Answer;
import com.kh.request.model.vo.Request;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class RequestSelectController
 */
@WebServlet("/requestList.rq")
public class RequestDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDetailController() {
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
		
		int requestNo = Integer.parseInt(request.getParameter("requestNo"));
		String userNo = request.getParameter("userNo");
		
		Request r = new Request();
		r.setRequestNo(requestNo);
		r.setRequestWriter(userNo);
		
		Answer answer = new UserService().selectAnswer(requestNo);
		
		Request req = new UserService().requestList(r);
		req.setRequestNo(requestNo);
		
		HashMap<String, Object> map = new HashMap<>();
		map.put("answer", answer);
		map.put("req", req);
		
		response.setContentType("json/application;charset=UTF-8");
		Gson gson = new Gson();
		gson.toJson(map, response.getWriter());
	}

}
