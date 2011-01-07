package com.zuaari.messages;

import com.zuaari.login.LoginService;

public class LoginRequest implements IMessage {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "LoginRequest";
	}

	@Override
	public void setMessage(String msg) {
		// TODO Auto-generated method stub

	}

	public IMessage performAction() {

		return LoginService.getLoginService().doLogin(this);

	}

}
