package com.kh.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {

	public static Connection getConnection() {

		Properties pro = new Properties();

		String filePath = (JDBCTemplate.class).getResource("/resources/driver/driver.properties").getPath();

		Connection con = null;

		try {
			pro.load(new FileInputStream(filePath));

			Class.forName(pro.getProperty("driver"));

			con = DriverManager.getConnection(pro.getProperty("url"), pro.getProperty("username"),
					pro.getProperty("password"));
			con.setAutoCommit(false);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public static void commit(Connection con) {

		try {
			if (con != null && !con.isClosed()) {
				con.commit();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {

		try {
			if (con != null && !con.isClosed()) {
				con.rollback();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Connection con) {

		try {
			if (con != null && !con.isClosed()) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(ResultSet rset) {

		try {
			if (rset != null && !rset.isClosed()) {
				rset.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void close(Statement stmt) {

		try {
			if (stmt != null && !stmt.isClosed()) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
