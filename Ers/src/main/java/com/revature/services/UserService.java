package com.revature.services;

import java.sql.SQLException;

import com.revature.daos.UserDAO;
import com.revature.dto.LoginRequestDTO;
import com.revature.models.User;
import com.revature.util.HttpException;

public class UserService {

	private UserDAO userDao = new UserDAO();
	
	public User login(LoginRequestDTO dto) throws ClassNotFoundException, SQLException {
		System.out.println(dto);
		User user = userDao.getUserByUsername(dto.getUsername());
		System.out.println(user);
		if(user != null && user.getPassword().equals(dto.getPassword())) {
			return user;
		}
		throw new HttpException(401, "Invalid login credentials");
	}

}