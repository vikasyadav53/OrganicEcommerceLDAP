package com.vikas.projects.springorganicecommerceldap.client;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.vikas.projects.springorganicecommerceldap.models.AddressDetails;
import com.vikas.projects.springorganicecommerceldap.models.ContactDetails;
import com.vikas.projects.springorganicecommerceldap.models.UserDetails;
import com.vikas.projects.springorganicecommerceldap.utils.RoleManager;

@Repository
public class LdapOperationsClient {

	@Autowired
	@Qualifier("ldapTemplate")
	private LdapTemplate ldapTemplate;

	@Autowired
	private RoleManager roleManager;

	public void addUser(UserDetails user) throws Exception {
		Attributes userAttr = new BasicAttributes();
		userAttr.put("customerId", user.getUserId());
		userAttr.put("customerName", user.getUserName());
		userAttr.put("customerGender", user.getGender());
		userAttr.put("customerDOB", user.getDob());
		userAttr.put("customerPwd", user.getUserPwd());

		for (String role : user.getUserRoles()) {
			if (!roleManager.isRoleAvailable(role)) {
				throw new Exception("Roles not available");
			}
		}
		String role_string = String.join(", ", user.getUserRoles());
		userAttr.put("customerRole", role_string);
		userAttr.put("isLock", user.isLock());
		if (null != user.getIsActive() && !user.getIsActive()) {
			userAttr.put("isActive", user.getIsActive());
		} else {
			userAttr.put("isActive", true);
		}
		userAttr.put("createrName", user.getCreaterName());
		userAttr.put("modifyName", user.getLastModiferName());
		userAttr.put("createTime", user.getCreatedDate());
		userAttr.put("modifyTime", user.getModifiedDate());

		userAttr = addContactDetails(userAttr, user.getContactDetails());

		// Add the multi-valued attribute
		BasicAttribute objectClassAttribute = new BasicAttribute("objectclass");
		objectClassAttribute.add("top");
		objectClassAttribute.add("person");
		objectClassAttribute.add("organizationalperson");
		objectClassAttribute.add("inetorgperson");
		objectClassAttribute.add("contactObject");
		objectClassAttribute.add("userRoleActionObject");
		objectClassAttribute.add("userObject");

		userAttr.put(objectClassAttribute);

		ldapTemplate.bind("uid=" + user.getContactDetails().getEmail() + ",ou=customer,dc=organicecommerce,dc=com",
				null, userAttr);

	}

	private Attributes addContactDetails(Attributes userAttr, ContactDetails contactDetails2) {
		userAttr.put("phone", contactDetails2.getPhone());
		userAttr.put("custEmailAddress", contactDetails2.getEmail());
		Attribute addresses = new BasicAttribute("addresses");
		for (AddressDetails address : contactDetails2.getAddress()) {
			addresses.add(address.toString());
		}
		userAttr.put(addresses);
		return userAttr;
	}

	public void modifyUserDetails(UserDetails user) {
		ModificationItem item;
		List<ModificationItem> list = new ArrayList<ModificationItem>();
		if (user.getUserId() != null && !StringUtils.isEmpty(user.getUserId())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("customerId", user.getUserId()));
			list.add(item);
		}
		if (user.getUserName() != null && !StringUtils.isEmpty(user.getUserName())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("customerName", user.getUserName()));
			list.add(item);
		}
		if (user.getUserPwd() != null && !StringUtils.isEmpty(user.getUserPwd())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("customerPwd", user.getUserPwd()));
			list.add(item);
		}
		if (user.getGender() != null && !StringUtils.isEmpty(user.getGender())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("customerGender", user.getGender()));
			list.add(item);
		}
		if (user.getDob() != null && !StringUtils.isEmpty(user.getDob())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("customerDOB", user.getDob()));
			list.add(item);
		}
		if (user.isLock()) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("isLock", true));
		} else {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("isLock", false));
		}
		list.add(item);
		if (user.getIsActive() != null && !user.getIsActive()) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("isActive", false));
		} else {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE, new BasicAttribute("isActive", true));
		}
		list.add(item);
		if (user.getCreaterName() != null && !StringUtils.isEmpty(user.getCreaterName())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("createrName", user.getCreaterName()));
			list.add(item);
		}
		if (user.getLastModiferName() != null && !StringUtils.isEmpty(user.getLastModiferName())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("modifyName", user.getLastModiferName()));
			list.add(item);
		}
		if (user.getContactDetails().getEmail() != null && !StringUtils.isEmpty(user.getContactDetails().getEmail())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("custEmailAddress", user.getContactDetails().getEmail()));
			list.add(item);
		}
		if (user.getContactDetails().getPhone() != null && !StringUtils.isEmpty(user.getContactDetails().getPhone())) {
			item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
					new BasicAttribute("phone", user.getContactDetails().getPhone()));
			list.add(item);
		}
		item = new ModificationItem(DirContext.REPLACE_ATTRIBUTE,
				new BasicAttribute("modifyTime", System.currentTimeMillis()));
		list.add(item);

		ldapTemplate.modifyAttributes(
				"uid=" + user.getContactDetails().getEmail() + "," + "ou=customer,dc=organicecommerce,dc=com",
				(ModificationItem[]) list.toArray());

	}

	public List<String> searchUserRole(String email) {
		List<String> rolesList = ldapTemplate.search("ou=customer dc=organicecommerce,dc=com",
				new EqualsFilter("modifyName", email.toLowerCase()).encode(), new RolesAttributesMapper());
		return rolesList;
	}
	
	private class RolesAttributesMapper implements AttributesMapper<String> {
        public String mapFromAttributes(Attributes attrs) throws NamingException {
        	String role = null;
        	Attribute rls = attrs.get("customerRole");
        	if (rls != null) {
        		role = ((String)rls.get());
        	}
            return role;
        }
    }
	
	public boolean validateIfAuthorizeForOperations(List<String> roles) {
		boolean isAuthorized = false;
		for(String role: roles) {
			isAuthorized = roleManager.isActionPresentForRole(role, "MODIFY_USER");
			if (isAuthorized) 
				break;
		}
		return isAuthorized;		
	}
	
	public boolean checkIfUserExists(String email) {
		List<UserDetails> userList = ldapTemplate.search("ou=customer dc=organicecommerce,dc=com", new EqualsFilter("custEmailAddress", email.toLowerCase()).encode(), new UserAttributesMapper());
		return (userList == null || userList.size() <0) ? false : true;
	}
	
	private class UserAttributesMapper implements AttributesMapper<UserDetails> {
        public UserDetails mapFromAttributes(Attributes attrs) throws NamingException {
        	UserDetails user = new UserDetails();
        	user.setUserName((String)attrs.get("customerName").get());
            return user;
        }
    }
}
