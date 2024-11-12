package com.kh.shop.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
				
		// 멀티파트 요청이 아닌 경우 처리
        if (ServletFileUpload.isMultipartContent(request)) {
            // 최대 업로드 파일 크기 (10MB)
            int maxSize = 10 * 1024 * 1024;
            
            // 파일을 저장할 경로 (서버의 실제 경로를 가져오기)
            String savePath = request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
            
            // MultipartRequest 객체를 사용하여 파일 업로드 처리
            MultipartRequest multiRequest = new MultipartRequest(
                request, 
                savePath, 
                maxSize, 
                "UTF-8", 
                new MyFileRenamePolicy() // 파일명을 변경할 정책 (파일명 중복 방지)
            );
            
            
            
            // 폼에서 받은 데이터 처리
            int proNo = Integer.parseInt(multiRequest.getParameter("proNo"));
            int shopFileNo = Integer.parseInt(multiRequest.getParameter("shopFileNo"));
            String proName = multiRequest.getParameter("proName");
            String proMenual = multiRequest.getParameter("proMenual");
            int price = Integer.parseInt(multiRequest.getParameter("price"));
            String status = multiRequest.getParameter("status");
            
            // 상품 객체 생성
            Product p = new Product();
            p.setProNo(proNo);
            p.setShopFileNo(shopFileNo);
            p.setProName(proName);
            p.setProMenual(proMenual);
            p.setPrice(price);
            p.setStatus(status);
            
            // 파일 업로드 처리 (파일이 있을 경우만 처리)
            ShopMediaFile smf = null;
            ArrayList<ShopMediaFile> smfList = new ArrayList<>();
            
            String key = "file1"; // 파일 input name
            if (multiRequest.getOriginalFileName(key) != null) {
                smf = new ShopMediaFile();
                smf.setOriginName(multiRequest.getOriginalFileName(key)); // 원본 파일명
                smf.setChangeName(multiRequest.getFilesystemName(key));  // 서버에 저장된 파일명
                smf.setFilePath("/resources/uploadFiles/");  // 파일 경로
                smf.setShopFileNo(shopFileNo);  // 파일의 참조 번호 (상품 번호 등)
                
                smfList.add(smf);
            }
            request.getPart("file1");
            
            // 상품과 파일 정보를 DB에 저장
            int result = new ShopService().insertProduct(p, smfList);
            
            // 세션에 결과 메시지 설정
            HttpSession session = request.getSession();
            String alertMsg = "";
            
            if (result > 0) {
                alertMsg = "상품 등록 성공!";
            } else {
                alertMsg = "상품 등록 실패!";
                // 파일 업로드 실패 시 서버에서 해당 파일 삭제
                if (smf != null) {
                    new File(savePath + smf.getChangeName()).delete();
                }
            }
            
            // 결과 메시지 세션에 저장
            session.setAttribute("alertMsg", alertMsg);
            // 상품 리스트 페이지로 리다이렉트
            response.sendRedirect(request.getContextPath() + "/list.sh?currentPage=1");
        }
    }
}


