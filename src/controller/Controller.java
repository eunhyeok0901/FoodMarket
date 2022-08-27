package controller;

import java.util.Random;

import service.AdvService;
import service.BuyService;
import service.MemberService;
import service.MypageService;
import service.ProdService;
import util.ScanUtill;
import util.View;

public class Controller {
	
	static public String login_id = null;
	static public boolean login = false;
	static public int page = View.MEMBER_LOGIN;
	static public Object pID = null;
	   
	BuyService buyService = BuyService.getInstance();
	MemberService memberservice = new MemberService();
	MypageService mypageService = MypageService.getInstance();  
	AdvService advService = AdvService.getInstance();
    ProdService prodService = ProdService.getInstance();
    
	static public int nextPage; //
    static public Object prod; 
    static public int pastPage; //
    static public int view; //
    
    
	
	public static void main(String[] args) {
		new Controller().start();
	}

	private void start() {
		view = View.SHOP;
		start:
		while(true) {
			
			//advService.adv();
				
			switch(view) {
				case View.END: break start;
				case View.SHOP : view = shop(); advService.adv(); break;	
				case View.MEMBER_SIGNUP : view = memberservice.signUp(); advService.adv(); break;
				case View.MEMBER_LOGIN  : view = memberservice.login(); advService.adv(); break;
			
	            //case View.MYPAGE      : view = mypageService.mypage(); break;
	            case View.MYPAGE_INFO : view = mypageService.info(); advService.adv();   break;       
	            case View.MYPAGE_INFO_UPDATE : view = mypageService.infoUpdate(); break;
	            case View.MYPAGE_PWDUPDATE : view = mypageService.pwdUpdate(); break;
	            case View.MYPAGE_BUYLIST : view = mypageService.buyList(); break;            
	            case View.MYPAGE_BUYLIST_PROD: view = mypageService.buyListProd(); break;    
	            case View.MYPAGE_REVIEW_INSERT : view = mypageService.reviewInsert(); break;        
	            case View.MYPAGE_REVIEW_CHECK : view = mypageService.reviewCheck();  break;   
	            case View.MYPAGE_REVIEW_UPDATE : view = mypageService.reviewUpdate();break;
	            case View.MYPAGE_REVIEW_DELETE : view = mypageService.reviewDelete();  break;
			
				case View.CART : view = memberservice.selectCart(); break;        
				case View.CART_UPDATE : view = memberservice.cartUpdate(); break;
				//case View.CART_DELETE : view = memberservice.cartDelete(); break;
				
				case View.BUY_PAGE : view = buyService.buy(); break;
				case View.BUY_CARD  : view = buyService.buyCard(); break;
				case View.BUY_PHONE : view = buyService.buyPhone(); break;
				case View.BUY_END1 : view = buyService.buyEnd1(); advService.adv(); break;
				case View.BUY_END2 : view = buyService.buyEnd2(); advService.adv(); break;
		    
				case View.PROD_LIST : view = prodService.list(); break;
				case View.PROD_DETAIL : view = prodService.showDetail(); break;
				case View.PROD_SEARCH : view = prodService.prodSearch(); break;
				case View.PROD_DETAIL_BUYQTY : view = prodService.memberBuy(); break;
				case View.PROD_MEMBERBUY : view = prodService.memberBuy(); break;
				
				case View.PROD_NONMEMBER: view = prodService.nonMember(); break;
	            case View.PROD_BUY_MEMBER: view = prodService.memberBuy(); break;
	            case View.REVIEWCHECK_MEMBER: view = prodService.memberReview(); break;
				
		    
		    
			}
			
			}            
			
		
	}

	public int shop() {
		System.out.println("=========================================================================================================");
		System.out.println("  https://나홀로 마켓.com                                         ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
		System.out.println("\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀혼자도 잘 먹는다⠀나홀로 마켓⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                ⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡀⠀⠀⠀⠀⠀⢀⣄⠀⠀⠀⠀⠀⠀⢀⣀⣀⣀⣠⣾⣦⣀⣀⣀⣀⠀⠀⢀⣀⣀⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠈⢹⣿⡟⠛⠛⠛⠛⢿⣿⡏⠀⠀⠘⠛⠛⠛⠛⠛⠛⢻⣿⠀⠀⠀⠀⠀⠀⠀⣰⣿⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⡦⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢸⣿⡀⠀⠀⠀⠀⠀⠀⠈⠻⠿⠷⣶⡶⠶⠿⠛⠁⠀⠀⢰⣶⣶⣶⣶⣶⣶⣾⣿⠀⠀⠀⠀⠀⠀⣼⡿⠉⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣿⠃⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢸⣿⡿⠿⠀⠀⠀⠀⠐⠶⠶⠶⠾⠿⠿⠶⠶⠶⠶⠆⠀⢸⣿⣁⣀⣀⣀⣀⣀⣀⠀⠀⠀⠀⠀⢰⣿⠁⣼⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⡏⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠀⠀⠀⠀⠀⢸⣿⠀⠀⠀⠀⠀⠀⠀⠤⠶⠶⠶⠶⠶⠶⠶⣶⣤⠀⠀⠘⠛⠛⠛⣿⡟⠛⠛⠛⠀⠀⠀⠀⢠⣿⠇⣴⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡟⠀⢀⣀⡤⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣿⣶⣶⣶⣦⡄⢸⣿⠃⠀⠀⠀⠀⠀⠀⣴⡶⠶⠶⠶⠶⠶⠶⠾⠟⠀⢠⣶⣶⣶⣶⣿⣷⣶⣶⣶⣦⡄⠀⠀⣸⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠋⢻⡆⠀⢻⣿⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠉⠉⠉⠉⠉⠀⠈⠛⠀⠀⠀⠀⠀⠀⠀⠛⠛⠒⠒⠒⠒⠒⠒⠒⠒⠀⠀⠁⠀⠀⠀⣿⠇⠀⠀⠀⠀⠀⠀⢠⣿⠇⠀⣠⡾⠿⢿⣷⡀⠀⠀⣰⣿⠃⠀⢀⣾⠃⠀⣼⡟⠀⠀⠀⠀⠀⢠⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⣤⣤⡀⠀⠀⠀⠀⡿⠷⣤⡄⠀⠀⠀⠀⢸⣿⠀⣰⠋⠀⠀⣨⣿⠃⠀⢰⣿⠃⢀⡴⠟⠁⠀⠀⣿⡇⠀⠀⠀⠀⢀⠂⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⠁⠀⠸⣿⠀⠀⠀⢠⠇⠀⢸⣿⡆⠀⠀⠀⣸⣿⢰⠃⠀⣤⡞⠛⠁⠀⠀⣸⣿⠛⠉⠀⠀⠀⢀⡞⣿⣧⡀⠀⢀⡰⠃⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿⡆⠀⣠⣤⣤⡄⠀⠀⠀⢠⣿⠃⠀⠀⠀⣼⡶⠀⠀⠀⡎⠀⠀⣾⡿⠀⠀⠀⣰⣿⣿⡾⠀⠀⠹⣧⡀⠀⠀⡴⠹⣿⣆⣀⣀⣠⠶⠃⠀⠈⠿⠿⠟⠋⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⣿⡇⣰⠏⠁⢘⣿⣇⡼⠋⠉⣿⡷⠀⠀⢀⣿⡇⠀⠀⢀⣼⣿⠁⠀⠀⡼⠀⠀⠠⣿⡇⠀⣠⡾⠁⢸⣿⠃⠀⠀⠀⠙⠿⠷⠋⠀⠀⠈⠉⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⣿⠃⠀⠀⢸⣿⡿⠀⠀⢰⣿⡇⠀⠀⣼⣿⡁⠀⢀⠞⢻⣿⣀⣠⠞⠀⠀⠀⠀⠛⠛⠛⠁⠀⠀⠀⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⠇⠀⠀⠀⣿⡿⠁⠀⠀⠘⢿⣧⣤⠞⠙⠿⡷⠶⠃⠀⠀⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                                                              \r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡏⠀⠀⠀⠀⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀                                                                                                        ⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\r\n" + 
				"⠀★ '나홀로마켓'은 늘어나는 자취. 혼밥러를 위한 밀키트 및 레토르트 식품 판매 온라인 스토어입니다⠀★⠀");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println(">> ENTER >>");
		ScanUtill.nextLine();
		
	//	switch (ScanUtill.nextInt()) {
//			case 1 : return View.MEMBER_LOGIN;
//			case 2 : return View.MYPAGE;
//			case 3 : return View.BUY_PAGE;
//			default : return View.SHOP;
		//}
		return View.PROD_LIST;
	}

	
	

	public static void cleaner() {
		for(int i =0; i< 60; i++) {
			System.out.println("\t");
		}	
	}
	

	
	public static void enter(int num) {
        for(int i = 0; i < num; i ++) {
           System.out.println();
        }
     }
	
	
	
}












