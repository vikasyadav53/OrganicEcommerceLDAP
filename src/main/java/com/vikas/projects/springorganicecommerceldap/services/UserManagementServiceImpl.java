package com.vikas.projects.springorganicecommerceldap.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vikas.projects.springorganicecommerceldap.models.User;
import com.vikas.projects.springorganicecommerceldap.utils.CreateUserUtility;

public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	CreateUserUtility createUserUtil;

	@Override
	public List<String> getUserRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserDetailsById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUserDetailsById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User lockUser(boolean isLock) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User activateUser(boolean isActive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser() {
		// TODO Auto-generated method stub
		return null;
	}

}
