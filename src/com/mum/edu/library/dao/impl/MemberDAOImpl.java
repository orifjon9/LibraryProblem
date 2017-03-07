package com.mum.edu.library.dao.impl;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.mum.edu.library.dao.MemberDAO;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.model.Members;

public class MemberDAOImpl implements MemberDAO {
	
	/* 
	 * Need to refactor
	 */
	@Override
	public void save(Member memberToSave) {
		JAXBContext jaxbContext = null;
		Members members = new Members();
		File file = new File("D:\\Java\\Testfile.xml");
		try {

			jaxbContext = JAXBContext.newInstance(Members.class);

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			members = (Members) jaxbUnmarshaller.unmarshal(file);
			members.getMember().add(memberToSave);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		try {
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(members, file);
			jaxbMarshaller.marshal(members, System.out);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
