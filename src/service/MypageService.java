package service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import controller.Controller;
import dao.MypageDAO;
import util.FormatUtil;
import util.ScanUtill;
import util.View;

public class MypageService {
   private static MypageService instance = null;
   private MypageService() {}
   public static MypageService getInstance() {
      if(instance==null) instance = new MypageService();
      return instance;
   }
   
   MypageDAO dao = MypageDAO.getInstance();
   
   //ID
   final String REGEXIDPATTERN = "^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$"; //시작은 영문으로만, '_'를 제외한 특수문자 안되며 영문, 숫자, '_'으로만 이루어진 5 ~ 12자 이하
   //PWD
   final String REGEXPWDPATTERN = "^(?=.*[A-Za-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,20}$"; //특수문자, 영문, 숫자 조합 (8~10 자리)
   //NAME
   final String REGEXNAMEPATTERN = "^[a-zA-Z]*|[가-힣]*$"; //한글 또는 영어만
   //BIRTH
   final String REGEXBIRTHPATTERN = "\\d{8}"; // 숫자만 8자리 
   //PhoneNUM
   final String REGEXPHPATTERN = "^\\d{3}-\\d{3,4}-\\d{4}$"; //EX 000-0000-0000
   //ADDRESS
   final String REGEXADDRESSPATTERN = "^[ㄱ-ㅎ가-힣0-9\s]*$"; //한글과 숫자만
   
   
   private Map<String, Object> temp;
   
   SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy년 MM월 dd일");
   SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
   
//   public int mypage() {
//      Controller.cleaner();
//      
//      System.out.println("=========================================================================================================");
//      System.out.println("                                                 마이페이지");
//      System.out.println("---------------------------------------------------------------------------------------------------------\n");
//      System.out.println("                    1.회원정보         2.구매내역         3.장바구니         7. 0.뒤로가기\n");
//      System.out.println("---------------------------------------------------------------------------------------------------------");
//      System.out.print("▶ 번호 선택 >> ");
//      switch(ScanUtill.nextInt()) {
//      case 1  : return View.MYPAGE_INFO;
//      case 2  : return View.MYPAGE_BUYLIST;
//      case 3  : return View.CART;
//      case 0  : return View.SHOP;
//      default : return View.SHOP;
//      }
//   }
   
   public int info() {
      Controller.cleaner();
      System.out.println("=========================================================================================================");
      System.out.println("                                                 회원정보");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      Map<String, Object> info = dao.info(Controller.login_id);
      System.out.println();
      System.out.println("ID       : "+info.get("MEM_ID")+"\n");
      System.out.println("이름     : "+info.get("MEM_NAME")+"\n");
      System.out.println("HP       : "+info.get("MEM_HP")+"\n");
      System.out.println("주소     : "+info.get("MEM_ADDR")+"\n");
      System.out.println("생년월일 : "+sdf1.format(info.get("MEM_BIRTH"))+"\n");
      System.out.println("포인트   : "+info.get("MEM_POINT")+"점\n");  
      System.out.println("---------------------------------------------------------------------------------------------------------");
      System.out.println("        1.회원정보수정         2.구매내역         3.장바구니          4.로그아웃          0.뒤로가기");
      System.out.println("---------------------------------------------------------------------------------------------------------");
       while(true) {
        System.out.print("▶ 번호 선택 >> ");
        switch(ScanUtill.nextInt()) {
        case 1  : return View.MYPAGE_INFO_UPDATE;
        case 2  : return View.MYPAGE_BUYLIST;
        case 3  : Controller.pastPage = View.MYPAGE_INFO; return View.CART;
        case 4  : 
            System.out.println("\n                                            로그아웃 하시겠습니까?\n");
             System.out.println();
             System.out.println("---------------------------------------------------------------------------------------------------------");
             System.out.println("                                            1.예         2.아니오");
             System.out.println("---------------------------------------------------------------------------------------------------------");
             while(true) {               
                System.out.print("▶ 번호 선택 >> ");
                switch(ScanUtill.nextInt()) {
                case 1 : 
                  Controller.login = false;
                  Controller.login_id = null;
                  System.out.println("\n                                           로그아웃 되었습니다.\n");
                  try {
                TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
             }
                   return View.PROD_LIST;
                case 2 : return View.MYPAGE_INFO;
                default : System.out.println("잘못된 입력입니다."); break;
                }
             } 
        case 0  : return View.PROD_LIST;
        default : System.out.println("잘못된 입력입니다.");
        }
      }
   }
   
   public int infoUpdate() {
         Controller.cleaner();
         System.out.println("=========================================================================================================");
         System.out.println("                                                 회원정보수정");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         Map<String, Object> info = dao.info(Controller.login_id);
         System.out.println();
         System.out.println("ID       : "+info.get("MEM_ID")+"\n");
         System.out.println("이름     : "+info.get("MEM_NAME")+"\n");
         System.out.println("HP       : "+info.get("MEM_HP")+"\n");
         System.out.println("주소     : "+info.get("MEM_ADDR")+"\n");
         System.out.println("생년월일 : "+sdf1.format(info.get("MEM_BIRTH"))+"\n");
         System.out.println("포인트   : "+info.get("MEM_POINT")+"점\n");  
         System.out.println("---------------------------------------------------------------------------------------------------------");
         System.out.println("       1.이름         2.HP         3.주소         4.생년월일         5.비밀번호         0.돌아가기");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         while(true) {
         System.out.print("▶ 번호 선택 >> ");
         switch(ScanUtill.nextInt()) {
         case 1  : 
            while(true) {
               System.out.print("▶ 변경 이름 >> ");
               String name = ScanUtill.nextLine();
               if(Pattern.matches(REGEXNAMEPATTERN, name)) {
                  int result = dao.memUpdate("MEM_NAME", name ,Controller.login_id);
                  if(result==1) {
                     System.out.println("이름이 변경되었습니다.");
                  }else {
                     System.out.println("이름 변경 실패!");
                  }
                  String enter = ScanUtill.nextLine();
                  return View.MYPAGE_INFO;
               }else {
                  System.out.println("이름은 한글 또는 영어만 입력 가능합니다.");
               }
            }
         case 2  :
            while(true) {
               System.out.print("▶ 변경 HP(000-0000-0000) >> ");
               String hp = ScanUtill.nextLine();
               if(Pattern.matches(REGEXPHPATTERN, hp)) {
                  int result = dao.memUpdate("MEM_HP", hp ,Controller.login_id);
                  if(result==1) {
                     System.out.println("핸드폰 번호가 변경되었습니다.");
                  }else {
                     System.out.println("핸드폰 번호 변경 실패!");
                  }
                  String enter = ScanUtill.nextLine();
                  return View.MYPAGE_INFO;
               }else {
                  System.out.println("핸드폰 번호는 '-'와 함께 입력해주세요.");
               }
            }
         case 3  :
            while(true) {
               System.out.print("▶ 변경 주소 >> ");
               String addr = ScanUtill.nextLine();
               if(Pattern.matches(REGEXADDRESSPATTERN, addr)) {
                  int result = dao.memUpdate("MEM_ADDR", addr ,Controller.login_id);
                  if(result==1) {
                     System.out.println("주소가 변경되었습니다.");
                  }else {
                     System.out.println("주소 변경 실패!");
                  }
                  String enter = ScanUtill.nextLine();
                  return View.MYPAGE_INFO;
               }else {
                  System.out.println("주소는 한글과 숫자만 입력가능합니다.");
               }
            }
         case 4  :
            while(true) {
               System.out.print("▶ 변경 생년월일 >> ");
               String birth = ScanUtill.nextLine();
               if(Pattern.matches(REGEXBIRTHPATTERN, birth)) {
                  int result = dao.memUpdate("MEM_BIRTH", birth ,Controller.login_id);
                  if(result==1) {
                     System.out.println("생년월일이 변경되었습니다.");
                  }else {
                     System.out.println("생년월일 변경 실패!");
                  }
                  String enter = ScanUtill.nextLine();
                  return View.MYPAGE_INFO;
               }else {
                  System.out.println("생년월일은 숫자 8자리여야 합니다.");
               }
            }
         case 5  : return View.MYPAGE_PWDUPDATE; 
         case 0  : return View.MYPAGE_INFO;
         default : System.out.println("잘못된 입력입니다.");
         }
         }
   }
   
   public int pwdUpdate() {
      System.out.print("▶ 현재 비밀번호 >> ");
      String pwd = ScanUtill.nextLine();
      
      Map<String, Object> info = dao.info(Controller.login_id);
      
      if(info.get("MEM_PWD").equals(pwd)) {
         while(true) {
            System.out.print("▶ 새 비밀번호 >> ");
            pwd = ScanUtill.nextLine();
            
            boolean pwdResult = Pattern.matches(REGEXPWDPATTERN, pwd);
            
            if(pwdResult) {
               int result = dao.memUpdate("MEM_PWD", pwd ,Controller.login_id);
               if(result==1) {
                  System.out.println("비밀번호가 변경되었습니다.");
                  
               }
               else {
                  System.out.println("비밀번호 변경 실패!");
               }
               String enter = ScanUtill.nextLine();
               return View.MYPAGE_INFO;
            }
            System.out.println("비밀번호는 특수문자, 영문, 숫자가 조합된 8~10 자리여야 합니다.");

            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                     1.다시시도         0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            while(true) {               
               System.out.print("▶ 번호 선택 >> ");
               if(ScanUtill.nextInt()==0) {
                  return View.MYPAGE_INFO;
               }else System.out.println("잘못된 입력입니다.");
            }            
         }
         
      }else {
         System.out.println("비밀번호가 틀렸습니다.");
         while(true) {
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                     1.다시시도         0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {
            case 1 : return View.MYPAGE_PWDUPDATE;
            case 0 : return View.MYPAGE_INFO;
            default : System.out.println("잘못된 입력입니다."); break;
            }
         }
      }
   }
   
   public int buyList() {
      Controller.cleaner();

      System.out.println("=========================================================================================================");
      System.out.println("                                                 구매내역");
      System.out.println("---------------------------------------------------------------------------------------------------------");

      Controller.enter(3);
      
      List<Map<String, Object>> buyList = dao.buyList(Controller.login_id);
      
      if(buyList!=null) {
          System.out.println("     -----------------------------------------------------------------------------------------------     ");
          System.out.println("             순번                  날짜                  주문번호                  주문상태");
          System.out.println("     -----------------------------------------------------------------------------------------------     ");
         for(int i = 0; i < buyList.size(); i++) {
            buyList.get(i).put("NUM", i+1);
            System.out.println();
            System.out.print("             "+buyList.get(i).get("NUM"));
            System.out.print("             "+sdf1.format(buyList.get(i).get("ORDERDT_DATE")));
            System.out.print("               "+buyList.get(i).get("ORDER_NO")+"                   ");
            if(buyList.get(i).get("ORDERDT_STATE").toString().equals("1")) System.out.println("결제 완료");
            else if(buyList.get(i).get("ORDERDT_STATE").toString().equals("2")) System.out.println("배송 중");
            else if(buyList.get(i).get("ORDERDT_STATE").toString().equals("3")) System.out.println("배송 완료");
            System.out.println();
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
         }      
         Controller.enter(3);
         
         while(true) {
           System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                 1.상세내역보기         0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
             System.out.print("▶ 번호 선택 >> ");
             switch(ScanUtill.nextInt()) {
             case 1 : 
                while(true) {
                   System.out.print("▶ 순번 선택 >> ");
                   int num = ScanUtill.nextInt();
                   if(num<=buyList.size()&&num>0) {
                      temp = buyList.get(num-1);
                      return View.MYPAGE_BUYLIST_PROD;                      
                   }
                   System.out.println("잘못된 입력입니다.");
                }
             case 0 : return View.MYPAGE_INFO;
             default : System.out.println("잘못된 입력입니다."); break;
             }
         }
      }else {
         System.out.println("\n구매내역이 없습니다!\n");
          while(true) {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                              0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            
            Controller.enter(5);
            
            if(ScanUtill.nextInt()==0) {
               break;
            }
              System.out.println("잘못된 입력입니다!");
           }
           return View.MYPAGE_INFO;
      }
   }
   
   public int buyListProd() {
      Controller.cleaner();

      List<Map<String, Object>> prodList = dao.buyListProd (Controller.login_id, this.temp.get("ORDER_NO"));
      System.out.println("=========================================================================================================");
      System.out.println("                                               구매상세내역");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      
      Controller.enter(3);
      
      System.out.println("     -----------------------------------------------------------------------------------------------     ");
      System.out.println("        번호                상품명                 수량        가격             날짜");
      System.out.println("     -----------------------------------------------------------------------------------------------     ");
      for(int i = 0; i < prodList.size(); i++) {
         prodList.get(i).put("NUM", i+1);

         System.out.println();
         System.out.print("     ");
         System.out.printf("%s\t   %s\t%s\t%s\t%s\n", FormatUtil.format(prodList.get(i).get("NUM"), 5, true),
                                                      FormatUtil.format(prodList.get(i).get("PROD_NAME"), 25, false),
                                                      FormatUtil.format(prodList.get(i).get("ORDERDT_QTY"), 5, true),
                                                      FormatUtil.format(prodList.get(i).get("ORDERDT_PRICE"), 10, true),
                                                      FormatUtil.format(sdf1.format(prodList.get(i).get("ORDERDT_DATE")), 15, false));
         System.out.println();
         System.out.println("     -----------------------------------------------------------------------------------------------     ");
      }

      Controller.enter(3);
      
      while(true) {
         System.out.println("---------------------------------------------------------------------------------------------------------");
         System.out.println("                        1.리뷰등록         2.리뷰확인         0.돌아가기");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         System.out.print("▶ 번호 선택 >> ");
         switch(ScanUtill.nextInt()) {
         case 1 : 
             while(true) {
                System.out.print("▶ 리뷰 등록 할 상품번호 >> ");
                int num = ScanUtill.nextInt();
                if(num<=prodList.size()&&num>0) {
                   if(prodList.get(num-1).get("ORDERDT_STATE").toString().equals("3")) {
                      temp = prodList.get(num-1);
                      return View.MYPAGE_REVIEW_INSERT;                                           
                   }
                   else {
                      System.out.println("\n                       배송 완료된 상품만 리뷰를 등록할 수 있습니다.\n");
                      try {
                   TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
                      return View.MYPAGE_BUYLIST_PROD;
                   }
                }
                System.out.println("잘못된 입력입니다.");
             }
         case 2 : 
            while(true) {
               System.out.print("▶ 리뷰 확인 할 상품번호 >> ");
               int num = ScanUtill.nextInt();
               if(num<=prodList.size()&&num>0) {
                  temp = prodList.get(num-1);
                  return View.MYPAGE_REVIEW_CHECK;                      
               }
               System.out.println("잘못된 입력입니다.");
            }
         case 0  : return View.MYPAGE_BUYLIST;
         default : System.out.println("잘못된 입력입니다."); break;
         }
     }   
   }
   
   public int reviewInsert() {
      Controller.cleaner();
      
      Map<String, Object> review = dao.reviewCheck(temp.get("ORDERDT_NO"));
      System.out.println("=========================================================================================================");
      System.out.println("                                               리뷰 등록");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      

      if(review == null||review.size()==0) {
        Controller.enter(5);
         List<Object> param = new ArrayList<>();
         System.out.println("상품명 : " + temp.get("PROD_NAME"));
         Controller.enter(3);
         System.out.print("▶ 리뷰 내용 >> ");
         param.add(ScanUtill.nextLine());
         
         int score;
         while(true) {
            System.out.print("\n▶ 별점(1~5) >> ");
            score = ScanUtill.nextInt();
            if(score<=5 && score >= 1) break;
            System.out.println("점수는 1점에서 5점 사이로 입력해주세요.");
         }
         param.add(score);
         param.add(Controller.login_id);
         param.add(temp.get("ORDERDT_NO"));
         
         int result = dao.reviewInsert(param);
         
         System.out.println();
         System.out.println("                                        리뷰가 등록되었습니다.");
         Controller.enter(3);
      }else {         
         Controller.enter(5);
         System.out.println("\n                                      이미 리뷰를 등록한 상품입니다.\n");
         Controller.enter(5);
      }
      
      
      while(true) {
         System.out.println("---------------------------------------------------------------------------------------------------------");
          System.out.println("                                       1.리뷰확인         0.돌아가기");
          System.out.println("---------------------------------------------------------------------------------------------------------");
          System.out.print("▶ 번호 선택 >> ");
         switch(ScanUtill.nextInt()) {
         case 1 : return View.MYPAGE_REVIEW_CHECK;
         case 0 : return View.MYPAGE_BUYLIST_PROD;
         default : System.out.println("잘못된 입력입니다."); break;
         }
         }      
   }
   
   public int reviewCheck() {
      Controller.cleaner();
      
      Map<String, Object> review = dao.reviewCheck(temp.get("ORDERDT_NO"));
      System.out.println("=========================================================================================================");
      System.out.println("                                               리뷰 확인");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      
      
      if(review == null||review.size()==0) {
         Controller.enter(5);
         System.out.println("\n                                      등록된 리뷰가 없습니다.\n");
         
         Controller.enter(5);
         
         while(true) {
           System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                   1.리뷰등록         0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {
            case 1 : return View.MYPAGE_REVIEW_INSERT;
            case 0 : return View.MYPAGE_BUYLIST_PROD;
            default : System.out.println("잘못된 입력입니다."); break;
            }
         }
      }else {
         Controller.enter(3);
        System.out.println();
        System.out.println("상품명 : " + review.get("PROD_NAME")+"\n");
         System.out.println("작성자 : " + review.get("MEM_ID")+"\n");
         System.out.println("날짜   : " + sdf1.format(review.get("REVIEW_DATE"))+"\n");
         System.out.println("내용   : " + review.get("REVIEW_CONTENT")+"\n");
         System.out.println("별점   : " + review.get("REVIEW_SCORE") + "점"+"\n");

         Controller.enter(3);
         
         while(true) {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                         1.리뷰수정         2.리뷰삭제         0.돌아가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {
            case 1 : return View.MYPAGE_REVIEW_UPDATE;
            case 2 : return View.MYPAGE_REVIEW_DELETE;
            case 0 : return View.MYPAGE_BUYLIST_PROD;
            default : System.out.println("잘못된 입력입니다."); break;
            }
         }
      }
   }
   
   public int reviewUpdate() {
      Controller.cleaner();
      
      Map<String, Object> review = dao.reviewCheck(temp.get("ORDERDT_NO"));
      System.out.println("=========================================================================================================");
      System.out.println("                                               리뷰 수정");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      Controller.enter(3);
      System.out.println("상품명 : " + review.get("PROD_NAME")+"\n");
      System.out.println("작성자 : " + review.get("MEM_ID")+"\n");
      System.out.println("날짜   : " + sdf1.format(review.get("REVIEW_DATE"))+"\n");
      System.out.println("내용   : " + review.get("REVIEW_CONTENT")+"\n");
      System.out.println("별점   : " + review.get("REVIEW_SCORE") + "점"+"\n");
      Controller.enter(3);
      System.out.println("---------------------------------------------------------------------------------------------------------");
      System.out.println();
       System.out.print("▶ 수정내용 : ");
       String contents = ScanUtill.nextLine();
      System.out.print("\n▶ 수정별점 : ");
      int score = ScanUtill.nextInt();
      System.out.println("\n---------------------------------------------------------------------------------------------------------");
      int result = dao.reviewUpdate(Controller.login_id, contents, score, temp.get("ORDERDT_NO"));
      
      if(result==1) System.out.println("리뷰수정이 완료되었습니다.");
      else System.out.println("리뷰 수정 실패!");
      
       while(true) {
          System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.println("                                   1.리뷰확인         0.돌아가기");
           System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.print("▶ 번호 선택 >> ");
           switch(ScanUtill.nextInt()) {
           case 1 : return View.MYPAGE_REVIEW_CHECK;
           case 0 : return View.MYPAGE_BUYLIST_PROD;
           default : System.out.println("잘못된 입력입니다."); break;
           }
        }
   }
   
   public int reviewDelete() {      
      System.out.println("리뷰를 삭제하시겠습니까?");
      System.out.println("---------------------------------------------------------------------------------------------------------");
       System.out.println("                                          1.예         0.아니오");
       System.out.println("---------------------------------------------------------------------------------------------------------");
       answer:
       while(true) {
          System.out.print("▶ 번호 선택 >> ");
          switch(ScanUtill.nextInt()) {
          case 1 :break answer;
          case 0 :return View.MYPAGE_BUYLIST_PROD;
          default : System.out.println("잘못된 입력입니다."); break;
          }
       }
      int result = dao.reviewDelete(temp.get("ORDERDT_NO"));
       
      if(result==1) System.out.println("리뷰삭제가 완료되었습니다.");
      else System.out.println("리뷰 삭제 실패!");
       
       while(true) {
          System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.println("                                                0.돌아가기");
           System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.print("▶ 번호 선택 >> ");
           switch(ScanUtill.nextInt()) {
           case 0 : return View.MYPAGE_BUYLIST_PROD;
           default : System.out.println("잘못된 입력입니다."); break;
           }
        }
   }

   

}