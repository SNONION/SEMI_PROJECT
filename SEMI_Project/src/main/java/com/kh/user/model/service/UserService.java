package com.kh.user.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.request.model.vo.Answer;
import com.kh.request.model.vo.Request;
import com.kh.user.model.dao.UserDao;
import com.kh.user.model.vo.LoginCount;
import com.kh.user.model.vo.MyItems;
import com.kh.user.model.vo.UserInfo;
import com.kh.user.model.vo.WorkoutList;

public class UserService {
	
	private UserDao dao = new UserDao();

	// 마이페이지에 회원 아이디로 회원의 정보를 가져오는 메소드
	public UserInfo selectMyPage(String userId) {
		
		Connection con = JDBCTemplate.getConnection();
		
		UserInfo user = dao.selectMyPage(con, userId);
		
		JDBCTemplate.close(con);
		
		return user;
	}

	// 회원번호로 운동 기록을 가져오는 메소드
	public ArrayList<WorkoutList> selectWorkoutList(int userNo, PageInfo p1) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<WorkoutList> wList = dao.selectWorkoutList(con, userNo, p1);
		
		JDBCTemplate.close(con);
		
		return wList;
	}

	public ArrayList<MyItems> selectMyItems(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<MyItems> iList = dao.selectMyItems(con, userNo);
		
		JDBCTemplate.close(con);
		
		return iList;
	}

	public ArrayList<Request> selectRequest(int userNo, PageInfo p2) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Request> rList = dao.selectRequest(con, userNo, p2);
		
		JDBCTemplate.close(con);
		
		return rList;
	}

	public int countWorkoutList(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.countWorkoutList(con, userNo);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int selectLoginCount(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.selectLoginCount(con, userNo);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int countRequestList(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.countRequestList(con, userNo);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int countMyItemList(int userNo) {

		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.countMyItemList(con, userNo);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int insertRequest(Request r) {

		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.insertRequest(con, r);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public Request requestList(Request r) {
		
		Connection con = JDBCTemplate.getConnection();
		
		Request req = dao.requestList(con, r);
		
		JDBCTemplate.close(con);
		
		return req;
	}

	public int RequestDelete(Request r) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.RequestDelete(con, r);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int deleteItems(MyItems item) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.deleteItems(con, item);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int deleteWorkout(WorkoutList workout) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.deleteWorkout(con, workout);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public Answer selectAnswer(int requestNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		Answer answer = dao.selectAnswer(con, requestNo);
		
		JDBCTemplate.close(con);
		
		return answer;
	}

	public int updateUserInfo(UserInfo user) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.updateUserInfo(con, user);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int checkNickname(String nickname) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.checkNickname(con, nickname);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int updatePwd(HashMap<String, String> map) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.updatePwd(con, map);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int deleteUser(String userId) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.deleteUser(con, userId);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public UserInfo loginUser(String userId, String userPwd) {
		
		Connection con = JDBCTemplate.getConnection();
		
		UserInfo user = dao.loginUser(con, userId, userPwd);
		
		JDBCTemplate.close(con);
		
		return user;
	}

	public LoginCount LoginCountInfo(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		LoginCount lc = dao.LoginCountInfo(con, userNo);
		
		JDBCTemplate.close(con);
		
		return lc;
	}

	public int updateAllLoginCount(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.updateAllLoginCount(con, userNo);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int updateOnlyLoginCount(int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.updateOnlyLoginCount(con, userNo);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int checkUserId(String userId) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.checkUserId(con, userId);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int checkUserNickname(String nickname) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.checkUserNickname(con, nickname);
		
		JDBCTemplate.close(con);
		
		return result;
	}

}
