package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class ProdDAO {
   
   private static ProdDAO instance = null; 
   
   private ProdDAO() {}
   public static ProdDAO getInstance() {
      if(instance == null) instance = new ProdDAO();
      return instance;
   }
   
   JDBCUtil jdbc = JDBCUtil.getInstance();
   
   public List<Map<String, Object>> list() {
      return jdbc.selectList("SELECT * FROM PROD");
   }
   
   public Map<String, Object> showDetail(Object prodID){
      return jdbc.selectOne("SELECT * FROM PROD WHERE PROD_ID = '" + prodID +"'");
   }
   
   public Map<String, Object> reviewScore(Object prodID) {
      return jdbc.selectOne("SELECT B.PROD_ID," + 
            "                     ROUND(AVG(A.REVIEW_SCORE),2) AS AVG" + 
            "                FROM REVIEW A, ORDERDETAIL B" + 
            "               WHERE A.ORDERDT_NO = B.ORDERDT_NO" + 
            "              AND B.PROD_ID= '"+ prodID +"'" + 
            "               GROUP BY B.PROD_ID");
   }
   
   public List<Map<String, Object>> reviewList(Object prodID) {
      return jdbc.selectList( "SELECT B.PROD_ID," +
              "                  A.REVIEW_CONTENT," +
              "                    A.REVIEW_SCORE" +
              "               FROM REVIEW A, ORDERDETAIL B" +
              "                WHERE A.ORDERDT_NO = B.ORDERDT_NO" +
              "                AND B.PROD_ID = '" + prodID + "'");
   }
   
   
   public Map<String, Object> checkOrderNO(String login_id){
      return jdbc.selectOne("SELECT ORDER_NO" + 
            "                FROM ORDERS" + 
            "               WHERE ORDER_POSS = 0" + 
            "                 AND MEM_ID = '" + login_id + "'");
   }
   
   public int insertOrders(String login_id){
      return jdbc.insert("INSERT INTO ORDERS (ORDER_NO, MEM_ID, ORDER_POSS) VALUES (SEQUENCE_ORDER.NEXTVAL, '"+ login_id+"',0)");
      
   }
   
   
   public Map<String, Object> checkPrice(String prodID){
      return jdbc.selectOne("SELECT PROD_PRICE FROM PROD WHERE PROD_ID = '" + prodID + "'");
   }
   
   public int insertOrders(String ordersNO, Object prodID, int qty, Object price) {
	      return jdbc.insert("INSERT INTO ORDERDETAIL(ORDER_NO, PROD_ID, ORDERDT_QTY, ORDERDT_PRICE, ORDERDT_NO)" + 
	            " VALUES ('" + ordersNO + "', '" + prodID + "', " + qty + ", " + qty + " * TO_NUMBER('"+price + "'), SEQUENCE_ORDERDT.NEXTVAL)");
	   }
   
   public Map<String, Object> checkOrderPoss(Object prodID){
      return jdbc.selectOne("SELECT PROD_POSS AS POSS" + 
            "               FROM PROD" + 
            "               WHERE PROD_ID = '"+ prodID +"'");
   }
   
   public List<Map<String, Object>> prodSearch(String search){
       return jdbc.selectList(" SELECT * " + 
             "  FROM PROD " + 
             " WHERE PROD_NAME LIKE '%"+ search +"%'");
   }
   
   public Map<String, Object> checkOrderProd(Object ordersNO, Object prodID){
         return jdbc.selectOne("SELECT ORDERDT_NO, ORDERDT_QTY, ORDERDT_PRICE" + 
               "               FROM ORDERDETAIL" + 
               "                WHERE ORDER_NO = '" + ordersNO + "'" +  
               "                AND PROD_ID = '" + prodID + "'");
   }
   
   
   public int updateOrderDetail(Object ordersNO, int qty, Object price){
      return jdbc.update("UPDATE ORDERDETAIL" + 
             " SET ORDERDT_QTY = ORDERDT_QTY +" + qty + ", ORDERDT_PRICE = ORDERDT_PRICE +" + qty +" * TO_NUMBER('"+price+"')" + 
           " WHERE ORDERDT_NO ='" + ordersNO + "'");
   }   
   
   
   
   
   
   
}