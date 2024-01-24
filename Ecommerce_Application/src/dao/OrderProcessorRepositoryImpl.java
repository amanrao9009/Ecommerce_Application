package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exception.InvalidNameException;
import exception.InvalidOrderQuantityException;
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
    	  
//    	  String empNo;
//    	  Statement stmt;
//    	  ResultSet rs;
//      
//    	  stmt = connection.createStatement();  
//    	  rs = stmt.executeQuery("SELECT name FROM `ecommerce_application`.`customers`");   
//      
//    	  while (rs.next()) {              
//    		  empNo = rs.getString(1);             
//    		  System.out.println("Employee name = " + empNo);                            
//    	  } 
      }    
      catch(Exception e){
    	  System.out.println(e);
    	  System.out.println("not conneted");
      }
    }
	

	@Override
	public boolean createProduct(Product product) {
		// TODO Auto-generated method stub
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO products (name, price, description, stockQuantity) VALUES (?, ?, ?, ?)")) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setString(3, product.getDescription());
            preparedStatement.setInt(4, product.getStockQuantity());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
		
		return false;
	}

	@Override
	public boolean createCustomer(Customers customer) {
		// TODO Auto-generated method stub
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO customers (name, email, password) VALUES (?, ?, ?)")) {
             
        	try {
				validateName(customer.getName());
			} catch (InvalidNameException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
		return false;
	}
	
	
    public static void validateName(String name) throws InvalidNameException {
        // Regular expression to match only letters and spaces
        String regex = "^[a-zA-Z\\s]+$";

        if (!name.matches(regex)) {
            throw new InvalidNameException("Name contains invalid characters.");
        }
    }

	@Override
	public boolean deleteProduct(int productId) {
		// TODO Auto-generated method stub
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM products WHERE product_id = ?")) {
            preparedStatement.setInt(1, productId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
		return false;
	}

	@Override
	public boolean deleteCustomer(int customerId) {
		// TODO Auto-generated method stub
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customers WHERE customer_id = ?")) {
            preparedStatement.setInt(1, customerId);

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            
        }
		return false;
	}
	
	
    public Customers getCustomerById(int customerId) {
        String query = "SELECT * FROM customers WHERE customer_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               
                return new Customers(
                        resultSet.getInt("customer_id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("password")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
    
    public Product getProductById(int productId) {
        String query = "SELECT * FROM products WHERE product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
               
                return new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getInt("stockQuantity")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

	

	@Override
	public boolean addToCart(Customers customer, Product product, int quantity) {
		// TODO Auto-generated method stub
		
      
        try {
    		
            if (quantity > 100) {
                throw new InvalidOrderQuantityException("Ordered quantity exceeds the maximum limit (100).");
            }
            // Check if the product is already in the cart for the customer
            if (isProductInCart(customer.getCustomer_id(), product.getProduct_id())) {
                // If it's in the cart, update the quantity
                updateCartQuantity(customer.getCustomer_id(), product.getProduct_id(), quantity);
            } else {
                // If it's not in the cart, add a new entry
                addProductToCart(customer.getCustomer_id(), product.getProduct_id(), quantity);
            }

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
           
        } catch (InvalidOrderQuantityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
    private boolean isProductInCart(int customerId, int productId) throws SQLException {
        String query = "SELECT * FROM cart WHERE customer_id = ? AND product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);
            return preparedStatement.executeQuery().next();
        }
    }

    private void addProductToCart(int customerId, int productId, int quantity) throws SQLException {
        String query = "INSERT INTO cart (customer_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);
            preparedStatement.setInt(3, quantity);
            preparedStatement.executeUpdate();
        }
    }

    private void updateCartQuantity(int customerId, int productId, int quantity) throws SQLException {
        String query = "UPDATE cart SET quantity = ? WHERE customer_id = ? AND product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, customerId);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        }
    }
	
	
	
/////////////////////////
	
	

	@Override
	public boolean removeFromCart(Customers customer, Product product) {
		// TODO Auto-generated method stub
        try {
            // Check if the product is in the cart for the customer
            if (isProductInCart(customer.getCustomer_id(), product.getProduct_id())) {
                // If it's in the cart, remove the entry
                removeProductFromCartFromIds(customer.getCustomer_id(), product.getProduct_id());
                return true;
            } else {
                System.out.println("Product or customer not found in cart");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
         
        }
		return false;
	}
	
	
    private void removeProductFromCartFromIds(int customerId, int productId) throws SQLException {
        String query = "DELETE FROM cart WHERE customer_id = ? AND product_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        }
    }

	@Override
	public List<Product> getAllFromCart(Customers customer) {
		// TODO Auto-generated method stub
		
        List<Product> productsInCart = new ArrayList<>();

        String query = "SELECT products.* FROM products " +
                "JOIN cart ON products.product_id = cart.product_id " +
                "WHERE cart.customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, customer.getCustomer_id());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Create a Product object for each result and add it to the list
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getString("description"),
                        resultSet.getInt("stockQuantity")
                );
                productsInCart.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productsInCart;
		
		
		
	}
	
	
	//list map for details
	
	public List<Map<Product, Integer>> getProductsFromCartByCustomer(Customers customer) {
	    List<Map<Product, Integer>> productsWithQuantities = new ArrayList<>();

	    String query = "SELECT products.*, cart.quantity " +
	            "FROM products " +
	            "JOIN cart ON products.product_id = cart.product_id " +
	            "WHERE cart.customer_id = ?";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, customer.getCustomer_id());
	        ResultSet resultSet = preparedStatement.executeQuery();

	        while (resultSet.next()) {
	            // Create a Product object for each result
	            Product product = new Product(
	                    resultSet.getInt("product_id"),
	                    resultSet.getString("name"),
	                    resultSet.getDouble("price"),
	                    resultSet.getString("description"),
	                    resultSet.getInt("stockQuantity")
	            );

	            // Get the quantity from the cart
	            int quantity = resultSet.getInt("quantity");

	          
	            Map<Product, Integer> productMap = new HashMap<>();
	            productMap.put(product, quantity);
	            productsWithQuantities.add(productMap);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return productsWithQuantities;
	}
	
	
    public boolean removeItemsFromCartByCustomer(Customers customer) {
        String deleteQuery = "DELETE FROM cart WHERE customer_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, customer.getCustomer_id());
            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@Override
	public boolean placeOrder(Customers customer, List<Map<Product, Integer>> productsWithQuantities,
			String shippingAddress) {
		// TODO Auto-generated method stub
		
        try {
            // Insert into the orders table
            int orderId = insertIntoOrders(customer.getCustomer_id(), shippingAddress);

            if (orderId != -1) {
                // Insert into the order_items table
              boolean oderplace =  insertIntoOrderItems(orderId, productsWithQuantities);
                
               if(oderplace) {
            	   removeItemsFromCartByCustomer(customer);
               } 
                
                
                
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
		
		
		return false;
	}
	
	
    private int insertIntoOrders(int customerId, String shippingAddress) throws SQLException {
        String query = "INSERT INTO orders (customer_id, order_date, total_price, shipping_address) VALUES (?, NOW(), ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, customerId);
            preparedStatement.setDouble(2, calculateTotalPrice(customerId));
            preparedStatement.setString(3, shippingAddress);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // Return the generated order_id
                }
            }
        }
        return -1; // Return -1 if the order insertion fails
    }

    private boolean insertIntoOrderItems(int orderId, List<Map<Product, Integer>> productsWithQuantities) throws SQLException {
        String query = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (Map<Product, Integer> entry : productsWithQuantities) {
                for (Map.Entry<Product, Integer> productEntry : entry.entrySet()) {
                    Product product = productEntry.getKey();
                    int quantity = productEntry.getValue();

                    preparedStatement.setInt(1, orderId);
                    preparedStatement.setInt(2, product.getProduct_id());
                    preparedStatement.setInt(3, quantity);

                    preparedStatement.addBatch(); // Add the batch for execution
                }
            }
            preparedStatement.executeBatch(); // Execute the batch
            return true;
        }
    }

    private double calculateTotalPrice(int customerId) throws SQLException {
        // Implement your logic to calculate the total price based on products in the cart
        // You might want to fetch the prices from the products table and calculate the sum
        // This is just a placeholder method, replace it with your actual logic
        String query = "SELECT products.price, cart.quantity " +
                "FROM products " +
                "JOIN cart ON products.product_id = cart.product_id " +
                "WHERE cart.customer_id = ?";

        double totalPrice = 0.0;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
        	preparedStatement.setInt(1, customerId);
        	ResultSet resultSet = preparedStatement.executeQuery();

        	while (resultSet.next()) {
        		double productPrice = resultSet.getDouble("price");
        		int quantity = resultSet.getInt("quantity");

        		totalPrice += productPrice * quantity;
        	}
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return totalPrice;
    	
        
    }
    


	@Override
	public List<Map<Product, Integer>> getOrdersByCustomer(int customerId) {
		// TODO Auto-generated method stub
		return null;
	}

}
