package com.revature.util;

import com.revature.controllers.AddReimbController;
import com.revature.controllers.Controller;
import com.revature.controllers.LoginController;
import com.revature.controllers.approveReimbController;
import com.revature.controllers.createReimbController;
import com.revature.controllers.getReimbController;

public enum Delegate {
	LOGIN(new LoginController()),
	GETREIMB(new getReimbController()),
	CREATEREIMB(new createReimbController()),
	APPROVEREIMB(new approveReimbController()),
	ADDREIMB(new AddReimbController()),
	NOT_FOUND(new Controller() {});
	
	public Controller controller;
	
	private Delegate(Controller controller) {
		this.controller = controller;
	}
	
	public static Delegate getDelegate(String str) {
		if (str == null) {
			return NOT_FOUND;
		}
		
		String upper = str.toUpperCase();
		for(Delegate d : Delegate.values()) {
			if (d.name().equals(upper)) {
				return d;
			}
		}
		return NOT_FOUND;
	}
}