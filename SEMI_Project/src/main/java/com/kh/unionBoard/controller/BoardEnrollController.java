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
 * Servlet implementation class BoardEnrollController
 */
@WebServlet("/insertBoard.un")
public class BoardEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardEnrollController() {
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
		
		// Multipart 방식으로 전달된 데이터인지 확인하는 메소드
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 받을 수 있는 파일의 용량을 정해준다.
			int maxSize = 10 * 1024 * 1024;
			
			// 저장 경로를 설정해준다. 배포되는 파일의 최상단인 webapp 기준으로 경로를 설정하여 경로를 지정해줌
			String savePath = session.getServletContext().getRealPath("/resources/uploadFiles/");
			
			// MultipartRequest 객체를 생성해준다. 파일의 전반적인 저장과 이름변경을 담당(?)
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 이후로는 mutiRequest를 request를 대신해서 사용하여 평소 방법대로 추출 중간에 파일이 있을때와 없을떄의 조건만 달아주면 끝
			String categoryNo = multiRequest.getParameter("categoryNo");
			String boardTitle = multiRequest.getParameter("boardTitle");
			String boardContent = multiRequest.getParameter("boardContent");
			String userNo = multiRequest.getParameter("userNo");
			
			UnionBoard ub = new UnionBoard();
			ub.setCategoryName(categoryNo);
			ub.setBoardTitle(boardTitle);
			ub.setBoardContent(boardContent);
			ub.setBoardWriter(userNo);
			
			ArrayList<MediaFile> mList = new ArrayList<>();
			
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
					
					mList.add(m);
				}
			}
			
			// 게시판을 먼저 insert한 후 boardNo를 가져온다.
			int result = service.insertBoard(ub, mList);
			
			String alertMsg = "";
			
			if(result > 0) {
				alertMsg = "등록되었습니다.";
			}
			else {
				alertMsg = "요청 오류";
			}
			
			session.setAttribute("alertMsg", alertMsg);
			request.getRequestDispatcher("/unionBoardListView.un?currentPage=1").forward(request, response);
		}
	}

}
