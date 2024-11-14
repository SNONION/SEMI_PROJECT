package com.kh.unionBoard.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.Category;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.UnionBoard;

/**
 * Servlet implementation class UpdateFormController
 */
@WebServlet("/updateBoard.un")
public class UpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnionBoardService service = new UnionBoardService();
		String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date()); 
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		
		UnionBoard ub = service.selectDetailBoard(boardNo);
		ArrayList<MediaFile> mediaList = service.selectMediaFile(boardNo);
		ArrayList<Category> cList = new UnionBoardService().selectCategory();
		
		request.setAttribute("ub", ub);
		request.setAttribute("date", date);
		request.setAttribute("cList", cList);
		request.setAttribute("boardNo", boardNo);
		request.setAttribute("mediaList", mediaList);
		request.getRequestDispatcher("/views/unionBoard/boardUpdateFormView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
