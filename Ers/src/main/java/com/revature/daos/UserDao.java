package com.revature.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;

public class UserDAO {

//	private static Logger log = Logger.getRootLogger();

	String URL = "jdbc:postgresql://localhost:5432/postgres";
	String USER = "postgres";
	String PASS = "password";

	public User createUser(User u) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO ERS_USER(ERS_USERS_ID, ERS_USERNAME, ERS_PASSWORD, USER_FIRST_NAME, USER_LAST_NAME, USER_EMAIL, USER_ROLE_ID FROM ERS_USERS) "
				+ "VALUES(NULL,?,?,?,?,?,?)";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getFirstName());
			statement.setString(4, u.getLastName());
			statement.setString(5, u.getEmail());
			statement.setInt(6, u.getRole());
			int RowsEffected = statement.executeUpdate();
			if(RowsEffected == 1) return u;
		}
		return null;
	}

	public User getUserByUsername(String Username) throws ClassNotFoundException, SQLException {
		User u = null;
		String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, Username);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUserId(rs.getLong("ERS_USERS_ID"));
				u.setUsername(rs.getString("ERS_USERNAME"));
				u.setPassword(rs.getString("ERS_PASSWORD"));
				u.setFirstName(rs.getString("ERS_FIRST_NAME"));
				u.setLastName(rs.getString("ERS_LAST_NAME"));
				u.setEmail(rs.getString("ERS_EMAIL"));
				u.setRole(rs.getInt("ERS_ROLE_ID"));
			}
		}
		return u;
	}

	public User getUserById(int id) throws ClassNotFoundException, SQLException {
		User u = null;
		String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERS_ID = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				u = new User();
				u.setUserId(rs.getLong("ERS_USERS_ID"));
				u.setUsername(rs.getString("ERS_USERNAME"));
				u.setPassword(rs.getString("ERS_PASSWORD"));
				u.setFirstName(rs.getString("ERS_FIRST_NAME"));
				u.setLastName(rs.getString("ERS_LAST_NAME"));
				u.setEmail(rs.getString("ERS_EMAIL"));
				u.setRole(rs.getInt("ERS_ROLE_ID"));
			}
		}
		return u;
	}

	public List<User> getAllUsers() throws ClassNotFoundException, SQLException {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM ERS_USER";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				User u = new User();
				u.setUserId(rs.getLong("ERS_USERS_ID"));
				u.setUsername(rs.getString("ERS_USERNAME"));
				u.setPassword(rs.getString("ERS_PASSWORD"));
				u.setFirstName(rs.getString("ERS_FIRST_NAME"));
				u.setLastName(rs.getString("ERS_LAST_NAME"));
				u.setEmail(rs.getString("ERS_EMAIL"));
				u.setRole(rs.getInt("ERS_ROLE_ID"));
				users.add(u);
			}
		}
		return users;
	}
	
	public User updateUser(User u) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO ERS_USER(ERS_USERS_ID,ERS_USERNAME,ERS_PASSWORD,ERS_FIRST_NAME,ERS_LAST_NAME,ERS_EMAIL,ERS_ROLE_ID)" + 
				"VALUES(NULL,?,?,?,?,?,?)";	
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, u.getUsername());
			statement.setString(2, u.getPassword());
			statement.setString(3, u.getFirstName());
			statement.setString(4, u.getLastName());
			statement.setString(5, u.getEmail());
			statement.setInt(6, u.getRole());
			int numRowsEffected = statement.executeUpdate();
			if(numRowsEffected == 1) return u;
		}
		return null;
	}

}
