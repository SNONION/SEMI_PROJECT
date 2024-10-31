package com.kh.unionBoard.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.request.model.dao.RequestDao;

public class UnionBoardDao {

	private Properties pro = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public UnionBoardDao() {
		
		String filePath = (RequestDao.class).getResource("/resources/driver/driver.properties").getPath();
		
		try {
			pro.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	   //총 게시글 개수 조회
	public int listCount(Connection conn) {
		
		 PreparedStatement pstmt = null;
		 ResultSet rset = null;
		 
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
	
	
	
	
	
	
	
	
}
