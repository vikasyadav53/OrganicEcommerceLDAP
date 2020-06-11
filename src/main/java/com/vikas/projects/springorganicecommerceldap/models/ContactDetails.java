package com.vikas.projects.springorganicecommerceldap.models;

import java.util.List;

public class ContactDetails {
	
	private String contactId;
	private String phone;
	private String email;
	private List<AddressDetails> address;
	public String getContactId() {
		return contactId;
	}
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<AddressDetails> getAddress() {
		return address;
	}
	public void setAddress(List<AddressDetails> address) {
		this.address = address;
	}
	
	

}
