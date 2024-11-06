package com.kh.unionBoard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.UnionBoard;

/**
 * Servlet implementation class UnionBoardDetailController
 */
@WebServlet("/UnionBoardDetailController.do")
public class UnionBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UnionBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //get방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		
		
		UnionBoardService service = new UnionBoardService();
		
		
		
		int result = service.increaseCount(bno);
		
		if(result>0) {
			
			
		}else {
			
			request.getSession().setAttribute("alertMsg", "조회실패");
			response.sendRedirect(request.getContextPath()+"/UnionBoardDetailController.do?currentPage=1");
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
