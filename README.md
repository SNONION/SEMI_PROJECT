# SEMI_Project
SEMI_Project

************ 작성 기준 ************

매핑주소 : CRUD + 사용테이블명 . vo파일이 들어있는 상위파일의 앞 두글자
	ex) insertUser.us -> 회원가입 매핑주소
Controller : 테이블명 + CRUD + Controller
	ex) UserInfoInsertController -> 회원가입 컨트롤러
메소드명 : CRUD + 테이블명()
	ex) insertUserInfo() -> 회원가입 삽입 메소드

    
************ 작성시 주의사항 **************
		
1. 이미 작성되어있는 파일의 경우에는 변경 전에 먼저 슬랙에 말씀해주세요.
2. dao의 경우 상단에 Connection(con), PreparedStatement(pstmt), ResultSet(rset), Properties(pro) 객체들은 생성되어있습니다.
	 따로 메소드 안에서 생성하실 필요없습니다.
3. Controller에 경우 위 작성 기준에 맞게 작성해주세요.
4. 상점이미지와 통합게시판에서 사용하는 이미지는 /resources/해당파일명/해당이미지명.확장자
5. 파일명 변경하는 클래스 재정의 해뒀습니다 (new MyFileRenamePolicy())
6. jsp파일 생성시 /views/상위파일명/파일명.jsp로 작성해주세요.

============================================================

   한 페이지로 구현 예정
- 기본적으로 비회원은 게시판 이용이 불가

1. 메인화면
- 로그인 / 회원가입 - 회원테이블 👉<현재 로그인한 유저의 정보는 loginUser에 저장합니다>
- 통합게시판 이동
- 마이페이지 이동

2. 통합게시판 - 통합게시판테이블, 카테고리 테이블, 추천수 테이블
- 공지사항 작성 - 작성시 태그에 관리자만 '공지' 태그가 보임
- 상단 태그로 게시판 종류 표시 - 관리자만 삭제 기능부여
 (통합, 인기, 자유, 챌린지, 전문가, 식단, 공지)
- 통합, 인기, 자유, 챌린지, 전문가, 식단, 공지 별게시물 검색기능 (키워드 검색)

3. 상점 페이지
- 상품 구매 (구매시 환불 불가)
- 상품 등록 및 삭제 기능 (관리자만 확인 가능)

4. 마이페이지 - 회원테이블, 구매상품테이블, 회원등급테이블, 로그인횟수테이블, 운동기록 테이블
- 운동 기록 (캘린더로 구현)
- 나의 운동 기록 상세보기 
- 포인트 및 구매한 상품 내역 조회
- 회원 탈퇴 및 회원 정보 수정, 비밀번호 변경
- 문의글 작성 & 내가 작성한 문의 내역

5. 관리자 기능 - 통합게시판, 상품테이블, 광고대기테이블, 
- 아이템 구매 현황 확인 기능
- 커뮤니티 내 광고(아이템) 대기열 확인 기능 - 알림기능
- 모든 문의 게시글 조회 기능

각 항목에 완료도 표시
EX) (완) OR 70%

