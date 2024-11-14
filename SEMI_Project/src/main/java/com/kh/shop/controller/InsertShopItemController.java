package com.kh.shop.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.kh.common.ShopFileRenamePolicy;
import com.kh.shop.model.service.ShopService;
import com.kh.shop.model.vo.Product;
import com.kh.shop.model.vo.ShopMediaFile;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class InsertShopItemController
 */
@WebServlet("/itemInsert.sh")
public class InsertShopItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertShopItemController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		if (ServletFileUpload.isMultipartContent(request)) {
			// 최대 업로드 파일 크기 (10MB)
			int maxSize = 10 * 1024 * 1024;

			// 파일을 저장할 경로 (서버의 실제 경로를 가져오기)
			String savePath = request.getSession().getServletContext().getRealPath("/resources/shopImgFile/");

			// MultipartRequest 객체를 사용하여 파일 업로드 처리
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8",
					new ShopFileRenamePolicy() // 파일명을 변경할 정책 (파일명 중복 방지)
			);
			
			
            String proName = multiRequest.getParameter("proName");
            String proMenual = multiRequest.getParameter("proMenual");
            int price = Integer.parseInt(multiRequest.getParameter("price"));            
            
            Product p = new Product();
            p.setProName(proName);
            p.setProMenual(proMenual);
            p.setPrice(price);
            
            ShopMediaFile smf = new ShopMediaFile();
            
            if(multiRequest.getOriginalFileName("file1") != null) {
            	
            	
            	smf.setOriginName(multiRequest.getOriginalFileName("file1"));
            	smf.setFilePath("/resources/shopImgFile/");
            	                                
            	
            }

            int result = new ShopService().insertProduct(p,smf);
            
            String alertMsg = "";
            
            if(result > 0) {
            	alertMsg = "등록되었습니다.";
            }
            else {
            	alertMsg = "처리 오류";
            }
            
            session.setAttribute("alertMsg", alertMsg);
            response.sendRedirect(request.getContextPath() + "/list.sh?currentPage=1");
		}
	}

}
