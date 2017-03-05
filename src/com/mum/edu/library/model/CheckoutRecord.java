package com.mum.edu.library.model;

import java.util.List;

public class CheckoutRecord {
	private List<CheckoutEntry> checkoutEntries;

	public List<CheckoutEntry> getCheckoutEntries() {
		return checkoutEntries;
	}

	public void setCheckoutEntries(List<CheckoutEntry> checkoutEntries) {
		this.checkoutEntries = checkoutEntries;
	}
	
}
