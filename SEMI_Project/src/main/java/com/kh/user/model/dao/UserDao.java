package com.kh.user.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.request.model.vo.Request;
import com.kh.user.model.vo.MyItems;
import com.kh.user.model.vo.UserInfo;
import com.kh.user.model.vo.WorkoutList;

public class UserDao {

	private Properties pro = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public UserDao() {
		
		String filePath = UserDao.class.getResource("/resources/mappers/user-mapper.xml").getPath();
		
		try {
			pro.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public UserInfo selectMyPage(Connection con, String userId) {
		
		UserInfo user = null;
		String select = pro.getProperty("selectMyPage");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, userId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				user = new UserInfo(rset.getInt("USER_NO"),
									rset.getString("USER_ID"),
									rset.getString("USER_NAME"),
									rset.getString("NICKNAME"),
									rset.getString("GENDER"),
									rset.getString("PHONE"),
									rset.getString("EMAIL"),
									rset.getString("ADDRESS"),
									rset.getString("GRADE_NAME"),
									rset.getInt("POINT"),
									rset.getDate("ENROLL_DATE"),
									rset.getDate("MODIFY_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return user;
	}

	public ArrayList<WorkoutList> selectWorkoutList(Connection con, int userNo, PageInfo p1) {
		
		ArrayList<WorkoutList> wList = new ArrayList<>();
		String select = pro.getProperty("selectWorkoutList");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p1.getCurrentPage() - 1) * p1.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p1.getCurrentPage() * p1.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				wList.add(new WorkoutList(rset.getString("WORKOUT_TITLE"),
										  rset.getString("WORKOUT_CONTENT"),
										  rset.getString("WORKOUT_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return wList;
	}

	public ArrayList<MyItems> selectMyItems(Connection con, int userNo) {
		
		ArrayList<MyItems> iList = new ArrayList<>();
		String select = pro.getProperty("selectMyItems");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				iList.add(new MyItems(rset.getInt("PRO_NO"),
									  rset.getString("PRO_NAME"),
									  rset.getInt("PRO_COUNT")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return iList;
	}

	public ArrayList<Request> selectRequest(Connection con, int userNo, PageInfo p2) {
		
		ArrayList<Request> rList = new ArrayList<>();
		String select = pro.getProperty("selectRequest");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p2.getCurrentPage() - 1) * p2.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p2.getCurrentPage() * p2.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				rList.add(new Request(rset.getInt("RQ_NO"),
									  rset.getString("RQ_TITLE"),
									  rset.getString("RQ_CONTENT"),
									  rset.getString("USER_NAME"),
									  rset.getString("RQ_DATE"),
									  rset.getString("CHECK_STATUS")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rList;
	}

	public int countWorkoutList(Connection con, int userNo) {
		
		int result = 0;
		String select = pro.getProperty("countWorkoutList");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectLoginCount(Connection con, int userNo) {
		
		int result = 0;
		String select = pro.getProperty("selectLoginCount");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("LOGIN_COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int countRequestList(Connection con, int userNo) {
		
		int result = 0;
		String select = pro.getProperty("countRequestList");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int countMyItemList(Connection con, int userNo) {
		
		int result = 0;
		String select = pro.getProperty("countMyItemList");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertRequest(Connection con, Request r) {
		
		int result = 0;
		String select = pro.getProperty("insertRequest");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, r.getRequestTitle());
			pstmt.setString(2, r.getRequestContent());
			pstmt.setInt(3, Integer.parseInt(r.getRequestWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public Request requestList(Connection con, Request r) {
		
		Request req = null;
		String select = pro.getProperty("requestList");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, r.getRequestNo());
			pstmt.setInt(2, Integer.parseInt(r.getRequestWriter()));
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				req = new Request(rset.getString("RQ_TITLE"),
								  rset.getString("RQ_CONTENT"),
								  rset.getString("NICKNAME"),
								  rset.getString("RQ_DATE"),
								  rset.getString("CHECK_STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return req;
	}

	public int RequestDelete(Connection con, Request r) {
		
		int result = 0;
		String delete = pro.getProperty("RequestDelete");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, r.getRequestNo());
			pstmt.setInt(2, Integer.parseInt(r.getRequestWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteItems(Connection con, MyItems item) {
		
		int result = 0;
		String delete = pro.getProperty("deleteItems");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, item.getProNo());
			pstmt.setInt(2, item.getBuyerNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteWorkout(Connection con, WorkoutList workout) {
		
		int result = 0;
		String delete = pro.getProperty("deleteWorkout");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, workout.getUserNo());
			pstmt.setString(2, workout.getWorkoutTitle());
			pstmt.setString(3, workout.getWorkoutContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}	
}
