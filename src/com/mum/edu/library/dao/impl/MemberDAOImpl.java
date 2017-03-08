package com.mum.edu.library.dao.impl;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.constant.Constant;
import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Members;
import com.mum.edu.library.rule.ApplicationException;

public class MemberDAOImpl implements MemberDAO {
	private static final String RESOURCES = "resources";
	private static final String BIN = "bin";
	JAXBContext jaxbContext = null;
	private final File file = new File(findExactlyDataBase());

	private String findExactlyDataBase() {
		return getClass().getClassLoader().getResource(Constant.MEMBER_FILE).getFile().replace(BIN, RESOURCES);
	}

	@Override
	public void save(Member memberToSave) throws ApplicationException {
		try {
			List<Member> membersLoaded = loadMembers();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			membersLoaded.add(memberToSave);

			flush(membersLoaded, jaxbMarshaller);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}

	private void flush(List<Member> membersLoadeds, Marshaller jaxbMarshaller) throws PropertyException, JAXBException {
		Members members = new Members();
		members.getMember().addAll(membersLoadeds);
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(members, file);
		jaxbMarshaller.marshal(members, System.out);
	}

	@Override
	public List<Member> loadMembers() throws ApplicationException {
		Members members = new Members();
		try {
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
		try {
			List<Member> members = loadMembers();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			for (Member member : members) {
				if (member.getMemberId() != editMember.getMemberId()) {
					continue;
				}
				member.copyFrom(editMember);
			}

			flush(members, jaxbMarshaller);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}

	@Override
	public void detele(Member memberToDelete) throws ApplicationException {
		try {
			List<Member> members = loadMembers();
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			Iterator<Member> iterator = members.iterator();
			while (iterator.hasNext()) {
				Member member = iterator.next();
				if (member.getMemberId() != memberToDelete.getMemberId()) {
					continue;
				}
				iterator.remove();
			}

			flush(members, jaxbMarshaller);
		} catch (JAXBException e) {
			e.printStackTrace();
			throw new ApplicationException("Error with database");
		}
	}

}
