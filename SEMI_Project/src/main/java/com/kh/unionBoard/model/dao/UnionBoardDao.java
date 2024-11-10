package com.kh.unionBoard.model.dao;

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
import com.kh.unionBoard.model.vo.Category;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.Reply;
import com.kh.unionBoard.model.vo.UnionBoard;

public class UnionBoardDao {

	private Properties pro = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public UnionBoardDao() {
		
		String filePath = (UnionBoardDao.class).getResource("/resources/mappers/unionBoard-mapper.xml").getPath();
		
		try {
			pro.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int selectListCount(Connection con) {
		
		int result = 0;
		String select = pro.getProperty("selectListCount");
		
		try {
			pstmt = con.prepareStatement(select);

			rset = pstmt.executeQuery();
			
			if(rset.next()){
				result = rset.getInt("LIST_COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<UnionBoard> selectBoardList(Connection con, PageInfo p) {
		
		ArrayList<UnionBoard> bList = new ArrayList<>();
		String select = pro.getProperty("selectBoardList");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bList.add(new UnionBoard(rset.getInt("BOARD_NO"),
										 rset.getString("CATEGORY_NAME"),
										 rset.getString("BOARD_TITLE"),
										 rset.getString("NICKNAME"),
										 rset.getInt("COUNT"),
										 rset.getString("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public int selectRecomCount(Connection con, int boardNo) {
		
		int recommend = 0;
		String select = pro.getProperty("selectRecomCount");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				recommend = rset.getInt("RECOM_COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return recommend;
	}

	public int cateBoardCount(Connection con, int cateNo) {
		
		int count = 0;
		String select = pro.getProperty("cateBoardCount");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, cateNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("BOARD_COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public ArrayList<UnionBoard> selectCateBoardList(Connection con, int cateNo, PageInfo p) {
		
		ArrayList<UnionBoard> list = new ArrayList<>();
		String select = pro.getProperty("selectCateBoardList");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, cateNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new UnionBoard(rset.getInt("BOARD_NO"),
									    rset.getString("CATEGORY_NAME"),
									    rset.getString("BOARD_TITLE"),
									    rset.getString("NICKNAME"),
									    rset.getInt("COUNT"),
									    rset.getString("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public UnionBoard selectDetailBoard(Connection con, int boardNo) {
		
		UnionBoard ub = null;
		String select = pro.getProperty("selectDetailBoard");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				ub = new UnionBoard(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"),
									rset.getString("BOARD_TITLE"),
									rset.getString("BOARD_CONTENT"),
									rset.getString("NICKNAME"),
									rset.getInt("COUNT"),
									rset.getString("CREATE_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ub;
	}

	public MediaFile selectImgFile(Connection con, int boardNo) {
		
		MediaFile file = null;
		String select = pro.getProperty("selectImgFile");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				file = new MediaFile(rset.getString("FILE_TYPE"),
									 rset.getString("ORIGINFILE_NAME"),
									 rset.getString("CHANGEFILE_NAME"),
									 rset.getString("FILE_PATH"),
									 rset.getDate("UPLOAD_DATE"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return file;
	}

	public ArrayList<Category> selectCategory(Connection con) {
		
		ArrayList<Category> cList = new ArrayList<>();
		String select = pro.getProperty("selectCategory");
		
		try {
			pstmt = con.prepareStatement(select);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				cList.add(new Category(rset.getInt("CATEGORY_NO"),
									   rset.getString("CATEGORY_NAME")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return cList;
	}

	public int updateClickCount(Connection con, int boardNo) {
		
		int result = 0;
		String update = pro.getProperty("updateClickCount");
		
		try {
			pstmt = con.prepareStatement(update);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertRecomCount(Connection con, int userNo, int boardNo) {
		
		int result = 0;
		String insert = pro.getProperty("insertRecomCount");
		
		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int checkDupRecom(Connection con, int userNo, int boardNo) {
		
		int result = 0;
		String select = pro.getProperty("checkDupRecom");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
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

	public int deleteRecomCount(Connection con, int userNo, int boardNo) {
		
		int result = 0;
		String delete = pro.getProperty("deleteRecomCount");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, userNo);
			pstmt.setInt(2, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Reply> selectReply(Connection con, int boardNo, PageInfo p2) {
		
		ArrayList<Reply> replyList = new ArrayList<>();
		String select = pro.getProperty("selectReply");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p2.getCurrentPage() - 1) * p2.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p2.getCurrentPage() * p2.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				replyList.add(new Reply(rset.getInt("REPLY_NO"),
										rset.getString("NICKNAME"),
										rset.getString("REPLY_CONTENT"),
										rset.getString("REPLY_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return replyList;
	}

	public int insertReply(Connection con, Reply reply) {
		
		int result = 0;
		String insert = pro.getProperty("insertReply");
		
		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setInt(1, reply.getUserNo());
			pstmt.setInt(2, reply.getRefBno());
			pstmt.setString(3, reply.getReplyContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<UnionBoard> selectTitleKeyBoard(Connection con, String keyword, PageInfo p) {
		
		ArrayList<UnionBoard> bList = new ArrayList<>();
		String select = pro.getProperty("selectTitleKeyBoard");
		
		String key = "%" + keyword + "%";
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bList.add(new UnionBoard(rset.getInt("BOARD_NO"),
									     rset.getString("CATEGORY_NAME"),
									     rset.getString("BOARD_TITLE"),
									     rset.getString("NICKNAME"),
									     rset.getInt("COUNT"),
									     rset.getString("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public int selectKeyBoardCount(Connection con, String keyword) {
		
		int count = 0;
		String select = pro.getProperty("selectKeyBoardCount");
		
		String key = "%" + keyword + "%";
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public int selectCateKeyBoardCount(Connection con, String keyword, int cateNo) {
		
		int count = 0;
		String select = pro.getProperty("selectCateKeyBoardCount");
		
		String key = "%" + keyword + "%";
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			pstmt.setInt(2, cateNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public ArrayList<UnionBoard> selectCateTitleKeyBoard(Connection con, String keyword, int cateNo, PageInfo p) {
		
		ArrayList<UnionBoard> bList = new ArrayList<>();
		String select = pro.getProperty("selectCateTitleKeyBoard");
		
		String key = "%" + keyword + "%";
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			pstmt.setInt(2, cateNo);
			pstmt.setInt(3, startRow);
			pstmt.setInt(4, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bList.add(new UnionBoard(rset.getInt("BOARD_NO"),
									     rset.getString("CATEGORY_NAME"),
									     rset.getString("BOARD_TITLE"),
									     rset.getString("NICKNAME"),
									     rset.getInt("COUNT"),
									     rset.getString("CREATE_DATE")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public int insertBoard(Connection con, int boardNo, UnionBoard ub) {
		
		int result = 0;
		String insert = pro.getProperty("insertBoard");

		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setInt(1, boardNo);
			pstmt.setInt(2, Integer.parseInt(ub.getCategoryName()));
			pstmt.setString(3, ub.getBoardTitle());
			pstmt.setString(4, ub.getBoardContent());
			pstmt.setInt(5, Integer.parseInt(ub.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectBoardNo(Connection con) {
		
		int boardNo = 0;
		String select = pro.getProperty("selectBoardNo");
		
		try {
			pstmt = con.prepareStatement(select);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				boardNo = rset.getInt("BOARD_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return boardNo;
	}

	public int insertMedia(Connection con, int boardNo, MediaFile file) {
		
		int result = 0;
		String insert = pro.getProperty("insertMedia");
		
		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setString(1,file.getFileType());
			pstmt.setString(2,file.getOriginFileName());
			pstmt.setString(3,file.getChangeFileName());
			pstmt.setInt(4,boardNo);
			pstmt.setString(5, file.getFilePath());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<MediaFile> selectMediaFile(Connection con, int boardNo) {
		
		ArrayList<MediaFile> mList = new ArrayList<>();
		String select = pro.getProperty("selectMediaFile");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				mList.add(new MediaFile(rset.getInt("FILE_NO"),
										rset.getString("FILE_TYPE"),
										rset.getString("ORIGINFILE_NAME"),
										rset.getString("CHANGEFILE_NAME"),
										rset.getString("FILE_PATH")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return mList;
	}

	public int insertWorkout(Connection con, UnionBoard ub) {
		
		int result = 0;
		String insert = pro.getProperty("insertWorkout");
		
		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setInt(1, Integer.parseInt(ub.getBoardWriter()));
			pstmt.setString(2, ub.getBoardTitle());
			pstmt.setString(3, ub.getBoardContent());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int selectReplyCount(Connection con, int boardNo) {
		
		int count = 0;
		String select = pro.getProperty("selectReplyCount");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public int deleteMyReply(Connection con, int replyNo) {
		
		int result = 0;
		String delete = pro.getProperty("deleteMyReply");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, replyNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteBoard(Connection con, int boardNo) {
		
		int result = 0;
		String update = pro.getProperty("deleteBoard");
		
		try {
			pstmt = con.prepareStatement(update);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int checkMedia(Connection con, int boardNo) {
		
		int result = 0;
		String select = pro.getProperty("checkMedia");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, boardNo);
			
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

	public int deleteMedia(Connection con, int boardNo) {
		
		int result = 0;
		String delete = pro.getProperty("deleteMedia");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateBoard(Connection con, UnionBoard ub) {
		
		int result = 0;
		String update = pro.getProperty("updateBoard");
		
		try {
			pstmt = con.prepareStatement(update);
			
			pstmt.setInt(1, Integer.parseInt(ub.getCategoryName()));
			pstmt.setString(2, ub.getBoardTitle());
			pstmt.setString(3, ub.getBoardContent());
			pstmt.setInt(4, ub.getBoardNo());
			pstmt.setInt(5, Integer.parseInt(ub.getBoardWriter()));
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int popularBoardListCount(Connection con) {
		
		int count = 0;
		String select = pro.getProperty("popularBoardListCount");
		
		try {
			pstmt = con.prepareStatement(select);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

	public ArrayList<UnionBoard> selectPopularBoardList(Connection con, PageInfo p) {
		
		ArrayList<UnionBoard> bList = new ArrayList<>();
		String select = pro.getProperty("selectPopularBoardList");
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				bList.add(new UnionBoard(rset.getInt("BOARD_NO"),
									     rset.getString("CATEGORY_NAME"),
									     rset.getString("BOARD_TITLE"),
									     rset.getString("NICKNAME"),
									     rset.getInt("COUNT"),
									     rset.getString("CREATE_DATE"),
									     rset.getInt("RECOMMEND_COUNT"),
									     rset.getInt("REPLY_COUNT")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<UnionBoard> selectPopularTitleKeyBoard(Connection con, String keyword, PageInfo p) {
		
		ArrayList<UnionBoard> pList = new ArrayList<>();
		String select = pro.getProperty("selectPopularTitleKeyBoard");
		String key = "%" + keyword + "%";
		
		// 시작번호 : (현재 페이지 - 1 * 한 페이지에 보여줄 게시글 수) + 1
		int startRow = ((p.getCurrentPage() - 1) * p.getwListLimit()) + 1;
		
		// 끝번호 : 현재 페이지 수 * 게시글 보여줄 수
		int endRow = p.getCurrentPage() * p.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				pList.add(new UnionBoard(rset.getInt("BOARD_NO"),
									     rset.getString("CATEGORY_NAME"),
									     rset.getString("BOARD_TITLE"),
									     rset.getString("NICKNAME"),
									     rset.getInt("COUNT"),
									     rset.getString("CREATE_DATE"),
									     rset.getInt("RECOMMEND_COUNT"),
									     rset.getInt("REPLY_COUNT")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return pList;
	}

	public int selectPopularKeyBoardCount(Connection con, String keyword) {
		
		int count = 0;
		String select = pro.getProperty("selectPopularKeyBoardCount");
		String key = "%" + keyword + "%";
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setString(1, key);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return count;
	}

}
