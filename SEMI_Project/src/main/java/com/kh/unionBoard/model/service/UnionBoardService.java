package com.kh.unionBoard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.unionBoard.model.dao.UnionBoardDao;
import com.kh.unionBoard.model.vo.Category;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.unionBoard.model.vo.UnionBoard;

public class UnionBoardService {

	private UnionBoardDao dao = new UnionBoardDao();
	
	public int selectListCount() {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.selectListCount(con);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public ArrayList<UnionBoard> selectBoardList(PageInfo p) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<UnionBoard> bList = dao.selectBoardList(con, p);
		
		JDBCTemplate.close(con);
		
		return bList;
	}

	public int selectRecomCount(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int recommend = dao.selectRecomCount(con, boardNo);
		
		JDBCTemplate.close(con);
		
		return recommend;
	}

	public int cateBoardCount(int cateNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int count = dao.cateBoardCount(con, cateNo);
		
		JDBCTemplate.close(con);
		
		return count;
	}

	public ArrayList<UnionBoard> selectCateBoardList(int cateNo, PageInfo p) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<UnionBoard> list = dao.selectCateBoardList(con, cateNo, p);
		
		JDBCTemplate.close(con);
		
		return list;
	}

	public UnionBoard selectDetailBoard(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		UnionBoard ub = dao.selectDetailBoard(con, boardNo);
		
		JDBCTemplate.close(con);
		
		return ub;
	}

	public MediaFile selectImgFile(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		MediaFile file = dao.selectImgFile(con, boardNo);
		
		JDBCTemplate.close(con);
		
		return file;
	}

	public ArrayList<Category> selectCategory() {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Category> cList = dao.selectCategory(con);
		
		JDBCTemplate.close(con);
		
		return cList;
	}

	public int updateClickCount(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.updateClickCount(con, boardNo);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int insertRecomCount(int userNo, int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.insertRecomCount(con, userNo, boardNo);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public int checkDupRecom(int userNo, int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.checkDupRecom(con, userNo, boardNo);
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int deleteRecomCount(int userNo, int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.deleteRecomCount(con, userNo, boardNo);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

	public ArrayList<Reply> selectReply(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Reply> replyList = dao.selectReply(con, boardNo);
		
		JDBCTemplate.close(con);
		
		return replyList;
	}

	public int insertReply(Reply reply) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.insertReply(con, reply);
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;
	}

}
