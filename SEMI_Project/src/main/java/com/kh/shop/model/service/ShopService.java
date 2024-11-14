package com.kh.shop.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.shop.model.dao.ShopDao;
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;
import com.kh.user.model.vo.MyItems;

public class ShopService {

	private ShopDao shopDao = new ShopDao();
	
	public int listCount() {
		
		Connection con = JDBCTemplate.getConnection();
			
		
		int listCount = new ShopDao().listCount(con);
		
		JDBCTemplate.close(con);
		
		return listCount;
		
		
		
	}

	public ArrayList<Product> selectProduct(PageInfo pi) {
		
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<Product> list = new ShopDao().selectProduct(con,pi);
		
		JDBCTemplate.close(con);
		
		return list;
		
		
		
		
	
	}

	public int insertProduct(Product p, ShopMediaFile smf) {
		
		Connection con = JDBCTemplate.getConnection();
		
		//fileNo를 미리 뽑아놓고 해당 번호로 게시글과 첨부파일 넣어주기
		int proNo = new ShopDao().selectProNo(con);
		p.setProNo(proNo);
		int result1 = new ShopDao().insertProduct(con,p);
		int result2 = 1;
		int result3 = 1;
		
		if(result1 > 0) { 
			
			if(smf != null) {
				
				// 파일 번호를 먼저 뽑아준다
				int fileNo = new ShopDao().selectFileNo(con, p);
				smf.setShopFileNo(fileNo);
				// 파일을 삽입
				result2 = new ShopDao().insertShopMedia(con, smf);

				// 파일이 있었던 경우 제품테이블의 SHOP_FILE_NO에 파일 번호를 UPDATE해준다.
				p.setShopFileNo(fileNo);
				result3 = new ShopDao().updateProduct(con, p);
			}
			
		}
		else {
			JDBCTemplate.rollback(con);
		}
		
		int result = result1 * result2 * result3;
		
		if(result > 0) {
			JDBCTemplate.commit(con);
		}
		else {
			JDBCTemplate.rollback(con);
		}
		JDBCTemplate.close(con);
	
		return result;
		
	}			
		
	


	public Product selectProduct(int pno) {
	
		Connection con = JDBCTemplate.getConnection();
		
		Product p = new ShopDao().selectProduct(con,pno);
		
		//조회구문은 트랙잭션 처리 필요없음
		
		JDBCTemplate.close(con);

		return p;		
		

	}


	public int increaseCount(int proNo) {

		Connection con = JDBCTemplate.getConnection();
		
		int result = new ShopDao().increaseCount(con,proNo);
		
		if(result>0) {
			JDBCTemplate.commit(con);
		}else {
			JDBCTemplate.rollback(con);
		}
		
		JDBCTemplate.close(con);
		
		return result;		
	}

	public ArrayList<Product> selectProductList(int proNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Product> pList = new ShopDao().selectProductList(conn,proNo);
		
		JDBCTemplate.close(conn);
		
		return pList;
	}


	public Product purchaseProduct(int boardNo, int quantity) {
		
		Connection con = JDBCTemplate.getConnection();
	    // Product 객체를 생성하고 필요한 속성 설정
	    Product order = new Product();
	    order.setBoardNo(boardNo);
	    order.setQuantity(quantity);
	    
	    // DAO 메소드를 호출하여 DB에 주문 정보를 저장합니다.
	    ShopDao shopDao = new ShopDao();
	    boolean isSuccess = shopDao.purchaseProduct(order,con);

	    // 주문이 성공적으로 처리되면 order 객체를 반환
	    if (isSuccess) {
	        return order; // 성공적으로 저장된 order 객체 반환
	    } else {
	        return null; // 실패 시 null 반환
	    }
	}

	public boolean purchaseProduct(Product order) {
		
		Connection con = JDBCTemplate.getConnection();
	    // Product 객체를 생성하고 필요한 속성 설정
	  
	    // DAO 메소드를 호출하여 DB에 주문 정보를 저장합니다.
		ShopDao shopDao = new ShopDao();
	    boolean isSuccess = shopDao.purchaseProduct(order,con);

	    if (isSuccess) {
	        JDBCTemplate.commit(con); // 성공 시 커밋
	    } else {
	        JDBCTemplate.rollback(con); // 실패 시 롤백
	    }
	    
	    // 자원 해제
	    JDBCTemplate.close(con);
	    
	    return isSuccess; // 성공 여부 반환
	}

	

	
	
	public int totalCount() {
	    Connection con = JDBCTemplate.getConnection();
	    
	    int result = new ShopDao().totalCount(con);  // ShopDao에서 totalCount 호출

	    JDBCTemplate.close(con);  // Connection 자원 해제
	    
	    return result;  // 총 상품 개수를 반환
	}

	
	
	public ArrayList<Product> productList(int startPage, int endPage) {


		
		return null;
	}

	public boolean insertMyItems(MyItems order) {

		Connection con = JDBCTemplate.getConnection();
	    // Product 객체를 생성하고 필요한 속성 설정
	  
	    // DAO 메소드를 호출하여 DB에 주문 정보를 저장합니다.
	    boolean result = shopDao.insertMyItems(con,order);

	    if (result) {
	        JDBCTemplate.commit(con); // 성공 시 커밋
	    } else {
	        JDBCTemplate.rollback(con); // 실패 시 롤백
	    }
	    
	    // 자원 해제
	    JDBCTemplate.close(con);
	    
	    return result; // 성공 여부 반환
	    
	}

	public ShopMediaFile selectMediaInfo(Product p) {
		
		Connection con = JDBCTemplate.getConnection();

		
		ShopMediaFile smf = shopDao.selectMediaInfo(con, p);
		
		JDBCTemplate.close(con);
		
		return smf;
	}

	public int updateUserPoint(Product pro, int userNo) {
		
		Connection con = JDBCTemplate.getConnection();
		
		int result = 0;
		int userPoint = shopDao.selectUserPoint(con, userNo);
		
		if(userPoint > pro.getPrice()) {
			
			int mPoint = shopDao.updateUserPoint(con, pro, userNo);
			
			if(mPoint > 0) {
				int insertPro = shopDao.insertMyItemsList(con, pro, userNo);
				
				if(insertPro > 0) {
					result = 1;
				}
			}
		}
		
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
	
	
	
	
	
	
	

	




