<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/views/common/menuBar.jsp" %>
	
	<!-- 
		************ 작성 기준 ************
		
		매핑주소 : CRUD + 사용테이블명 . vo파일이 들어있는 상위파일의 앞 두글자
				ex) insertUser.us -> 회원가입 매핑주소
		
		Controller : 테이블명 + CRUD + Controller
					ex) UserInfoInsertController -> 회원가입 컨트롤러
					
		메소드명 : CRUD + 테이블명()
				ex) insertUserInfo() -> 회원가입 삽입 메소드
	
		************ 작성시 주의사항 **************
		
		1. 이미 작성되어있는 파일의 경우에는 변경 전에 먼저 슬랙에 말씀해주세요.
		2. dao의 경우 상단에 PreparedStatement(pstmt), ResultSet(rset), Properties(pro) 객체들은 생성되어있습니다.
		   따로 메소드 안에서 생성하실 필요없습니다.
		3. Controller에 경우 위 작성 기준에 맞게 작성해주세요.
		4. 상점이미지와 통합게시판에서 사용하는 이미지는 /resources/해당파일명/해당이미지명.확장자
		5. 파일명 변경하는 클래스 재정의 해뒀습니다 (new MyFileRenamePolicy())
		6. jsp파일 생성시 /views/상위파일명/파일명.jsp로 작성해주세요.
	-->
	
	
</body>
</html>