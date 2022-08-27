package service;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import controller.Controller;
import dao.MemberServiceDAO;
import util.ScanUtill;
import util.View;

public class MemberService {
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
    //final String REGEXADDRESSPATTERN = "^[ㄱ-ㅎ가-힣0-9]*$"; //한글과 숫자만
    
    MemberServiceDAO memberserviceDAO = new MemberServiceDAO();
    
    
   
    
    
   
//   private void cartQtyUpdate() {
//      System.out.println("1.수량 변경 2.삭제 ");
//      int cartQtyUpdate = new Scanner(System.in).nextInt();
//      
//      switch(cartQtyUpdate) {
//      case 1: cartUpdate();
//         break;
//      case 2: cartDelete();
//         break;
//      }
//      
//   }



public int signUp() {
   Controller.cleaner();
   
   System.out.println("=========================================================================================================");
   System.out.println("                                                회원가입");
   System.out.println("---------------------------------------------------------------------------------------------------------");
   System.out.println();
         
         String id;
         String pwd;
         String name;
         String birth;
         String ph;
         String address;
         System.out.println("                                              * 주의사항 *");
         System.out.println("                 ┌─────────────────────────────────────────────────────────────────┐");
         System.out.println("                 │ID          : 영어와 숫자로 이루어진 5~12자리(특수문자 '_' 가능) │" );
         System.out.println("                 │비밀번호    : 특수문자, 영어, 숫자를 조합한 8~10 자리            │");
         System.out.println("                 │이름        : 한글 또는 영어                                     │");
         System.out.println("                 │생년월일    : 숫자 8자리 (ex: 19990101)                          │");
         System.out.println("                 │핸드폰 번호 : '-'와 함께 입력 (ex: 000-0000-0000)                │");
         System.out.println("                 │주소        : 거주지 자세하게 입력                               │");
         System.out.println("                 └─────────────────────────────────────────────────────────────────┘");
         
         id:
        while(true) {
//                  System.out.println("    ┌────────────────────────────────────────────────────────┐");
//                  System.out.println(" ID │ 특수문자X 영어, 숫자, '_'으로만 이루어진 5 ~ 12자 이하 │" );
//                  System.out.println("    └────────────────────────────────────────────────────────┘");
              System.out.println();
                  System.out.print(" ▶ ID >> ");
                  id = new Scanner(System.in).nextLine();
                  boolean regexID = Pattern.matches(REGEXIDPATTERN, id);
                  if(!regexID) {
                     System.out.println();
                     System.out.println("ID는 영어와 숫자로 이루어진 5~12자로 입력해주세요." );
//                     System.out.println(" ┌───────────────────────────┐");
//                     System.out.println(" │ ID를 잘못 입력 하였습니다 │");
//                     System.out.println(" └───────────────────────────┘");
                  }else{
                     boolean idCheck = memberserviceDAO.idCheck(id);
                     if(idCheck) {
                        break id;
                     }
                  }

            
            
//            while(rs.next()) {
//               String memID = rs.getString("MEM_ID");
//               
//               if(memID.equals(id)) {
//                  System.out.println("존재하는 ID입니다");
//                  break;
//               }
//               
//            }
            

         }
         pwd:
            while(true) {
//                 System.out.println("          ┌─────────────────────────────────────┐");
//                  System.out.println(" PASSWORD │ 특수문자, 영문, 숫자 조합 8~10 자리 │");
//                  System.out.println("          └─────────────────────────────────────┘");
               System.out.println();
                  System.out.print(" ▶ 비밀번호 >> ");
                  pwd = new Scanner(System.in).nextLine();
                  boolean regexPWD = Pattern.matches(REGEXPWDPATTERN, pwd);
                  if(!regexPWD) {
                     System.out.println();
                     System.out.println("비밀번호는 특수문자, 영문, 숫자를 조합한 8~10자리로 입력해주세요.");
                  }else {
                     break pwd;
                  }
                  
               }
         name:
             while(true) {
//                  System.out.println("      ┌──────────────────┐");
//                   System.out.println(" 이름 │ 한글 또는 영어만 │");
//                   System.out.println("      └──────────────────┘");
                System.out.println();
                   System.out.print(" ▶ 이름 >> ");
                   name = new Scanner(System.in).nextLine();
                   boolean regexNAME = Pattern.matches(REGEXNAMEPATTERN, name);
                   if(!regexNAME) {
                      System.out.println();
                      System.out.println("이름은 한글 또는 영어로 입력해주세요.");
                   }else {
                      break name;
                   }
                   
                }
         birth:
            while(true) {
//                 System.out.println("          ┌─────────────────────────┐");
//                  System.out.println(" 생년월일 │ 숫자 8자리 ex: 19990101 │");
//                  System.out.println("          └─────────────────────────┘");
                System.out.println();
                  System.out.print(" ▶ 생년월일 >> ");
                  birth = new Scanner(System.in).nextLine();
                  boolean regexBIRTH = Pattern.matches(REGEXBIRTHPATTERN, birth);
                  if(!regexBIRTH) {
                     System.out.println();
                     System.out.println("생년월일은 8자리로 입력해주세요.");
                  }else {
                     break birth;
                  }
                  
               }
         hp:
             while(true) {
//                  System.out.println("             ┌───────────────────┐");
//                   System.out.println(" 핸드폰 번호 │ ex: 000-0000-0000 │");
//                   System.out.println("             └───────────────────┘ ");
                 System.out.println();
                   System.out.print(" ▶ 핸드폰 번호 >> ");
                   ph = new Scanner(System.in).nextLine();
                   boolean regexHp = Pattern.matches(REGEXPHPATTERN, ph);
                   if(!regexHp) {
                      System.out.println();
                      System.out.println("핸드폰 번호는 000-0000-0000에 맞춰서 입력해주세요.");
                   }else {
                      break hp;
                   }
                   
                }
         address:
            while(true) {
//                 System.out.println("      ┌─────────────┐");
//                  System.out.println(" 주소 │ 거주지 입력 │");
//                  System.out.println("      └─────────────┘");
                System.out.println();
                  System.out.print(" ▶ 주소 >>");
                  address = new Scanner(System.in).nextLine();
                  break address;
               }
            
         int insertResult = memberserviceDAO.insertsignUp(id, pwd, name, address, birth, ph);
         
         if(insertResult > 0) {
            System.out.println();
             System.out.println("                                           회원가입이 완료되었습니다!");
             System.out.println();
            while(true) {
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.println("                                          0.메인화면으로 이동");
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.print("▶ 번호 선택 >> ");
               switch(ScanUtill.nextInt()) {
               case 0 : return View.PROD_LIST;
               default : System.out.println("잘못된 입력입니다."); break;
               }
            } 
         }else{
             System.out.println("                                                 회원 가입 실패! ");
           return View.PROD_LIST;
         }
         

      
   }



   public int login() {   
    //  loginExit:
      Controller.cleaner();
         System.out.println("=========================================================================================================");
         System.out.println("                                                 로그인");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         Controller.enter(3);
      while(true) {
//         System.out.println(" ┌───────────────────┐");
//          System.out.println(" │ ID를 입력해주세요 │");
//          System.out.println(" └───────────────────┘");
          System.out.print("▶ ID 입력 >> ");
          String id = new Scanner(System.in).nextLine();
//          System.out.println(" ┌─────────────────────────┐");
//          System.out.println(" │ PASSWORD를 입력해주세요 │");
//          System.out.println(" └─────────────────────────┘");
          System.out.print("\n▶ PASSWORD 입력 >>");
          String pwd = new Scanner(System.in).nextLine();
          Controller.enter(3);
          
         String loginId = memberserviceDAO.login(id, pwd);
         
         Controller.enter(3);

         if(loginId != null) {
            Controller.login_id = loginId;
            while(true) {
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.println("                                          0.메인화면으로 이동");
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.print("▶ 번호 선택 >> ");
               switch(ScanUtill.nextInt()) {
               case 0 : return View.PROD_LIST;
               default : System.out.println("잘못된 입력입니다."); break;
               }
            } 
         }else {
            while(true) {
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.println("                                 1.다시시도         0.메인화면으로 이동");
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.print("▶ 번호 선택 >> ");
               switch(ScanUtill.nextInt()) {
               case 1 : return View.MEMBER_LOGIN;
               case 0 : return View.PROD_LIST;
               default : System.out.println("잘못된 입력입니다."); break;
               }
            } 
         }

      }
         
         
      
    
      
   }



   public int cartUpdate() {
	      
//     if(Controller.login_id == null || Controller.login == false) {
//        System.out.println("로그인후 이용가능한 서비스 입니다");
//        return;
//     }
     
     
//    System.out.println(" ┌─────────────────────────────────┐");
//    System.out.println(" │ 수정할 주문번호을 입력해 주세요 │");
//    System.out.println(" └─────────────────────────────────┘");
    System.out.print("▶ 주문 번호 >>");
     String updateNum = new Scanner(System.in).nextLine();
     
//     System.out.println(" ┌─────────────────────┐");
//     System.out.println(" │ 1.수량 변경 2.삭제  │");
//     System.out.println(" └─────────────────────┘");
        System.out.println();
        update:
        while(true){           
           System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.println("                                         1.수량 변경         2.삭제");
           System.out.println("---------------------------------------------------------------------------------------------------------");
           System.out.print("▶ 번호 선택 >>");
           
           int selectNum = new Scanner(System.in).nextInt();
           Map<String, Object> price = memberserviceDAO.selectPrice(updateNum);
           switch(selectNum) {
           case 1:   
//        System.out.println(" ┌────────────────────────────────────────┐");
//        System.out.println(" │ 수정할 주문번호의 수량을 입력해 주세요 │");
//        System.out.println(" └────────────────────────────────────────┘");
              System.out.print("▶ 상품 수량 >> ");
              int updateQty = new Scanner(System.in).nextInt();
              int result = memberserviceDAO.cartUpdate(updateNum, updateQty, price.get("PROD_PRICE").toString());
              if(result > 0) {
                 System.out.println("                                        수량이 변경되었습니다!");
              }else {
                 System.out.println("                                           수량 변경 실패! ");
              }
                 break update;
           case 2:   cartDelete(updateNum);
                  break update;
           default : System.out.println("잘못된 입력입니다."); break;
           }
        }
     System.out.println();   
      System.out.println("---------------------------------------------------------------------------------------------------------");
      System.out.println("                                           0.장바구니로 이동");
      System.out.println("---------------------------------------------------------------------------------------------------------");
      System.out.println();
      while(true) {               
         System.out.print("▶ 번호 선택 >> ");
         switch(ScanUtill.nextInt()) {          
         case 0: return View.CART;
         default : System.out.println("잘못된 입력입니다."); break;
         }
      }
     
     
     
     
     
  }




   public void cartDelete(String updateNum) {
      
//      if(Controller.login_id == null || Controller.login == false) {
//         System.out.println("로그인후 이용가능한 서비스 입니다");
//         return;
//      }
      
      
      String deleteNum = updateNum;
      int result = memberserviceDAO.cartDelete(deleteNum);
      
      if(result > 0) {
          System.out.println("                                             삭제되었습니다! ");
      }else {
          System.out.println("                                                삭제 실패!");
      } 
   }



   public int selectCart() {
     
//      if(Controller.login_id == null || Controller.login == false) {
//         System.out.println("로그인후 이용가능한 서비스 입니다");
//         
//      }
      
     Controller.cleaner();
      
     System.out.println("=========================================================================================================");      
      System.out.println("                                                 장바구니");
     System.out.println("---------------------------------------------------------------------------------------------------------");
      int count = memberserviceDAO.selectCart();
      
      memberserviceDAO.sumPrice();
      
      if(count>0) {
         Controller.enter(3);
         System.out.println("---------------------------------------------------------------------------------------------------------");
         System.out.println("                           1.장바구니 수정         2.구매         0.돌아가기");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         while(true) {               
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {
            case 1: return View.CART_UPDATE;            
            case 2: return View.BUY_PAGE;            
            case 0: return Controller.pastPage;
            default : System.out.println("잘못된 입력입니다."); break;
            }
         }         
      }else {
         System.out.println("---------------------------------------------------------------------------------------------------------");
         System.out.println("                                             0.돌아가기");
         System.out.println("---------------------------------------------------------------------------------------------------------");
         while(true) {               
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {          
            case 0: return Controller.pastPage;
            default : System.out.println("잘못된 입력입니다."); break;
            }
         }
      }
     // System.out.println(Controller.login_id + "님의 주문내역" );
      

      
   }


}