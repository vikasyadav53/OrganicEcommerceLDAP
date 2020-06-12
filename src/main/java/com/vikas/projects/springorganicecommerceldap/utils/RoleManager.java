package com.vikas.projects.springorganicecommerceldap.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;


@Component
public class RoleManager {

	private static Map<String, List<String>> roles_actions_map;
	private static String[] adminRoles = new String[] { "", "", "" };
	private static String[] managerRoles = new String[] { "", "", "" };
	private static String[] userRoles = new String[] { "", "", "" };
	private static String[] guestRoles = new String[] { "", "", "" };

	static {
		if (roles_actions_map != null) {
			roles_actions_map = new HashMap<String, List<String>>();
		}
		roles_actions_map.put("adminRoles", Arrays.asList(adminRoles));
		roles_actions_map.put("managerRoles", Arrays.asList(managerRoles));
		roles_actions_map.put("userRoles", Arrays.asList(userRoles));
		roles_actions_map.put("guestRoles", Arrays.asList(guestRoles));
	}

	public String getActions(String key) throws Exception {
		try {
			ArrayList<String> roles = new ArrayList<String>(); 
			if (roles_actions_map.containsKey(key)) {
				roles = (ArrayList)roles_actions_map.get(key);
			}
			String roles_string = String.join(", ", roles);
			return roles_string;
		} catch(Exception ex) {
			throw ex;
		}
	}
	
	public boolean isRoleAvailable(String role) {
		return roles_actions_map.containsKey(role);
	}
	
	public boolean isActionPresentForRole(String role, String action) {
		if(isRoleAvailable(role)) {
			List<String> actions = roles_actions_map.get("role");
			return actions.contains(action);
		}
		
		return false;
	}

}
