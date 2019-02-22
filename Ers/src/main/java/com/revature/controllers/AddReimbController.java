package com.revature.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;

public class AddReimbController implements Controller {
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("get actor");
	}

	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ObjectMapper om = new ObjectMapper();
    ReimbursementDAO addReimb = new ReimbursementDAO();
    Reimbursement reimb = om.readValue(request.getReader(), Reimbursement.class);
    try {
		addReimb.addReimbursement(reimb);
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}

