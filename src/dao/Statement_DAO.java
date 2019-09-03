package dao;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import core.Statement_;

public class Statement_DAO {
	
	private Connection myConn;

	public Statement_DAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("Statement_ DAO - DB connection successful to: " + dburl);
	}
	
	public String statement_inject(String Statement_) throws SQLException {
		
		
		Statement statement = null;
		Boolean ret = null;
		
		String success = null;
		String fail= null;
		
		ExceptionUtils utils = new ExceptionUtils();
		
		try {
		
			statement = myConn.createStatement();
			
			// execute SQL
			ret = statement.execute(Statement_);
			
			success= "Statement executed successfully.";
			
			return success;
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
			fail =  utils.getStackTrace(exc);
			return fail;
		}
		
	}
	
}
