package com.vikas.projects.springorganicecommerceldap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;

@SpringBootApplication
public class OrganicEcommerceLdapApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrganicEcommerceLdapApplication.class, args);
	}
	
	@Bean
	public LdapContextSource getContextSource() {
		LdapContextSource contextSource = new LdapContextSource();
		contextSource.setUrl("ldap://localhost:389");
		contextSource.setUserDn("cn=Manager");
		contextSource.setPassword("secret");
		return contextSource;
	}
	
	@Bean(name="ldapTemplate")
	public LdapTemplate getLdapTemplate() {
		LdapTemplate ldapTemplate = new LdapTemplate(getContextSource());
		return ldapTemplate;
	}

}
