package dbConnection;

/*
 * AUTOR:  Daniel García Páez
 * 
 * Clase encargada de crear la conexión con la BD de MySQL
 */ 

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.NamingException;

public class DB_Connection {

	private final static String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	
	static {
		
		try {
			Class.forName(DRIVER_CLASS_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}
		
	}

	private DB_Connection() {}
	
	
	//Devuelve una conexión a la BD
	public final static Connection getConnection() throws SQLException, NamingException, FileNotFoundException, IOException {
		Connection conn = null;
	    Properties props = new Properties();
	    
	    props.load(new FileInputStream("resources/database.properties"));

	    conn = DriverManager.getConnection("jdbc:mysql://localhost/"+props.getProperty("database"),
	    		props.getProperty("dbuser"), "");
	    
	    System.out.println("Connected to database");
	    return conn;
	}

}
