package com.zuaari.messages;

import com.zuaari.registration.RegistrationService;

public class RegistrationRequest implements IMessage {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMessage(String msg) {
		/// TODO Auto-generated method stub

	}

	public IMessage performAction() {
		return RegistrationService.getRegistrationService()
				.doRegistration(this);
	}

}
