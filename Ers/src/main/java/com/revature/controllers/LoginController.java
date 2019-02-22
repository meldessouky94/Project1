package com.revature.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.LoginRequestDTO;
import com.revature.models.User;
import com.revature.services.UserService;

public class LoginController implements Controller{
	
	UserService userService = new UserService();
	static User user = new User();
	
	public void handlePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ObjectMapper om = new ObjectMapper();
		LoginRequestDTO dto = om.readValue(request.getReader(), LoginRequestDTO.class);
	
		try {
			user = userService.login(dto);
			response.setContentType("application/json");
			response.getWriter().write(om.writeValueAsString(user));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}