package com.zuaari.messages;

public class LoginRequest extends Message {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8625052008882548529L;
	private String user;
	private String password;

	public LoginRequest() {
	}
	public LoginRequest(String user,String password) {
		this.user=user;
		this.password=password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return "LoginRequest";
	}

	@Override
	public void setMessage(String msg) {
		// TODO Auto-generated method stub

	}


}
