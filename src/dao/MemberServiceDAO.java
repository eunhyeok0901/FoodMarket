package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import util.FormatUtil;
import util.JDBCUtil;

public class MemberServiceDAO {
	private static MemberServiceDAO instance = null;
	public MemberServiceDAO () {}
	public static MemberServiceDAO getInstance() {
		if(instance==null) instance = new MemberServiceDAO();
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();

   int insertResult;
    
    
    public int insertsignUp(String id, String pwd, String name, String address, String birth, String ph) {
       String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
       String user = "YB";
       String passWord = "java";
       
       Connection conn = null;
       PreparedStatement ps =null;
       ResultSet rs = null;
       
       String insertsql = "INSERT INTO MEMBER (MEM_ID, MEM_PWD, MEM_NAME, MEM_ADDR, MEM_BIRTH, MEM_HP)VALUES(?, ?, ?, ?, ?, ?)";
       
       try {
          conn = DriverManager.getConnection(url, user, passWord);
          ps = conn.prepareStatement(insertsql);
          
//          SimpleDateFormat sd1 = new SimpleDateFormat("yyyyMMdd");
//          Date datestr = null;
//       try {
//          datestr = sd1.parse(birth);
//       } catch (ParseException e) {
//          // TODO Auto-generated catch block
//          e.printStackTrace();
//       }
//          SimpleDateFormat sd2 = new SimpleDateFormat("yyyy/MM/dd");
          
          
          
//          System.out.println(birth+"생년월일");
//          System.out.println((Integer.parseInt(birth))+"숫자형 생년월일");
          
          ps.setString(1, id);
          ps.setString(2, pwd);
          ps.setString(3, name);
          ps.setString(4, address);
          ps.setString(5, birth);
       // ps.setString(5, sd2.format(datestr));
        //  System.out.println("birth :: " + sd.format(Integer.parseInt(birth)));
          ps.setString(6, ph);
          
          insertResult = ps.executeUpdate();
          
       } catch (SQLException e) {
          
          e.printStackTrace();
       }finally {
          // 5. ResultSet, Statement, Connection 닫기
          if(rs != null) 
             try{rs.close();}catch (Exception e) {}
          
          if(ps != null) 
             try{ps.close();}catch (Exception e) {}
          
          if(conn != null) 
             try{conn.close();}catch (Exception e) {}
          
          
       
       }
       
          return insertResult;
       
    }


    public boolean idCheck(String id) {
       String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
       String user = "YB";
       String passWord = "java";
       
       Connection conn = null;
       PreparedStatement ps =null;
       ResultSet rs = null;
       String idCheckSql = "SELECT MEM_ID FROM MEMBER";
       try {
          conn = DriverManager.getConnection(url, user, passWord);
          ps = conn.prepareStatement(idCheckSql);
          rs = ps.executeQuery();
          while(rs.next()) {
             if(rs.getString("MEM_ID").equals(id)) {
                System.out.println();
                 System.out.println("존재하는 ID입니다");
                 System.out.println();
                return false;
             }
          }
          
          
          
       } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }finally {
          // 5. ResultSet, Statement, Connection 닫기
          if(rs != null) 
             try{rs.close();}catch (Exception e) {}
          
          if(ps != null) 
             try{ps.close();}catch (Exception e) {}
          
          if(conn != null) 
             try{conn.close();}catch (Exception e) {}
          
          
       
       }
       return true;
       
       
    }


    public String login(String id, String pwd) {
       String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
       String user = "YB";
       String passWord = "java";
       
       Connection conn = null;
       PreparedStatement ps =null;
       ResultSet rs = null;
       String loginSql = "SELECT MEM_ID, MEM_PWD FROM MEMBER";
       try {
          conn = DriverManager.getConnection(url, user, passWord);
          ps = conn.prepareStatement(loginSql);
          rs = ps.executeQuery();
         
          while(rs.next()) {
             if(rs.getString("MEM_ID").equals(id) && rs.getString("MEM_PWD").equals(pwd)) {
//                System.out.println(" ┌──────────────────────────┐");
//                 System.out.println(" │ 로그인이 완료되었습니다! │");
//                 System.out.println(" └──────────────────────────┘");
                System.out.println("                                       로그인이 완료되었습니다!");
                 System.out.println();
                 System.out.println();
                 System.out.println();

                Controller.login = true;
                return rs.getString("MEM_ID");
       
             }
               
            
                
          }
         
          
       } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }finally {
          // 5. ResultSet, Statement, Connection 닫기
          if(rs != null) 
             try{rs.close();}catch (Exception e) {}
          
          if(ps != null) 
             try{ps.close();}catch (Exception e) {}
          
          if(conn != null) 
             try{conn.close();}catch (Exception e) {}
          
          
       
       }
       
//       System.out.println(" ┌───────────────────────────────────────────┐");
//       System.out.println(" │ 존재하지 않는 아이디 혹은 비밀번호 입니다 │");
//       System.out.println(" └───────────────────────────────────────────┘");
         System.out.println("                                존재하지 않는 아이디 혹은 비밀번호 입니다");
        return null;
       
    }


    public int cartUpdate(String updateNum, int updateQty, String price) {
        String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
        String user = "YB";
        String passWord = "java";
        
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        
        
        int result = 0;
        
        try {
           conn = DriverManager.getConnection(url, user, passWord);
           
           String cartUpdateSql  =  " UPDATE ORDERDETAIL"
                                +" SET ORDERDT_QTY = ?, "
                                +" ORDERDT_PRICE = ? * ?"
                              +" WHERE ORDERDT_NO = ?";
           ps = conn.prepareStatement(cartUpdateSql);
           
           ps.setInt(1, updateQty);
           ps.setInt(2, updateQty);
           ps.setString(3, price);
           ps.setString(4, updateNum);
           
           result = ps.executeUpdate();
           
           
           
           
        } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }finally {
           // 5. ResultSet, Statement, Connection 닫기
           if(rs != null) 
              try{rs.close();}catch (Exception e) {}
           
           if(ps != null) 
              try{ps.close();}catch (Exception e) {}
           
           if(conn != null) 
              try{conn.close();}catch (Exception e) {}
           
           
        
        }
        
        
        return result;
     }


    public int cartDelete(String deleteNum) {
       String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
       String user = "YB";
       String passWord = "java";
       
       Connection conn = null;
       PreparedStatement ps =null;
       ResultSet rs = null;
       
       int result = 0;
       
       String cartDeleteSql =   "DELETE"
                          +" FROM ORDERDETAIL"
                         +" WHERE ORDERDT_NO = (SELECT B.ORDERDT_NO" 
                                               +" FROM ORDERS A, ORDERDETAIL B, PROD C"
                                              +" WHERE A.ORDER_NO = B.ORDER_NO"
                                                +" AND B.PROD_ID = C.PROD_ID"
                                                +" AND A.MEM_ID = ?"
                                                +" AND B.ORDERDT_NO = ?"
                                                +" AND A.ORDER_POSS = 0)"; 
       
       try {
          conn = DriverManager.getConnection(url, user, passWord);
          ps = conn.prepareStatement(cartDeleteSql);
          ps.setString(1, Controller.login_id);
          ps.setString(2, deleteNum);
          
          result = ps.executeUpdate();
          
          
       } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
       }finally {
          // 5. ResultSet, Statement, Connection 닫기
          if(rs != null) 
             try{rs.close();}catch (Exception e) {}
          
          if(ps != null) 
             try{ps.close();}catch (Exception e) {}
          
          if(conn != null) 
             try{conn.close();}catch (Exception e) {}
          

       }
       
       
       return result;
    }


    
    public int selectCart() {
       String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
       String user = "YB";
       String passWord = "java";
       
       Connection conn = null;
       PreparedStatement ps =null;
       ResultSet rs = null;
       
       List<Map<String, Object>> selectCartList = new ArrayList<Map<String,Object>>();
       Map<String, Object> row = new HashMap<String, Object>();
       String selectCartSql = " SELECT B.ORDERDT_NO,"  
                                 +" C.PROD_NAME,"
                                 + "B.ORDERDT_QTY,"
                                 +" B.ORDERDT_PRICE" 
                            +" FROM ORDERS A, ORDERDETAIL B, PROD C"
                            +" WHERE A.ORDER_NO = B.ORDER_NO"
                            +" AND B.PROD_ID = C.PROD_ID"
                            +" AND A.MEM_ID = ?"
                            +" AND A.ORDER_POSS = 0";
       
       int count = 0;
       
       try {
          conn = DriverManager.getConnection(url, user, passWord);
          ps = conn.prepareStatement(selectCartSql);
          ps.setString(1, Controller.login_id);
          
          rs = ps.executeQuery();
          
          ResultSetMetaData rsmd = rs.getMetaData();
         // int i = 1;
          
         
          Controller.enter(3);
          System.out.println("     -----------------------------------------------------------------------------------------------     ");
          System.out.println("             주문번호               상품명                       수량                가격");
          System.out.println("     -----------------------------------------------------------------------------------------------     ");
          while(rs.next()) {
             count++;
//             for (int i = 1; i < rsmd.getColumnCount(); i++) {
//                row.put(rsmd.getColumnName(i), rs.getObject(i));
//                selectCartList.add(i-1, row);
//                System.out.println(i + rs.getString("PROD_NAME")+"\t"+ rs.getString("ORDERDT_QTY")+" 개 \t"+ rs.getString("PRICE")+" 원");
//             }
           // System.out.println("=========================================================================================================");
           System.out.println();
            System.out.print("              "+FormatUtil.format(rs.getString("ORDERDT_NO"), 4, true));
            System.out.print("          "+FormatUtil.format(rs.getString("PROD_NAME"), 30, false));
            System.out.print("     "+FormatUtil.format(rs.getString("ORDERDT_QTY"), 3, true) + "개");
            System.out.println("         "+FormatUtil.format(rs.getString("ORDERDT_PRICE"), 10, true) + "원");    
            System.out.println();
            System.out.println("     -----------------------------------------------------------------------------------------------     ");
            // i++;
         
          }
          if(count==0) {
             Controller.enter(2);
             System.out.println("                                          장바구니가 비어있습니다.");
             Controller.enter(5);
             System.out.println("     -----------------------------------------------------------------------------------------------     ");             
          }
           
       } catch (SQLException e) {
          e.printStackTrace();
       }finally {
          if(rs != null) 
             try{rs.close();}catch (Exception e) {}
          
          if(ps != null) 
             try{ps.close();}catch (Exception e) {}
          
          if(conn != null) 
             try{conn.close();}catch (Exception e) {}

       }
       
       return count;
       
    }


    public void sumPrice() {
        String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
        String user = "YB";
        String passWord = "java";
        
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
        String sumPriceSql = " SELECT E.PRODSUMPRICE * 0.05 AS POINT,"
                +" PRODSUMPRICE"
            +" FROM (SELECT SUM(D.ORDERDT_PRICE) AS PRODSUMPRICE"
                    +" FROM (SELECT B.ORDERDT_NO," 
                                 + " C.PROD_NAME,"
                                 +" B.ORDERDT_QTY,"
                                 +" B.ORDERDT_PRICE" 
                            +" FROM ORDERS A, ORDERDETAIL B, PROD C"
                           +" WHERE A.ORDER_NO = B.ORDER_NO"
                             +" AND B.PROD_ID = C.PROD_ID"
                             +" AND A.MEM_ID = ?"
                             +" AND A.ORDER_POSS = 0)D)E";
        try {
           conn = DriverManager.getConnection(url, user, passWord);
           ps = conn.prepareStatement(sumPriceSql);
           ps.setString(1, Controller.login_id);
           rs = ps.executeQuery();
           while(rs.next()) {
              if(rs.getObject("PRODSUMPRICE") == null) {
                 System.out.println();
              }else {
                 System.out.println("\t\t\t\t\t\t\t\t\t" + "합계\t"+rs.getInt("PRODSUMPRICE")+" 원" );
                 System.out.println("\t\t\t\t\t\t\t\t\t\t " + rs.getInt("POINT")+" P "+"(예상적립포인트) " );
                 
              }
              
           }
           
        } catch (SQLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
        }finally {
           if(rs != null) 
              try{rs.close();}catch (Exception e) {}
           
           if(ps != null) 
              try{ps.close();}catch (Exception e) {}
           
           if(conn != null) 
              try{conn.close();}catch (Exception e) {}
           
           
        
        }
        
     }
    
    public Map<String, Object> selectPrice(String orderdtNo){

        String url = "jdbc:oracle:thin:@192.168.144.23:1521:xe";
        String user = "YB";
        String passWord = "java";
        
        Connection conn = null;
        PreparedStatement ps =null;
        ResultSet rs = null;
    
    Map<String, Object> row = null;
    
    try {
       conn = DriverManager.getConnection(url,user,passWord);
       
       String sql = " SELECT PROD_PRICE FROM PROD WHERE PROD_ID = (SELECT PROD_ID FROM ORDERDETAIL WHERE ORDERDT_NO = '" + orderdtNo+"')";
       
       ps = conn.prepareStatement(sql);
       ResultSetMetaData rsmd = ps.getMetaData();
       rs = ps.executeQuery();
       int columnCount = rsmd.getColumnCount();
       while(rs.next()) {
          row = new HashMap<>();
          for(int i = 1; i <= columnCount; i++) {
             String key = rsmd.getColumnLabel(i);
             // getColumnName vs getColumnLabel
             // getColumnName : 원본 컬럼명을 가져옴
             // getColumnLabel : as로 선언된 별명을 가져옴, 없으면 원본 컬럼명
             Object value = rs.getObject(i);
             row.put(key, value);
          }
       }
    }catch(SQLException e) {
       e.printStackTrace();
    }finally{
       if(rs != null) try { rs.close();} catch(Exception e ) {}
       if(ps != null) try { ps.close();} catch(Exception e ) {}
       if(conn != null) try { conn.close();} catch(Exception e ) {}
    }
    
    return row;
 }

}