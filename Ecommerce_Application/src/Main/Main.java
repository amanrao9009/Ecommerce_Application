
package Main;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import dao.OrderProcessorRepositoryImpl;
import exception.NegativeValueException;
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
		
		
//		try {
//			
//	      Product Laptop = new Product("AC", 1212 ,"Best in tech", 74);
//			
//			System.out.println(Laptop.getDescription());
//			
//			boolean a = x.createProduct(Laptop);
//			System.out.println(a);
//			
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//			
		
			
		
		
				
		
		
//		Customers NewUser = new Customers("amit rao","amit@gmail.com","abc");
//		boolean userCreated = x.createCustomer(NewUser);
//		System.out.println(userCreated);	
		
		
//		boolean ProductDeleted = x.deleteProduct(17);
//		System.out.println(ProductDeleted);
		
     
        
//       boolean DeleteCustoemr = x.deleteCustomer(7);
//       System.out.println(DeleteCustoemr);
		
//		
//		Customers user =  x.getCustomerById(7);
//		System.out.println(user.getCustomer_id());		
//		Product item =  x.getProductById(5);
//		System.out.println(item.getProduct_id());	
//		boolean AddToShopingList = x.addToCart(user, item, 9);
//		System.out.println(AddToShopingList);	
//		
		
		
//		Customers user =  x.getCustomerById(9);
//		System.out.println(user.getName());		
//		Product item =  x.getProductById(6);
//		System.out.println(item.getName());	
//		boolean removedItemfromCart = x.removeFromCart(user, item);
//		System.out.println(removedItemfromCart);
		
		
			
//		Customers user =  x.getCustomerById(7);
//		List<Product> productsInCart = x.getAllFromCart(user);
//		System.out.println(productsInCart);	
//        if (productsInCart.isEmpty()) {
//            System.out.println("Your cart is empty.");
//        } else {
//            System.out.println("Products in your cart:");
//            for (Product product : productsInCart) {
//                System.out.println("Product ID: " + product.getProduct_id());
//                System.out.println("Name: " + product.getName());
//                System.out.println("Price: $" + product.getPrice());
//                System.out.println("Description: " + product.getDescription());
//                System.out.println("Stock Quantity: " + product.getStockQuantity());
//                System.out.println("------------------------");
//            }
//        }
		
		//
		
//		  Customers userDate = x.getCustomerById(7);		
//	      List<Map<Product, Integer>> productsWithQuantities = x.getProductsFromCartByCustomer(userDate);
//	      
//	       for (Map<Product, Integer> productMap : productsWithQuantities) {
//	            for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
//	                Product product = entry.getKey();
//	                int quantity = entry.getValue();
//
//	                System.out.println("Product: " + product.getName() +
//	                        ", Quantity: " + quantity +
//	                        ", Price: $" + product.getPrice());
//	            }
//	       }
//	       
//	       String address = "Bhopal awadhpuri";
//	       
//	       boolean placedOder = x.placeOrder(userDate, productsWithQuantities, address);
//	       System.out.println(placedOder);
	       
		
	}

}
