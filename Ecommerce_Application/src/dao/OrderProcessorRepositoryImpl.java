package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import model.Customers;
import model.Product;
import util.DBConnUtil;

public class OrderProcessorRepositoryImpl implements OrderProcessorRepository {

	DBConnUtil ab = new DBConnUtil();	
	Connection connection = ab.getConnectionString("src/db.properties");	
	
	
    public OrderProcessorRepositoryImpl() {
//        System.out.println(con);
        
      try{        
    	  if(connection!=null){
          System.out.println("Conneted succesfully");
    	  }        
    	  
    	  String empNo;
    	  Statement stmt;
    	  ResultSet rs;
      
    	  stmt = connection.createStatement();  
    	  rs = stmt.executeQuery("SELECT Status FROM `courie_management`.`courier`");   
      
    	  while (rs.next()) {              
    		  empNo = rs.getString(1);             
    		  System.out.println("Employee number = " + empNo);                            
    	  } 
      }    
      catch(Exception e){
    	  System.out.println(e);
    	  System.out.println("not conneted");
      }
    }
	

	@Override
	public boolean createProduct(Product product) {
		// TODO Auto-generated method stub
//        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (name, price, description, stockQuantity) VALUES (?, ?, ?, ?)")) {
//            preparedStatement.setString(1, product.getName());
//            preparedStatement.setDouble(2, product.getPrice());
//            preparedStatement.setString(3, product.getDescription());
//            preparedStatement.setInt(4, product.getStockQuantity());
//
//            int rowsAffected = preparedStatement.executeUpdate();
//            return rowsAffected > 0;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        }
//	}

	@Override
	public boolean createCustomer(Customers customer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addToCart(Customers customer, Product product, int quantity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFromCart(Customers customer, Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Product> getAllFromCart(Customers customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean placeOrder(Customers customer, List<Map<Product, Integer>> productsWithQuantities,
			String shippingAddress) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Map<Product, Integer>> getOrdersByCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
