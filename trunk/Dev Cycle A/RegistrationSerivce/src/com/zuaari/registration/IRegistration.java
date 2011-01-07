package com.zuaari.registration;

import com.zuaari.messages.RegistrationRequest;
import com.zuaari.messages.RegistrationResponse;

/**
 * Registration service can be used for user registration through any medium.
 * User registration can be done through web-client or web-site directly.
 * Registration service returns RegistrationResponse message contained active
 * session ID which can be used for auto login.
 * 
 * @author Ravish
 * 
 */

public interface IRegistration {
	RegistrationResponse doRegistration(RegistrationRequest regReq);

}
