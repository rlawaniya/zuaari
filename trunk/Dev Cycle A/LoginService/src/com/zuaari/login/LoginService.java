package com.zuaari.login;

import com.zuaari.messages.LoginRequest;
import com.zuaari.messages.LoginResponse;

public class LoginService implements ILoginService {

	static LoginService loginService;

	private LoginService() {
	}

	public static LoginService getLoginService() {
		if (loginService == null) {
			loginService = new LoginService();
		}
		return loginService;
	}

	@Override
	public LoginResponse doLogin(LoginRequest loginRequest) {
		// TODO Auto-generated method stub
		return new LoginResponse();
	}

}
