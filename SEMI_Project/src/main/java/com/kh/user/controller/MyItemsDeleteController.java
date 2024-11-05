package com.kh.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.user.model.service.UserService;
import com.kh.user.model.vo.MyItems;

/**
 * Servlet implementation class MyItemsDeleteController
 */
@WebServlet("/deleteItem.us")
public class MyItemsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyItemsDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		
		MyItems item = new MyItems();
		item.setProNo(proNo);
		item.setBuyerNo(userNo);
		
		int result = new UserService().deleteItems(item);
		
		String alertMsg = "";
		if(result > 0) {
			alertMsg = "아이템이 사용되었습니다. (새로고침)";
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
