package com.authentication.einstein.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.ToString;

//inbuilt to validate
@ToString
@Component

@NoArgsConstructor

public class UserDetail implements UserDetails {

	private static final long serialVersionUID = 1L;

	private String emailId;

	private String userName;

	private String password;
	

	public UserDetail(PersonalDetails personalDetails) {
		this.emailId = personalDetails.getEmailId();
		this.userName = personalDetails.getUserName();
		this.password = personalDetails.getPassword();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
