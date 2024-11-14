package com.kh.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;
import com.kh.user.model.vo.UserInfo;

/**
 * Servlet implementation class UpdateMyItemsController
 */
@WebServlet("/purchProduct.sh")
public class UpdateMyItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateMyItemsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		int price = Integer.parseInt(request.getParameter("proPrice"));
		
		Product pro = new Product();
		pro.setPrice(price);
		pro.setProNo(proNo);
		
		UserInfo loginUser = (UserInfo)session.getAttribute("loginUser");
				
		// 해당 구매자의 보유 포인트가 구매할 제품 포인트와 비교 - (많다면)해당 구매자 포인트 차감 (없다면) 구매실패 메시지
		int result = new ShopService().updateUserPoint(pro, loginUser.getUserNo());
		
		String msg = "";
		
		if(result > 0) {
			msg = "NNNNY";
		}
		else {
			msg = "NNNNN";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(msg);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		
		int result = new ShopService().deleteProduct(proNo);
		
		String msg = "";
		
		if(result > 0) {
			msg = "NNNNY";
		}
		else {
			msg = "NNNNN";
		}
		
		response.setContentType("text.html;charset=UTF-8");
		response.getWriter().print(msg);
	}

}
