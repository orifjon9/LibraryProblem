package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Members;

public class MemberDAOImpl implements MemberDAO {

	@Override
	public void save(Member memberToSave) {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		File file = new File("E:\\MPP\\member.xml");
		try {

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
		}
	}

	@Override
	public List<Member> loadMembers() {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		File file = new File("E:\\MPP\\member.xml");
		try {

			jaxbContext = JAXBContext.newInstance(Members.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			members = (Members) jaxbUnmarshaller.unmarshal(file);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return members.getMember();
	}

}
