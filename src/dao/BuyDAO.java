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

public class BuyDAO {

	private static BuyDAO instance = null;
	public BuyDAO () {}
	public static BuyDAO getInstance() {
		if(instance==null) instance = new BuyDAO();
		return instance;
	}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	public Map<String, Object> address(String login_id) {
		return jdbc.selectOne("SELECT MEM_ADDR FROM MEMBER WHERE MEM_ID ="+"'"+login_id+"'");
	}
	
	public Map<String, Object> name(String login_id) {
		
		return jdbc.selectOne("SELECT MEM_NAME FROM MEMBER WHERE MEM_ID ="+"'"+login_id+"'");
	}
	public Map<String, Object> hp(String login_id) {
		
		return jdbc.selectOne("SELECT MEM_HP FROM MEMBER WHERE MEM_ID ="+"'"+login_id+"'");
	}
	public Map<String, Object> beforeAdd(String login_id) {
		
		return jdbc.selectOne("SELECT MEM_ADDR FROM MEMBER WHERE MEM_ID ="+"'"+login_id+"'");
	}
	public int updateAddr(String newAddr,String login_id) {
		
		return jdbc.update("UPDATE MEMBER SET MEM_ADDR = "+"'"+newAddr +"'"+" WHERE MEM_ID ="+"'"+login_id+"'");
	}
	
	public Map<String, Object> order_No(String login_id) {
		
		return jdbc.selectOne("SELECT DISTINCT(B.ORDER_NO) AS ORDER_NO"
							+ " FROM ORDERDETAIL A,ORDERS B "
							+ " WHERE A.ORDER_NO = B.ORDER_NO "
							+ " AND B.ORDER_POSS = 0"
							+ " AND B.MEM_ID ="+"'"+login_id+"'");
	}
//	public List<Map<String, Object>> prod_name(String login_id) {
//		
//		return jdbc.selectList("SELECT C.PROD_NAME" + 
//				" FROM ORDERDETAIL A,ORDERS B,PROD C" + 
//				" WHERE A.ORDER_NO=B.ORDER_NO" + 
//				" AND A.PROD_ID=C.PROD_ID" + 
//				" AND B.ORDER_POSS=0" + 
//				" AND B.MEM_ID="+login_id);
//	}
//	public List<Map<String, Object>> prod_buylist(String login_id) {
//		
//		return jdbc.selectList("SELECT B.ORDERDT_NO, C.PROD_NAME, B.ORDERDT_QTY,SUM(B.ORDERDT_PRICE * B.ORDERDT_QTY) AS PRICE" + 
//								" FROM ORDERS A, ORDERDETAIL B, PROD C" + 
//								" WHERE A.ORDER_NO = B.ORDER_NO" + 
//								" AND B.PROD_ID = C.PROD_ID" + 
//								" AND A.ORDER_POSS = 1" + 
//								" AND A.MEM_ID = '"+ login_id + 
//								"' GROUP BY B.ORDERDT_NO, C.PROD_NAME, B.ORDERDT_QTY");
//	}
	public int updateorderdt1(String login_id) {
		
		return jdbc.update("UPDATE ORDERDETAIL" + 
							 " SET ORDERDT_STATE = 1, ORDERDT_PAYWAY = 1, ORDERDT_DATE = SYSDATE" + 
							 " WHERE ORDER_NO=(SELECT DISTINCT(B.ORDER_NO) " + 
							 				   " FROM ORDERDETAIL A,ORDERS B" + 
							 				   " WHERE A.ORDER_NO=B.ORDER_NO" + 
							 				   " AND B.ORDER_POSS=0" + 
							 				   " AND B.MEM_ID='"+login_id+"')");
	}
	
	public int updateorderdt2(String login_id) {
		
		return jdbc.update("UPDATE ORDERDETAIL" + 
				" SET ORDERDT_STATE = 1, ORDERDT_PAYWAY = 2, ORDERDT_DATE = SYSDATE" + 
				" WHERE ORDER_NO=(SELECT DISTINCT(B.ORDER_NO) " + 
				" FROM ORDERDETAIL A,ORDERS B" + 
				" WHERE A.ORDER_NO=B.ORDER_NO" + 
				" AND B.ORDER_POSS=0" + 
				" AND B.MEM_ID='"+login_id+"')");
	}
	
	
	
	
	
	
	
	
	public int updateorders(String login_id) {
		
		return jdbc.update("UPDATE ORDERS" + 
			               " SET ORDER_POSS = 1" + 
				           " WHERE ORDER_NO=(SELECT DISTINCT(B.ORDER_NO)" + 
				           					" FROM ORDERDETAIL A,ORDERS B" + 
				           					" WHERE A.ORDER_NO=B.ORDER_NO" + 
				           					" AND B.ORDER_POSS=0" + 
				           					" AND B.MEM_ID='"+login_id+"')");
	}
	
	 public void selectCart() {
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
	       
	       try {
	          conn = DriverManager.getConnection(url, user, passWord);
	          ps = conn.prepareStatement(selectCartSql);
	          ps.setString(1, Controller.login_id);
	          
	          rs = ps.executeQuery();
	          
	          ResultSetMetaData rsmd = rs.getMetaData();
	          int i = 1;
	          
	          
	          while(rs.next()) {
//	             for (int i = 1; i < rsmd.getColumnCount(); i++) {
//	                row.put(rsmd.getColumnName(i), rs.getObject(i));
//	                selectCartList.add(i-1, row);
//	                System.out.println(i + rs.getString("PROD_NAME")+"\t"+ rs.getString("ORDERDT_QTY")+" 개 \t"+ rs.getString("PRICE")+" 원");
//	             }
	            
	        	 //System.out.println("=========================================================================================================");
	        	  System.out.print("\t\t"+FormatUtil.format(i, 3, true)+".");;
	              System.out.print("\t"+FormatUtil.format(rs.getString("PROD_NAME"), 30, false));;
	              System.out.print("\t"+FormatUtil.format(rs.getString("ORDERDT_QTY"), 3, true)+"개");;
	              System.out.println("\t"+FormatUtil.format(rs.getString("ORDERDT_PRICE"), 10, true)+"원");;

	             
	             i++;
	         
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
	                //System.out.println("=========================================================================================================");
	                
	                System.out.println("                                           결제금액 : " + rs.getObject("PRODSUMPRICE")+" 원");
	                
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
	public Map<String, Object> mempoint(String login_id) {
		
		return jdbc.selectOne("SELECT MEM_POINT FROM MEMBER WHERE MEM_ID = '"+login_id+"'");
		
		
	}
	public int pointupdate(String login_id) {
		
		return jdbc.update("UPDATE MEMBER SET MEM_POINT = MEM_POINT + (SELECT E.SUMPRICE * 0.05 AS POINT" + 
				                                                      "  FROM (SELECT SUM(D.SUMPRI) AS SUMPRICE" + 
				                                                               " FROM (SELECT A.ORDER_NO," + 
				                                                                            " C.PROD_NAME," + 
				                                                                            " B.ORDERDT_QTY," + 
				                                                                            " SUM(B.ORDERDT_PRICE) AS SUMPRI" + 
				                                                                     "  FROM ORDERS A, ORDERDETAIL B, PROD C" + 
				                                                                     " WHERE A.ORDER_NO = B.ORDER_NO" + 
				                                                                       " AND B.PROD_ID = C.PROD_ID" + 
				                                                                       " AND A.MEM_ID = '"+login_id+"'" + 
				                                                                       " AND A.ORDER_POSS = 0" + 
				                                                                     " GROUP BY A.ORDER_NO, C.PROD_NAME, B.ORDERDT_QTY)D)E)" + 
						 "  WHERE MEM_ID = '"+login_id+"' ");
	}
	
	
	
	
	
	public Map<String, Object> updatedpoint(String login_id) {
		
		return jdbc.selectOne("SELECT MEM_POINT FROM MEMBER WHERE MEM_ID = '"+login_id+"'");
	}
	 
	

}
