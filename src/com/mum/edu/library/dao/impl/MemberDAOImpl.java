package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Members;
import com.mum.edu.library.rule.ApplicationException;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public void save(Member memberToSave) throws ApplicationException {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		try {
			File file = new File(Constant.MEMBER_FILE);
			jaxbContext = JAXBContext.newInstance(Members.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			members = (Members) jaxbUnmarshaller.unmarshal(file);
			members.getMember().add(memberToSave);
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(members, file);
			jaxbMarshaller.marshal(members, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}

	@Override
	public List<Member> loadMembers() throws ApplicationException {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		try {
			File file = new File(Constant.MEMBER_FILE);
			jaxbContext = JAXBContext.newInstance(Members.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			members = (Members) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
		return members.getMember();
	}

	@Override
	public void edit(Member editMember) throws ApplicationException {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		try {
			File file = new File(Constant.MEMBER_FILE);
			jaxbContext = JAXBContext.newInstance(Members.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			members = (Members) jaxbUnmarshaller.unmarshal(file);
			for(Member member : members.getMember()) {
				if (member.getMemberId() != editMember.getMemberId()) {
					continue;
				}
				member.copyFrom(editMember);
			}

			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.marshal(members, file);
			jaxbMarshaller.marshal(members, System.out);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}

}
