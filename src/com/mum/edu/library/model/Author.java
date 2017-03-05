package com.mum.edu.library.model;

public class Author extends People {

	private String credentials;
	private String shortbio;
	
	public Author(String firstName, String lastName, Address address,
			String phoneNumber, String credentials, String shortbio) {
		super(firstName, lastName, address, phoneNumber);
		this.credentials = credentials;
		this.shortbio = shortbio;
	}

	public String getCredentials() {
		return credentials;
	}

	public void setCredentials(String credentials) {
		this.credentials = credentials;
	}

	public String getShortbio() {
		return shortbio;
	}

	public void setShortbio(String shortbio) {
		this.shortbio = shortbio;
	}

}
