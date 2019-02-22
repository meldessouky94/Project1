package com.revature.services;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;

public class ReimbursementService {

	private ReimbursementDAO reimbDao = new ReimbursementDAO();
	
	public List<Reimbursement> getReimbs(User u) throws ClassNotFoundException, SQLException {
		List<Reimbursement> reimb = null;
		reimb = reimbDao.getAll();
		return reimb;
	}
	
	public Reimbursement createReimb(User u, double amount, String description, int type) throws ClassNotFoundException, SQLException {
		Reimbursement reimb = new Reimbursement();
		reimb.setAmount(amount);
		reimb.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
		reimb.setResolved(null);
		reimb.setDescription(description);
		reimb.setReceipt(null);
		reimb.setAuthor(u.getUserId());
		reimb.setResolver(null);
		reimb.setStatus(1);
		reimb.setType(type);
		return reimbDao.insert(reimb);
	}
	
	public Reimbursement createReimb(Reimbursement reimb) throws ClassNotFoundException, SQLException {
		reimb.setSubmitted(Timestamp.valueOf(LocalDateTime.now()));
		reimb.setResolved(null);
		reimb.setReceipt(null);
		reimb.setResolver(null);
		reimb.setStatus(1);
		return reimbDao.insert(reimb);
	}
	
	public Reimbursement approveReimb(Reimbursement t) throws ClassNotFoundException, SQLException {
		Reimbursement dbTicket = reimbDao.getReimbById(t.getReimbId());
		System.out.println("ReimbTicket " + dbTicket.toString() + " gathered from DB");
		if (dbTicket.getStatus()==1) {
			//If the resolver is not the author, and has the authorization to resolve reimbTickets, and the ticket is not resolved
			dbTicket.setResolved(Timestamp.valueOf(LocalDateTime.now()));
			dbTicket.setAuthor(t.getResolver());
			dbTicket.setStatus(3);
			t = reimbDao.insert(dbTicket); // If the SQL update is successful, it will return the updated ReimbTicket
			System.out.println("Ticket is updated to " + t.toString());
			return t;
		}
		return null;
	}
	
	public Reimbursement approveReimb(Reimbursement t, User u) throws ClassNotFoundException, SQLException {
		if (t.getAuthor() != u.getUserId() && u.getRole()==1 && t.getAuthor() == null) {
			//If the resolver is not the author, and has the authorization to resolve reimbTickets, and the ticket is not resolved
			t.setResolved(Timestamp.valueOf(LocalDateTime.now()));
			t.setReimbId(u.getUserId());
			t.setStatus(3);
			return reimbDao.insert(t); // If the SQL update is successful, it will return the updated ReimbTicket
		}
		return null;
	}
}
