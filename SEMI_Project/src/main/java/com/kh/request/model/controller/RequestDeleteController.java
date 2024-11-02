package com.kh.request.model.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.request.model.vo.Request;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class RequestDeleteController
 */
@WebServlet("/deleteRequest.rq")
public class RequestDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userNo = request.getParameter("userNo");
		int requestNo = Integer.parseInt(request.getParameter("requestNo"));
		
		Request r = new Request();
		r.setRequestNo(requestNo);
		r.setRequestWriter(userNo);
		
		int result = new UserService().RequestDelete(r);
		
		String alertMsg = "";
		
		if(result > 0) {
			alertMsg = "요청이 취소되었습니다.";
		}
		else {
			alertMsg = "요청 오류";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(alertMsg);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
