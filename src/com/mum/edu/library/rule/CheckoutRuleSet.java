package com.mum.edu.library.rule;

import com.mum.edu.library.dao.impl.MemberDAOImpl;
import com.mum.edu.library.model.Book;
import com.mum.edu.library.model.Member;
import com.mum.edu.library.ui.checkout.CheckoutBookController;

import javafx.application.Application;
import javafx.stage.Stage;

public class CheckoutRuleSet implements RuleSet {
	private CheckoutBookController controller;

	@Override
	public void applyRule(Application application) throws RuleException {
		// TODO Auto-generated method stub
	}

	private void isMemberIdNumber() throws RuleException{
		try{
			String memberId = controller.getMemberIdValue();
			Integer.parseInt(memberId);
		}
		catch(Exception ex){
			///throw new RuleException(ex.getMessage());
			throw new RuleException("MemberId is not number");
		}
	}
	
	private void checkMember()  throws RuleException {
		try
		{
			MemberDAOImpl mDao = new MemberDAOImpl();
			Member requiredMember = mDao.getMember(Integer.parseInt(controller.getMemberIdValue()));
			
			if(requiredMember == null){
				throw new RuleException("Member was not found");
			}
		}
		catch(ApplicationException ex){
			throw new RuleException(ex.getMessage());
		}
	}
	
	@Override
	public void applyRule(Stage stage) throws RuleException {
		
	}

	@Override
	public void applyRule(Object object) throws RuleException {
		// TODO Auto-generated method stub
		controller =  (CheckoutBookController)object;
		
		isMemberIdNumber();
		checkMember();
		
		Book book = controller.getSelectedItem();
		if(book == null){
			throw new RuleException("Book does not select");
		}
		
		if(!book.getIsAvaliable()){
			throw new RuleException("Book is not available");
		}
	}

}
