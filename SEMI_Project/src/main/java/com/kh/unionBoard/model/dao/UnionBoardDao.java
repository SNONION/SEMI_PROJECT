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
import com.kh.request.model.dao.RequestDao;
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

	
	   //총 게시글 개수 조회메소드
	public int listCount(Connection conn) {
		
		 int listCount = 0;
		 
		 String sql = pro.getProperty("listCount");
		 
		 
		     try {
				pstmt = conn.prepareStatement(sql);
				
				
				
				rset = pstmt.executeQuery();
				
				
				if(rset.next()) {
					
					listCount = rset.getInt("COUNT");
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
		
		
		return listCount;
	}
	
	
	
	//게시글 목록 조회 (DAO 부분)
		public ArrayList<UnionBoard> selectList(Connection conn, PageInfo pi) {
			
			
			 ArrayList<UnionBoard> list = new ArrayList<>();
			 
			 
			 String sql = pro.getProperty("selectList");
			 
			 
			 
			 int startRow = (pi.getCurrentPage()-1) * pi.getPageLimit() +1;
			 
			 
			 int endRow = pi.getCurrentPage() * pi.getPageLimit();
			 
			 
			 
			    try {
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setInt(1, startRow);
					pstmt.setInt(2, endRow);
					
					rset = pstmt.executeQuery();
					
					//목록을 조회
					
					while(rset.next()) {
						
						list.add(new UnionBoard(rset.getInt("BOARD_NO"),
								           rset.getString("CATEGORY_NAME"),
								           rset.getString("BOARD_TITLE"),
								           rset.getString("BOARD_CONTENT"),
								           rset.getInt("COUNT"),
								           rset.getDate("CREATE_DATE")));
								           
						
					}
										
				
				} catch (SQLException e) {
					
					e.printStackTrace();
				} finally {
					
					
					JDBCTemplate.close(rset);
					JDBCTemplate.close(pstmt);
				}
			
			
			return list;
		}


	
	//조회수 증가 메소드
	
   public int increaseCount(Connection conn, int bno) {
	   
	  
	   int result = 0;
	   
	   String sql = pro.getProperty("increaseCount");
	   
	   
	      try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
	   
	   return result;
	   
   }
	
	
   
}
