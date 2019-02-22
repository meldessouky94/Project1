package com.revature.daos;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementDAO {

	String URL = "jdbc:postgresql://localhost:5432/postgres";
	String USER = "postgres";
	String PASS = "password";

	public List<Reimbursement> getAll() throws SQLException, ClassNotFoundException {
		List<Reimbursement> reimbursement = new ArrayList<>();
		String sql = "SELECT * FROM ERS_REIMBURSEMENT";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			Reimbursement reimb = new Reimbursement();
			while (rs.next()) {
				reimb.setReimbId(rs.getLong("REIMB_ID"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setAuthor(rs.getLong("REIMB_AUTHOR"));
				reimb.setResolver(rs.getLong("REIMB_RESOLVER"));
				reimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
				reimb.setType(rs.getInt("REIMB_TYPE_ID"));
				reimbursement.add(reimb);
			}
		}
		return reimbursement;
	}

	public List<Reimbursement> getAll(User u) throws SQLException, ClassNotFoundException {
		List<Reimbursement> reimbursement = new ArrayList<>();
		String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, u.getUserId());
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getLong("REIMB_ID"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setAuthor(rs.getLong("REIMB_AUTHOR"));
				reimb.setResolver(rs.getLong("REIMB_RESOLVER"));
				reimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
				reimb.setType(rs.getInt("REIMB_TYPE_ID"));
				reimbursement.add(reimb);
			}
		}
		return reimbursement;
	}

	public void getReimbursements(List<Reimbursement> rList, long id) throws ClassNotFoundException {
		String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Reimbursement reimb = new Reimbursement();
				reimb.setReimbId(rs.getLong("REIMB_ID"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setAuthor(rs.getLong("REIMB_AUTHOR"));
				reimb.setResolver(rs.getLong("REIMB_RESOLVER"));
				reimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
				reimb.setType(rs.getInt("REIMB_TYPE_ID"));
				rList.add(reimb);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Reimbursement getReimbById(Long id) throws ClassNotFoundException, SQLException {
		Reimbursement reimb = new Reimbursement();
		String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_ID = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				reimb = new Reimbursement();
				reimb.setReimbId(id);
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setAuthor(rs.getLong("REIMB_AUTHOR"));
				reimb.setResolver(rs.getLong("REIMB_RESOLVER"));
				reimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
				reimb.setType(rs.getInt("REIMB_TYPE_ID"));
			}
		}
		return reimb;
	}

	public Reimbursement getReimbByAuthor(long authorId, Timestamp submitted)
			throws ClassNotFoundException, SQLException {
		Reimbursement reimb = new Reimbursement();
		String sql = "SELECT * FROM ERS_REIMBURSEMENT WHERE REIMB_AUTHOR = ? AND REIMB_SUBMITTED = ?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, authorId);
			statement.setTimestamp(2, submitted);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				reimb = new Reimbursement();
				reimb.setReimbId(rs.getLong("REIMB_ID"));
				reimb.setAmount(rs.getDouble("REIMB_AMOUNT"));
				reimb.setSubmitted(rs.getTimestamp("REIMB_SUBMITTED"));
				reimb.setResolved(rs.getTimestamp("REIMB_RESOLVED"));
				reimb.setDescription(rs.getString("REIMB_DESCRIPTION"));
				reimb.setAuthor(rs.getLong("REIMB_AUTHOR"));
				reimb.setResolver(rs.getLong("REIMB_RESOLVER"));
				reimb.setStatus(rs.getInt("REIMB_STATUS_ID"));
				reimb.setType(rs.getInt("REIMB_TYPE_ID"));
			}
		}
		return reimb;
	}

	public Reimbursement insert(Reimbursement reimb) throws SQLException, ClassNotFoundException {
		String sql = "INSERT INTO ERS_REIMBURSEMENT "
				+ "(REIMB_AMOUNT, REIMB_SUBMITTED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_STATUS_ID"
				+ ", REIMB_TYPE_ID) " + "VALUES (?, ?, ?, ?, ?, ?)";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			reimb.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
			PreparedStatement statement = conn.prepareStatement(sql);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				statement.setDouble(1, reimb.getAmount());
				statement.setTimestamp(2, reimb.getSubmitted());
				statement.setString(3, reimb.getDescription());
				statement.setLong(4, reimb.getAuthor());
				statement.setInt(5, reimb.getStatus());
				statement.setInt(6, reimb.getType());
				int RowsAffected = statement.executeUpdate();
				if (RowsAffected == 1) {
					return this.getReimbByAuthor(reimb.getAuthor(), reimb.getSubmitted());
				}
			}
		}
		return null;
	}

	public void addReimbursement(Reimbursement reimb) throws ClassNotFoundException {
		String sql = "INSERT INTO ERS_REIMBURSEMENT(reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_id, reimb_type_id) VALUES (?, CURRENT_TIMESTAMP, ?, ?, ?, ?)";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setDouble(1, reimb.getAmount());
			statement.setString(2, reimb.getDescription());
			statement.setLong(3, reimb.getAuthor());
			statement.setInt(4, 1);
			statement.setLong(5, reimb.getType());
			statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public Reimbursement updateReimbursement(Reimbursement r) throws SQLException, ClassNotFoundException {
		String sql = "UPDATE ERS_REIMBURSEMENT SET REIMB_RESOLVED=?,REIMB_RESOLVER=?,REIMB_STATUS_ID=? WHERE REIMB_ID=?";
		Class.forName("org.postgresql.Driver");
		try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setTimestamp(1, r.getResolved());
			statement.setLong(2, r.getResolver());
			statement.setInt(3, r.getStatus());
			statement.setLong(4, r.getReimbId());
			int numRowsAffected = statement.executeUpdate();
			if (numRowsAffected == 1) {
				return r;
			}
		}
		return null;
	}
}

