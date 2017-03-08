package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "checkoutRecords")
@XmlAccessorType (XmlAccessType.FIELD)
public class CheckoutRecords implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@XmlElement(name = "checkoutRecord", required = true)
	protected List<CheckoutRecord> checkoutRecords;
	
	public CheckoutRecords(){
		this.checkoutRecords = new ArrayList<>();
	}
	
	public List<CheckoutRecord> getCheckoutRecords(){
		return this.checkoutRecords;
	}
	
	
	public void setCheckoutRecords(List<CheckoutRecord> aCheckoutRecords){
		this.checkoutRecords = aCheckoutRecords;
	}
	
}


