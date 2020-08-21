package com.saif.nisum.model;

import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserDTO {

	@Id
	private String userId;
	private String name;
	private String email;
	private String password;
	private Date created;
	private Date modified;
	private Date lastLogin;
	private Phones phones[];
	private String token;
	private boolean isactive;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	public Phones[] getPhones() {
		return phones;
	}

	public void setPhones(Phones[] phones) {
		this.phones = phones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isIsactive() {
		return isactive;
	}

	public void setIsactive(boolean isactive) {
		this.isactive = isactive;
	}

	@Override
	public String toString() {
		return String.format(
				"UserDTO [userId=%s, name=%s, email=%s, password=%s, created=%s, modified=%s, lastLogin=%s, phones=%s, token=%s, isactive=%s]",
				userId, name, email, password, created, modified, lastLogin, Arrays.toString(phones), token, isactive);
	}

}
