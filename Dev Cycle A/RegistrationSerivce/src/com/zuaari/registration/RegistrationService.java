package com.zuaari.registration;

import com.zuaari.messages.RegistrationRequest;
import com.zuaari.messages.RegistrationResponse;

public class RegistrationService implements IRegistration {
	static RegistrationService registrationService;

	private RegistrationService() {
	}

	public static RegistrationService getRegistrationService() {
		if (registrationService == null) {
			registrationService = new RegistrationService();
		}
		return registrationService;

	}

	@Override
	public RegistrationResponse doRegistration(RegistrationRequest regReq) {
		// TODO Auto-generated method stub
		return new RegistrationResponse();
	}

}
