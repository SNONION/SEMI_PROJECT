package com.kh.request.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class RequestDao {

	private Properties pro = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public RequestDao() {
		
		String filePath = (RequestDao.class).getResource("/resources/driver/driver.properties").getPath();
		
		try {
			pro.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
