package dao;

import java.util.List;
import java.util.Map;

import util.JDBCUtil;

public class AdvDAO {
      private static AdvDAO instance = null;
      private AdvDAO()   {}
      public static AdvDAO getInstance() {
         if(instance == null) instance = new AdvDAO();
         return instance;
      }
      
      JDBCUtil jdbc = JDBCUtil.getInstance();
      
      public List<Map<String, Object>> advertisement(){
         return jdbc.selectList("SELECT * FROM ADVER");
      }
}