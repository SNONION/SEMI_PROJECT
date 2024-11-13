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
import com.kh.user.model.vo.MyItems;

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
		PreparedStatement pstmt = null;
		String sql = pro.getProperty("insertProduct");

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p.getProNo());
			pstmt.setInt(2, p.getShopFileNo());
			pstmt.setString(3, p.getProName());
			pstmt.setString(4, p.getProMenual());
			pstmt.setInt(5, p.getPrice());
			pstmt.setString(6, p.getStatus());
			
			
			result = pstmt.executeUpdate();
			
			
			if (result > 0) {
				JDBCTemplate.commit(con);  // 커밋
            } else {
            	JDBCTemplate.rollback(con);  // 롤백
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt);
            JDBCTemplate.close(con);
        }

        return result;
    }
	
	

	public int selectProduct(Connection con) {
		
		int proNo = 0;
		
		String sql = pro.getProperty("selectProduct");		
		
		try {
			pstmt = con.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
		if(rset.next()) {
			
			proNo = rset.getInt("pno");
		}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
				
		return proNo;
	}

	public int insertShopMediaFiles(Connection con, ArrayList<ShopMediaFile> smfList) {

		int result = 0;
		String sql = pro.getProperty("insertShopMediaFiles");
		PreparedStatement pstmt = null;
		
		try {
			for(ShopMediaFile smf : smfList) {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, smf.getRefBno());
			pstmt.setString(2, smf.getChangeName());
			pstmt.setString(3, smf.getFilePath());

			result = pstmt.executeUpdate();		
			}
			
			if (result == smfList.size()) {
				JDBCTemplate.commit(con);  // 모두 성공하면 커밋
            } else {
            	JDBCTemplate.rollback(con);  // 실패하면 롤백
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	JDBCTemplate.close(pstmt);
        	JDBCTemplate.close(con);
        }

        return result;
        
        
	}

	public Product selectProduct(Connection con, int pno) {
		
		String sql = pro.getProperty("selectProduct");
		
		Product p = null;
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, pno);
			
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

	public ArrayList<Product> selectProductList(Connection con, int proNo) {
		
		ShopMediaFile smf = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		ArrayList<Product> pList = new ArrayList<>();
		String sql = pro.getProperty("selectProductList");
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, proNo);
			
			rset = pstmt.executeQuery();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
			JDBCTemplate.close(rset);
		}			
		
		return pList;		

	}
	
	
	public int increaseCount(Connection con, int proNo) {
		
		 int result = 0;
	     PreparedStatement pstmt = null;
	     
	     String sql = pro.getProperty("increaseCount");
	     
	     try {
			pstmt = con.prepareStatement(sql);			
			
			pstmt.setInt(1, proNo);			
			
			result = pstmt.executeUpdate();			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		 		 
		 return result;
	}

	public boolean purchaseProduct(Product order, Connection con) {
		
		int result = 0;		
        PreparedStatement pstmt = null;        
        
        String sql = pro.getProperty("purchaseProduct");       

        try {
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, order.getBoardNo());
            pstmt.setInt(2, order.getQuantity());

            result = pstmt.executeUpdate();
            return result>0; // 성공 시 true 반환

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 실패 시 false 반환
        } finally {
            JDBCTemplate.close(pstmt); // 자원 해제
            
        }
    }

	public int totalCount(Connection con) {
	    PreparedStatement pstmt = null;
	    ResultSet rset = null;
	    int totalCount = 0;  // 여기서 totalCount를 계산해야 하므로 초기값을 설정합니다.

	    String sql = pro.getProperty("totalCount");  // 상품 테이블에서 총 개수 조회 SQL

	    try {
	        pstmt = con.prepareStatement(sql);
	        rset = pstmt.executeQuery();

	        if (rset.next()) {
	            totalCount = rset.getInt(1);  // 첫 번째 컬럼의 값을 가져옴
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }

	    return totalCount;  // 총 상품 개수 반환
		
		
		
		
}

	public boolean insertMyItems(Connection con, MyItems order) {
		
		int result = 0;		
        PreparedStatement pstmt = null;        
        
        String sql = pro.getProperty("insertMyItems");       

        try {
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, order.getProNo());
            pstmt.setString(2, order.getProName());
            pstmt.setInt(3, order.getBuyerNo());
            pstmt.setInt(4, order.getProCount());         

            result = pstmt.executeUpdate();
            return result>0; // 성공 시 true 반환

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 실패 시 false 반환
        } finally {
            JDBCTemplate.close(pstmt); // 자원 해제
            
        }

		
		

	}

	
}


	

