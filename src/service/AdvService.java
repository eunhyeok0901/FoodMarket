package service;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import controller.Controller;
import dao.AdvDAO;
import util.ScanUtill;
import util.View;

public class AdvService {
      private static AdvService instance = null;
      private AdvService() {}
      public static AdvService getInstance() {
         if(instance==null) instance = new AdvService();
         return instance;
      }
      
      AdvDAO dao = AdvDAO.getInstance();
      int num;
      
      List<Map<String, Object>> adv;
      Map<String, Object> temp;
      
      public void adv() {
         if(new Random().nextInt(3)==0) {            
            adv =  dao.advertisement();
            
            num = new Random().nextInt(adv.size());
            Controller.cleaner();
            System.out.println("=========================================================================================================");
            System.out.println("                                                   *광고*");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            
            Controller.enter(11);
            
            System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
            System.out.println();
            System.out.println("            [ "+adv.get(num).get("ADVER_TITLE")+" ] ");
            System.out.println();
            System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
            
            Controller.enter(11);
            
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("4초 후 지나갈 수 있습니다");
            
            for(int i = 4; i>0; i--) {
               try {
                  TimeUnit.SECONDS.sleep(1);
               } catch (InterruptedException e) {
                  e.printStackTrace();
               }
               System.out.println(i-1);
            }
            
            Controller.cleaner();
            System.out.println("=========================================================================================================");
            System.out.println("                                                   *광고*");
            System.out.println("---------------------------------------------------------------------------------------------------------");
            System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
            System.out.println(adv.get(num).get("ADVER_CONTENT"));
            System.out.println("★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★");
            answer:
               while(true) {
                  System.out.println("---------------------------------------------------------------------------------------------------------");
                  System.out.println("                                   1.광고상품 보기         0.광고 끄기");
                  System.out.println("---------------------------------------------------------------------------------------------------------");
                  System.out.print("▶ 번호 선택 >> ");
                  switch(ScanUtill.nextInt()) {
                  case 1  : 
                     Controller.pID = adv.get(num).get("PROD_ID");
                     Controller.view = View.PROD_DETAIL;     
                  case 0  : 
                  default : break answer;
                  }
               }
         }         
      }

}