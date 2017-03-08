package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Author extends People  implements Serializable {

	private static final long serialVersionUID = 1L;
	private String credentials;
	private String shortbio;
	
	public Author() {
	}

	public Author(String firstName, String lastName, Address address,
			String phoneNumber, String credentials, String shortbio) {
		super(firstName, lastName, address, phoneNumber);
		this.credentials = credentials;
		this.shortbio = shortbio;
	}
	
	public String getCredentials() {
		return credentials;
	}

	@XmlAttribute
	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}
	
	public String getShortbio() {
		return shortbio;
	}

	@XmlAttribute
	public void setShortbio(String shortbio) {
		this.shortbio = shortbio;
	}
	
	@Override
	public String toString(){
		return super.getFirstName() + " " + super.getLastName();
	}

}
