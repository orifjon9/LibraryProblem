package com.mum.edu.library.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BookCopy implements Serializable {

	private static final long serialVersionUID = 1L;
	private int IdCopyNumber;

	public BookCopy() {
	}

	@XmlAttribute
	public int getIdCopyNumber() {
		return IdCopyNumber;
	}

	public void setIdCopyNumber(int idCopyNumber) {
		IdCopyNumber = idCopyNumber;
	}

}
