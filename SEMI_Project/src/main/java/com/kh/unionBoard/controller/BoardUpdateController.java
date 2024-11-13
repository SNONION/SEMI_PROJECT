package com.kh.unionBoard.controller;

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
import com.kh.unionBoard.model.service.UnionBoardService;
import com.kh.unionBoard.model.vo.MediaFile;
import com.kh.unionBoard.model.vo.UnionBoard;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.un")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
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
		
		UnionBoardService service = new UnionBoardService();
		HttpSession session = request.getSession();
		
		// multipart 형식으로 받아온건지 확인
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 받을 수 있는 파일의 용량을 정해준다.
			int maxSize = 10 * 1024 * 1024;
			
			// 저장 경로를 설정해준다. 배포되는 파일의 최상단인 webapp 기준으로 경로를 설정하여 경로를 지정해줌
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			// MultipartRequest 객체를 생성해준다. 파일의 전반적인 저장과 이름변경을 담당(?)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			String categoryNo = multiRequest.getParameter("categoryNo");
			String newImg = multiRequest.getParameter("newImg");
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			int boardNo = Integer.parseInt(multiRequest.getParameter("boardNo"));
			String userNo = multiRequest.getParameter("userNo");
			
			UnionBoard ub = new UnionBoard();
			ub.setCategoryName(categoryNo);
			ub.setBoardTitle(boardTitle);
			ub.setBoardContent(boardContent);
			ub.setBoardNo(boardNo);
			ub.setBoardWriter(userNo);
			
			ArrayList<MediaFile> mList = new ArrayList<>();
			
			int result = 0;
			
			if(newImg != null) {
				
				for(int i = 0; i < 4; i++) {
					String key = "file" + i;
					
					if(multiRequest.getOriginalFileName(key) != null) {
						
						MediaFile m = new MediaFile();
						
						String fileOrigin = multiRequest.getOriginalFileName(key);
						
						String exp = fileOrigin.substring(fileOrigin.lastIndexOf("."));
						
						m.setFileType(exp);
						m.setOriginFileName(multiRequest.getOriginalFileName(key));
						m.setChangeFileName(multiRequest.getFilesystemName(key));
						m.setFilePath("/resources/uploadFiles/");
						m.setRefBno(boardNo);
						
						mList.add(m);
					}
				}
				result = service.updateBoard(ub, mList);
			}
			else {
				result = service.updateBoard(ub);
			}
			
			
			String alertMsg = "";
			
			if(result > 0) {
				alertMsg = "수정되었습니다.";
			}
			else {
				alertMsg = "요청 오류";
			}
			
			session.setAttribute("alertMsg", alertMsg);
			request.getRequestDispatcher("/unionBoardListView.un?currentPage=1").forward(request, response);
		}
	}

}
