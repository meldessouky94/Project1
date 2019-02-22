package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class createReimbController implements Controller {

	ReimbursementService rs = new ReimbursementService();
	
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		Reimbursement reimbTicket = null;
		try {
			reimbTicket = mapper.readValue(request.getReader(), Reimbursement.class);
			System.out.println("Creating new reimbursement request:\n" + reimbTicket.toString());
			reimbTicket = rs.createReimb(reimbTicket);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		return reimbTicket;
	}
}
