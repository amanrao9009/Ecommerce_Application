package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnUtil {
	
	
    public static Connection getConnectionString(String propertyFileName) {

    	
  
        
        
		Properties prop = new Properties();
		String connectionString = propertyFileName;
		
		 Connection con = null;
		 
//		try {
//			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courie_management","root","root");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		 
		 
		 
		 
		try (FileInputStream fis = new FileInputStream(connectionString)) {
		    prop.load(fis);
		   
//		    con = DriverManager.getConnection("jdbc:mysql://localhost:3306/courie_management","root","root");
		    con = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.user"),prop.getProperty("db.password"));
		    
		    
		} catch (FileNotFoundException ex) {
		    System.out.println(ex);
		} catch (IOException ex) {
			System.out.println(ex);
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prop.getProperty("db.url"));
		System.out.println(prop.getProperty("db.password"));
		
		
		
		

        return con;
    }

}
