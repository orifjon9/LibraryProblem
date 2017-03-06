package com.mum.edu.library.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Members", propOrder = { "member" })
@XmlRootElement(name = "Members")
public class Members implements Serializable {
	
	public Members() {
		member = new ArrayList<>();
	}

	private static final long serialVersionUID = 1L;
	@XmlElement(name = "Member", required = true)
	protected List<Member> member;

	public List<Member> getMember() {
		return member;
	}

	public void setMember(List<Member> member) {
		this.member = member;
	}

}
