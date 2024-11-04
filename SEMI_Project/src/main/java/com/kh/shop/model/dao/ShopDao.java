package com.kh.shop.model.dao;

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
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;

public class ShopDao {

	private Properties pro = new Properties();
	private PreparedStatement pstmt = null;
	private ResultSet rset = null;
	
	public ShopDao() {
		
		String filePath = (ShopDao.class).getResource("/resources/mappers/shop-mapper.xml").getPath();
		
		try {
			pro.loadFromXML(new FileInputStream(filePath));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int listCount(Connection con) {
		
		int listCount = 0; // 조회된 게시글 개수 담을 변수
		
		String sql = pro.getProperty("listCount");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				//조회된 게시글 개수 추출하기
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

	public ArrayList<Product> selectProduct(Connection con, PageInfo pi) {
		
		//빈 리스트 준비
		ArrayList<Product> list = new ArrayList<>();
		//구문 준비
		String sql = pro.getProperty("selectList");
		
		
		//시작번호 : 현재 페이지-1 * 게시글 보여줄 수 + 1
		int startRow = (pi.getCurrentPage()-1) * pi.getwListLimit()+1;
		
		//끝번호 : 현재페이지 수 * 게시글 보여줄 수
		int endRow = pi.getCurrentPage() * pi.getwListLimit();
		
		try {
			pstmt = con.prepareStatement(sql);
			
			//위치홀더 채워주기(게시글 시작번호 끝번호)
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				list.add(new Product(rset.getInt("PRO_NO"),
									rset.getInt("SHOP_FILE_NO"),
									rset.getString("PRO_NAME"),
									rset.getString("PRO_MENUAL"),
									rset.getInt("PRICE")));
				
			}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
					
		
		return list;
	}

	
	public int insertProduct(Connection con, Product p) {

		int result = 0;
		String sql = pro.getProperty("insertProduct");
		
		
//		PRO_NO
//		SHOP_FILE_NO
//		PRO_NAME
//		PRO_MENUAL
//		PRICE
//		STATUS
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p.getProNo());
			pstmt.setInt(2, p.getShopFileNo());
			pstmt.setString(3, p.getProName());
			pstmt.setString(4, p.getProMenual());
			pstmt.setInt(5, p.getPrice());
			pstmt.setString(6, p.getStatus());
			
			
			result = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}

		
		return result;
	}
	
	

	public int selectProduct(Connection con) {
		
		int boardNo = 0;
		
		String sql = pro.getProperty("selectProduct");
		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
		if(rset.next()) {
			
			boardNo = rset.getInt("BNO");
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return boardNo;
	}

	public int insertShopMediaFile(Connection con, ShopMediaFile smf) {

		int result = 0;
		String sql = pro.getProperty("insertShopMediaFile");
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, smf.getRefBno());
			pstmt.setString(2, smf.getOriginName());
			pstmt.setString(3, smf.getChangeName());
			pstmt.setString(4, smf.getFilePath());

			result = pstmt.executeUpdate();					
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
				
		
		return result;
	}

	public Product selectProduct(Connection con, int bno) {
		
		String sql = pro.getProperty("selectProduct");
		
		Product p = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();

			
			
			//조회가 된다면 1개만 조회될테니 단순 조건처리
			if(rset.next()) {
				p = new Product(rset.getInt("PRO_NO")
							  ,rset.getInt("FILE_NO")
							  ,rset.getString("PRO_NAME")
							  ,rset.getString("PRO_MENUAL")
							  ,rset.getInt("PRICE")
							  ,rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
						
		
		return p;
	}

	public ShopMediaFile selectShopMediaFile(Connection con, int bno) {
		
		ShopMediaFile smf = null;
		String sql = pro.getProperty("selectAttachment");
		
		try {
			pstmt =con.prepareStatement(sql);
			
			pstmt.setInt(1, bno);
			
			rset = pstmt.executeQuery();
			
			//일반 게시글에 첨부파일은 하나만 등록될 수 있기 때문에 if 문으로 처리
			if(rset.next()) {
				smf= new ShopMediaFile(rset.getInt("FILE_NO"),
									rset.getString("FILE_NAME")
									,rset.getString("FILE_PATH"));
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
				
		return smf;

		
		

	}
}
