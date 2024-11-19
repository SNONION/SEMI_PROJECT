package com.kh.unionBoard.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.user.model.service.UserService;

/**
 * Servlet implementation class ReplyInsertController
 */
@WebServlet("/replyInsert.un")
public class ReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReplyInsertController() {
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
		
		String replyContent = request.getParameter("replyContent");
		int userNo = Integer.parseInt(request.getParameter("userNo"));
		int boardNo = Integer.parseInt(request.getParameter("boardNo"));

		Reply reply = new Reply();
		reply.setUserNo(userNo);
		reply.setRefBno(boardNo);
		reply.setReplyContent(replyContent);
		
		int result1 = new UserService().updateReplyQuest();
		int result2 = new UnionBoardService().insertReply(reply);
		
		int result = result1 * result2;
		
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

}
