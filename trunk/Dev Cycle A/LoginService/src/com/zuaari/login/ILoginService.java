package com.zuaari.login;

import com.zuaari.messages.LoginRequest;
import com.zuaari.messages.LoginResponse;

public interface ILoginService {
	LoginResponse doLogin(LoginRequest loginRequest);

}
