package com.mum.edu.library.ui;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import com.mum.edu.library.model.Address;
import com.mum.edu.library.model.Employee;
import com.mum.edu.library.model.Role;


public class DefaultData {
	public static Role LIBRARYROLE = new Role("LIBRARIAN");
	public static Role ADMINROLE = new Role("ADMIN");
	public static List<Employee> LIBRARY_EMPLOYEE;
	static {
		Employee nguyenLe = new Employee(123,"123", "Nguyen", "Le", new Address("1000 N 4th", "Fairfield", "Iowa", "52707"), "+1 932 434 444", new HashSet<>(Arrays.asList(ADMINROLE)));
		Employee caoHa = new Employee(1234,"handsomeBoy", "Cao", "Ha", new Address("1000 N 4th", "Fairfield", "Iowa", "52707"), "+1 939 329 333", new HashSet<>(Arrays.asList(LIBRARYROLE)));
		Employee xing = new Employee(999,"handsomeBoy", "Xing", "He", new Address("1000 N 4th", "Fairfield", "Iowa", "52707"), "+1 333 444 444", new HashSet<>(Arrays.asList(ADMINROLE,LIBRARYROLE)));
		
		ADMINROLE.getEmployees().add(nguyenLe);
		ADMINROLE.getEmployees().add(xing);
		LIBRARYROLE.getEmployees().add(caoHa);
		LIBRARYROLE.getEmployees().add(xing);
		
		LIBRARY_EMPLOYEE = new ArrayList<>(Arrays.asList(nguyenLe,caoHa,xing));
	}
}