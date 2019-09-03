package dao;

import java.io.FileInputStream;
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

import core.User_;
import util.User_PasswordUtils;

public class User_DAO {
	
	private Connection myConn;
	
	public User_DAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("User_DAO - DB connection successful to: " + dburl);
	}
	
	public List<User_> user_get(boolean admin, int user_Id) throws Exception {
		List<User_> list = new ArrayList<User_>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			
			String sql = null;
			
			if (admin) {
				// get all user_
				sql = "select * from user_ order by user_id";
			}
			else {
				// only the current user_
				sql = "select * from user_ where user_id=" + user_Id + " order by user_id";
			}
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				User_ user_temp = user_convertRow(myRs);
				list.add(user_temp);
			}

			return list;		
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	private User_ user_convertRow(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("user_id");
		String name = myRs.getString("user_name");
		String username = myRs.getString("user_userName");
		String password = myRs.getString("user_password");

		boolean admin = myRs.getBoolean("user_isAdmin");
		boolean bachelor = myRs.getBoolean("user_isBachelor");
		boolean contestant = myRs.getBoolean("user_isContestant");
		
		User_ user_temp = new User_(id, name, username, password, admin, bachelor, contestant);
		
		return user_temp;
	}
	
	public void user_add(User_ user_) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("insert into user_"
					+ " (user_name, user_userName, user_password, user_isAdmin, user_isBachelor, user_isContestant)"
					+ " values (?, ?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setString(1, user_.getName());
			myStmt.setString(2, user_.getUsername());
			myStmt.setString(3, user_.getPassword());
			myStmt.setBoolean(4, user_.isAdmin());
			myStmt.setBoolean(5, user_.isBachelor());
			myStmt.setBoolean(6, user_.isContestant());
			
			// encrypt password
			String encryptedPassword = User_PasswordUtils.encryptPassword(user_.getPassword());
			myStmt.setString(3, encryptedPassword);
			
			// execute SQL
			myStmt.executeUpdate();				
		}
		finally {
			DAOUtils.close(myStmt);
		}
		
	}
	
	public void user_delete(User_ theUser_) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("delete from user_ where user_id=?");
			
			// set params
			myStmt.setInt(1, theUser_.getId());
			
			// execute SQL
			myStmt.executeUpdate();	
			
		}
		finally {
			DAOUtils.close(myStmt);
		}
		
	}
	
	public void user_update(User_ user_) throws Exception {
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update user_"
					+ " set user_name=?, user_userName=?, user_isAdmin=?"
					+ " where user_id=?");
			
			// set params
			myStmt.setString(1, user_.getName());
			myStmt.setString(2, user_.getUsername());
			myStmt.setBoolean(3, user_.isAdmin());
			myStmt.setInt(4, user_.getId());
			
			// execute SQL
			myStmt.executeUpdate();

		}
		finally {
			DAOUtils.close(myStmt);
		}		
	}
	
	public boolean authenticate(User_ user_) throws Exception {
		boolean result = false;
		
		String plainTextPassword = user_.getPassword();
		
		// get the encrypted password from database for this user_
		String passwordFromDatabase = getPassword(user_.getId());	
		String encryptedPasswordFromDatabase = null;
		Boolean result_check = false;
		
		if (passwordFromDatabase.length()>20) {
			encryptedPasswordFromDatabase= getEncrpytedPassword(user_.getId());
			result_check= User_PasswordUtils.checkPassword(plainTextPassword, encryptedPasswordFromDatabase);
		}
		
		System.out.println("plainTextPassword= "+plainTextPassword);
		System.out.println("passwordFromDatabase= "+passwordFromDatabase);
		
		if(plainTextPassword.equals(passwordFromDatabase)) {
			result = true;
		} else if (result_check) {
			result = true;
		}
	
		return result;
	}
	
	private String getEncrpytedPassword(int id) throws Exception {
		String encryptedPassword = null;
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select user_password from user_ where user_id=" + id);
			
			if (myRs.next()) {
				encryptedPassword = myRs.getString("user_password");
			}
			else {
				throw new Exception("User_ id not found: " + id);
			}

			return encryptedPassword;		
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}		
	}
	
	private String getPassword(int id) throws Exception {
		String password = null;
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select user_password from user_ where user_id=" + id);
			
			if (myRs.next()) {
				password = myRs.getString("user_password");
			}
			else {
				throw new Exception("User_ id not found: " + id);
			}

			return password;		
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}		
	}
	
	public void changePassword(User_ user_) throws Exception {

		// get plain text password
		String plainTextPassword = user_.getPassword();
		
		// encrypt the password
		String encryptedPassword = User_PasswordUtils.encryptPassword(plainTextPassword);
		
		// update the password in the database
		PreparedStatement myStmt = null;

		try {
			// prepare statement
			myStmt = myConn.prepareStatement("update user_"
					+ " set user_password=?"
					+ " where user_id=?");
			
			// set params
			myStmt.setString(1, encryptedPassword);
			myStmt.setInt(2, user_.getId());
			
			// execute SQL
			myStmt.executeUpdate();

		}
		finally {
			DAOUtils.close(myStmt);
		}		

	}
	
	public List<User_> user_search(String search, String columnName) throws Exception {
		List<User_> list = new ArrayList<User_>();

		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		
		String temp = columnName;
		
		try {
			search += "%";
			myStmt = myConn.prepareStatement("select * from user_ where "+temp+" like ?");
			
			//myStmt.setString(1, columnName);
			myStmt.setString(1, search);
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				User_ temp_User_ = user_convertRowTo(myRs);
				list.add(temp_User_);
			}
			
			return list;
			
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	public List<User_> user_getAll() throws Exception {
		List<User_> list = new ArrayList<User_>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from user_ order by user_name");
			
			while (myRs.next()) {
				User_ temp_User_ = user_convertRowTo(myRs);
				list.add(temp_User_);
			}

			return list;		
		}
		finally {
			DAOUtils.close(myStmt, myRs);
		}
	}
	
	private User_ user_convertRowTo(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("user_id");
		String name = myRs.getString("user_name");
		String username = myRs.getString("user_userName");
		String password = myRs.getString("user_password");
		boolean admin = myRs.getBoolean("user_isAdmin");
		boolean bachelor = myRs.getBoolean("user_isBachelor");
		boolean contestant = myRs.getBoolean("user_isContestant");
		
		User_ temp_User_ = new User_(id, name, username, password, admin, bachelor, contestant);
		
		return temp_User_;
	}

}
