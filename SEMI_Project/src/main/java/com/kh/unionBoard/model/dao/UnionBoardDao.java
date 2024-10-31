package com.kh.unionBoard.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

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
}
