package com.mum.edu.library.model;

<<<<<<< HEAD
import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
=======
// Addrees Class
public class Address {
>>>>>>> 8548017809f9f77cc494ac968687d5dae82c8365
	private String street;
	private String city;
	private String state;
	private String zip;
	
	public Address() {
	}
	
	public Address(String street, String city, String state, String zip) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public String getStreet() {
		return street;
	}
	
	@XmlElement
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	
	@XmlElement
	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	@XmlElement
	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}
	
	@XmlElement
	public void setZip(String zip) {
		this.zip = zip;
	}

}
