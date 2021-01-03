package com.springboot.h2.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

// @Entity annotation specifies that the class is mapped to a database table.
@Entity
public class CommonUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// @Id annotation specifies the primary key of an entity.
	// @GeneratedValue provides the generation strategy specification for the primary key values.
	@Id
	@GeneratedValue
	private int userId;
	
	private String userName;
	private String password;
	private boolean isActive=true;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="user_id")
	//@JoinTable(name ="user_role", joinColumns = @JoinColumn (name="user_id"), inverseJoinColumns = @JoinColumn (name="role_id"))
	private Set<UserRole> userRoleSet=new HashSet<UserRole>();

	// Default constructor.
	public CommonUser() {	}

	// Parameterized constructor.
	
		public CommonUser(int userId, String userName, String pass,boolean isActive) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = pass;
		this.isActive=isActive;
	}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String pass) {
			this.password = pass;
		}

		public Set<UserRole> getUserRoleSet() {
			return userRoleSet;
		}

		public void setUserRoleSet(Set<UserRole> userRoleSet) {
			this.userRoleSet = userRoleSet;
		}

		
		public boolean isActive() {
			return isActive;
		}

		public void setActive(boolean isActive) {
			this.isActive = isActive;
		}

		@Override
		public String toString() {
			return "CommonUser [userId=" + userId + ", userName=" + userName + ", password=" + password + ", isActive="
					+ isActive + ", userRoleSet=" + userRoleSet + "]";
		}

		
	}
