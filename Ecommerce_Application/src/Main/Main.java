
package Main;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import dao.OrderProcessorRepositoryImpl;
import model.*;
import util.DBConnUtil;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		
		
//		Product aman = new Product("iphone", 1220.2,"model 16 promax", 999);
//		
//	   System.out.println(aman.getStockQuantity());
		

//		Properties prop = new Properties();
//		String fileName = "src/db.properties";
//		try (FileInputStream fis = new FileInputStream(fileName)) {
//		    prop.load(fis);
//		} catch (FileNotFoundException ex) {
//		    System.out.println(ex);
//		} catch (IOException ex) {
//			System.out.println(ex);
//		}
//		System.out.println(prop.getProperty("db.url"));
//		System.out.println(prop.getProperty("db.password"));
		
//		DBConnUtil ab = new DBConnUtil();	
//		Connection con = ab.getConnectionString("src/db.properties");	
//		System.out.println(con);
//		
//		
//      try{        
//      if(con!=null){
//          System.out.println("Conneted succesfully");
//      }        
//      
//      String empNo;
//      Statement stmt;
//      ResultSet rs;
//      
//      stmt = con.createStatement();  
//      rs = stmt.executeQuery("SELECT Status FROM `courie_management`.`courier`");   
//      
//      while (rs.next()) {              
//       empNo = rs.getString(1);             
//       System.out.println("Employee number = " + empNo);                            
//      } 
//  }    
//  catch(Exception e){
//      System.out.println(e);
//      System.out.println("not conneted");
//  }
		
      
		OrderProcessorRepositoryImpl x = new OrderProcessorRepositoryImpl();
		
		

		
		
//		System.out.println(db.password);
		

//		System.out.println("Hello world");
//        
//        
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courie_management","root","root");
//            
//            if(con!=null){
//                System.out.println("Conneted succesfully");
//            }   
//            
//            
//            String empNo;
//            Statement stmt;
//            ResultSet rs;
//            
//            stmt = con.createStatement();  
//            rs = stmt.executeQuery("SELECT Status FROM `courie_management`.`courier`");
//          
//            
//            while (rs.next()) {              
//             empNo = rs.getString(1);             
//             System.out.println("Employee number = " + empNo);
//                                  
//            
//
//            }
//     
//            
//        }    
//        catch(Exception e){
//            System.out.println(e);
//            System.out.println("not conneted");
//        }
        
       

       
	}

}
