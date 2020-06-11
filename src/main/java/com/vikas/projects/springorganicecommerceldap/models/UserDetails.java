package com.vikas.projects.springorganicecommerceldap.models;

import java.util.Date;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class UserDetails {
	
	private String userId;
	private String userName;
	private String userPwd;
	private String gender;
	private Long dob;
	private List<String> userRoles;
	private boolean isLock;
	private Boolean isActive;
	private String createrName;
	private String lastModiferName;
	private Long createdDate;
	private Long modifiedDate;
	private ContactDetails contactDetails;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Long getDob() {
		return dob;
	}
	public void setDob(Long dob) {
		this.dob = dob;
	}
	public List<String> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}
	public boolean isLock() {
		return isLock;
	}
	public void setLock(boolean isLock) {
		this.isLock = isLock;
	}
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getLastModiferName() {
		return lastModiferName;
	}
	public void setLastModiferName(String lastModiferName) {
		this.lastModiferName = lastModiferName;
	}
	public Long getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Long createdDate) {
		this.createdDate = createdDate;
	}
	public Long getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Long modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public ContactDetails getContactDetails() {
		return contactDetails;
	}
	public void setContactDetails(ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	
	

}
