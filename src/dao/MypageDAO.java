package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class MypageDAO {
   private static MypageDAO instance = null;
   private MypageDAO()   {}
   public static MypageDAO getInstance() {
      if(instance == null) instance = new MypageDAO();
      return instance;
   }
   
   JDBCUtil jdbc = JDBCUtil.getInstance();
   
   public Map<String, Object> info(String id) {
      return jdbc.selectOne("SELECT * FROM MEMBER "
                     + " WHERE MEM_ID = '"+id+"'");
   }
   
   public List<Map<String, Object>> buyList(String id) {
 
      return jdbc.selectList(" SELECT DISTINCT A.ORDERDT_DATE, " + 
            "        B.ORDER_NO, " + 
            "        A.ORDERDT_STATE " + 
            "   FROM ORDERDETAIL A, ORDERS B " + 
            "  WHERE A.ORDER_NO = B.ORDER_NO " + 
            "    AND B.ORDER_POSS = 1 " + 
            "    AND B.MEM_ID = '"+id+"'"
                  + " ORDER BY 1 " );
   }

  public List<Map<String, Object>> buyListProd (String id, Object orderNo){
      return jdbc.selectList(" SELECT A.PROD_NAME, "
                                     + " B.ORDERDT_NO, "
                                   + " B.ORDERDT_DATE, "
                                   + " B.ORDERDT_QTY, "
                                   + " B.ORDERDT_PRICE, "
                                   + " B.ORDERDT_STATE, "
                                   + " B.ORDER_NO"
                              + " FROM PROD A, ORDERDETAIL B, ORDERS C "
                             + " WHERE A.PROD_ID = B.PROD_ID "
                               + " AND B.ORDER_NO = " + orderNo
                             + " AND B.ORDER_NO = C.ORDER_NO "
                             + " AND C.MEM_ID = '" + id + "'"
                                   + "AND B.ORDERDT_STATE>=1"
                                   + " ORDER BY A.PROD_ID " );
   }
   
  public int reviewInsert(List<Object> param) {
   return jdbc.update(" INSERT INTO REVIEW(REVIEW_NO,REVIEW_CONTENT,REVIEW_SCORE,REVIEW_DATE,MEM_ID,ORDERDT_NO) "
            + " VALUES(SEQUENCE_REVIEW.NEXTVAL, ?, ?, SYSDATE, ?, ?)", param);
  }
  
  public Map<String, Object> reviewCheck(Object orderdtNum) {
   return jdbc.selectOne(" SELECT C.PROD_NAME, "
                         + " A.MEM_ID, "
                            + " A.REVIEW_CONTENT, "
                            + " A.REVIEW_SCORE, "
                            + " A.REVIEW_DATE, "
                            + " B.ORDER_NO "
                       + " FROM REVIEW A, ORDERDETAIL B, PROD C"
                      + " WHERE A.ORDERDT_NO = "+ orderdtNum
                      + " AND A.ORDERDT_NO=B.ORDERDT_NO "
                      + " AND B.PROD_ID=C.PROD_ID ");
  }
  
  public int reviewUpdate(String id, String contents, int score, Object orderdtNum) {
      return jdbc.update(" UPDATE REVIEW SET REVIEW_CONTENT = '" + contents +"'"
            + " , REVIEW_SCORE = " + score
            + " , REVIEW_DATE = SYSDATE " 
            + " WHERE MEM_ID = '" + id + "'"
            + " AND ORDERDT_NO = " + orderdtNum);
     }
   
  public int reviewDelete(Object orderdtNum) {
     return jdbc.update(" DELETE FROM REVIEW WHERE ORDERDT_NO = " + orderdtNum);
  }
  
  public int memUpdate(String column, String value ,String id) {
     return jdbc.update(" UPDATE MEMBER SET " + column + " = '" + value + "' WHERE MEM_ID = '" + id + "'" );
  }
  
  
}