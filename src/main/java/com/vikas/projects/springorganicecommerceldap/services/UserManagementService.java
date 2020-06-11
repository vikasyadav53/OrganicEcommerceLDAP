package com.vikas.projects.springorganicecommerceldap.services;

import java.util.List;

import com.vikas.projects.springorganicecommerceldap.models.User;

public interface UserManagementService {
	
	public List<String> getUserRoles();
	
	public User getUserDetailsById();
	
	public User saveUserDetailsById();
	
	public User lockUser(boolean isLock);
	
	public User activateUser( boolean isActive);
	
	public User deleteUser(); 
	
	
}
