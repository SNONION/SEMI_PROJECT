package com.kh.shop.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.shop.model.dao.ShopDao;
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;

public class ShopService {


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
		
		//boardNo를 미리 뽑아놓고 해당 번호로 게시글과 첨부파일 넣어주기
		int boardNo = new ShopDao().selectProduct(con);
		
		if(boardNo != 0) { //추출한 게시글 번호가 0이 아닐 때 (제대로 추출되었을 때)
			//게시글 객체에 추출한 게시글 넣어주기
			p.setBoardNo(boardNo);
			int result = new ShopDao().insertProduct(con,p); //게시글 등록이 잘 되었는지 여부
			
			//첨부파일 등록처리 후 사용할 변수
			int result2 = 1; //첨부파일이 없어도 게시글 등록처리는 될 수 있도록 1로 초기화해놓기
			
			//첨부파일이 없는 경우 게시글만 등록할 수 있도록 처리
			
			if(result>0 && smf != null) { //게시글 등록이 성공했고 전달받은 첨부파일 정보도 있을 때
				//첨부파일 정보 db에 등록
				//첨부파일이 어떠한 게시글에 등록된 첨부파일인지 알 수 있도록 참조게시글 번호 추가해주기
				smf.setRefBno(boardNo);
				
				result2 = new ShopDao().insertShopMediaFile(con,smf); //첨부파일 등록이 되었는지 여부
				
					//result !=0 && result2 !=0		
				
			}
			
			//게시글 등록 또는 게시글 + 첨부파일 등록처리 후
			//트랜잭션 처리하기
			if(result*result2 >0) { // 둘다 0이 아닌경우 조건 통과
				JDBCTemplate.commit(con);
			}else { //실패
				JDBCTemplate.rollback(con);
			}
			//자원반납
			JDBCTemplate.close(con);
			
			//번호는 잘 뽑혔고 등록처리 후 결과값
			return result*result2;
				
				
			}else { //게시글 번호부터 제대로 추출 안됐을 때
				//자원반납
				JDBCTemplate.close(con);
				return boardNo; //등록처리하지 않고 되돌리기 boardNo 제대로 추출안됐으면 0으로 돌아옴
						
		}			
		
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

	public ShopMediaFile selectShopMediaFile(int bno) {


		
		return null;
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
	       
	}
	
	
	
	
	
	
	

	




