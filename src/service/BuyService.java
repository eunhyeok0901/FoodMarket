package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import controller.Controller;
import dao.BuyDAO;
import util.ScanUtill;
import util.View;



public class BuyService {

	
    final String REGEXCOUNTPATTERN = "\\d{14}"; // 숫자만 14 
    //PhoneNUM
    final String REGEXPHPATTERN = "^\\d{3}-\\d{3,4}-\\d{4}$"; //EX 000-0000-0000
    //ADDRESS
    //final String REGEXADDRESSPATTERN = "^[ㄱ-ㅎ가-힣0-9]*$"; //한글과 숫자만
	
	
	
	public static BuyService instance = null;
	private BuyService () {}
	public static BuyService getInstance() {
		if(instance==null) instance = new BuyService();
		return instance;
	}
	
	BuyDAO dao = BuyDAO.getInstance();
	BuyDAO buydao = new BuyDAO();
	
//	Map<String,Object> list = dao.name(Controller.login_id);
//	List<Map<String,Object>> list1 = dao.hp(Controller.login_id);
//	List<Map<String,Object>> list2 = dao.address(Controller.login_id);
	public static void cleaner() {
		for(int i =0; i< 70; i++) {
			System.out.println("\t");
		}	
	}
	
	public int buy() {
		Map<String,Object> list = dao.name(Controller.login_id);
		Map<String,Object> list2 = dao.hp(Controller.login_id);
		Map<String,Object> list3 = dao.address(Controller.login_id);
		Map<String,Object> list4 = dao.mempoint(Controller.login_id);
		
		cleaner();
		System.out.println("=========================================================================================================");
		System.out.println("                                               결제 페이지                                               ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                               주문자 정보 "); 
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                          회원이름 : "+list.get("MEM_NAME"));
		System.out.println();
		System.out.println("                                           회원 HP : "+list2.get("MEM_HP"));
		System.out.println();
		System.out.println("                                        보유포인트 : "+list4.get("MEM_POINT")+" 점");
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                               배송지 정보 ");
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("                                          기본주소 : "+list3.get("MEM_ADDR"));
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println("               1. 배송지 정보 수정     2. 계좌간편결제     3. 휴대폰 결제     0. 뒤로가기");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();

		
	    switch(ScanUtill.nextInt()) {
	    	case 1 : 
	    System.out.print("배송지 정보를 수정합니다.");
	    		for(int i = 2; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
	    		System.out.println();
	    		cleaner();
	    		
	    System.out.println("=========================================================================================================");		
	    System.out.println("                                        [배송지 정보] 변경 페이지");
	    System.out.println("---------------------------------------------------------------------------------------------------------");	 
	    System.out.println();
	    System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
	    System.out.println("                                               기존 배송지");
		System.out.println("    -------------------------------------------------------------------------------------------------");
	    System.out.println();
	    System.out.println();
		System.out.println("                                          기본주소 : "+list3.get("MEM_ADDR"));
	    System.out.println();
	    System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
	    System.out.println("                                               신규 배송지");
		System.out.println("    -------------------------------------------------------------------------------------------------");
	    System.out.println();
	    System.out.println();
	    System.out.println("입력 >>");
	    System.out.println();
	    System.out.println();
	    System.out.println();
	    	String addr = ScanUtill.nextLine();
	    	
	    	String newAddr = addr;// = new ArrayList<>();
	    	//newAddr.add(addr);
	    	
	    	int result = dao.updateAddr(newAddr,Controller.login_id);
	    	System.out.println("\n\n\n\n\n");
	    System.out.print("[배송지 정보]를 수정하는 중 입니다.");
	    
	    	
	    	for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
	    	
	    	
	    	if(result>0) {
	    		System.out.print("[배송지 정보]가 수정되었습니다. 결제 페이지로 이동합니다.");

	    	}else {
	    		System.out.println("정보 수정에 실패하였습니다");
	    	    	
	    		return buy(); 
	    	}
	    	for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
	    	cleaner();
	    		return View.BUY_PAGE;
	    		
	    	case 2 : cleaner(); buyCard(); return View.PROD_LIST; 
	    	case 3 : cleaner(); buyPhone(); return View.PROD_LIST;
	    	case 0 : return View.CART;
	    	
	    }
	    
		return View.CART;
		}
	
	
	
	public int buyCard() {
		
		System.out.println("=========================================================================================================");
		System.out.println("                                               결제 페이지                                               ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println("                          *****************************************************");
		System.out.println("                          *                 계좌간편결제 페이지               *");
		System.out.println("                          *****************************************************");
		System.out.println("                            결제하실 은행/증권을 선택해주세요                 ");
		System.out.println("                                                                              ");
		System.out.println("                                                                              ");
		System.out.println("                                                                              ");
		System.out.println("                             1.KB국민  2.NH농협  3.IBK기업은행  4.카카오뱅크  ");
		System.out.println("                                                                              ");		
		System.out.println("                             5.우리은행  6.신한은행  7.SC제일은행 8. 씨티뱅크 ");
		System.out.println("                                                                              ");		
		System.out.println("                             0. 뒤로가기                                      ");
		System.out.println("                                                                              ");
		System.out.println("                          *****************************************************");
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println(" 해당 은행/증권 번호를 입력해주세요 >>");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		
		switch(ScanUtill.nextInt()) {
		case 1 : 
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                        
			System.out.println();                        
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : KB국민"); break;
		case 2 : 
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                        
			System.out.println();                        
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : NH농협"); break;
		case 3 : 
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                        
			System.out.println();                        
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : IBK기업은행"); break;
		case 4 : 
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                        
			System.out.println();                        
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : 카카오뱅크"); break;
		case 5 :
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                         
			System.out.println();                         
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : 우리은행"); break;
		case 6 :
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                        
			System.out.println();                        
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : 신한은행"); break;
		case 7 :
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                         
			System.out.println();                         
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : SC제일은행"); break;
		case 8 :
			cleaner();
			System.out.println("=========================================================================================================");
			System.out.println("                                               결제 페이지                                               ");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println();                         
			System.out.println();                         
			System.out.println("                          *****************************************************");
			System.out.println("                          *                 계좌간편결제 페이지               *");
			System.out.println("                          *****************************************************");
			System.out.println("                             거래은행 : 씨티뱅크"); break;
		case 0 : 
			System.out.println();
			System.out.println();
			System.out.println("결제페이지로 이동합니다..."); return buy();
		default : 
			System.out.println();
			System.out.println("유효한 선택이 아닙니다"); return buyCard();
	}
		System.out.println("\n");
		System.out.println("                             해당 계좌의 계좌번호를 입력해주세요");
		System.out.println("                             ( 14자리의 숫자를 '-' 없이 입력해주세요)");
		System.out.println("\n");
		
		String input = ScanUtill.nextLine();
		System.out.println();
		if(Pattern.matches(REGEXCOUNTPATTERN, input)) {
			System.out.println("                             계좌번호(인증완료) : "+input);			
		}else {
			cleaner();
			System.out.println("                             인증되지 않은 계좌번호입니다.");
			
			return buyCard();
		}
		
			System.out.println("                             비밀번호 4자리를 입력해주세요");
			System.out.println();
			int input2 = ScanUtill.nextInt();
			if(input2 > 9999 || input2 < 1000) { 
				cleaner();
				System.out.println("                             잘못된 비밀번호 입니다");
				return buyCard();
			}else {
				cleaner();
				System.out.print("확인 중입니다");
				for(int i = 1; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
				System.out.println();
				System.out.println("확인되었습니다");
			}
		System.out.println();
		System.out.println("결제하시겠습니까?");
		System.out.println(" 1. 네   2. 아니오 ");
		switch(ScanUtill.nextInt()) {
			case 1 : 
				System.out.print("결제가 진행 중입니다. 잠시만 기다려주세요.");
		    	for(int i = 3; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
		    	System.out.println();
		    	
		    	System.out.println();
		    	System.out.print("결제가 완료되었습니다. 결제완료 페이지로 이동합니다.");
		    	for(int i = 2; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
		    	System.out.println();
		    	cleaner();
				return buyEnd1();
			case 2 :
				System.out.println();
				System.out.print("취소되었습니다. 결제페이지로 이동합니다.");
				for(int i = 2; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
				System.out.println();
				cleaner();
				return buy();
			
		}
	
		
		System.out.println();
		
		return 0;
	}
	
	
	
	public int buyPhone() {
		Random rnd = new Random();
		int Num = rnd.nextInt(8999)+1001;
		cleaner();
		System.out.println("=========================================================================================================");
		System.out.println("                                               결제 페이지                                               ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("                         *****************************************************");
		System.out.println("                         *                  휴대폰 결제 페이지               *");
		System.out.println("                         *****************************************************");
		System.out.println("                           해당하는 통신사를 선택해주세요                    ");
		System.out.println("                                                                             ");
		System.out.println("                            	       1.KT       2.SKT       3.U+   	       ");
		System.out.println("                                                                             ");
		System.out.println("                         *****************************************************");
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println(" 해당 통신사를 선택해주세요 >>");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		switch(ScanUtill.nextInt()) {
		 case 1 :
			 cleaner();
			    System.out.println("=========================================================================================================");
				System.out.println("                                               결제 페이지                                               ");
				System.out.println("---------------------------------------------------------------------------------------------------------");
				System.out.println();
				System.out.println();                        
				System.out.println("                         *****************************************************");
				System.out.println("                         *                  휴대폰 결제 페이지               *");
				System.out.println("                         *****************************************************");
				System.out.println("                            통신사 : KT"); break;
		 case 2 : 
			 cleaner();
			    System.out.println("=========================================================================================================");
				System.out.println("                                               결제 페이지                                               ");
				System.out.println("---------------------------------------------------------------------------------------------------------");
				System.out.println();
				System.out.println();                       
				System.out.println("                         *****************************************************");
				System.out.println("                         *                  휴대폰 결제 페이지               *");
				System.out.println("                         *****************************************************");
				System.out.println("                            통신사 : SKT"); break;
		 case 3 :
			 cleaner();
			    System.out.println("=========================================================================================================");
				System.out.println("                                               결제 페이지                                               ");
				System.out.println("---------------------------------------------------------------------------------------------------------");
				System.out.println();                       
				System.out.println();                       
				System.out.println("                         *****************************************************");
				System.out.println("                         *                  휴대폰 결제 페이지               *");
				System.out.println("                         *****************************************************");
				System.out.println("                            통신사 : U+");break;
		default :
			cleaner();
			System.out.println("정확한 숫자를 기입해주세요"); return buyPhone();
		}                                                   
		
		System.out.println("                            휴대폰 번호를 입력해주세요");
		System.out.println("                            ( '-' 포함하여 입력해주세요)");
		System.out.println();
		System.out.println();
		String input = ScanUtill.nextLine();
		if(Pattern.matches(REGEXPHPATTERN, input)) {
			System.out.println("                            "+input + " 의 번호로 인증번호가 발송되었습니다. 인증번호를 입력해주세요");			
			System.out.println("\n\n\n\n");
		}else {
			System.out.println("정확한 휴대폰 번호를 입력해주세요");
			return buyPhone();
		}
		for(int i = 2; i >= 0;i--) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		cleaner();
		
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┳");
        System.out.println("┃                                   ┃");
        System.out.println("┃  [Web발신]                        ┃");
        System.out.println("┃ [(주)나홀로마켓] 결제 인증번호    ┃");
        System.out.println("┃ ["+Num+"]을 입력해주세요             ┃");
        System.out.println("┃                                   ┃");
        System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		System.out.println();
		System.out.println("인증번호 입력 >> ");
		int input2 = ScanUtill.nextInt();
		if(Num == input2) {
			System.out.println("인증번호가 확인되었습니다.");
		}else {
			System.out.println("잘못된 번호입니다.");
			for(int i = 1; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
			return buyPhone();
		}
		System.out.println();
		System.out.println("결제하시겠습니까?");
		System.out.println(" 1. 네   2. 아니오 ");
		System.out.println();
		System.out.println();
		switch(ScanUtill.nextInt()) {
			case 1 : 
				System.out.print("결제가 진행 중입니다. 잠시만 기다려주세요.");
		    	for(int i = 3; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
		    	System.out.println();
		    	System.out.println();
		    	System.out.print("결제가 완료되었습니다. 결제완료 페이지로 이동합니다.");
		    	for(int i = 2; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
		    	cleaner();
				return buyEnd2();
			case 2 :
				System.out.print("취소되었습니다. 결제페이지로 이동합니다.");
				for(int i = 2; i >= 0;i--) {
	    			try {
	    				TimeUnit.SECONDS.sleep(1);
	    			} catch (InterruptedException e) {
	    				e.printStackTrace();
	    			}
	    			System.out.print(".");
	    		}
		    	cleaner();

				return buy();
			default : return buyPhone();
		}
		
		
	}
	
	
	public int buyEnd1() {
		Date today = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //일반적으로 많이 씀
		Map<String,Object> listOrderno = dao.order_No(Controller.login_id);
		Map<String,Object> list = dao.name(Controller.login_id);
		Map<String,Object> list2 = dao.hp(Controller.login_id);
		Map<String,Object> list3 = dao.address(Controller.login_id);
		int pointupdate = dao.pointupdate(Controller.login_id);
		Map<String,Object> updatedpoint = dao.updatedpoint(Controller.login_id);
		
		System.out.println("=========================================================================================================");
		System.out.println("                                                결제 완료                                                ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println(" [결제] 가 완료되었습니다");
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                                주문정보");
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("                                           주문번호 : "+listOrderno.get("ORDER_NO"));
		System.out.println("                                           주문일시 : "+sdf3.format(today));
		System.out.println("                                             주문자 : "+ list.get("MEM_NAME"));
		System.out.println("                                 적립 후 보유포인트 : "+ updatedpoint.get("MEM_POINT")+" 점" );
		buydao.sumPrice();
		System.out.println("                                           배송장소 : "+ list3.get("MEM_ADDR"));
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                             상품 주문 내역");
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println();
		buydao.selectCart();
		System.out.println();
		System.out.println("* 상품 도착 예정일은 ["+(new Random().nextInt(3)+1) +"] 일 후 입니다 *");

		int orderdtresult = dao.updateorderdt1(Controller.login_id);
    	int ordersresult = dao.updateorders(Controller.login_id);
    	
		
    	System.out.println();
    	System.out.println();
    	System.out.println("                                            0. 메인화면으로 이동");
		System.out.println("=========================================================================================================");
		System.out.println();
		System.out.println();
    	switch(ScanUtill.nextInt()) {
    		case 0 : System.out.print("메인화면으로 이동합니다.");
    		for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
    			return View.PROD_LIST;
    			
    		default : System.out.print("메인화면으로 이동합니다."); 
    		for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
    	}
    	return View.PROD_LIST;
		
	}
	
	
	
	public int buyEnd2() {
		Date today = new Date();
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); //일반적으로 많이 씀
		Map<String,Object> listOrderno = dao.order_No(Controller.login_id);
		Map<String,Object> list = dao.name(Controller.login_id);
		Map<String,Object> list2 = dao.hp(Controller.login_id);
		Map<String,Object> list3 = dao.address(Controller.login_id);
		int pointupdate = dao.pointupdate(Controller.login_id);
		Map<String,Object> updatedpoint = dao.updatedpoint(Controller.login_id);
		
		
		System.out.println("=========================================================================================================");
		System.out.println("                                                결제 완료                                                ");
		System.out.println("---------------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println(" [결제] 가 완료되었습니다");
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                                주문정보");
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println();
		System.out.println("                                           주문번호 : "+listOrderno.get("ORDER_NO"));
		System.out.println("                                           주문일시 : "+sdf3.format(today));
		System.out.println("                                             주문자 : "+ list.get("MEM_NAME"));
		System.out.println("                                 적립 후 보유포인트 : "+ updatedpoint.get("MEM_POINT")+" 점" );
		buydao.sumPrice();
		System.out.println("                                           배송장소 : "+ list3.get("MEM_ADDR"));
		System.out.println();
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println("                                             상품 주문 내역");
		System.out.println("    -------------------------------------------------------------------------------------------------");
		System.out.println();
		buydao.selectCart();
		System.out.println();
		System.out.println("* 상품 도착 예정일은 ["+(new Random().nextInt(3)+1) +"] 일 후 입니다 *");
		
		int orderdtresult = dao.updateorderdt2(Controller.login_id);
    	int ordersresult = dao.updateorders(Controller.login_id);
    	
		
    	System.out.println();
    	System.out.println();
    	System.out.println("                                            0. 메인화면으로 이동");
		System.out.println("=========================================================================================================");
		System.out.println();
		System.out.println();
    	switch(ScanUtill.nextInt()) {
    		case 0 : System.out.print("메인화면으로 이동합니다.");
    		for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
    			return View.PROD_LIST;
    			
    		default : System.out.print("메인화면으로 이동합니다."); 
    		for(int i = 2; i >= 0;i--) {
    			try {
    				TimeUnit.SECONDS.sleep(1);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
    			System.out.print(".");
    		}
	    	System.out.println();
    	}
    	return View.PROD_LIST;
    	
    	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
