package com.mum.edu.library.model;

import java.util.HashSet;
import java.util.Set;

public class Role {
	private String roleName;
	private Set<Employee> employees;
	
	public Role(String roleName) {
		this.roleName = roleName;
		employees = new HashSet<>();
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}

}
