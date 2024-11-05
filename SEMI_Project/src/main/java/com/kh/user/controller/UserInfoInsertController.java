package com.kh.user.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UserInfoInsertController
 */
@WebServlet("/enrollUser.us")
public class UserInfoInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserInfoInsertController() {
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
		
		// 데이터를 받아옴 -> request.getParameter();
		
		// UserInfo 테이블에 위에서 받아온 회원정보를 삽입하는 기능 insertUserInfo();
		
//		var inputId = $("#inputId").val();
//		var userPwd = $("#inputPwd").val();
//		var doubleCheckPwd = $("#doubleCheckPwd").val();
//		var userName = $("#userName").val();
//		var nickname = $("#nickname").val();
//		var gender = $("input[name=gender]:checked").val();
//		var phoneFront = $("#phoneFront").val();
//		var phoneMiddle = $("#phoneMiddle").val();
//		var phoneLast = $("#phoneLast").val();
//		var phone = phoneFront + "-" + phoneMiddle + "-" + phoneLast;
//		var email = $("#email").val();
//		var address = $("#address").val();
		
		// 회원정보 insert 성공시 if문 통과
		// if문 안에서 TB_MYITEMS에 회원번호(USER_NO)를 들고가서 TB_LOGINCOUNT에 insert 구문 INSERT INTO TB_LOGINCOUNT(USER_NO) VALUES(?)
		
		// 둘다 성공시 메인페이지로 복귀
	}

}
