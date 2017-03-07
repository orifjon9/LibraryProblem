package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleStringProperty;

@XmlRootElement
public class People implements Serializable {
	private static final long serialVersionUID = 1L;
	private SimpleStringProperty firstName;
	private SimpleStringProperty lastName;
	private Address address;
	private String phoneNumber;
	
	public People() {
	}
	
	public People(String firstName, String lastName, Address address,
			String phoneNumber) {
		this.firstName = new SimpleStringProperty(firstName);
		this.lastName = new SimpleStringProperty(lastName);
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	
	public String getFirstName() {
		return firstName.get();
	}
	
	@XmlElement
	public void setFirstName(String firstName) {
		this.firstName.set(firstName);
	}

	public String getLastName() {
		return lastName.get();
	}
	
	@XmlElement
	public void setLastName(String lastName) {
		this.lastName.set(lastName);
	}

	public Address getAddress() {
		return address;
	}
	
	@XmlElement
	public void setAddress(Address address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	@XmlElement
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
