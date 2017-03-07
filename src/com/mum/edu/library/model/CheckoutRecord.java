package com.mum.edu.library.model;

import java.util.List;

public class CheckoutRecord {
	private List<CheckoutEntry> checkoutEntries;
	private Member libraryMember;  //here
	
	public CheckoutRecord(Member aLibraryMember, List<CheckoutEntry> checkoutEntries){
		this.libraryMember = aLibraryMember;
		this.checkoutEntries = checkoutEntries;
	}
	
	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}
	
}
