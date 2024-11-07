package com.kh.unionBoard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.Reply;

/**
 * Servlet implementation class ReplyUpdateController
 */
@WebServlet("/replyUpdate.un")
public class ReplyUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UnionBoardService service = new UnionBoardService();
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));
		ArrayList<Reply> replyList = service.selectReply(boardNo);
		
		response.setContentType("json/application;charset=UTF-8");
		Gson gson = new Gson();
		gson.toJson(replyList, response.getWriter());
	}

}
