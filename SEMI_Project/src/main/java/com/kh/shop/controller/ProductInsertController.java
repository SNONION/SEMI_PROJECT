package com.kh.shop.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import com.kh.common.MyFileRenamePolicy;
import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ProductInsertController
 */
@WebServlet("/insert.sh")
public class ProductInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//카테고리는 필요 시 추가 예정.
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		if(ServletFileUpload.isMultipartContent(request)) {
						
			int maxSize = 10 * 1024 * 1024;
				
			String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			//			
			MultipartRequest multiRequest 
			= new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
		
			int proNo = Integer.parseInt(multiRequest.getParameter("proNo"));
			int fileNo = Integer.parseInt(multiRequest.getParameter("fileNo"));
			String proName = multiRequest.getParameter("proName");
			String proMenual = multiRequest.getParameter("proMenual");
			int pricePoint = Integer.parseInt(multiRequest.getParameter("pricePoint"));
			String status = multiRequest.getParameter("status");
						
			Product p = new Product();
			p.setProNo(proNo);
			p.setShopFileNo(fileNo);
			p.setProName(proName);
			p.setProMenual(proMenual);
			p.setPrice(pricePoint);
			p.setStatus(status);			
						
			ShopMediaFile smf = null; 
			
			if(multiRequest.getOriginalFileName("uploadFile") != null) {
	
				smf = new ShopMediaFile();
				
				smf.setOriginName(multiRequest.getOriginalFileName("uploadFile")); //원본 파일명 가져옴
				smf.setChangeName(multiRequest.getFilesystemName("uploadFile")); //서버에 업로드된 파일명 가져옴
				smf.setFilePath("/resources/uploadFiles/");
			}
			
			int result = new ShopService().insertProduct(p,smf);
			
			HttpSession session = request.getSession();
			
			String alertMsg = "";
			
			if(result>0) {
				alertMsg = "상품 등록 성공";
			}else {
				alertMsg = "상품 등록 실패";
				//첨부파일이 있었다면 서버에 업로드 되었을테니 필요없어진 팡리 삭제 처리하기
				
				if(smf!=null) {
					//삭제하고자하는 파일 경로를 파일객체 생성 후 삭제 메소드 수행
					new File(savePath+smf.getChangeName()).delete();
				}
				
			}				
						
			session.setAttribute("alertMsg", alertMsg);
			response.sendRedirect(request.getContextPath()+"/list.sh?currentPage=1");//경로 확인필요
			
		}
		
		
		
		
		
		
		
		
		
		
	
		
	}

}
