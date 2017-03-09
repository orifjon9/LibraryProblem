package com.mum.edu.library.rule;

import com.mum.edu.library.model.Book;
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
			throw new RuleException("MemberId id not number");
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
		
		Book book = controller.getSelectedItem();
		if(book == null){
			throw new RuleException("Book does not select");
		}
		
		if(!book.getIsAvaliable()){
			throw new RuleException("Book is not available");
		}
	}

}
