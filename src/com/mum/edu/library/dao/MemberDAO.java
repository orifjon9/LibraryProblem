package com.mum.edu.library.dao;

import java.util.List;

import com.mum.edu.library.model.Member;
import com.mum.edu.library.rule.ApplicationException;

public interface MemberDAO {
	
	public void save(Member member) throws ApplicationException;
	
	public List<Member> loadMembers() throws ApplicationException ;

	public void edit(Member editMember) throws ApplicationException;

}
