package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Member extends People implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int memberId;
	
	public Member() {
	}

	public Member(int memberId, String firstName, String lastName, Address address, String phoneNumber) {
		super(firstName, lastName, address, phoneNumber);
		this.memberId = memberId;
	}

	public int getMemberId() {
		return memberId;
	}
	
	@XmlAttribute
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		return "Member have memberID:" + memberId;
	} 
}
