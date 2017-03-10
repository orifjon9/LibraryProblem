package com.mum.edu.library.ui.checkout;

import java.util.Set;

import com.mum.edu.library.model.Role;

public class RoleFactory {
	private static RoleFactory roleFactory = new RoleFactory();
	private Set<Role> roles;
	
	RoleFactory(){}
	
	public static RoleFactory getInstance(){
		return roleFactory;
	}
	
	public Set<Role> getRoles(){
		return roles;
	}
	
	public void setRoles(Set<Role> aRoles){
		this.roles = aRoles;
	}

}
