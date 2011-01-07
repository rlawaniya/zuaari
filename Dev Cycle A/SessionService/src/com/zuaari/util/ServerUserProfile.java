package com.zuaari.util;

/**
 * This class hold all the user details with can be requested by any service.
 * Server create a ServerUserProfile for a user after successful login only.All
 * data fetch from DB. PersonalProfile contains player personal
 * details.PersonalProfile data is not expected to change frequently.
 * 
 * @author Ravish
 */

public class ServerUserProfile {
	String userName;
	String password;
	String screenName;
	PersonalProfile profile;

}
