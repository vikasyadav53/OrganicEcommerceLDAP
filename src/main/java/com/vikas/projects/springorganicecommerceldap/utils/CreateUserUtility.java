package com.vikas.projects.springorganicecommerceldap.utils;

import org.springframework.stereotype.Component;

import com.vikas.projects.springorganicecommerceldap.models.User;

@Component
public class CreateUserUtility {

	public static User clientToLdap(User user) {
		return user;
	}
	
	public static User ldapToUser(User user) {
		return user;
	}

}
