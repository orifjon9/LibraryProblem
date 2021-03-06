package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CheckoutRecord")
@XmlAccessorType(XmlAccessType.FIELD)
public class CheckoutRecord implements Serializable {

	private static final long serialVersionUID = 1L;

	@XmlElement(name = "CheckoutEntry", required = true)
	protected List<CheckoutEntry> checkoutEntries;

	@XmlElement(name = "Member", required = true)
	private Member libraryMember; // here

	public CheckoutRecord() {
	}

	public CheckoutRecord(Member aLibraryMember, List<CheckoutEntry> checkoutEntries) {
		this.libraryMember = aLibraryMember;
		this.checkoutEntries = checkoutEntries;
	}

	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}

	public Member getMember() {
		return this.libraryMember;
	}

	@Override
	public String toString() {
		return "Check out Record of Library Member" + "\n"  
				+ "	Checkout Entry :" + checkoutEntries.toString()
				+ " And Information of member Who Checkout Books : " + libraryMember.toString();
	}

}
