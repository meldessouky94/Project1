package com.revature.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.ReimbursementDAO;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.services.ReimbursementService;

public class getReimbController extends LoginController implements Controller {

	ReimbursementService rs = new ReimbursementService();
	User u = new User();

	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ObjectMapper om = new ObjectMapper();
		List<Reimbursement> rList = new ArrayList<>();
		ReimbursementDAO viewReimb = new ReimbursementDAO();
		try {
			viewReimb.getReimbursements(rList, user.getUserId());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		System.out.println(user.getUsername());
		om.writeValue(output, rList);
		byte[] reimbByte = output.toByteArray();
		System.out.println(new String(reimbByte));
		Writer w = response.getWriter();
		w.write(new String(reimbByte));
	}
}
