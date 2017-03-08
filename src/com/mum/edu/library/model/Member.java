package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.SimpleIntegerProperty;

@XmlRootElement
public class Member extends People implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private SimpleIntegerProperty memberId;
	
	public Member() {
	}

	public Member(int memberId, String firstName, String lastName, Address address, String phoneNumber) {
		super(firstName, lastName, address, phoneNumber);
		this.memberId = new SimpleIntegerProperty(memberId);
	}
	
	public int getMemberId() {
		return memberId.get();
	}
	
	@XmlAttribute
	public void setMemberId(int memberId) {
		if (this.memberId == null) {
			this.memberId = new SimpleIntegerProperty(memberId);
		} else {
			this.memberId.set(memberId);
		}
	}
	
	@Override
	public String toString() {
		return "Member have memberID:" + memberId;
	}

	public void copyFrom(Member editMember) {
		this.setFirstName(editMember.getFirstName());
		this.setLastName(editMember.getLastName());
		this.getAddress().setCity(editMember.getAddress().getCity());
		this.getAddress().setStreet(editMember.getAddress().getStreet());
		this.getAddress().setState(editMember.getAddress().getState());
		this.getAddress().setZip(editMember.getAddress().getZip());
		this.setPhoneNumber(editMember.getPhoneNumber());
	}
}
