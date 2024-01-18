package dao;

import java.util.List;
import java.util.Map;

import model.Customers;
import model.Product;

public interface OrderProcessorRepository {
	
	   boolean createProduct(Product product);
	    boolean createCustomer(Customers customer);
	    boolean deleteProduct(int productId);
	    boolean deleteCustomer(int customerId);
	    boolean addToCart(Customers customer, Product product, int quantity);
	    boolean removeFromCart(Customers customer, Product product);
	    List<Product> getAllFromCart(Customers customer);
	    boolean placeOrder(Customers customer, List<Map<Product, Integer>> productsWithQuantities, String shippingAddress);
	    List<Map<Product, Integer>> getOrdersByCustomer(int customerId);

}
