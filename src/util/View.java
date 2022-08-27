package util;

public interface View {
	int SHOP = 1;
	
	//광고
	int ADV = 2; //광고배너
	
	int END = 0;
	//멤버 = 회원(승주)
	int MEMBER = 70;
	int MEMBER_SIGNUP = 71;  //회원가입
	int MEMBER_LOGIN = 72;  //로그인
	
	
	
	   // 마이페이지(소영)
	int MYPAGE = 30; 
	int MYPAGE_INFO = 31; // 회원정보
	int MYPAGE_INFO_UPDATE = 32;   //회원정보 수정
	int MYPAGE_PWDUPDATE = 39;   //비밀번호 수정
	int MYPAGE_BUYLIST = 33; // 구매내역(날짜별)
	int MYPAGE_BUYLIST_PROD = 34; // 선택 날짜의 구매내역 안 구매했던 상품들
	int MYPAGE_REVIEW_INSERT = 35; // 리뷰남기기
	int MYPAGE_REVIEW_CHECK = 36; // 내가 쓴 리뷰확인
	int MYPAGE_REVIEW_UPDATE = 37;   // 내가 쓴 리뷰 수정
	int MYPAGE_REVIEW_DELETE = 38;   // 내가 쓴 리뷰 수정
	
	
	
	//장바구니(승주)
	int CART = 40;
	int CART_UPDATE = 41; // 장바구니 수량 수정
	int CART_DELETE = 42; // 장바구니 상품 삭제
	
	
	
	//구매
	int BUY_PAGE = 50; 
	int BUY_CARD = 51;  // 계좌결제
	int BUY_PHONE = 52;  // 휴대폰결제
	int BUY_END1 = 53; // 구매완료 = 계좌
	int BUY_END2 = 54; // 구매완료 = 휴대폰
	
	
	//상품(지영)
	int PROD = 60; //상품 조회
	int PROD_LIST = 61; // 상품목록(메인)
	int PROD_DETAIL = 62; // 상품 상세 페이지(조회)
	int PROD_SEARCH = 59;
	int PROD_DETAIL_BUYQTY = 63; // 상세 페이지에서 수량 입력
	int PROD_DETAIL_REVIEWCHECK = 64; // 
	int PROD_MEMBERBUY = 67;
	
	
	 int PROD_NONMEMBER = 68;
     int PROD_BUY_MEMBER = 69;
     int REVIEWCHECK_MEMBER = 98;
	
	
	

}
