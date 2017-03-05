package com.mum.edu.library.model;

import java.util.Set;

public class Employee extends People {
	private int idNumber;
	private String password;
	private Set<Role> roles;
	
	public Employee(int idNumber,String password, String firstName, String lastName, Address address, String phoneNumber, 
			Set<Role> roles) {
		super(firstName,lastName,address,phoneNumber);
		this.idNumber = idNumber;
		this.password = password;
		this.roles = roles;
	}

	public int getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(int idNumber) {
		this.idNumber = idNumber;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	

}
