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
		String sql = pro.getProperty("insertProduct");

		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p.getProNo());
			pstmt.setString(2, p.getProName());
			pstmt.setString(3, p.getProMenual());
			pstmt.setInt(4, p.getPrice());
			
			result = pstmt.executeUpdate();
			
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt);
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
        String sql = pro.getProperty("insertMyItems");       

        try {
            
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, order.getProNo());
            pstmt.setString(2, order.getProName());
            pstmt.setInt(3, order.getBuyerNo());
            pstmt.setInt(4, order.getProCount());         

            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // 실패 시 false 반환
        } finally {
            JDBCTemplate.close(pstmt); // 자원 해제
            
        }

        return result>0; // 성공 시 true 반환
		
		

	}

	public int insertShopMedia(Connection con, ShopMediaFile smf) {
		
		int result = 0;		           
        String sql = pro.getProperty("insertShopMedia");       

        try {
            
            pstmt = con.prepareStatement(sql);
            
            pstmt.setInt(1, smf.getShopFileNo());
            pstmt.setString(2, smf.getOriginName());
            pstmt.setString(3, smf.getFilePath());
            
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt); // 자원 해제
            
        }
		
		return result;
	}

	public int selectFileNo(Connection con, Product p) {
		
		int fileNo = 0;
		String select = pro.getProperty("selectFileNo");
		
		try {
			pstmt = con.prepareStatement(select);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fileNo = rset.getInt("FILE_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
		
		return fileNo;
	}

	public int updateProduct(Connection con, Product p) {
		
		int result = 0;		           
        String sql = pro.getProperty("updateProduct");       

        try {
            
            pstmt = con.prepareStatement(sql);
            
            System.out.println(p.getShopFileNo());
            System.out.println(p.getProNo());
            
            pstmt.setInt(1, p.getShopFileNo());
            pstmt.setInt(2, p.getProNo());
           
            result = pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTemplate.close(pstmt); // 자원 해제
            
        }
		
		return result;
	}

	public int selectProNo(Connection con) {
		
		int proNo = 0;
		String select = pro.getProperty("selectProNo");
		
		try {
			pstmt = con.prepareStatement(select);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				proNo = rset.getInt("PRO_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
		
		return proNo;
	}

	public ShopMediaFile selectMediaInfo(Connection con, Product p) {
		
		ShopMediaFile smf = new ShopMediaFile();
		String select = pro.getProperty("selectMediaInfo");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, p.getProNo());
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				smf = new ShopMediaFile(rset.getString("FILE_NAME"),
										rset.getString("FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
		
		return smf;
	}

	public int selectUserPoint(Connection con, int userNo) {
		
		int point = 0;
		String select = pro.getProperty("selectUserPoint");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, userNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				point = rset.getInt("POINT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
		
		return point;
	}

	public int updateUserPoint(Connection con, Product pro2, int userNo) {
		
		int result = 0;
		String update = pro.getProperty("updateUserPoint");
		
		try {
			pstmt = con.prepareStatement(update);
			
			pstmt.setInt(1, pro2.getPrice());
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCTemplate.close(pstmt);
        }
		
		return result;
	}

	public int insertMyItemsList(Connection con, Product pro2, int userNo) {
		
		int result = 0;
		String insert = pro.getProperty("insertMyItemsList");
		
		try {
			pstmt = con.prepareStatement(insert);
			
			pstmt.setInt(1, pro2.getProNo());
			pstmt.setInt(2, userNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCTemplate.close(pstmt);
        }
		
		return result;
	}

	public int selectFileNoFromProduct(Connection con, int proNo) {
		
		int fileNo = 0;
		String select = pro.getProperty("selectFileNoFromProduct");
		
		try {
			pstmt = con.prepareStatement(select);
			
			pstmt.setInt(1, proNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				fileNo = rset.getInt("FILE_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(pstmt);
	    }
		
		return fileNo;
	}

	public int deleteProduct(Connection con, int proNo) {
		
		int result = 0;
		String update = pro.getProperty("deleteProduct");
		
		try {
			pstmt = con.prepareStatement(update);
			
			pstmt.setInt(1, proNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCTemplate.close(pstmt);
        }
		
		return result;
	}

	public int deleteMediaFile(Connection con, int fileNo) {
		
		int result = 0;
		String delete = pro.getProperty("deleteMediaFile");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, fileNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCTemplate.close(pstmt);
        }
		
		return result;
	}

	public int deleteMyItemsList(Connection con, int proNo) {
		
		int result = 0;
		String delete = pro.getProperty("deleteMyItemsList");
		
		try {
			pstmt = con.prepareStatement(delete);
			
			pstmt.setInt(1, proNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
            JDBCTemplate.close(pstmt);
        }
		
		return result;
	}

	
}


	

