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

	public ArrayList<UnionBoard> selectTitleKeyBoard(String keyword, PageInfo p) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<UnionBoard> bList = dao.selectTitleKeyBoard(con, keyword, p);
		
		JDBCTemplate.close(con);
		
		return bList;
	}

	public int selectKeyBoardCount(String keyword) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int count = dao.selectKeyBoardCount(con, keyword);
		
		JDBCTemplate.close(con);
		
		return count;
	}

	public int selectCateKeyBoardCount(String keyword, int cateNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int count = dao.selectCateKeyBoardCount(con, keyword, cateNo);
		
		JDBCTemplate.close(con);
		
		return count;
	}

	public ArrayList<UnionBoard> selectCateTitleKeyBoard(String keyword, int cateNo, PageInfo p) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<UnionBoard> bList = dao.selectCateTitleKeyBoard(con, keyword, cateNo, p);
		
		JDBCTemplate.close(con);
		
		return bList;
	}

	public int insertBoard(UnionBoard ub, ArrayList<MediaFile> mList) {
		
		Connection con = JDBCTemplate.getConnection();
		
		// 게시물 번호를 먼저 생성해 가져온다.
		int boardNo = dao.selectBoardNo(con);
		
		// 각 게시물 삽입 성공여부를 담을 변수들
		int result1 = 0;
		int result2 = 1; // 없을 경우에는 1이 그대로 출력
		
		if(boardNo > 0) {
			
			// 위에서 가져온 게시물 번호로 게시물을 insert
			result1 = dao.insertBoard(con, boardNo, ub);
			
			if(result1 > 0) {
				
				// 게시물에 사진이 같이 있는 경우
				if(mList != null) {
					for(MediaFile file : mList) {
						result2 = dao.insertMedia(con, boardNo, file);
					}
				}
			}
		}
		
		int result = result1 * result2;
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
		
		return result;

	}

	public ArrayList<MediaFile> selectMediaFile(int boardNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<MediaFile> mList = dao.selectMediaFile(con, boardNo);
		
		JDBCTemplate.close(con);
		
		return mList;
	}

	public int insertWorkout(UnionBoard ub) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = dao.insertWorkout(con, ub);
		
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
