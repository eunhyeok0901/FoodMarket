package service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import dao.ProdDAO;
import util.FormatUtil;
import util.JDBCUtil;
import util.ScanUtill;
import util.View;

public class ProdService {

         
         
         List<Map<String, Object>> list;
         
         static String sql;
         
         static JDBCUtil jdbc = JDBCUtil.getInstance();
         
         private static ProdService instance = null;
         
         private ProdService() {}
         public static ProdService getInstance() {
            if(instance == null) instance = new ProdService();
            return instance;
             }

         static ProdDAO dao = ProdDAO.getInstance();
         Object prodID;
         int detailNum;
         int qty;
         Object avg;
         Object name;

         int ten = 0;
         
         public int list() {
           Controller.cleaner();
            System.out.println("=========================================================================================================");
            System.out.println("                                               \t상품목록");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
            System.out.println("\t\t\t번호\t\t\t상품명\t\t\t\t상품가격");
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
             
            this.list = dao.list();
            int sip = list.size()/10;
            int il = list.size()%10;
//            for(Map<String, Object> item : this.list) {
//               item.put("NUM", count++);
//               System.out.print("\t\t\t"+FormatUtil.format(item.get("NUM"), 3, true)+"\t\t");   
//               System.out.print(FormatUtil.format(item.get("PROD_NAME"), 30, false)+"\t\t");   
//               System.out.println(FormatUtil.format(item.get("PROD_PRICE"), 5, true)+"원");   
//               System.out.println("     -----------------------------------------------------------------------------------------------     ");
//            }
            int end = ten== sip? list.size() : (ten+1)*10;
            for(int i = ten*10; i < end; i++   ) {               
               list.get(i).put("NUM", i+1);
               System.out.println();
               System.out.print("\t\t\t"+FormatUtil.format(list.get(i).get("NUM"), 3, true)+"\t\t");   
               System.out.print(FormatUtil.format(list.get(i).get("PROD_NAME"), 30, false)+"\t\t");   
               System.out.println(FormatUtil.format(list.get(i).get("PROD_PRICE"), 5, true)+"원");   
               System.out.println();
               System.out.println("     -----------------------------------------------------------------------------------------------     ");
            }
            
//            System.out.println("---------------------------------------------------------------------------------------------------------");
//            System.out.println("         1.상품조회         2.상품검색         3.로그인         4.회원가입         9.마이페이지");
//            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                      1.상품조회       2.상품검색        3.로그인         4.회원가입                     ");
            System.out.println("                      7.전페이지       8.다음페이지      9.마이페이지     0.종료");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.print("▶ 번호 선택 >> ");
            switch(ScanUtill.nextInt()) {
            case 0 : System.out.println("시스템을 종료합니다"); 
            return View.END;
               case 1:    
                  while(true) {
                     Controller.pID = null;
                     System.out.print("번호선택 >> ");
                     detailNum = ScanUtill.nextInt();
                     if(detailNum <= ten*10 || detailNum > end) {
                        System.out.println("잘못된 입력입니다.");                        
                     }else {
                        return View.PROD_DETAIL;
                     }
                  }
         case 2:
                 this.ten = 0;
                  return View.PROD_SEARCH;
                  
               case 3: 
                  if(Controller.login_id != null) {
                     System.out.println("\n로그인이 되어있는 상태입니다");
                    try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                    return View.PROD_LIST;
                  }
                  return View.MEMBER_LOGIN;
                  
               case 4:
                  if(Controller.login == true) {
                     System.out.println("\n로그인이 되어있는 상태입니다");
                     try {
                   TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
                     return View.PROD_LIST;
                  }
                  this.ten = 0;
                  return View.MEMBER_SIGNUP;
               case 7 :
                  if(ten == 0) {
                     System.out.println("전 페이지가 없습니다.");
                     try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                     return View.PROD_LIST;
                  }else {
                     ten--;
                     System.out.println("전 페이지로 이동합니다.");
                     try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                     return View.PROD_LIST;
                  }
                  
               case 8 :
                  if(sip == ten) {
                     System.out.println("다음 페이지가 없습니다.");
                     try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                     return View.PROD_LIST;
                  }else {
                     ten++;
                     System.out.println("다음 페이지로 이동합니다.");
                     try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  // TODO Auto-generated catch block
                  e.printStackTrace();
               }
                     return View.PROD_LIST;
                  }
               case 9:
                  if(Controller.login == true) {
                     this.ten = 0;
                       Controller.pastPage = View.PROD_LIST; return View.MYPAGE_INFO;

                  }else {
                     System.out.println("\n로그인이 필요한 페이지 입니다. 로그인 페이지로 이동합니다");
                     try {
                   TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                   // TODO Auto-generated catch block
                   e.printStackTrace();
                }
                  }
                  return View.MEMBER_LOGIN;
                  
               default:
                  return View.PROD_LIST;
            }   
         }        
         
         public int showDetail() {
            
            if(Controller.pID!=null) {
               prodID = Controller.pID;
            }else {
               prodID = list.get(detailNum-1).get("PROD_ID");
            }

            Map<String, Object> detail = dao.showDetail(prodID);
            Map<String, Object> score = dao.reviewScore(detail.get("PROD_ID"));
            Map<String, Object> poss = dao.checkOrderPoss(detail.get("PROD_ID"));
            this.name = detail.get("PROD_NAME");
            this.avg = score.get("AVG");
            Controller.cleaner();
            //전제 가운데정렬
            Controller.cleaner();
            System.out.println("=========================================================================================================");
            System.out.println("                                                상품 조회");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            Controller.enter(3);
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
            System.out.println("                                        "+detail.get("PROD_NAME"));
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
            System.out.println("\n                                       ● 상품가격 : " + detail.get("PROD_PRICE"));
            System.out.println();
            System.out.println("                                       ● 제조     : " + detail.get("PROD_BUYER"));
            System.out.println(); 
            System.out.println("                                       ● 별점     : " + score.get("AVG"));
            System.out.println();
            int num = Integer.parseInt(String.valueOf(poss.get("POSS")));
            
            if(num == 1) {
               System.out.println("                                       ● 상품상태 : 주문 가능");
            }else {
               System.out.println("                                       ● 상품상태 : 품절");
            }
            System.out.println();
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
            Controller.enter(3);
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                     1.구매         2.리뷰확인         9.마이페이지         0.뒤로가기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            while(true) {
               
               System.out.print("▶ 번호 선택 >> ");
               prod:
               switch(ScanUtill.nextInt()) {
               case 1:
                  if(!Controller.login) {
                     return View.PROD_NONMEMBER;
                  }else{
                     if(num==1) {
                        return View.PROD_BUY_MEMBER;                        
                     }else {
                        System.out.println("                                               품절 상품입니다!"); break prod;
                     }
                  }
               case 2:
                  if(!Controller.login) {
                     return View.PROD_NONMEMBER;
                  }else{
                     return View.REVIEWCHECK_MEMBER;
                  }
               case 9:
                  if(!Controller.login) {
                     return View.PROD_NONMEMBER;
                  }else{
                     Controller.pastPage = View.PROD_DETAIL; Controller.pID = prodID; 
                     return View.MYPAGE_INFO;  
                  }
               default:
                  return View.PROD_LIST;
                  
               }
            }
            
         }
         
         
         
         public int memberReview() {
            
            Controller.cleaner();
            
            System.out.println("=========================================================================================================");
            System.out.println("                                             \t리뷰게시판");
            System.out.println("---------------------------------------------------------------------------------------------------------");  
            Controller.enter(3);
            System.out.println("       상품명 : " + this.name + "                                   평균 별점 : " + this.avg +  "점");
            System.out.println("     -----------------------------------------------------------------------------------------------     ");  
            System.out.println("        별점  |                                   내용");
            System.out.println("     -----------------------------------------------------------------------------------------------     ");

            List<Map<String, Object>> review = dao.reviewList(prodID);
            
            int count = 1;
            for(Map<String, Object> item : review) {
               item.put("NUM", count++);
//               System.out.print(FormatUtil.format(item.get("NUM"), 5, true)+"\t  ");
               System.out.print("        "+FormatUtil.format(item.get("REVIEW_SCORE"), 2, true)+"점  |\t");
               System.out.println(FormatUtil.format(item.get("REVIEW_CONTENT"), 100, false));
               System.out.println("     -----------------------------------------------------------------------------------------------     ");
            }
            Controller.enter(3);
            while(true) {
                System.out.println("---------------------------------------------------------------------------------------------------------");
                 System.out.println("                                                0.돌아가기");
                 System.out.println("---------------------------------------------------------------------------------------------------------");
                 System.out.print("▶ 번호 선택 >> ");
                 switch(ScanUtill.nextInt()) {
                 case 0 : return View.PROD_DETAIL;
                 default : System.out.println("잘못된 입력입니다."); break;
                 }
              }          
            
         }
         
         public int memberBuy() {
           while(true){
              System.out.print("▶ 구매 수량 >> ");
              qty = ScanUtill.nextInt();
              if(qty < 1) {
                 System.out.println("\n                                           잘못된 입력입니다.\n");
              }else {
                 break;
              }
           }
           Map<String, Object> row = dao.checkOrderNO(Controller.login_id);
           String ordersNO = "";
            
            if(row == null) {//ORDER_POSS가 0인게 없다. 
               dao.insertOrders(Controller.login_id);//새로운 ORDER_NO 부여
               row = dao.checkOrderNO(Controller.login_id);
            }
            //새로운 ORDER_NO부여해야 하는 사람이든 아닌 사람이든 모든  ORDER_NO모이게 됨
            ordersNO = row.get("ORDER_NO").toString();
            
            //ORDERDT_NO/PRICE/QTY
            Map<String, Object> checkOrderProd = dao.checkOrderProd(ordersNO, prodID);//ORDERDT_NO
            Map<String, Object> price = dao.checkPrice(prodID.toString());  
            if(checkOrderProd != null) {               
               dao.updateOrderDetail(checkOrderProd.get("ORDERDT_NO"), qty, price.get("PROD_PRICE"));
            }else {
               dao.insertOrders(ordersNO, prodID, qty, price.get("PROD_PRICE"));
            }
            
            
            
            
            System.out.println("\n                                선택하신 상품이 장바구니에 등록되었습니다.\n");
             while(true) {
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("                                  1.장바구니로 이동         2.계속보기");
            System.out.println("---------------------------------------------------------------------------------------------------------");
               switch(ScanUtill.nextInt()) {
               case 1:
                  Controller.pastPage =  View.PROD_DETAIL; return View.CART;
               case 2:  
                  return View.PROD_LIST;
               default:
               System.out.println("잘못된 입력입니다.");
               break;
               }
             }   
            
         }
         
         public int nonMember() {
            System.out.println("1.로그인페이지로 이동\t0.돌아가기");
            switch(ScanUtill.nextInt()) {
            case 1:
               return View.MEMBER_LOGIN;
            default:
               return View.PROD_DETAIL; 
            }
            
            
         }
         public int prodSearch() {
               System.out.print("검색할 상품 >> ");
               String search = ScanUtill.nextLine();
               
               List<Map<String, Object>> prod = dao.prodSearch(search);
               
               Controller.cleaner();
               
               System.out.println("=========================================================================================================");
               System.out.println("                                                 상품검색");
               System.out.println("---------------------------------------------------------------------------------------------------------");
               
               if(prod == null || prod.size() == 0) {
                 Controller.enter(7);
                  System.out.println("                                   '" + search + "'에 해당되는 상품이 없습니다.");
                  Controller.enter(7);
               }else {
                 System.out.println("\n                                      '" + search + "'로 검색한 상품입니다.\n");

                  System.out.println("     -----------------------------------------------------------------------------------------------     ");
                  System.out.println("                 상품번호                      상품명                       상품가격");
                  System.out.println("     -----------------------------------------------------------------------------------------------     ");
                  for(int i = 0; i < prod.size(); i++) {
                     prod.get(i).put("NUM", i+1);
                     System.out.println();

                     System.out.printf("\t\t%s\t\t       %s\t%s\n",FormatUtil.format(prod.get(i).get("NUM"), 5, true),
                                                  FormatUtil.format(prod.get(i).get("PROD_NAME"), 25, false),
                                                  FormatUtil.format(prod.get(i).get("PROD_PRICE"), 10, true));
                     System.out.println("\n     -----------------------------------------------------------------------------------------------     ");
                  } 
                  Controller.enter(2);
               }
               System.out.println("---------------------------------------------------------------------------------------------------------");
               System.out.println("                              1.상품선택         2.다시검색         0.돌아가기");
               System.out.println("---------------------------------------------------------------------------------------------------------");
               while(true) { 
                  System.out.print("번호선택 >> ");
                  switch(ScanUtill.nextInt()) {
                  case 1 : 
                     System.out.print("상품 번호 선택>> ");
                     Controller.pID = prod.get(ScanUtill.nextInt()-1).get("PROD_ID");
                     return View.PROD_DETAIL;
                  case 2 : 
                     return View.PROD_SEARCH;
                  case 0 : 
                     return View.PROD_LIST;
                  default:
                     System.out.println("잘못된 입력입니다.");
                     break;
                  }
               }
            }
         
         }
   
   
   